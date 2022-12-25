package gui;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

public class UserFlightsController {
	
	@FXML
	private Button deleteFlightButton;
	
	@FXML
	private ListView<String> userFlightsListView;
	
	private String[] userFlights = {};
	
	public void initialize(URL url, ResourceBundle resourceBundle) {
		userFlightsListView.getItems().addAll(userFlights);
	}
	
	public void deleteUserFlight(ActionEvent event) throws IOException {
		//if(userFlightsListView.getValue() = "") {
		//	Alert alert = new Alert(AlertType.CONFIRMATION);
		//	alert.setTitle("Delete Flight Confirmation");
		//	alert.setHeaderText("You're about to delete a flight booking!");
		//	alert.setContentText("Press OK to delete the booking");
		//	System.out.println(Main.userType + Main.user + " is attempting to delete a flight booking!");
		//	if(alert.showAndWait().get() == ButtonType.OK) {
		//		stage = (Stage) mainMenuScene.getScene().getWindow();
		//		System.out.println(Main.userType + Main.user + " chose to delete a flight booking!");
		//		userFlightsListView.getValue() // Deletes This Flight
		//	} else
		//		System.out.println(Main.userType + Main.user + " chose not to delete a flight booking!");
		//} else
		//	System.out.println(Main.userType + Main.user + " did not select a flight booking to delete!");
	}
	
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	// sends the user to the main menu
	public void switchToMainMenu(ActionEvent event) throws IOException {
		System.out.println(Main.userType + Main.user + " is now viewing Main Menu");
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("MainMenu.fxml"));	
		root = loader.load();	
		MenuController menuController = loader.getController();
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
		menuController.hideLogin(Main.user);
	}
}