package gui;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
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
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class FlightsPageController implements Initializable {
	
	private Stage stage;
	private Scene scene;
	private Parent root;
	int z = 0;
	
	@FXML
	private ListView<String> flightListView;
	
	@FXML
	private Label selectFlightLabel;
	
	@FXML
	private Button addFlightButton;
	
	String[] flightsCode = {"ATL","PEK","LHR","ORD","HND","LAX","CDG","DFW","FRA","HKG","DEN","DXB","CGK","AMS","MAD",
			"BKK","JFK","SIN","CAN","LAS","PVG","SFO","PHX","IAH","CLT","MIA","MUC","KUL","FCO","IST","SYD","MCO","ICN",
			"DEL","BCN","LGW","EWR","YYZ","SHA","MSP","SEA","DTW","PHL","BOM","GRU","MNL","CTU","BOS","SZX","MEL","NRT",
			"ORY","MEX","DME","AYT","TPE","ZRH","LGA","FLL","IAD","PMI","CPH","SVO","BWI","KMG","VIE","OSL","JED","BNE",
			"SLC","DUS","BOG","MXP","JNB","ARN","MAN","MDW","DCA","BRU","DUB","GMP","DOH","STN","HGH","CJU","YVR","TXL",
			"SAN","TPA","CGH","BSB","CTS","XMN","RUH","FUK","GIG","HEL","LIS","ATH","AKL"};	
	String[] flightsCity = {"ATLANTA GA, US (ATL)","BEIJING, CN (PEK)","LONDON, GB (LHR)","CHICAGO IL, US (ORD)","TOKYO, JP (HND)",
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
	
	Calendar[] flightsDates; //= ;
	
	String[] flights; //= ;
	
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
	
	public void searchFlights(KeyEvent event) throws IOException {
		
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
		System.out.println(Main.userType + Main.user + " is now viewing Main Menu");
		
		root = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
}
