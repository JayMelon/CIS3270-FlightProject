package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Classes.Login;
import Classes.Registration;
import javafx.application.Application;
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
	private Label formFailureLabel = new Label("");
	
	@FXML
	private Button forgotPasswordButton;
	
	@FXML
	private TextField usernameTextField;
	@FXML
	private PasswordField passwordField;
	@FXML
	private TextField firstNameTextField;
	@FXML
	private TextField lastNameTextField;
	@FXML
	private TextField emailTextField;
	@FXML
	private TextField addressTextField;
	@FXML
	private TextField zipCodeTextField;
	@FXML
	private ChoiceBox<String> stateChoiceBox;

	private String[] states = { "AL", "AK", "AZ", "AR", "CA", "CO", "CT", "DE", "FL", "GA", "HI", "ID", "IL", "IN",
			"IA", "KS", "KY", "LA", "ME", "MD", "MA", "MI", "MN", "MS", "MO", "MT", "NE", "NV", "NH", "NJ", "NM", "NY",
			"NC", "ND", "OH", "OK", "OR", "PA", "RI", "SC", "SD", "TN", "TX", "UT", "VT", "VA", "WA", "WV", "WI", "WY", };
	@FXML
	private TextField ssnTextField;
	@FXML
	private ChoiceBox<String> securityQChoiceBox;

	private String[] securityQs = { "What city were you born in?",
			"What is the name of the high school you graduated from?", "What is your favorite color?" };
	@FXML
	private TextField securityATextField;

	public void login(ActionEvent event) throws IOException {
		Login.validateLogin(usernameTextField.getText(), passwordField.getText());
		if(Main.userType != "[User]")
			switchToMainMenu(event);
		else
			formFailureLabel.setText("Please enter a correct username and password combination");
	}

	public void forgotPassword(ActionEvent event) throws IOException {		
		
		formFailureLabel.setText("Please enter a correct username then click Forgot Password");
		System.out.println(Main.userType + Main.user + " is attempting password recovery");
		
		if (usernameTextField.getText().isBlank()) {
			System.out.println(Main.userType + Main.user + " is attempting password recovery");
			formFailureLabel.setText("Please enter a correct username then click Forgot Password");
		} else if(usernameTextField.getText() != "" && forgotPasswordButton.getText() == "Forgot Password"){
			//check username and return security question
			System.out.println(Main.userType + Main.user + " is answering their security question");
		} else if(usernameTextField.getText() != "" && securityATextField.getText() != "" && forgotPasswordButton.getText() == "Submit") {
		
		}
	}
	
	//Fills in State and Security Question Choice Boxes with Options
	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		securityQChoiceBox.getItems().addAll(securityQs);
		stateChoiceBox.getItems().addAll(states);
	}
	
	//Creates a newUser Object
	public void register(ActionEvent event) throws IOException {
		if (usernameTextField.getText().isBlank() || passwordField.getText().isBlank()
				|| firstNameTextField.getText().isBlank() || lastNameTextField.getText().isBlank()
				|| emailTextField.getText().isBlank() || addressTextField.getText().isBlank()
				|| zipCodeTextField.getText().isBlank() || stateChoiceBox.getValue().isBlank()
				|| securityQChoiceBox.getValue().isBlank() || securityATextField.getText().isBlank()) {
			formFailureLabel.setText("Please fill out all fields to Register");
			System.out.println(Main.userType + Main.user + " failed to register");
		} else {
			//Registers user by assigning to Object and referencing from it. 
			Registration newUser = new Registration();
			newUser.userName = usernameTextField.getText();
			newUser.password = passwordField.getText();
			newUser.firstName = firstNameTextField.getText();
			newUser.lastName = lastNameTextField.getText();
			newUser.address = addressTextField.getText();
			newUser.email = emailTextField.getText();
			newUser.zipcode = zipCodeTextField.getText();
			newUser.state = stateChoiceBox.getValue();
			newUser.socialSecurityNumber = ssnTextField.getText();
			newUser.securityQuestion = securityQChoiceBox.getValue();
			newUser.securityAnswer = securityATextField.getText();
			//If there is no duplicate of username
			if(newUser.checkUserName()) {
				newUser.registerUser();
				System.out.println("[Customer]" + usernameTextField.getText() + " registered for an account");
				login(event);
			}else {
				//If DUPLICATED USER EXECUTE THIS
				formFailureLabel.setText("Please enter a different Username");
				System.out.println(Main.userType + Main.user + " entered a duplicate username when trying to register");
			}
		}
	}

	private Stage stage;
	private Scene scene;
	private Parent root;

	public void switchToMainMenu(ActionEvent event) throws IOException {
		System.out.println(Main.userType + Main.user + " is now viewing Main Menu");

		root = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

}
