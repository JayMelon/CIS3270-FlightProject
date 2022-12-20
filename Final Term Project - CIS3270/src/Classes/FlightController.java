package Classes;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Database.FlightDatabase;

public class FlightController{
//Empty List of Flights
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
public void genTable() {
	try {
		Connection con = FlightDatabase.getConnect();	
		String query = "SELECT * FROM FlightData;";
		Statement statement = con.createStatement();
		ResultSet result = statement.executeQuery(query);
		Flight flight;
		while(result.next()) {
			flight= new Flight(
					result.getString(Flight.flightIDColName),
					result.getString(Flight.departDateColName),
					result.getString(Flight.departTimeColName),
					result.getString(Flight.arrivalDateColName),
					result.getString(Flight.arrivalTimeColName),
					result.getString(Flight.fromCityCodeColName),
					result.getString(Flight.fromCityColName),
					result.getString(Flight.toCityCodeColName),
					result.getString(Flight.toCityColName),
					result.getInt(Flight.occupanyColName),
					result.getInt(Flight.capacityColName)
					);
			flights.add(flight);
		}
		for(int i = 0;i<flights.size();i++) {
		}
	}catch(Exception e) {
		e.printStackTrace();
	}
}
public static void main(String[]arg) {
	FlightController x = new FlightController();
	x.genTable();
	//Returns are 
	String result = x.flights.get(0).getFlightID();
	
}
}
