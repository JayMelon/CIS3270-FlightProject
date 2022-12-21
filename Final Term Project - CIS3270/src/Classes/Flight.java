package Classes;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import Database.FlightDatabase;
import javafx.scene.control.Button;

public class Flight {
	// Constants
	public static final String databaseName = "FlightData";
	public static final String flightIDColName = "FlightID";
	public static final String fromCityColName = "FromCity";
	public static final String fromCityCodeColName = "FromCityCode";
	public static final String departTimeColName = "DepartureTime";
	public static final String departDateColName = "DepartureDate";
	public static final String toCityColName = "ToCity";
	public static final String toCityCodeColName = "ToCityCode";
	public static final String arrivalTimeColName = "arrivalTime";
	public static final String arrivalDateColName = "arrivalDate";
	public static final String occupanyColName = "currentOccupancy";
	public static final String capacityColName = "maxOccupancy";
	// Properties
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

	public Flight(String flightID, String departDate, String departTime, String ArrivalDate, String ArrivalTime,
			String FromCityCode, String FromCity, String ToCity, String ToCityCode, int Occupancy, int maxoccupy) {
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

//Generates a row for the flight column
	@Override
	public String toString() {
		return "FlightID: " + flightID + "\tdepartDate: " + departDate + "\tdepartTime: " + departDate
				+ "\tArrivalDate: " + arrivalDate + "\tArrivalTime: " + arrivalTime + "\tFromCityCode: " + fromCityCode
				+ "\tFromCity: " + fromCity + "\tToCity:  " + toCity + "\tToCityCode:  " + toCityCode + "\tOccupancy:  "
				+ occupany + "\tCapacity:  " + capacity;

	}

//Getters
	public String getFlightID() {
		return flightID;
	}

	public String getFromCity() {
		return fromCity;
	}

	public String getFromCityCode() {
		return fromCityCode;
	}

	public String getDepartTime() {
		return departTime;
	}

	public String getDepartDate() {
		return departDate;
	}

	public String getToCity() {
		return toCity;
	}

	public String getToCityCode() {
		return toCityCode;
	}

	public String getArrivalTime() {
		return arrivalTime;
	}

	public String getArrivalDate() {
		return arrivalDate;
	}

	public int getOccupany() {
		return occupany;
	}

	public int getCapacity() {
		return capacity;
	}

//Setters
	public void setFlightID(String flightID) {
		this.flightID = flightID;
	}

	public void setFromCity(String fromCity) {
		this.fromCity = fromCity;
	}

	public void setFromCityCode(String fromCityCode) {
		this.fromCityCode = fromCityCode;
	}

	public void setToCity(String toCity) {
		this.toCity = toCity;
	}

	public void setToCityCode(String toCityCode) {
		this.toCityCode = toCityCode;
	}

	public void setArrivalTime(String arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public void setArrivalDate(String arrivalDate) {
		this.arrivalDate = arrivalDate;
	}

	public void setOccupany(int occupany) {
		this.occupany = occupany;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	
	

}
