package Classes;
import java.sql.*;
import java.util.*;


import Database.FlightDatabase;


public class Flight extends Airline {
public static final String databaseName = "Flight";
private String flightID;
private String departCountry;
private String departCity;
private String departState;
private String departureTime;
private String departureDate;
private String arrivalCountry;
private String arrivalCity;
private String arrivalState;
private String arrivalTime;
private String arrivalDate;
private int seats;
private int capacity;



//Check if Flight is full, If full returns true else false. 
public boolean checkFull() {
	Connection con = FlightDatabase.getConnect();
	String check = "SELECT Seats,capacity FROM "+Flight.databaseName+" WHERE FlightID='"+this.flightID+"';";
	try {
		System.out.println(check);
		Statement statement = con.createStatement();
		ResultSet queryResult = statement.executeQuery(check);
		while (queryResult.next()) {
			//If the seats is greater then Capacity return false
			if(queryResult.getInt(2)<queryResult.getInt(1)) {
				System.out.println("Searched flight has room");
				return false;
			}else {
				System.out.println("Searched flight is full");
				return true;
			}
		}
	}catch (Exception e ) {
		e.printStackTrace();
	}return false;
}


public void AddFlight() {
	
}
public void EditFlight() {
	
}
public void DeleteFlight() {
	
}
public static void main(String[]arg) {
	Flight test = new Flight();
		test.flightID = "F1231";
	System.out.println(test.checkFull());
}
}
