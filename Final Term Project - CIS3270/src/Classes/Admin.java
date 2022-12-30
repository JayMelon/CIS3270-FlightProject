package Classes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

import Database.FlightDatabase;

public class Admin extends User  {

	//Check if User is admin
	
//Runs query to delete flight from FlightData based on given flight ID
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
//Runs query to add flight toFlightData based on Properties of given flight
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


//Takes strings from the boxes and stores it to an Object/Edits flight based on selected on table by keeping flight id and alter everything else.
	public static void editFlight(Flight flight) {
		Connection con = FlightDatabase.getConnect();
		try {
			Statement statement = con.createStatement();
			statement = con.createStatement();
			String updateQuery = ("UPDATE " + Flight.databaseName + " SET " + Flight.departDateColName + "= '"
					+ flight.getDepartDate() + "', " + Flight.departTimeColName + "= '" + flight.getDepartTime() + "', "
					+ Flight.arrivalDateColName + "= '" + flight.getArrivalDate() + "', " + Flight.arrivalTimeColName
					+ "= '" + flight.getArrivalTime() + "', " + Flight.fromCityCodeColName + "= '"
					+ flight.getFromCityCode() + "', " + Flight.fromCityColName + "= '" + flight.getFromCity() + "', "
					+ Flight.toCityCodeColName + "= '" + flight.getToCityCode() + "', " + Flight.toCityColName + "= '"
					+ flight.getToCity() + "', " + Flight.occupanyColName + "= '" + flight.getOccupancy() + "' WHERE "
					+ Flight.flightIDColName + "= '" + flight.getFlightID()) + "'";
			statement.execute(updateQuery);
		} catch (Exception e) {
			System.out.println("Editing failed");

		}
	}
}