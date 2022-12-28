package gui;

import java.io.IOException;

import gui.LoginController;

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
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MenuController {
	
	@FXML
	Button loginButton = new Button();
	@FXML
	Button logoutButton;
	@FXML
	AnchorPane mainMenuScene;
	
	@FXML
	Label usernameLabel;
	
	Alert alert = new Alert(AlertType.CONFIRMATION);
	
	// Hides the login button, displays the logout button, and provides a welcome message to the user
	public void hideLogin(String user) {
		usernameLabel.setText("Welcome: " + user);
		loginButton.setVisible(false);
		logoutButton.setVisible(true);
	}
	
	// Logs the user out of their account after a confirmation window
	public void logout(ActionEvent event) {
		
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Logout");
		alert.setHeaderText("You're about to logout!");
		alert.setContentText("Your flights will be saved.");
		System.out.println(Main.userType + Main.user + " is attempting to log out!");
		
		if(alert.showAndWait().get() == ButtonType.OK){
			stage = (Stage) mainMenuScene.getScene().getWindow();
			System.out.println(Main.userType + Main.user + " successfully logged out!");
			Main.userType = "[User]";
			Main.user = "";
			usernameLabel.setText("");
			loginButton.setVisible(true);
			logoutButton.setVisible(false);
		}
	}
	
	
	
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	// Sends the user to the login form
	public void switchToLoginForm(ActionEvent event) throws IOException {
		System.out.println(Main.userType + Main.user + " is now viewing Login Form");
		
		root = FXMLLoader.load(getClass().getResource("LoginForm.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	// Sends the user to the flights page
	public void switchToFlightsPage(ActionEvent event) throws IOException {
		System.out.println(Main.userType + Main.user + " is now viewing Flights Page");
		
		root = FXMLLoader.load(getClass().getResource("FlightsPage.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	//Sends the user to their personal bookings page. Does not work if they are not logged in.
	public void switchToUserFlights(ActionEvent event) throws IOException {
		if(Main.userType != "[User]") {
		System.out.println(Main.userType + Main.user + " is now viewing User Flights Page");
		
		root = FXMLLoader.load(getClass().getResource("UserFlights.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
		} else {
			System.out.println(Main.userType + Main.user + " attempted to view the User Flights Page without logging in");
			alert.setTitle("Log in");
			alert.setHeaderText("You have to log in to view your flights");
			alert.setContentText("");
			if (alert.showAndWait().get() == ButtonType.OK)
				System.out.println(Main.userType + Main.user + " will try again.");
		}
			
	}
	
	
	
}