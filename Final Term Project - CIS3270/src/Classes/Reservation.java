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
	//Holds Current user's flightIDs
	public ArrayList<Reservation> userFlightIds = new ArrayList<Reservation>();
	//Holds holds user's FlightData(such as FlightID, Flight toCity,FromCity, Times, Occupancy, etc.
	public ArrayList<Flight> userFlightData = new ArrayList<Flight>();
	public static final String databaseName = "Reservation";
	public static final String flightIdColName = "flight_id";
	public static final String userIdColName = "user_id";
	public static final String orderIdColName = "order_id";

	public String orderID;
	public String flightID;
	public String userID;

//Getters/Setters
	public String getOrderID() {
		return orderID;
	}

	public void setOrder_id(String order_id) {
		this.orderID = order_id;
	}

	public String getFlightID() {
		return flightID;
	}

	public void setFlightID(String flight_id) {
		this.flightID = flight_id;
	}

	public String getUserID() {
		return userID;
	}

	public void setUser_id(String user_id) {
		this.userID = user_id;
	}
//Time related method/functions
//----------------------------------------------------------------------------------------
//Grabs the string and converts the time to hours
	public static int hoursToInt(String time) {
		time = time.substring(0, 2);
		if(time.contentEquals("20") || time.contentEquals("10")) {
		}
		if(time.contentEquals("00")) {
				time = time.substring(0,1);
		}else {
			time = time.replaceAll("0", "");	
		}
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
//----------------------------------------------------------------------------------------
//Creates a random Order ID that goes to DBS;
	public static int randomID() {
		return (int) (Math.random() * 1000000);
	}
//Contructor
	public Reservation(String user_id) {
	this.userID = user_id;
	}
	
	
//Returns an array of UserFlight Ids
	public ArrayList<Reservation> getUserFlightIDs(){
		try {
			Reservation userData;
			// Adds all FlightID for object to hold.
			Connection con = FlightDatabase.getConnect();
			String query = "Select " + Reservation.flightIdColName + " from " + databaseName + " where " + userIdColName
					+ "= '" + this.userID + "'";
			Statement statement = con.createStatement();
			ResultSet queryResult = statement.executeQuery(query);
			while (queryResult.next()) {
				userData = new Reservation(userID);
				userData.setFlightID(queryResult.getString(flightIdColName));
				this.userFlightIds.add(userData);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return this.userFlightIds;
	}
	
//Returns an array of Flight for the current user to hold.
	
	
//Gets FlightData based on the User's Reserved flights in Reservation DBS
	public ArrayList<Flight> getUserFlightData(){
		//Setting up counter to add flights.
		int amountOfFlights = this.userFlightIds.size();
		Flight flight;
		try {
		Connection con = FlightDatabase.getConnect();
		for(int i = 0;i<amountOfFlights;i++) {
			String flightID = this.getUserFlightIDs().get(i).getFlightID();
			String query = "Select * from "+Flight.databaseName+" Where "+Flight.flightIDColName+"= '"+flightID+"'";
			Statement statement = con.createStatement();
			ResultSet result = statement.executeQuery(query);
		//Creating new flight object 
			while(result.next()) {
				flight= new Flight(
					this.getUserFlightIDs().get(i).getFlightID(),
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
				this.userFlightData.add(flight);
			}
		}
	}catch(Exception e) {
		e.printStackTrace();
	}
		return this.userFlightData;
		
	}
	
// Checks if the user has flights returns true if found flight_ids are greater than 1
	public boolean hasFlights() {
		int x = 0;
		try {
			//Gets count of booked flights from reservation Table
			Connection con = FlightDatabase.getConnect();
			String query = "Select count ("+Reservation.flightIdColName+") from "+ databaseName + " where " + userIdColName + "= '" + this.userID + "'";
			Statement statement = con.createStatement();
			ResultSet queryResult = statement.executeQuery(query);
			while (queryResult.next()) {
				x = queryResult.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(x >= 1) {
			System.out.println("User has flights");
			return true;
		}
		System.out.println("User is hasn't booked any flights");
		return false;
	}
	
	//Takes current occupancy of the selected flight and increments by one
	private void incrementOccupancy(String flightID,int newOccupancy) {
		newOccupancy++;
		Connection con = FlightDatabase.getConnect();
		String update = "UPDATE "+Flight.databaseName+" SET "+ Flight.occupanyColName +" = '"+newOccupancy+"'  WHERE "+Flight.flightIDColName+"= '"+flightID+"'";
		String query = "Select * from FlightData where FlightID = '"+flightID+"'";
		try {
			
			Statement statement = con.createStatement();
			statement.executeUpdate(update);
			ResultSet result = statement.executeQuery(query);
			while(result.next()) {
				System.out.println("Update Occupancy From "+--newOccupancy+" to "+result.getInt(Flight.occupanyColName));
			}
		} catch (Exception e) {

		}
	}
	
	//Takes current occupancy of the selected flight and decreases by one
		private void decrementOccupancy(String flightID,int newOccupancy) {
			newOccupancy--;
			Connection con = FlightDatabase.getConnect();
			String update = "UPDATE "+Flight.databaseName+" SET "+ Flight.occupanyColName +" = '"+newOccupancy+"'  WHERE "+Flight.flightIDColName+"= '"+flightID+"'";
			String query = "Select * from FlightData where FlightID = '"+flightID+"'";
			try {
				
				Statement statement = con.createStatement();
				statement.executeUpdate(update);
				ResultSet result = statement.executeQuery(query);
				while(result.next()) {
					System.out.println("Updated Occupancy "+(++newOccupancy)+" To "+result.getInt(Flight.occupanyColName));
				}
			} catch (Exception e) {
				System.out.println("Error occured while updating Occupancy");
			}
		}
	
	//Checks time and date to see if the time is between the (min)Departure and Arrival Date(Max).	
public boolean checkTimeConflict(String departDate, String departTime, String arrivalDate, String arrivalTime, String selectedDate,
				String selectedTime) throws Exception {
			// Converting sql date to format for Date Class, Times to hours
			Date minDate = Reservation.convertToDate(departDate);
			Date maxDate = Reservation.convertToDate(arrivalDate);
			int minTime = Reservation.hoursToInt(departTime);
			int maxTime = Reservation.hoursToInt(arrivalTime);
			Date currentDate = Reservation.convertToDate(selectedDate);
			int currentTime = Reservation.hoursToInt(selectedTime);
			currentDate = Reservation.getDateFromHoursAway(currentDate, currentTime);
			minDate = Reservation.getDateFromHoursAway(minDate, minTime);
			maxDate = Reservation.getDateFromHoursAway(maxDate, maxTime);
			if ((currentDate.getTime()>=minDate.getTime())&&(currentDate.getTime()<=maxDate.getTime())){
				return true;
			} else {
				return false;
			}
		}
/*Goes into DBS checks the User's flightID, DBS gets the times based on the FlightID and checks the times and compares to given time. 
Takes a long time based on how many flights the user has booked. 
*/
public boolean userHasTimeConflict(String selectedDate, String selectedTime) {
	try {
		
	Connection con = FlightDatabase.getConnect();
	int count = this.userFlightIds.size();
	for(int i = 0;i<count;i++) {
		//Get user's flight dates and times to check conflict
		String query = "SELECT arrivalDate, arrivalTime, DepartureDate, DepartureTime from FlightData Where FlightID = '"+this.userFlightIds.get(i).getFlightID()+"'";
		Statement statement = con.createStatement();
		ResultSet result = statement.executeQuery(query);
		while(result.next()) {
			if(this.checkTimeConflict(result.getString(Flight.departDateColName), 
					result.getString(Flight.departTimeColName), result.getString(Flight.arrivalDateColName), 
					result.getString(Flight.arrivalTimeColName), selectedDate, 
					selectedTime)) {
				System.out.println("Time Conflict is found.");
				return true;
			}
		}
	}
	}catch(Exception e){
		e.printStackTrace();
	}
	return false;
}		
		
/*Takes a userid and FLight id and retrives a count of booked flights with that flight id.
 * If the count of flightID is 1 or higher returns TRUE;
*/
	public static boolean checkduplicatedFlight(String userID, String flightID) {
		Connection con = FlightDatabase.getConnect();
		String query = "SELECT COUNT(flight_id) FROM Reservation Where flight_id = '" + flightID + "' AND user_id = '"
				+ userID + "'";
		try {
			Statement statement = con.createStatement();
			ResultSet queryResult = statement.executeQuery(query);
			while (queryResult.next()) {
				if (queryResult.getInt(1) >= 1) {
					System.out.println(Main.user+"has a dupilcated flight");
					return true;
				}
			}
		} catch (Exception e) {
			System.out.println("Failed while checking dupilcatedFlight");
		}
		System.out.println(Main.user+" hasn't booked this flight");
		return false;
	}

/*
 * Takes userID, FlightID, Occupancy, Adds to the reservation table while increasing the Occupancy by 1
 */
	public void bookFlight(String userid, String flightID,int occupancy) {
		String Query = "INSERT INTO Reservation(" + Reservation.orderIdColName + "," + Reservation.flightIdColName + ","
				+ Reservation.userIdColName + ")" + " Values" + "('" + Reservation.randomID() + "','" + flightID + "','"
				+ userid + "')";
		try {
			Connection con = FlightDatabase.getConnect();
			//Increase the current occupancy of the flight by 1
			this.incrementOccupancy(flightID, occupancy);
			PreparedStatement posted = con.prepareStatement(Query);
			posted.executeUpdate();
			System.out.println(Main.user + " has successfully booked");
		} catch (Exception e) {

			e.printStackTrace();
		}
	}
	/*
	 * Takes userID, FlightID, Occupancy, Delete the reservation table while decreasing the Occupancy by 1
	 */
	public void cancelFlight(String userid, String flightID,int occupancy) {
		String Query = "DELETE FROM "+Reservation.databaseName+" WHERE "+Reservation.flightIdColName+" = '" + flightID+"'";
		try {
			Connection con = FlightDatabase.getConnect();
			//Decrease the current occupancy of the flight by 1
			this.decrementOccupancy(flightID, occupancy);
			PreparedStatement posted = con.prepareStatement(Query);
			posted.executeUpdate();
			System.out.println(Main.user + " has successfully cancelled booking flight "+flightID);
		} catch (Exception e) {
			
		}
	}



}

