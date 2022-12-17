package Classes;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import Database.FlightDatabase;

public class FlightController {
public List<Flight> flights;

public static final String dbTableName = "FlightData";

FlightController() {
		flights = new ArrayList<Flight>();
	}
public List<Flight> populateFlights() {
	Connection con = FlightDatabase.getConnect();
	String getFlights = "SELECT TOP "+FlightController.dbTableName+"";
	
	return flights;
}
public static void main(String[]arg) {
	FlightController x = new FlightController();
	x.flights.add(0, Fli
	
}
}
