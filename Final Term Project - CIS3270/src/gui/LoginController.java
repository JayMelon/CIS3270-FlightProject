package gui;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {
	
	@FXML
	Label loginFailedLabel;
	@FXML
	TextField usernameTextField;
	@FXML
	PasswordField passwordField;
	
	
	public void login(ActionEvent event) throws IOException {
		if(usernameTextField.getText().isBlank() == false && passwordField.getText().isBlank() == false) 
			validateLogin();
		else
			loginFailedLabel.setText("Please enter a correct username and password");
	}
	
	public void validateLogin() {
		DatabaseConnection connectNow = new DatabaseConnection();
		Connection connectDB = connectNow.getConnection();
		
		String verifyLogin = "Select count(1) FROM UserAccounts Where username = '" + usernameTextField.getText(); + "' AND password = '" + passwordField.getText() + "'";
		
		try {
			Statement statement = connectDB.createStatement();
			ResultSet queryResult = statement.executeQuery(verifyLogin);
			
			while(queryResult.next()) {
				if (queryResult.getInt(columnindex 1) == 1) {
					//Logs User In and Sends them to Main Menu
				} else
					loginFailedLabel.setText("Please enter a correct username and password");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	public void switchToMainMenu(ActionEvent event) throws IOException {
		System.out.println("User is now viewing Main Menu");
		
		root = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
}
