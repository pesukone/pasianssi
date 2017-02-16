package fi.jussi.pasianssi.kali;

import fi.jussi.pasianssi.kortit.Kortti;
import fi.jussi.pasianssi.kortit.NakyvaKortti;
import fi.jussi.pasianssi.kortit.Korttipino;
import fi.jussi.pasianssi.kortit.Pasianssi;
import java.util.List;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Kayttoliittyma extends Application {
    private static Pasianssi pasianssi;
    
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Spider-pasianssi");
        BorderPane root = new BorderPane();
        Scene scene = new Scene(root, 1280, 720, Color.GREEN);
        
        root.setTop(this.piirraKorttipinot());
		
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
    
    private AnchorPane piirraKorttipino(Korttipino pino) {
        AnchorPane pane = new AnchorPane();
        
        NakyvaKortti nakyva = pino.getNakyvat();
        
        if (nakyva == null) {
            return pane;
        }
        
        while (nakyva != null) {
            ImageView korttikuva = piirraKortti(nakyva);
            pane.getChildren().add(korttikuva);
            AnchorPane.setTopAnchor(korttikuva, pane.getChildren().size() * 35.0);
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
    
    public static void setPasianssi(Pasianssi pasianssi) {
        Kayttoliittyma.pasianssi = pasianssi;
    }
}
