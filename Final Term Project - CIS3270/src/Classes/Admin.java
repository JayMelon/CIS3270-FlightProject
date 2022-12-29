package Classes;

import java.sql.Connection;
import java.sql.PreparedStatement;

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
			e.printStackTrace();
	}
	
}

}