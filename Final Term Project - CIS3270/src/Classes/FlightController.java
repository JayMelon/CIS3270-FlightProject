package Classes;

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
public List<Flight> flights;



public FlightController() {
		flights = new ArrayList<Flight>();
	}

//Search method that pulls from DBS to fliter dbs and returns the list
public List<Flight> populateTable(String toCity, String fromCity, String date, String time) {
	try {
		Connection con = FlightDatabase.getConnect();	
		String query = "SELECT * FROM "+Flight.databaseName+
				" Where "+Flight.toCityColName+"= '"+toCity+
				"' AND "+Flight.fromCityColName+"= '"+fromCity+
				"' AND "+Flight.departDateColName+">= '"+FlightDatabase.convertDate(date)+
				"' ORDER BY "+Flight.departDateColName;
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
		for(int i = 0;i<flights.size();i++) {
		}
	}catch(Exception e) {
		e.printStackTrace();
	}
	return flights;
}

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
System.out.println(x.populateTable("LAS VEGAS NV, US (LAS)", "ATLANTA GA, US (ATL)","12/16/2022"
		,""));
x.bookFlight("3bb0db04-875b-4f47-a722-d9f8eab6c755", 1);
}

}
