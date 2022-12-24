package Classes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import Database.FlightDatabase;


public class FlightController{
//Empty List of Flights
public ArrayList<Flight> actualFlightData;
//Empty List of Flights that will be shown to the user
public ArrayList<Flight> visualFlightData;

public FlightController() {
		actualFlightData = new ArrayList<Flight>();
		visualFlightData = new ArrayList<Flight>();
	}

//Returns an Array of flights contains all information
public ArrayList<Flight> getFlightList() {
	try {
		Connection con = FlightDatabase.getConnect();	
		String query = "SELECT TOP(100) * FROM "+Flight.databaseName;
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

//References the actual flight list and returns values not including FlightID,Flight city full names
public ArrayList<Flight> getVisibleFlightList(){
	for(int i = 0;i<actualFlightData.size();i++) {
		String fromCity = actualFlightData.get(i).getFromCityCode();
		String toCity = actualFlightData.get(i).getToCityCode();
		String departDate = actualFlightData.get(i).getDepartDate();
		String arriveDate = actualFlightData.get(i).getArrivalDate();
		String departTime = actualFlightData.get(i).getDepartTime();
		String arriveTime = actualFlightData.get(i).getArrivalTime();
		int occupancy = actualFlightData.get(i).getOccupancy();
		int capacity = actualFlightData.get(i).getCapacity();
		
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
		
		visualFlightData.add(visualFlight);
	}
	return visualFlightData;
}
//Returns an ArrayList in a searched fashion - Updates the flights array.
public ArrayList<Flight> getFlightList(String fromCity,String toCity, String departDate, String departTime) {
	try {
		Connection con = FlightDatabase.getConnect();	
		String query = "SELECT TOP(100) * FROM "+Flight.databaseName
				+" WHERE " +Flight.fromCityColName
				+" = '" +fromCity
				+"' AND "+Flight.toCityColName 
				+"= '"+toCity
				+"' AND "
				+Flight.departDateColName
				+" >= '"+departDate+"'"+" ORDER BY "+Flight.departTimeColName;
		Statement statement = con.createStatement();
		ResultSet result = statement.executeQuery(query);
		//Creating new flight object 
		Flight flightData;
		while(result.next()) {
			flightData= new Flight(
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
			this.actualFlightData.add(flightData);
			}
	}catch(Exception e) {
		e.printStackTrace();
	}
	return this.actualFlightData;
}


//Checks if the flight is full
public boolean checkDuplicatedFlight() {
	
	return true;
}
//Checks if the flight is full
public boolean checkCapacity(int col) {
	int occupancy = this.actualFlightData.get(col).occupancy;
	int capacity = this.actualFlightData.get(col).capacity;
	return(occupancy<capacity);
}
//Adds flight
public void bookFlight(String userid, int Flightcolumn) {
	String flightID = this.actualFlightData.get(Flightcolumn).flightID;
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
	System.out.println(x.getFlightList("ATLANTA GA, US (ATL)","London, GB (STN)","2022-12-23","0"));
	System.out.println(x.getVisibleFlightList().get(0).arrivalTime);
	}
}
