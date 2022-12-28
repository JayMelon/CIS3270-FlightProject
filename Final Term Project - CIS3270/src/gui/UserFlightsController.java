package gui;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import Classes.Flight;
import Classes.FlightController;
import Classes.Reservation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class UserFlightsController implements Initializable{
	private Stage stage;
	private Scene scene;
	private Parent root;
	@FXML
	private Button deleteFlightButton;
	
	@FXML
	private TableView<Flight> userFlightsTableView;
	@FXML
	private TableColumn<Flight, String> flightFromCityCodeCol;
	@FXML
	private TableColumn<Flight, String> flightToCityCodeCol;
	@FXML
	private TableColumn<Flight, String> flightDepartTimeCol;
	@FXML
	private TableColumn<Flight, String> flightArrivalTimeCol;
	@FXML
	private TableColumn<Flight, String> flightDepartDateCol;
	@FXML
	private TableColumn<Flight, String> flightArrivalDateCol;

	

	@Override
	public void initialize(URL arg0, ResourceBundle arg) {
		try {
			
			
		} catch (Exception e) {
			Logger.getLogger(UserFlightsController.class.getName()).log(Level.SEVERE, null, e);
			e.printStackTrace();
		}
			new TableView<Flight>();
			ObservableList<Flight> flightObservableList = FXCollections.observableArrayList();
			Reservation userFlights = new Reservation(Main.userID);
			userFlights.getUserFlightIDs();
			userFlights.getUserFlightData();
			flightObservableList.addAll(userFlights.userFlightData);
			flightFromCityCodeCol.setCellValueFactory(new PropertyValueFactory<Flight, String>("FromCityCode"));
			flightToCityCodeCol.setCellValueFactory(new PropertyValueFactory<Flight, String>("ToCityCode"));
			flightDepartTimeCol.setCellValueFactory(new PropertyValueFactory<Flight, String>("departTime"));
			flightArrivalTimeCol.setCellValueFactory(new PropertyValueFactory<Flight, String>("arrivalTime"));
			flightDepartDateCol.setCellValueFactory(new PropertyValueFactory<Flight, String>("departDate"));
			flightArrivalDateCol.setCellValueFactory(new PropertyValueFactory<Flight, String>("arrivalDate"));
			userFlightsTableView.setItems(flightObservableList);
			userFlightsTableView.refresh();
			
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