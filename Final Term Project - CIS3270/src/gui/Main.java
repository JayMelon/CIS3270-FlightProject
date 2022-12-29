package gui;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;

public class Main extends Application {
	
	//Declares the type of user that is using the application and their username and user Id
	public static String userType = "[User]";
	public static String user = "";
	public static String userID = "";
	
	Image logo = new Image("Images/GildedWingsLogo.png");
	
	//Initializes the Gilded Wings application
	@Override
	public void start(Stage stage) {
		try {
			System.out.println(Main.userType + Main.user + " launched Gilded Wings Application");
	  
			Parent root = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
			Scene scene = new Scene(root);
			stage.getIcons().add(logo);
			stage.setTitle("Glided Wings");
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