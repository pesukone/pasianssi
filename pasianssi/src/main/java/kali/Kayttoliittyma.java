package kali;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.layout.AnchorPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Kayttoliittyma extends Application {
	
	@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle("Spider-pasianssi");
		Group root = new Group();
		Scene scene = new Scene(root, 1280, 720, Color.GREEN);
		
		Image image = new Image("file:resources/img/10_of_clubs.png");
		
		ImageView r10 = new ImageView();
		r10.setImage(image);
		r10.setFitWidth(120);
		r10.setPreserveRatio(true);
		
		ImageView pa = new ImageView();
		pa.setImage(new Image("file:resources/img/ace_of_spades.png"));
		pa.setFitWidth(120);
		pa.setPreserveRatio(true);
		
		ImageView u9 = new ImageView();
		u9.setImage(new Image("file:resources/img/9_of_diamonds.png"));
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
	
}
