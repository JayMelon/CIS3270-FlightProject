package Classes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.UUID;

import Database.FlightDatabase;

public class Admin extends User  {

	//Check if User is admin
	

public static void deleteFlight(String flightID) {

Connection con = FlightDatabase.getConnect();
	String deleteFromReservationQuery = "DELETE FROM "+Reservation.databaseName+" WHERE "+Reservation.flightIdColName+" = '" + flightID+"'";
	String deleteFromFlightDataQuery = "DELETE FROM "+Flight.databaseName+" WHERE "+Flight.flightIDColName+" = '" + flightID+"'";
	try {
		PreparedStatement posted = con.prepareStatement(deleteFromReservationQuery+"; "+deleteFromFlightDataQuery+"; ");
		posted.executeUpdate();
		System.out.println("Deleting flight "+flightID);
	} catch (Exception e) {
		System.out.println("Deleting failed");
	}
	
}

public static void addFlight(Flight flight) {
	Connection con = FlightDatabase.getConnect();
	String insertIntoFlightTableQuery = "Insert into FlightData "
			+ "(FlightID,"
			+ "DepartureDate,"
			+ "DepartureTime,"
			+ "arrivalDate,"
			+ "arrivalTime,"
			+ "FromCityCode,"
			+ "FromCity,"
			+ "ToCityCode,"
			+ "ToCity,"
			+ "currentOccupancy,"
			+ "maxOccupancy) "
			+ "VALUES ('"+flight.getFlightID()+"', '"
			+flight.getDepartDate()+"', '"
			+flight.getDepartTime()+"', '"
			+flight.getArrivalDate()+"', '"
			+flight.getArrivalTime()+"', '"
			+flight.getFromCityCode()+"', '"
			+flight.getFromCity()+"' , '"
			+flight.getToCity()+"' , '"
			+flight.getToCity()+"' , '"
			+flight.getOccupancy()+"' , '"
			+flight.getCapacity()+"')";
	try {
		PreparedStatement posted = con.prepareStatement(insertIntoFlightTableQuery);
		posted.executeUpdate();
		System.out.println("Added new flight "+flight.getFlightID());
	} catch (Exception e) {
		System.out.println("Insertion failed");
	}
	
}


public static void editFlight(Flight flight) {
	Connection con = FlightDatabase.getConnect();
	String query = "Select COUNT(flight_id) from Reservation where flight_id = '"+flight.getFlightID()+"'";
	int maxOccupancy = 0;
	try {
		Statement statement = con.createStatement();
		ResultSet queryResult = statement.executeQuery(query);
		while (queryResult.next()) {
			maxOccupancy = queryResult.getInt(0);
		}
		if(flight.getOccupancy()>maxOccupancy) {
			statement = con.createStatement();
			//IM TOO LAZY TO WRITE QUERY!!!
			queryResult = statement.executeQuery("UPDATE "+Flight.databaseName+" SET "
			+Flight.departDateColName+"'"+flight.getDepartDate()+"'"+flight.get+"'" "'");
		}else {
			System.out.println("User tried updating flight capacity with users exceeding the new occupancy");
		}

}catch(Exception e) {
	
}
}
}