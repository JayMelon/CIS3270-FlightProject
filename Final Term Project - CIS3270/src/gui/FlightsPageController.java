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
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
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
	
	String[] flights = {"ATL -> NYC","TKO -> ATH","CHI -> LA"};
	
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
	
	public void switchToMainMenu(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
}
