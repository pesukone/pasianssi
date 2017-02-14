package fi.jussi.pasianssi.kali;

import fi.jussi.pasianssi.kortit.Kortti;
import fi.jussi.pasianssi.kortit.Maa;
import fi.jussi.pasianssi.kortit.NakyvaKortti;
import fi.jussi.pasianssi.kortit.Korttipino;
import fi.jussi.pasianssi.kortit.Pasianssi;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Kayttoliittyma extends Application {
    private Pasianssi pasianssi;
    
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Spider-pasianssi");
        BorderPane root = new BorderPane();
        Scene scene = new Scene(root, 1280, 720, Color.GREEN);
		
		Image image = kortinKuva(new Kortti(Maa.RISTI, 10));
		
        ImageView r10 = new ImageView();
        r10.setImage(image);
        r10.setFitWidth(120);
        r10.setPreserveRatio(true);
		
        ImageView pa = new ImageView();
        pa.setImage(new Image("file:resources/img/pata1.png"));
        pa.setFitWidth(120);
        pa.setPreserveRatio(true);
		
        ImageView u9 = new ImageView();
        u9.setImage(new Image("file:resources/img/ruutu9.png"));
        u9.setFitWidth(120);
        u9.setPreserveRatio(true);
        
        ImageView pk = new ImageView();
        pk.setImage(new Image("file:resources/img/pata13.png"));
        pk.setFitWidth(120);
        pk.setPreserveRatio(true);
		
        AnchorPane pino = new AnchorPane();
		AnchorPane toinen = new AnchorPane();
        AnchorPane kolmas = new AnchorPane();
        
        pino.getChildren().add(r10);
        pino.getChildren().add(pa);
        toinen.getChildren().add(u9);
        kolmas.getChildren().add(pk);
		
        AnchorPane.setTopAnchor(pa, 35.0);
	
        HBox pinot = new HBox(10.0);
        pinot.setAlignment(Pos.CENTER);
        
        pinot.getChildren().add(pino);
        pinot.getChildren().add(toinen);
        pinot.getChildren().add(kolmas);
        
        root.setTop(pinot);
		
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
	launch(args);
    }
    
    private ImageView piirraKortti(NakyvaKortti kortti) {
        ImageView palautettava = new ImageView();
        palautettava.setImage(kortinKuva(kortti.getKortti()));
        palautettava.setFitWidth(120);
        palautettava.setPreserveRatio(true);
        
        return palautettava;
    }
    
    /*private AnchorPane piirraKorttipino(Korttipino pino) {
        
    }*/
    
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
    
    /*private HBox piirraKorttipinot() {
        HBox pinot = new HBox(10.0);
        pinot.setAlignment(Pos.CENTER);
        
        for (int i = 0; i < 10; i++) {
            pinot.getChildren().add(new AnchorPane());
        }
    }*/
    
    public void setPasianssi(Pasianssi pasianssi) {
        this.pasianssi = pasianssi;
    }
}
