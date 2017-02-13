package fi.jussi.pasianssi.kali;

import fi.jussi.pasianssi.kortit.Kortti;
import fi.jussi.pasianssi.kortit.NakyvaKortti;
import fi.jussi.pasianssi.kortit.Pasianssi;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.layout.AnchorPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Kayttoliittyma extends Application {
    private Pasianssi pasianssi;
    
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Spider-pasianssi");
        Group root = new Group();
        Scene scene = new Scene(root, 1280, 720, Color.GREEN);
		
        Image image = new Image("file:resources/img/risti10.png");
		
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
		
        AnchorPane pino = new AnchorPane();
		
        pino.getChildren().add(r10);
        pino.getChildren().add(pa);
        pino.getChildren().add(u9);
		
        AnchorPane.setTopAnchor(pa, 35.0);
        AnchorPane.setTopAnchor(u9, 70.0);
		
        root.getChildren().add(pino);
		
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
        
        return new Image("file:resources/img/" + maa + kortti.getArvo() + ".png");
    }
    
    public void setPasianssi(Pasianssi pasianssi) {
        this.pasianssi = pasianssi;
    }
}
