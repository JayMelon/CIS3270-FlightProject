package gui;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class UserFlightsController {
	
	@FXML
	private Button deleteFlightButton;
	
	public void deleteUserFlight(ActionEvent event) throws IOException {
		//if(Main.userType == "[User]") {
		//	selectFlightLabel.setText("Please Login before trying to add flights.");
		//	System.out.println(Main.userType + Main.user + " tried to book a flight without logging in");
		//} else if(flight is full) {
		//	selectFlightLabel.setText("Flight is Full.");
		//	System.out.println(Main.userType + Main.user + " tried to book a full flight");
		//} else if(customer already booked this flight) {
		//	selectFlightLabel.setText("You have already booked this flight.");
		//	System.out.println(Main.userType + Main.user + " tried to book a flight they already had booked");
		//} else if(flightTime overlaps with another flight user has) {
		//	Alert alert = new Alert(AlertType.CONFIRMATION);
		//	alert.setTitle("Flight Time Conflict");
		//	alert.setHeaderText("You're about to book a flight that happens at the same time as another flight!");
		//	alert.setContentText("Press OK to book it anyway or Cancel to look for a different flight");
		//	System.out.println(Main.userType + Main.user + " is attempting to log out!");
		//	if(alert.showAndWait().get() == ButtonType.OK) {
		//		stage = (Stage) mainMenuScene.getScene().getWindow();
		//		System.out.println(Main.userType + Main.user + " chose to book a flight with a time conflict");
		//	} else
		//		System.out.println(Main.userType + Main.user + " chose not to book a flight with a time conflict");
		//			
		//} else {
		//	adds flight to customer's list
		//	adds customer to flight's list
		//}
	}
	
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	public void switchToMainMenu(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
}