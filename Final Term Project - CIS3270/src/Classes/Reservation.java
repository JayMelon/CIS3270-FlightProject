package Classes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import Database.FlightDatabase;
import gui.Main;

public class Reservation {
public ArrayList<Flight> userFlights;
public static final String databaseName = "Reservation";
public static final String flightIdColName = "flight_id";
public static final String userIdColName = "user_id";
public static final String orderId = "order_id";

//Time related method/functions


//Grabs the string and converts the time to hours
public static int hoursToInt(String time) {
	time = time.substring(0,2);
	time = time.replaceAll("0", "");
	int newInt = Integer.parseInt(time);
	return newInt;
}

//Returns Date with hours added
public static Date getDateFromHoursAway(Date startingDate, int hours) {
    long startingMillis = startingDate.getTime();
    // Resolves the current ms/second/minute/hour/day added by some amount
    long currentDay = startingMillis / 1000 / 60 / 60;
    long futureTimeMillis = (currentDay + hours) * 60 * 60 * 1000;
    return new Date(futureTimeMillis);
}
//Converts Date yyyy-dd-mm to yyyy/MM/dd in a DateObject
public static Date convertToDate(String date) throws Exception {
	date = FlightDatabase.convertFromSql(date);
	Date newdate = new SimpleDateFormat("yyyy/MM/dd").parse(date);
	return newdate;
}
//Checks time and date to see if the time is between the Departure and Arrival Date.
public static boolean checkTimeConflict(String date1, String time1, String date2, String time2, String dateCheck, String timeCheck) throws Exception {
	//Converting sql date to format for Date Class, Times to hours
	Date minDate = Reservation.convertToDate(date1);
	Date maxDate = Reservation.convertToDate(date2);
	int minTime = Reservation.hoursToInt(time1);
	int maxTime = Reservation.hoursToInt(time2);
	Date currentDate = Reservation.convertToDate(dateCheck);
	int currentTime = Reservation.hoursToInt(timeCheck);
	currentDate = Reservation.getDateFromHoursAway(currentDate, currentTime);
	minDate = Reservation.getDateFromHoursAway(minDate, minTime);
	maxDate = Reservation.getDateFromHoursAway(maxDate, maxTime);
	System.out.println(minDate.getTime());
	System.out.println(currentDate.getTime());
	System.out.println(maxDate.getTime());
	if((currentDate.getTime() >= minDate.getTime()) && (currentDate.getTime()<= maxDate.getTime())) {
		return true;
} else {
		return false;
	}
}




//Creates a random Order ID that goes to DBS;
public static int randomID() {
	return (int)(Math.random()*1000000);
}
//Need to work on to hold current user information 
public Reservation(String userID) {
	new ArrayList<Flight>();
	try {
		Connection con = FlightDatabase.getConnect();
		String query = "Select ("+flightIdColName+") from "+databaseName+" where "+userIdColName+"= '"+userID+"'";
		
		
		
	}catch(Exception e) {
		e.printStackTrace();
	}
}
//Need to work on - Need to have it refer to an Array.
private static void incrementOccupancy(String flightID) {
	Connection con = FlightDatabase.getConnect();
	String query = "Select ("+Flight.occupanyColName+") from FlightData where " +Flight.flightIDColName+"= '"+flightID+"'";
	try {
		Statement statement = con.createStatement();
		ResultSet queryResult = statement.executeQuery(query);
		while (queryResult.next()) { 
			int x = queryResult.getInt(1);
			System.out.println(x);
			}
	}catch(Exception e) {
		System.out.println("Failed while incrementing");
		e.printStackTrace();
	}	
}

//Need to work on - Need to have it refer to an Array.
public static boolean checkduplicatedFlight(String userID, String flightID) {
	Connection con = FlightDatabase.getConnect();
	String query = "SELECT COUNT(flight_id) FROM Reservation Where flight_id = '"+flightID+"' AND user_id = '"+userID+"'";
	try {
		Statement statement = con.createStatement();
		ResultSet queryResult = statement.executeQuery(query);
		while (queryResult.next()) { 
			if(queryResult.getInt(1)>=1) {
				System.out.println("User has a dupilcated flight");
				return true;
			}
		}
	}catch(Exception e) {
		System.out.println("Failed while checking dupilcatedFlight");
		e.printStackTrace();
	}
	System.out.println("User hasn't booked this flight");
	return false;
}


//Adds flight
public static void bookFlight(String userid, String flightID) {
	String Query = "INSERT INTO Reservation("
			+Reservation.orderId+","
			+Reservation.flightIdColName+","
			+Reservation.userIdColName+")"
			+" Values"+"('"+Reservation.randomID()+"','"
			+flightID+"','"+userid+"')";
	System.out.println(Query);
	try {
		Connection con = FlightDatabase.getConnect();
		PreparedStatement posted = con.prepareStatement(Query);
		posted.executeUpdate();
		System.out.println(Query);
		System.out.println(Main.user+" has successfully booked");
	}catch(Exception e){
		
		e.printStackTrace();
	}
}

public static void main(String[]arg) throws Exception {

}

}
