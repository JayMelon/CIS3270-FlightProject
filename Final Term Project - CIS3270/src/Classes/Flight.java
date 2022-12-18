package Classes;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import Database.FlightDatabase;
import javafx.scene.control.Button;

public class Flight extends Airline {
	public static final String databaseName = "Flight";
	public String flightID;
	public String fromCity;
	public String fromCityCode;
	public String departTime;
	public String departDate;
	public String toCity;
	public String toCityCode;
	public String arrivalTime;
	public String arrivalDate;
	public int occupany;
	public int capacity;
	
	public Button addFlight;
	
Flight(String flightID, String departDate, String departTime, String FromCityCode, String FromCity, String ToCity, String ToCityCode ,String ArrivalTime, String ArrivalDate,  int Occupancy, int maxoccupy ){
		this.flightID = flightID;
		this.departDate = departDate;
		this.departTime = departTime;
		this.fromCityCode = FromCityCode;
		this.fromCity = FromCity;
		this.toCity = ToCity;
		this.toCityCode = ToCityCode;
		this.arrivalTime = ArrivalTime;
		this.arrivalDate = ArrivalDate;
		this.occupany = Occupancy;
		this.capacity = maxoccupy;
	}	
	


//Check if Flight is full, If full returns true else false. 
	public boolean checkFull() {
		Connection con = FlightDatabase.getConnect();
		String check = "SELECT Seats,capacity FROM " + Flight.databaseName + " WHERE FlightID='" + this.flightID + "';";
		try {
			System.out.println(check);
			Statement statement = con.createStatement();
			ResultSet queryResult = statement.executeQuery(check);
			while (queryResult.next()) {
				// If the seats is greater then Capacity return false
				if (queryResult.getInt(2) < queryResult.getInt(1)) {
					System.out.println("Searched flight has room");
					return false;
				} else {
					System.out.println("Searched flight is full");
					return true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}




//Check if the Cities are valid. 
	public Flight AddFlight() {
		
		return this;
	}

	public void EditFlight() {

	}

	public void DeleteFlight() {

	}
}
