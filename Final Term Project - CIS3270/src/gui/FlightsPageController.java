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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class FlightsPageController implements Initializable {

	private Stage stage;
	private Scene scene;
	private Parent root;
	int z = 0;

	@FXML
	private Label flightPageErrorLabel;

	@FXML
	private TableView<Flight> flightTableView;
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
	@FXML
	private TableColumn<Flight, Integer> flightOccupancyCol;
	@FXML
	private TableColumn<Flight, Integer> flightCapacityCol;

	@FXML
	private Label selectFlightLabel;

	@FXML
	private Button searchFlightButton;

	@FXML
	private Button addFlightButton;

	@FXML
	private ChoiceBox<String> flightsToChoiceBox;
	@FXML
	private ChoiceBox<String> flightsFromChoiceBox;
	// Choice Box fill
	private String[] flightsCity = { "ATLANTA GA, US (ATL)", "BEIJING, CN (PEK)", "LONDON, GB (LHR)",
			"CHICAGO IL, US (ORD)", "TOKYO, JP (HND)", "LOS ANGELES CA, US (LAX)", "PARIS, FR (CDG)",
			"DALLAS/FORT WORTH TX, US (DFW)", "FRANKFURT, DE (FRA)", "HONG KONG, HK (HKG)", "DENVER CO, US (DEN)",
			"DUBAI, AE (DXB)", "JAKARTA, ID (CGK)", "AMSTERDAM, NL (AMS)", "MADRID, ES (MAD)", "BANGKOK, TH (BKK)",
			"NEW YORK NY, US (JFK)", "SINGAPORE, SG (SIN)", "GUANGZHOU, CN (CAN)", "LAS VEGAS NV, US (LAS)",
			"SHANGHAI, CN (PVG)", "SAN FRANCISCO CA, US (SFO)", "PHOENIX AZ, US (PHX)", "HOUSTON TX, US (IAH)",
			"CHARLOTTE NC, US (CLT)", "MIAMI FL, US (MIA)", "MUNICH, DE (MUC)", "KUALA LUMPUR, MY (KUL)",
			"ROME, IT (FCO)", "ISTANBUL, TR (IST)", "SYDNEY, AU (SYD)", "ORLANDO FL, US (MCO)", "INCHEON, KR (ICN)",
			"NEW DELHI, IN (DEL)", "BARCELONA, ES (BCN)", "LONDON, GB (LGW)", "NEWARK NJ, US (EWR)",
			"TORONTO ON, CA (YYZ)", "SHANGHAI, CN (SHA)", "MINNEAPOLIS MN, US (MSP)", "SEATTLE WA, US (SEA)",
			"DETROIT MI, US (DTW)", "PHILADELPHIA PA, US (PHL)", "MUMBAI, IN (BOM)", "SÃO PAULO, BR (GRU)",
			"MANILA, PH (MNL)", "CHENGDU, CN (CTU)", "BOSTON MA, US (BOS)", "SHENZHEN, CN (SZX)", "MELBOURNE, AU (MEL)",
			"TOKYO, JP (NRT)", "PARIS, FR (ORY)", "MEXICO CITY, MX (MEX)", "MOSCOW, RU (DME)", "ANTALYA, TR (AYT)",
			"TAIPEI, TW (TPE)", "ZURICH, CH (ZRH)", "NEW YORK NY, US (LGA)", "FORT LAUDERDALE, FL, US (FLL)",
			"WASHINGTON, DC, US (IAD)", "PALMA DE MALLORCA, ES (PMI)", "COPENHAGEN, DK (CPH)", "MOSCOW, RU (SVO)",
			"BALTIMORE MD, US (BWI)", "KUNMING, CN (KMG)", "VIENNA, AT (VIE)", "OSLO, NO (OSL)", "JEDDAH, SA (JED)",
			"BRISBANE, AU (BNE)", "SALT LAKE CITY UT, US (SLC)", "DÜSSELDORF, DE (DUS)", "BOGOTA, CO (BOG)",
			"MILAN, IT (MXP)", "JOHANNESBURG, ZA (JNB)", "STOCKHOLM, SE (ARN)", "MANCHESTER, GB (MAN)",
			"CHICAGO IL, US (MDW)", "WASHINGTON DC, US (DCA)", "BRUSSELS, BE (BRU)", "DUBLIN, IE (DUB)",
			"SEOUL, KR (GMP)", "DOHA, QA (DOH)", "LONDON, GB (STN)", "HANGZHOU, CN (HGH)", "JEJU, KR (CJU)",
			"VANCOUVER BC, CA (YVR)", "BERLIN, DE (TXL)", "SAN DIEGO CA, US (SAN)", "TAMPA FL, US (TPA)",
			"SÃO PAULO, BR (CGH)", "BRASILIA, BR (BSB)", "SAPPORO, JP (CTS)", "XIAMEN, CN (XMN)", "RIYADH, SA (RUH)",
			"FUKUOKA, JP (FUK)", "RIO DE JANEIRO, BR (GIG)", "HELSINKI, FI (HEL)", "LISBON, PT (LIS)",
			"ATHENS, GR (ATH)", "AUCKLAND, NZ (AKL)" };

	@FXML
	private DatePicker flightsDatePicker;

	@FXML
	private ChoiceBox<String> flightsTimeChoiceBox;

	private String[] flightsTimes = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14",
			"15", "16", "17", "18", "19", "20", "21", "22", "23" };
	
	Alert alert = new Alert(AlertType.CONFIRMATION);

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		try {
			// Creates the Arraylist of Flights
			// Populates the ObservableList with Flight that are visible to user.

			// Adds To Observable List
		} catch (Exception e) {
			Logger.getLogger(FlightsPageController.class.getName()).log(Level.SEVERE, null, e);
			e.printStackTrace();
		}

		flightsToChoiceBox.getItems().addAll(flightsCity);
		flightsToChoiceBox.setValue("ATLANTA GA, US (ATL)");
		flightsFromChoiceBox.getItems().addAll(flightsCity);
		flightsFromChoiceBox.setValue("CHICAGO IL, US (ORD)");
		flightsTimeChoiceBox.getItems().addAll(flightsTimes);
		flightsTimeChoiceBox.setValue("12");
		flightsDatePicker.setValue(LocalDate.now());

	}

	public void searchFlights(ActionEvent event) throws IOException {
		try {
			new TableView<Flight>();
			ObservableList<Flight> flightObservableList = FXCollections.observableArrayList();
			FlightController Flights = new FlightController();
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);

			String searchDate = dtf.format(flightsDatePicker.getValue());
			// Returns an ArrayList of requested flights via Search bar

			flightObservableList.addAll(Flights.getFlightList(flightsToChoiceBox.getValue(),
					flightsFromChoiceBox.getValue(), searchDate, flightsTimeChoiceBox.getValue()));
			flightFromCityCodeCol.setCellValueFactory(new PropertyValueFactory<Flight, String>("FromCityCode"));
			flightToCityCodeCol.setCellValueFactory(new PropertyValueFactory<Flight, String>("ToCityCode"));
			flightDepartTimeCol.setCellValueFactory(new PropertyValueFactory<Flight, String>("departTime"));
			flightArrivalTimeCol.setCellValueFactory(new PropertyValueFactory<Flight, String>("arrivalTime"));
			flightDepartDateCol.setCellValueFactory(new PropertyValueFactory<Flight, String>("departDate"));
			flightArrivalDateCol.setCellValueFactory(new PropertyValueFactory<Flight, String>("arrivalDate"));
			flightOccupancyCol.setCellValueFactory(new PropertyValueFactory<Flight, Integer>("Occupancy"));
			flightCapacityCol.setCellValueFactory(new PropertyValueFactory<Flight, Integer>("Capacity"));
			flightTableView.setItems(flightObservableList);
			flightTableView.refresh();
		} catch (Exception e) {
			Logger.getLogger(FlightsPageController.class.getName()).log(Level.SEVERE, null, e);
			e.printStackTrace();
		}
	}

	// Adds a flight to the user's personal bookings, visible in the User Flights
	// Page
	public void addUserFlight(ActionEvent event) throws IOException {
 		try {
			Reservation currentUser;
			// Gets the flightID from the selected column in gui
			String x = flightTableView.getSelectionModel().getSelectedItem().getFlightID();
			String selectedDate = flightTableView.getSelectionModel().getSelectedItem().getDepartDate();
			String selectedTime = flightTableView.getSelectionModel().getSelectedItem().getDepartTime();
			int selectedOccupancy = flightTableView.getSelectionModel().getSelectedItem().getOccupancy();
			int selectedCapacity = flightTableView.getSelectionModel().getSelectedItem().getCapacity();
			currentUser = new Reservation(Main.userID);
			if (Main.userType == "[User]") {
				// If user tries booking a flight without logging in.
				System.out.println(Main.userType + Main.user + " tried to book a flight without logging in");
				
				Alert alert = new Alert(AlertType.CONFIRMATION);
				alert.setTitle("Log in to Book");
				alert.setHeaderText("You must Log in to book a flight");
				if (alert.showAndWait().get() == ButtonType.OK)
					System.out.println(Main.userType + Main.user + " will try again.");
				
			}else if (Reservation.checkduplicatedFlight(Main.userID, x)) {
				// Checks Reservation DBS to see if user has booked the requested flight.
				alert.setTitle("Duplicate Flight");
				alert.setHeaderText("You have already booked this flight");
				alert.setContentText("You cannot book a duplicate flight.");
				System.out.println(Main.userType + Main.user + " tried to book a duplicate flight");
				if (alert.showAndWait().get() == ButtonType.OK)
					System.out.println(Main.userType + Main.user + " will try again.");
			}else if(selectedOccupancy>=selectedCapacity) {
				//If flight is full
				System.out.println(Main.userType + Main.user + " tried to book a full flight");
				
				alert.setTitle("Log in to Book");
				alert.setHeaderText("You must Log in to book a flight");
				alert.setContentText("");
				if (alert.showAndWait().get() == ButtonType.OK)
					System.out.println(Main.userType + Main.user + " will try again.");
			}
			// If user has flights
			else if (currentUser.hasFlights()) {
				boolean conflict = false;
				for (int i = 0; i < currentUser.userFlightIds.size(); i++) {
					if (currentUser.userHasTimeConflict(selectedDate, selectedTime)) {
						System.out.println("conflict found");
						conflict = true;
						break;
					}
				}
				if (!conflict) {
					//If There is not conflict go and book
					currentUser.bookFlight(Main.userID, x,selectedOccupancy);
					System.out.println(Main.userType + Main.user + " has booked flight " + x);
				}else {
					//If is not conflict 
					System.out.println(Main.userType + Main.user + "has Conflicting flight");
					alert.setTitle("Flight Conflict");
					alert.setHeaderText("This flight has a conflict with another previously-booked flight");
					alert.setContentText("");
					if (alert.showAndWait().get() == ButtonType.OK)
						System.out.println(Main.userType + Main.user + " will try again.");
				}
			}else {
				//Books flight if everything passes.
				currentUser.bookFlight(Main.userID, x,selectedOccupancy);
				System.out.println(Main.userType + Main.user + " has booked flight " + x);
			}
		} catch (Exception e) {
			// If User searches flight without Populating or select data.
			alert.setTitle("No Selection Made");
			alert.setHeaderText("Please select a flight.");
			alert.setContentText("");
			System.out.println(Main.userType + Main.user + " has not selected a flight");
			if (alert.showAndWait().get() == ButtonType.OK)
				System.out.println(Main.userType + Main.user + " will try again.");
		}
	}

	// else if(flight is full) {
	// selectFlightLabel.setText("Flight is Full.");
	// System.out.println(Main.userType + Main.user + " tried to book a full
	// flight");
	// } else if(customer already booked this flight) {
	// selectFlightLabel.setText("You have already booked this flight.");
	// System.out.println(Main.userType + Main.user + " tried to book a flight they
	// already had booked");
	// } else if(flightTime overlaps with another flight user has) {
	// Alert alert = new Alert(AlertType.CONFIRMATION);
	// alert.setTitle("Flight Time Conflict");
	// alert.setHeaderText("You're about to book a flight that happens at the same
	// time as another flight!");
	// alert.setContentText("Press OK to book it anyway or Cancel to look for a
	// different flight");
	// System.out.println(Main.userType + Main.user + " is attempting to log out!");
	// if(alert.showAndWait().get() == ButtonType.OK) {
	// stage = (Stage) mainMenuScene.getScene().getWindow();
	// System.out.println(Main.userType + Main.user + " chose to book a flight with
	// a time conflict");
	// } else
	// System.out.println(Main.userType + Main.user + " chose not to book a flight
	// with a time conflict");
	//
	// } else {
	// adds flight to customer's list
	// adds customer to flight's list
	// }

	// Sends an Admin to the Flight editor Page
	public void switchToFlightEditor(ActionEvent event) throws IOException {
		try {
		if (Main.userType .equals("[Admin]")) {
			System.out.println(Main.userType + Main.user + " is now viewing Flight Editor");
			root = FXMLLoader.load(getClass().getResource("FlightEditor.fxml"));
			stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		}
		//IF User isn't an Admin.
		else {
			System.out.println(Main.userType +"Attempted to open flight editor");
		}
	}
	catch(Exception e) {
		System.out.println(Main.userType +"Attempted to open flight editor");
		
	}
	}
	// Sends the user to the Main Menu
	public void switchToMainMenu(ActionEvent event) throws IOException {
		System.out.println(Main.userType + Main.user + " is now viewing Main Menu");

		FXMLLoader loader = new FXMLLoader(getClass().getResource("MainMenu.fxml"));
		root = loader.load();
		MenuController menuController = loader.getController();
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
		if (Main.userType != "[User]")
			menuController.hideLogin(Main.user);
	}
}
