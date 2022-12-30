package Classes;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import Database.FlightDatabase;


public class FlightController{
//Empty List of Flights 
public ArrayList<Flight> actualFlightData;

public FlightController() {
		actualFlightData = new ArrayList<Flight>();
	}

//Returns an ArrayList of flight contains all Flights based in FlightData Table
public ArrayList<Flight> getFlightList() {
	try {
		Connection con = FlightDatabase.getConnect();	
		String query = "SELECT TOP(10) * FROM "+Flight.databaseName;
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
			actualFlightData.add(flight);
			}
	}catch(Exception e) {
		e.printStackTrace();
	}
	return actualFlightData;
}
//Returns an ArrayList of flight contains all Flights based in FlightData Table(Flitered)
//Returns an ArrayList in a searched fashion - Updates the current FlightController Object's Array of FlightData.
public ArrayList<Flight> getFlightList(String fromCity,String toCity, String departDate, String departTime) {
	try {
		Connection con = FlightDatabase.getConnect();	
		String query = "SELECT TOP(30) * FROM "+Flight.databaseName
				+" WHERE " +Flight.fromCityColName
				+" = '" +fromCity
				+"' AND "+Flight.toCityColName 
				+"= '"+toCity
				+"' AND "
				+Flight.departDateColName
				+" >= '"+departDate+"'"+" ORDER BY "+Flight.departDateColName+" asc, "+Flight.departTimeColName+" Asc";
		System.out.println(query);
		Statement statement = con.createStatement();
		ResultSet result = statement.executeQuery(query);
		//Creating new flight object with 
		Flight flightData;
		while(result.next()) {
			flightData= new Flight(
					result.getString(Flight.flightIDColName),
					result.getString(Flight.departDateColName),
					result.getString(Flight.departTimeColName).substring(0,8),
					result.getString(Flight.arrivalDateColName),
					result.getString(Flight.arrivalTimeColName).substring(0,8),
					result.getString(Flight.fromCityCodeColName),
					result.getString(Flight.fromCityColName),
					result.getString(Flight.toCityCodeColName),
					result.getString(Flight.toCityColName),
					result.getInt(Flight.occupanyColName),
					result.getInt(Flight.capacityColName)
					);
			this.actualFlightData.add(flightData);
			}
	}catch(Exception e) {
	}
	return this.actualFlightData;
}

//Checks flight capacity based on Flight Object.
//Checks if the flight is full
public boolean checkCapacity(int col) {
	int occupancy = this.actualFlightData.get(col).occupancy;
	int capacity = this.actualFlightData.get(col).capacity;
	return(occupancy<capacity);
}
//Takes current date and hours and sums them to a new date
//Counts the amount of hours 
public static Date getDateFromHoursAway(Date startingDate, int hours) {
    long startingMillis = startingDate.getTime();
    // Resolves the current ms/second/minute/hour/day 
    long currentDay = startingMillis / 1000 / 60 / 60;
    long futureTimeMillis = (currentDay + hours) * 60 * 60 * 1000;
    return new Date(futureTimeMillis);
}

//Adds flight
}

