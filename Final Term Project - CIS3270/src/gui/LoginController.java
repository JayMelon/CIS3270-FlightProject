package gui;

import java.io.IOException;

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
	private Label loginFailedLabel;
	@FXML
	private TextField usernameTextField;
	@FXML
	private PasswordField passwordField;
	@FXML
	private TextField securityQTextField;
	@FXML
	private TextField securityATextField;
	
	
	public void login(ActionEvent event) throws IOException {
		if(usernameTextField.getText().isBlank() || passwordField.getText().isBlank() 
		  //|| usernameTextField.getText() != checkUsernameDatabase for matching username || passwordField.getText() != checkPasswordDatabase for matching password
		  ) {
			loginFailedLabel.setText("Please enter a correct username and password combination");
			System.out.println(Main.userType + Main.user + " failed to login");
		} else 
			validateLogin();
	}
	
	public void forgotPassword(ActionEvent event) throws IOException {
		if(usernameTextField.getText().isBlank())
			System.out.println(Main.userType + Main.user + " is attempting password recovery");
			loginFailedLabel.setText("Please enter a correct username then click Forgot Password");
		else
			System.out.println(Main.userType + Main.user + " is answering their security question");
			// Sets securityQTextField to User's Security Question
			loginFailedLabel.setText("Please answer the Security Question in the Security Answer Field then click Submit");
	}
	
	public void register(ActionEvent event) throws IOException {
		if(usernameTextField.getText().isBlank() || passwordField.getText().isBlank()) {
			loginFailedLabel.setText("Please fill out all fields to register");
			System.out.println(Main.userType + Main.user + " failed to login");
		} else if//|| usernameTextField.getText() != checkUsernameDatabase for matching username || passwordField.getText() != checkPasswordDatabase for matching password {
			
		} else {
			//validateRegistration();
			validateLogin();
		}
	}
	
	public void validateLogin() {
		/*DatabaseConnection connectNow = new DatabaseConnection();
		Connection connectDB = connectNow.getConnection();
		
		String verifyLogin = "Select count(1) FROM UserAccounts Where username = '" + usernameTextField.getText() + "' AND password = '" + passwordField.getText() + "'";
		
		try {
			Statement statement = connectDB.createStatement();
			ResultSet queryResult = statement.executeQuery(verifyLogin);
			
			while(queryResult.next()) {
				if (queryResult.getInt(columnindex 1) == 1) {
					
					Member
					//Logs User In and Sends them to Main Menu
					//Hides Login Button
					//Displays Log Out Button
					//Displays Add Flights Button
					//Enables UserLedger Button
					
					Admin
					//Displays Delete Flights Button
					//Displays Add Flights Function
					//Displays Edit Flights Function
					
					System.out.println(Main.userType + Main.user + " is now viewing Main Menu");
		
					root = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
					stage = (Stage)((Node)event.getSource()).getScene().getWindow();
					scene = new Scene(root);
					stage.setScene(scene);
					stage.show();
				} else {
					loginFailedLabel.setText("Please enter a correct username and password combination");
					System.out.println(Main.userType + Main.user + " failed to login");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}**/
	}
	
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	public void switchToMainMenu(ActionEvent event) throws IOException {
		System.out.println(Main.userType + Main.user + " is now viewing Main Menu");
		
		root = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
}
