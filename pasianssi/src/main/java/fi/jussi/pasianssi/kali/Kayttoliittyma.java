package fi.jussi.pasianssi.kali;

import fi.jussi.pasianssi.kortit.Kortti;
import fi.jussi.pasianssi.kortit.Korttipakka;
import fi.jussi.pasianssi.kortit.NakyvaKortti;
import fi.jussi.pasianssi.kortit.Korttipino;
import fi.jussi.pasianssi.kortit.Pasianssi;
import java.util.List;
import java.util.HashMap;
import java.util.Optional;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;
import javafx.scene.control.MenuBar;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Dialog;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.stage.Stage;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;

public class Kayttoliittyma extends Application {
	private static Pasianssi pasianssi;
	private ImageView siirrettava;
	private HashMap<ImageView, NakyvaKortti> kortit = new HashMap();
	private HashMap<NakyvaKortti, ImageView> kuvat = new HashMap();
	private HashMap<AnchorPane, Korttipino> pinot = new HashMap();
	private HashMap<Korttipino, AnchorPane> pinokuvat = new HashMap();
	private Korttipino lahde;
	private BorderPane poyta;
	
	@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle("Spider-pasianssi");
		BorderPane juuri = new BorderPane();
		poyta = new BorderPane();
		poyta.setStyle("-fx-background-color: green");
		juuri.setCenter(poyta);
		Scene scene = new Scene(juuri, 1280, 720);

		alustaPoyta();
		
		MenuBar valikkopalkki = new MenuBar();
		Menu valikko = piirraValikko(primaryStage);
		valikkopalkki.getMenus().add(valikko);
		juuri.setTop(valikkopalkki);
		
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	private void alustaPoyta() {
		poyta.setTop(piirraKorttipinot());
		poyta.setBottom(piirraPakka(pasianssi.getPakka()));
		poyta.requestLayout();
	}
	
	private ImageView piirraPakka(Korttipakka pakka) {
		ImageView pakkanappi = new ImageView();
		pakkanappi.setImage(new Image(getClass().getClassLoader().getResourceAsStream("tausta.jpg")));
		pakkanappi.setFitWidth(120);
		pakkanappi.setPreserveRatio(true);
		BorderPane.setAlignment(pakkanappi, Pos.BOTTOM_RIGHT);
		
		pakkanappi.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				pasianssi.nosta10Korttia();
				
				for (Korttipino pino : pasianssi.getPinot()) {
					ImageView uusi = piirraNakyvaKortti(pino.getNakyvat().hanta());
					pinokuvat.get(pino).getChildren().add(uusi);
					asetaVali(uusi);
					pinokuvat.get(pino).requestLayout();
				}
				
				if (pasianssi.getPakka().tyhja()) {
					((BorderPane) pakkanappi.getParent()).getChildren().remove(pakkanappi);
				}
				event.consume();
			}
		});
		
		return pakkanappi;
	}
	
	private Menu piirraValikko(Stage primaryStage) {
		Menu valikko = new Menu("Valikko");
		MenuItem uusi = new MenuItem("Uusi peli");
		MenuItem nelja = new MenuItem("Neljä maata");
		MenuItem kaksi = new MenuItem("Kaksi maata");
		MenuItem yksi = new MenuItem("Yksi maa");
		
		uusi.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				setPasianssi(new Pasianssi(pasianssi.getMaita()));
				alustaPoyta();
			}
		});
		
		nelja.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				setPasianssi(new Pasianssi(4));
				alustaPoyta();
			}
		});
		
		kaksi.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				setPasianssi(new Pasianssi(2));
				alustaPoyta();
			}
		});
		
		yksi.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				setPasianssi(new Pasianssi(1));
				alustaPoyta();
			}
		});
		
		valikko.getItems().add(uusi);
		valikko.getItems().add(nelja);
		valikko.getItems().add(kaksi);
		valikko.getItems().add(yksi);
		
		return valikko;
	}
    
	private ImageView piirraNakyvaKortti(NakyvaKortti kortti) {
		ImageView palautettava = new ImageView();
		palautettava.setImage(kortinKuva(kortti.getKortti()));
		palautettava.setFitWidth(120);
		palautettava.setPreserveRatio(true);
		
		kortit.put(palautettava, kortti);
		kuvat.put(kortti, palautettava);
		
		palautettava.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if (siirrettava == null) {
					siirrettava = palautettava;
					
					for (Korttipino pino : pasianssi.getPinot()) {
						if (pino.onPinossa(kortti)) {
							lahde = pino;
							break;
						}
					}
					
					event.consume();
				}
			}
		});
		
		return palautettava;
	}
	
	private ImageView piirraKaannettyKortti() {
		ImageView palautettava = new ImageView();
		palautettava.setImage(new Image(getClass().getClassLoader().getResourceAsStream("tausta.jpg")));
		palautettava.setFitWidth(120);
		palautettava.setPreserveRatio(true);
		
		return palautettava;
	}
    
	private AnchorPane piirraKorttipino(Korttipino pino) {
		AnchorPane pane = new AnchorPane();
		pinot.put(pane, pino);
		pinokuvat.put(pino, pane);
        
		Rectangle nakymaton = new Rectangle(120, 120, Color.TRANSPARENT);
		pane.getChildren().add(nakymaton);
		
		for (int i = 0; i < pino.getKaannetyt().size(); i++) {
			ImageView kortti = piirraKaannettyKortti();
			pane.getChildren().add(kortti);
			
			asetaVali(kortti);
		}

		NakyvaKortti nakyva = pino.getNakyvat();
        
		if (nakyva == null) {
			return pane;
		}
        
		while (nakyva != null) {
			ImageView korttikuva = piirraNakyvaKortti(nakyva);
			pane.getChildren().add(korttikuva);
			asetaVali(korttikuva);
			
			pane.setOnMouseClicked(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					if (siirrettava != null) {
						if (pino.onPinossa(kortit.get(siirrettava))) {
							siirrettava = null;
							event.consume();
							return;
						}
						
						int kohteenKorttimaara;
						if (pino.getNakyvat() == null) {
							kohteenKorttimaara = 0;
						} else {
							kohteenKorttimaara = pino.getNakyvat().korttimaara();
						}
						
						if (lahde.siirraKortti(kortit.get(siirrettava), pino)) {
							
							while (true) {
								((AnchorPane) siirrettava.getParent()).getChildren().remove(siirrettava);
								pane.getChildren().add(siirrettava);
								asetaVali(siirrettava);
								pane.requestLayout();
								siirrettava.getParent().requestLayout();
								if (kortit.get(siirrettava).getSeuraava() != null) {
									siirrettava = kuvat.get(kortit.get(siirrettava).getSeuraava());
								} else {
									break;
								}
							}
							
							if (pino.getNakyvat() == null || kohteenKorttimaara > pino.getNakyvat().korttimaara()) {
								for (int i = 1; i <= 13; i++) {
									pane.getChildren().remove(pane.getChildren().size() - 1);
								}
								
								if (pino.getNakyvat() != null && pino.getNakyvat().korttimaara() == 1) {
									pane.getChildren().remove(pane.getChildren().size() - 1);
									ImageView kaannetty = piirraNakyvaKortti(pino.getNakyvat());
									pane.getChildren().add(kaannetty);
									asetaVali(kaannetty);
								}
								pane.requestLayout();
							}
						
							if (!lahde.tyhja() && lahde.getNakyvat().korttimaara() == 1) {
								ImageView kaannetty = piirraNakyvaKortti(lahde.getNakyvat());
								AnchorPane lahdekuva = pinokuvat.get(lahde);
							
								lahdekuva.getChildren().remove(lahdekuva.getChildren().size() - 1);
								lahdekuva.getChildren().add(kaannetty);
								asetaVali(kaannetty);
							}
							
							if (pasianssi.voitettu()) {
								Dialog dialog = new Dialog();
								dialog.setContentText("Voitit pelin!");
								
								ButtonType uusi = new ButtonType("Uusi peli", ButtonData.OK_DONE);
								dialog.getDialogPane().getButtonTypes().add(uusi);
								
								Optional<ButtonType> result = dialog.showAndWait();
								if (result.isPresent() && result.get().getButtonData() == ButtonData.OK_DONE) {
									setPasianssi(new Pasianssi(pasianssi.getMaita()));
									alustaPoyta();
								}
							}
							
							if (pasianssi.havitty()) {
								Dialog dialog = new Dialog();
								dialog.setContentText("Hävisit pelin");
								
								ButtonType uusi = new ButtonType("Uusi peli", ButtonData.OK_DONE);
								dialog.getDialogPane().getButtonTypes().add(uusi);
								
								Optional<ButtonType> result = dialog.showAndWait();
								if (result.isPresent() && result.get().getButtonData() == ButtonData.OK_DONE) {
									setPasianssi(new Pasianssi(pasianssi.getMaita()));
									alustaPoyta();
								}
							}
						}
						siirrettava = null;
						lahde = null;
						event.consume();
					}
				}
			});
			
			nakyva = nakyva.getSeuraava();
		}
        
		return pane;
	}
    
	private Image kortinKuva(Kortti kortti) {
		String maa;
        
		switch (kortti.getMaa()) {
			case HERTTA:    maa = "hertta";
							break;
			case RUUTU:     maa = "ruutu";
							break;
			case RISTI:     maa = "risti";
							break;
			case PATA:      maa = "pata";
							break;
			default:        throw new IllegalArgumentException();
		}
        
		String polku = maa + kortti.getArvo() + ".png";
		return new Image(getClass().getClassLoader().getResourceAsStream(polku));
	}
    
	private HBox piirraKorttipinot() {
		HBox laatikko = new HBox(5.0);
		laatikko.setAlignment(Pos.CENTER);
        
		List<Korttipino> pinot = pasianssi.getPinot();
            
		for (Korttipino pino : pinot) {
			laatikko.getChildren().add(piirraKorttipino(pino));
		}
        
		return laatikko;
	}
	
	private void asetaVali(ImageView kortti) {
		AnchorPane.setTopAnchor(kortti, (((AnchorPane) kortti.getParent()).getChildren().size() - 2) * 23.0);
	}
	
	public static void setPasianssi(Pasianssi pasianssi) {
		Kayttoliittyma.pasianssi = pasianssi;
	}
}
