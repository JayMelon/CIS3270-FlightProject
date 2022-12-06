package gui;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MenuController {
	
	@FXML
	private Button loginButton;	
	@FXML
	private Button logoutButton;
	@FXML
	private AnchorPane mainMenuScene;
	
	
	
	public void logout(ActionEvent event) {
		
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Logout");
		alert.setHeaderText("You're about to logout!");
		alert.setContentText("Your flights will be saved.");
		
		if(alert.showAndWait().get() == ButtonType.OK){
			stage = (Stage) mainMenuScene.getScene().getWindow();
			System.out.println("You successfully logged out!");
			stage.close();
		}	
	}
	
	public void loggedIn(String username, String password) {
		// Temp variables for you to change
		String	user[];
		String	pass[];
		for(int i = 0; i < user.length; i++) {
			for(int j = 0; i < pass.length; j++) {
				if(username == user[i] &&  password == pass[j]) {
					// Send User to Main Menu while Logged In
				}
			}
		}
		
		
		
		
	}
	
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	public void switchToLoginForm(ActionEvent event) throws IOException {
		System.out.println("User is now viewing Login Form");
		
		root = FXMLLoader.load(getClass().getResource("LoginForm.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	public void switchToFlightsPage(ActionEvent event) throws IOException {
		System.out.println("User is now viewing Flights Page");
		
		root = FXMLLoader.load(getClass().getResource("FlightsPage.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	public void switchToUserFlights(ActionEvent event) throws IOException {
		System.out.println("User is now viewing User Flights Page");
		
		root = FXMLLoader.load(getClass().getResource("UserFlights.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	
}