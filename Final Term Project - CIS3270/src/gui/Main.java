package gui;

import java.io.IOException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

public class Main extends Application {
	
	public static String userType = "[User]";
	public static String user = "";
	
	@Override
	public void start(Stage stage) {
		try {
			System.out.println(Main.userType + Main.user + " launched Gilded Wings Application");
	  
			Parent root = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
   
		} catch(Exception e) {
			e.printStackTrace();
		}
	} 

	public static void main(String[] args) {
		launch(args);
	}
}