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



FlightController() {
		flights = new ArrayList<Flight>();
	}

//Search method that pulls from DBS to fliter dbs 
public void genTable(String toCity, String fromCity, String date, String time) {
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
	x.genTable("ATLANTA GA, US (ATL)","LONDON, GB (STN)","12/20/2022","12");

	
}
}
