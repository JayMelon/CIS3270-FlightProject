package gui;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import Classes.Admin;
import Classes.Flight;
import Classes.FlightController;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class FlightEditorController implements Initializable {
	
	private Stage stage;
	private Scene scene;
	private Parent root;
	int z = 0;
	
	@FXML
	private TableView<Flight> adminFlightTableView;
	@FXML
	private TableColumn<Flight, String> adminFlightFromCityCodeCol;
	@FXML
	private TableColumn<Flight, String> adminFlightToCityCodeCol;
	@FXML
	private TableColumn<Flight, String> adminFlightDepartTimeCol;
	@FXML
	private TableColumn<Flight, String> adminFlightArrivalTimeCol;
	@FXML
	private TableColumn<Flight, String> adminFlightDepartDateCol;
	@FXML
	private TableColumn<Flight, String> adminFlightArrivalDateCol;
	@FXML
	private TableColumn<Flight, Integer> adminFlightOccupancyCol;
	@FXML
	private TableColumn<Flight, Integer> adminFlightCapacityCol;
	
	/*@FXML
	private Label selectFlightLabel;
	*/
	@FXML
	private Button searchFlightButton;
	@FXML
	private Button addFlightButton;
	@FXML
	private ChoiceBox<String> flightsToChoiceBox;
	@FXML
	private ChoiceBox<String> flightsFromChoiceBox;
	
	@FXML
	private ChoiceBox<String> flightsToEditChoiceBox;
	@FXML
	private ChoiceBox<String> flightsFromEditChoiceBox;
	
	private String[] flightsCity = {"ATLANTA GA, US (ATL)","BEIJING, CN (PEK)","LONDON, GB (LHR)","CHICAGO IL, US (ORD)","TOKYO, JP (HND)",
			"LOS ANGELES CA, US (LAX)","PARIS, FR (CDG)","DALLAS/FORT WORTH TX, US (DFW)","FRANKFURT, DE (FRA)","HONG KONG, HK (HKG)",
			"DENVER CO, US (DEN)","DUBAI, AE (DXB)","JAKARTA, ID (CGK)","AMSTERDAM, NL (AMS)","MADRID, ES (MAD)","BANGKOK, TH (BKK)",
			"NEW YORK NY, US (JFK)","SINGAPORE, SG (SIN)","GUANGZHOU, CN (CAN)","LAS VEGAS NV, US (LAS)","SHANGHAI, CN (PVG)",
			"SAN FRANCISCO CA, US (SFO)","PHOENIX AZ, US (PHX)","HOUSTON TX, US (IAH)","CHARLOTTE NC, US (CLT)","MIAMI FL, US (MIA)",
			"MUNICH, DE (MUC)","KUALA LUMPUR, MY (KUL)","ROME, IT (FCO)","ISTANBUL, TR (IST)","SYDNEY, AU (SYD)","ORLANDO FL, US (MCO)",
			"INCHEON, KR (ICN)","NEW DELHI, IN (DEL)","BARCELONA, ES (BCN)","LONDON, GB (LGW)","NEWARK NJ, US (EWR)","TORONTO ON, CA (YYZ)",
			"SHANGHAI, CN (SHA)","MINNEAPOLIS MN, US (MSP)","SEATTLE WA, US (SEA)","DETROIT MI, US (DTW)","PHILADELPHIA PA, US (PHL)",
			"MUMBAI, IN (BOM)","SÃO PAULO, BR (GRU)","MANILA, PH (MNL)","CHENGDU, CN (CTU)","BOSTON MA, US (BOS)","SHENZHEN, CN (SZX)",
			"MELBOURNE, AU (MEL)","TOKYO, JP (NRT)","PARIS, FR (ORY)","MEXICO CITY, MX (MEX)","MOSCOW, RU (DME)","ANTALYA, TR (AYT)",
			"TAIPEI, TW (TPE)","ZURICH, CH (ZRH)","NEW YORK NY, US (LGA)","FORT LAUDERDALE, FL, US (FLL)","WASHINGTON, DC, US (IAD)",
			"PALMA DE MALLORCA, ES (PMI)","COPENHAGEN, DK (CPH)","MOSCOW, RU (SVO)","BALTIMORE MD, US (BWI)","KUNMING, CN (KMG)",
			"VIENNA, AT (VIE)","OSLO, NO (OSL)","JEDDAH, SA (JED)","BRISBANE, AU (BNE)","SALT LAKE CITY UT, US (SLC)","DÜSSELDORF, DE (DUS)",
			"BOGOTA, CO (BOG)","MILAN, IT (MXP)","JOHANNESBURG, ZA (JNB)","STOCKHOLM, SE (ARN)","MANCHESTER, GB (MAN)","CHICAGO IL, US (MDW)",
			"WASHINGTON DC, US (DCA)","BRUSSELS, BE (BRU)","DUBLIN, IE (DUB)","SEOUL, KR (GMP)","DOHA, QA (DOH)","LONDON, GB (STN)",
			"HANGZHOU, CN (HGH)","JEJU, KR (CJU)","VANCOUVER BC, CA (YVR)","BERLIN, DE (TXL)","SAN DIEGO CA, US (SAN)","TAMPA FL, US (TPA)",
			"SÃO PAULO, BR (CGH)","BRASILIA, BR (BSB)","SAPPORO, JP (CTS)","XIAMEN, CN (XMN)","RIYADH, SA (RUH)","FUKUOKA, JP (FUK)",
			"RIO DE JANEIRO, BR (GIG)","HELSINKI, FI (HEL)","LISBON, PT (LIS)","ATHENS, GR (ATH)","AUCKLAND, NZ (AKL)"};
	@FXML
	private DatePicker flightsDatePicker;
	
	@FXML
	private DatePicker flightsDepartureDatePicker;
	@FXML
	private DatePicker flightsArrivalDatePicker;
	
	
	@FXML
	private ChoiceBox<String> flightsTimeChoiceBox;
	
	@FXML
	private ChoiceBox<String> flightsDepartureTimeChoiceBox;
	@FXML
	private ChoiceBox<String> flightsArrivalTimeChoiceBox;	
	
	private String[] flightsTimes = {"0","1","2","3","4","5","6","7","8","9","10",
			"11","12","13","14","15","16","17","18","19","20","21","22","23"};
	
	@FXML
	private ChoiceBox<String> currentOccupancyChoiceBox;
	
	private String[] flightOccupancy = {"0","1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22",
			"23","24","25","26","27","28","29","30","31","32","33","34","35","36","37","38","39","40","41","42","43","44","45","46","47","48",
			"49","50","51","52","53","54","55","56","57","58","59","60","61","62","63","64","65","66","67","68","69","70","71","72","73","74",
			"75","76","77","78","79","80","81","82","83","84","85","86","87","88","89","90","91","92","93","94","95","96","97","98","99","100",
			"101","102","103","104","105","106","107","108","109","110","111","112","113","114","115","116","117","118","119","120"};
	
	// Adds default values to the search boxes
	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		flightsToChoiceBox.getItems().addAll(flightsCity);
		flightsToChoiceBox.setValue("ATLANTA GA, US (ATL)");
		flightsFromChoiceBox.getItems().addAll(flightsCity);
		flightsFromChoiceBox.setValue("CHICAGO IL, US (ORD)");
		flightsTimeChoiceBox.getItems().addAll(flightsTimes);
		flightsTimeChoiceBox.setValue("12");
		flightsDatePicker.setValue(LocalDate.now());
		
		flightsToEditChoiceBox.getItems().addAll(flightsCity);
		flightsToEditChoiceBox.getItems().add("");
		flightsFromEditChoiceBox.getItems().addAll(flightsCity);
		flightsFromEditChoiceBox.getItems().add("");
		flightsDepartureTimeChoiceBox.getItems().addAll(flightsTimes);
		flightsArrivalTimeChoiceBox.getItems().addAll(flightsTimes);
		currentOccupancyChoiceBox.getItems().addAll(flightOccupancy);
		
	}
	
	public void searchFlights(ActionEvent event) throws IOException {
		try {
			new TableView<Flight>();
			ObservableList<Flight> flightObservableList = FXCollections.observableArrayList();
			FlightController adminFlights = new FlightController();
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);

			String searchDate = dtf.format(flightsDatePicker.getValue());
			// Returns an ArrayList of requested flights via Search bar
			flightObservableList.addAll(adminFlights.getFlightList(flightsToChoiceBox.getValue(),
					flightsFromChoiceBox.getValue(), searchDate, flightsTimeChoiceBox.getValue()));
			adminFlightFromCityCodeCol.setCellValueFactory(new PropertyValueFactory<Flight, String>("FromCityCode"));
			adminFlightToCityCodeCol.setCellValueFactory(new PropertyValueFactory<Flight, String>("ToCityCode"));
			adminFlightDepartTimeCol.setCellValueFactory(new PropertyValueFactory<Flight, String>("departTime"));
			adminFlightArrivalTimeCol.setCellValueFactory(new PropertyValueFactory<Flight, String>("arrivalTime"));
			adminFlightDepartDateCol.setCellValueFactory(new PropertyValueFactory<Flight, String>("departDate"));
			adminFlightArrivalDateCol.setCellValueFactory(new PropertyValueFactory<Flight, String>("arrivalDate"));
			adminFlightOccupancyCol.setCellValueFactory(new PropertyValueFactory<Flight, Integer>("Occupancy"));
			adminFlightCapacityCol.setCellValueFactory(new PropertyValueFactory<Flight, Integer>("Capacity"));
			adminFlightTableView.setItems(flightObservableList);
			adminFlightTableView.refresh();
		} catch (Exception e) {
			Logger.getLogger(FlightsPageController.class.getName()).log(Level.SEVERE, null, e);
			e.printStackTrace();
		}
	}
	
	public void addFlight(ActionEvent event) throws IOException {
		//flightsToEditChoiceBox.getValue(), flightsFromEditChoiceBox.getValue(), flightsDepartureDatePicker.getValue(),
		//flightsArrivalDatePicker.getValue(), flightsDepartureTimeChoiceBox.getValue(), flightsArrivalTimeChoiceBox.getValue()
		//
		//checks the above values and as long as all are there it creates a new flight in the database
	}
	
	public void editFlight(ActionEvent event) throws IOException {
		//flightsListView.getValue()
		//
		//Takes the Above Value to look for a flight, then edits the it with any of the fields below that are not blank
		//
		//flightsToEditChoiceBox.getValue(), flightsFromEditChoiceBox.getValue(), flightsDepartureDatePicker.getValue(),
		//flightsArrivalDatePicker.getValue(), flightsDepartureTimeChoiceBox.getValue(), flightsArrivalTimeChoiceBox.getValue()
	}
	
	public void deleteFlight(ActionEvent event) throws IOException {
		//Deletes flight from FlightDBS and from all users who have registered.
		Admin.deleteFlight(adminFlightTableView.getSelectionModel().getSelectedItem().getFlightID());
		adminFlightTableView.getItems().removeAll(adminFlightTableView.getSelectionModel().getSelectedItem());
	}
	
	// sends the user to the flights page
	public void switchToFlightsPage(ActionEvent event) throws IOException {
		System.out.println(Main.userType + Main.user + " is now viewing Flight Editor");
		
		root = FXMLLoader.load(getClass().getResource("FlightsPage.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
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
