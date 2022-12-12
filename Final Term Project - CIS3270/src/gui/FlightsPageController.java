package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.SpinnerValueFactory.IntegerSpinnerValueFactory;
import javafx.stage.Stage;

public class FlightsPageController implements Initializable {
	
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	@FXML
	private ListView<String> flightListView;
	
	@FXML
	private Label selectFlightLabel;
	
	@FXML
	private Button addFlightButton;
	
	String[] flights = {""};
	
	String currentFlight;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		flightListView.getItems().addAll(flights);
		flightListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
				
				currentFlight = flightListView.getSelectionModel().getSelectedItem();
				selectFlightLabel.setText(currentFlight);
				
			}	
		});
	}
	
	public void addUserFlight(ActionEvent event) throws IOException {
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
	
	public void switchToMainMenu(ActionEvent event) throws IOException {
		System.out.println("User is now viewing Main Menu");
		
		root = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
}
