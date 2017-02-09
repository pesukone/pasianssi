package kali;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.Group;
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
		
		ImageView iv1 = new ImageView();
		iv1.setImage(image);
		iv1.setFitWidth(120);
		iv1.setPreserveRatio(true);
		iv1.relocate(10, 10);
		
		root.getChildren().add(iv1);
		
		
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
