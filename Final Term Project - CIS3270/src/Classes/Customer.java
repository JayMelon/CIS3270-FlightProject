package Classes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import Database.FlightDatabase;
//Need to make smaller, Customer class is inherited by the User. 

public class Customer extends User {
//Generates random UserID, Not sure if it's useful or not - Jay 
	public Customer() {
		this.userID = "" + (int) (Math.random() * 1000);
	}

	public Customer(String firstName, String lastName, String Email, String phoneNumber, String Address) {
	}

	// Generates Query Statement - Jay (This is probably not useful since we know I know how to pull specifics from DBS now) - Jay
	private String QueryGen(String custID, String lastName, String firstName, String email, String phoneNumber,
			String Address) {

		String Statement = "(CustomerID, LastName, FirstName, Email, Address, phoneNumber)" + "VALUES (" + "'" + custID
				+ "', " + "'" + lastName + "', " + "'" + firstName + "', " + "'" + email + "', " + "'" + Address + "', "
				+ "'" + phoneNumber + "'" + ");";
		System.out.println(Statement);

		return Statement;
	}

	// Inserts a Users value into Users DBS - Jay
	public void addUser() throws Exception {
		String Tablename = User.databaseName;
		try {
			Connection con = FlightDatabase.getConnect();
			// Needs to add the textfinder from the GUI into this, I'll update soon. - Jay
			PreparedStatement posted = con.prepareStatement("INSERT INTO " + User.databaseName + "");
			posted.executeUpdate();
			System.out.println("Added Customers");
		} catch (Exception e) {
			System.out.println(e);
		}

	}
}
