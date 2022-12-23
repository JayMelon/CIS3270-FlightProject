package Classes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Database.FlightDatabase;
import gui.Main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Database.FlightDatabase;
import gui.Main;

public class FlightController{
//Empty List of Flights
public ArrayList<Flight> flights;
//Empty List of Flights that will be shown to the user
public ArrayList<Flight> visualFlights;

public FlightController() {
		flights = new ArrayList<Flight>();
		visualFlights = new ArrayList<Flight>();
	}

//Returns an Array of flights contains all information
public ArrayList<Flight> getFlightList() {
	try {
		Connection con = FlightDatabase.getConnect();	
		String query = "SELECT TOP(1) * FROM "+Flight.databaseName;
		System.out.println(query);
		Statement statement = con.createStatement();
		ResultSet result = statement.executeQuery(query);
		//Creating new flight object 
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
	}catch(Exception e) {
		e.printStackTrace();
	}
	return flights;
}


public ArrayList<Flight> getVisibleFlightList(){
	for(int i = 0;i<flights.size();i++) {
		String fromCity = flights.get(i).getFromCityCode();
		String toCity = flights.get(i).getToCityCode();
		String departDate = flights.get(i).getDepartDate();
		String arriveDate = flights.get(i).getArrivalDate();
		String departTime = flights.get(i).getDepartTime();
		String arriveTime = flights.get(i).getArrivalTime();
		int occupancy = flights.get(i).getOccupany();
		int capacity = flights.get(i).getCapacity();
		
		Flight visualFlight = new Flight(
				fromCity, 
				toCity, 
				departDate, 
				arriveDate, 
				departTime, 
				arriveTime, 
				occupancy,
				capacity
				);
		
		visualFlights.add(visualFlight);
	}
	return visualFlights;
}

//Checks if the flight is full
public boolean checkDuplicatedFlight() {
	
	return true;
}
//Checks if the flight is full
public boolean checkCapacity(int col) {
	int occupany = this.flights.get(col).occupany;
	int capacity = this.flights.get(col).capacity;
	return(occupany<capacity);
}
//Al
public void bookFlight(String userid, int Flightcolumn) {
	String flightID = this.flights.get(Flightcolumn).flightID;
	String Query = "INSERT INTO Reservation("
			+Reservation.orderId+","
			+Reservation.flightIdColName+","
			+Reservation.userIdColName+")"
			+" Values"+"('"+Reservation.randomID()+"','"
			+flightID+"','"+userid+"')";
	try {
		Connection con = FlightDatabase.getConnect();
		PreparedStatement posted = con.prepareStatement(Query);
		posted.executeUpdate();
		System.out.println(Query);
		System.out.println("Booking has succeeded");
	}catch(Exception e){
		e.printStackTrace();
	}
}
public static void main(String[]arg) {
	FlightController x = new FlightController();
	System.out.println(x.getFlightList());
	System.out.println(x.getVisibleFlightList().get(0));
	}
}
