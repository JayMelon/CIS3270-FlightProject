package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Classes.Login;
import Classes.Registration;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController implements Initializable {
	
	@FXML
	public static Label loginFailedLabel;
	@FXML
	private TextField usernameTextField;
	@FXML
	private PasswordField passwordField;
	@FXML
	private TextField firstNameTextField;
	@FXML
	private TextField lastNameTextField;
	@FXML
	private TextField addressTextField;
	@FXML
	private TextField zipCodeTextField;
	@FXML
	private TextField stateTextField;
	@FXML
	private TextField securityQTextField;
	@FXML
	private TextField securityATextField;
	
	Registration newUser = new Registration();
	
	
	
	public void login(ActionEvent event) throws IOException {
		if(usernameTextField.getText().isBlank() || passwordField.getText().isBlank() 
		  //|| usernameTextField.getText() != checkUsernameDatabase for matching username || passwordField.getText() != checkPasswordDatabase for matching password
		  ) {
			loginFailedLabel.setText("Please enter a correct username and password combination");
			System.out.println(Main.userType + Main.user + " failed to login");
		} else 
			Login.validateLogin(usernameTextField.getText(),passwordField.getText());
	}
	
	public void forgotPassword(ActionEvent event) throws IOException {
		if(usernameTextField.getText().isBlank())
			System.out.println(Main.userType + Main.user + " is attempting password recovery");
			loginFailedLabel.setText("Please enter a correct username then click Forgot Password");
		//else
		//	System.out.println(Main.userType + Main.user + " is answering their security question");
			// Sets securityQTextField to User's Security Question
			//loginFailedLabel.setText("Please answer the Security Question in the Security Answer Field then click Submit");
	}
	
	public void register(ActionEvent event) throws IOException {
		if(usernameTextField.getText().isBlank() || passwordField.getText().isBlank() || firstNameTextField.getText().isBlank()
		   || lastNameTextField.getText().isBlank() || addressTextField.getText().isBlank() || zipCodeTextField.getText().isBlank()
		   || stateTextField.getText().isBlank() || securityQTextField.getText().isBlank() || securityATextField.getText().isBlank()) {
			loginFailedLabel.setText("Please enter a correct username and password combination");
			System.out.println(Main.userType + Main.user + " failed to register");
		}else {
			newUser.registerUser(usernameTextField.getText(),passwordField.getText(),firstNameTextField.getText(),
					  				  lastNameTextField.getText(),addressTextField.getText(),zipCodeTextField.getText(),
					  				  stateTextField.getText(),securityQTextField.getText(),securityATextField.getText());
			System.out.println(Main.userType + Main.user + " registered for an account");
			Login.validateLogin(usernameTextField.getText(),passwordField.getText());
		}
	}
			
		//} else {
			/*validateRegistration();
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
			validateLogin();
			*/
	


	
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

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		loginFailedLabel = new Label();
		
	}
	
}



