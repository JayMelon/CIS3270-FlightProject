package gui;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {
	
	@FXML
	TextField usernameTextField;
	@FXML
	PasswordField passwordField;
	
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	public void login(ActionEvent event) throws IOException {
		
		String username = usernameTextField.getText();
		String password = passwordField.getText();
		
		//Temporary Variables you can change
		String[] user = {"1","JJJ"};
		String[] pass = {"2","KKK"};
		for(int i = 0; i < user.length; i++) {
			for(int j = 0; i < pass.length; j++) {
				if(username == user[i] &&  password == pass[j] && i == j) {
					// Send User to Main Menu while Logged In
					FXMLLoader loader = new FXMLLoader(getClass().getResource("MainMenu.fxml"));	
					root = loader.load();	
					stage = (Stage)((Node)event.getSource()).getScene().getWindow();
					scene = new Scene(root);
					stage.setScene(scene);
					stage.show();
				}
			}
		}
	}
	
	public void switchToMainMenu(ActionEvent event) throws IOException {
		System.out.println("User is now viewing Main Menu");
		
		root = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
}
