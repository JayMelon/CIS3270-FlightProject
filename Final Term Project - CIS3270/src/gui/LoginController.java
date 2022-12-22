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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
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
	
	//Fills in State and Security Question Choice Boxes with Options
	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		securityQChoiceBox.getItems().addAll(securityQs);
		stateChoiceBox.getItems().addAll(states);
	}
	
	
	// Event that logs the user into their account and sends them to the Main Menu
	public void login(ActionEvent event) throws IOException {
		Login currentUser = new Login();
		currentUser.validateLogin(usernameTextField.getText(), passwordField.getText());
		if(Main.userType != "[User]") {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("MainMenu.fxml"));	
			root = loader.load();	
			MenuController menuController = loader.getController();
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
			menuController.hideLogin(Main.user);
		} else
			formFailureLabel.setText("Please enter a correct username and password combination");
	}
	
	// Event that gives the user their password when they click Forgot Password
	public void forgotPassword(ActionEvent event) throws IOException {
		if(usernameTextField.getText().isBlank())
			formFailureLabel.setText("Please enter a correct username then click Forgot Password");
		else {
			Login passwordRecovery = new Login();
			passwordRecovery.userName = usernameTextField.getText();
			if(passwordRecovery.checkCurrentUserName()) {
				securityQChoiceBox.setValue(passwordRecovery.getCurrentUserSecurityQuestion());
				formFailureLabel.setText("Please answer the security question then click Forgot Password");
			} else
				formFailureLabel.setText("Please enter a correct username then click Forgot Password");
			//If User Security Answer is correct, send the user their password in an alert
			if(passwordRecovery.getCurrentUserSecurityAnswer().equals(securityATextField.getText())) {
				Alert alert = new Alert(AlertType.CONFIRMATION);
				alert.setTitle("Your Password");
				alert.setHeaderText("The Following Text is your Password");
				alert.setContentText(passwordRecovery.getCurrentUserPassword());
				formFailureLabel.setText("");
				System.out.println(Main.userType + Main.user + " is receiving their password");
				if(alert.showAndWait().get() == ButtonType.OK) {
					System.out.println(Main.userType + Main.user + " received their password");
				}
			} else if(passwordRecovery.checkCurrentUserName()) {
				formFailureLabel.setText("Incorrect Security Answer");
				System.out.println(Main.userType + Main.user + " entered an incorrect security answer for the account of " + usernameTextField.getText());
			}
		}
	}
	
	//Event that registers a new user for an account and sends it to the database
	public void register(ActionEvent event) throws IOException {
		//Tells user that a field is not filled
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
	
	// Switches the screen to the main menu
	public void switchToMainMenu(ActionEvent event) throws IOException {
		System.out.println(Main.userType + Main.user + " is now viewing Main Menu");

		root = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

}
