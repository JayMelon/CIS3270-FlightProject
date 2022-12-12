package Classes;

/*
 * Class that are related to the login & forgot password Methods and such..
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import Database.FlightDatabase;
import gui.LoginController;
import gui.Main;

public class Login extends User {

//Validates login by checking Database for specific username and password - Jay
	public static void validateLogin(String username, String pass) {
		Connection con = FlightDatabase.getConnect();
		// Creates a string of a query replace asking to SELECT a count of 1 where
		// usertext and password text equal - Jay
		String verifyLogin = "SELECT Count(1) from " + User.databaseName + " Where user_name = '" + username
				+ "' AND password = '" + pass + "';";
		try {
			Statement statement = con.createStatement();
			ResultSet queryResult = statement.executeQuery(verifyLogin);
			while (queryResult.next()) {
				if (queryResult.getInt(1) == 1) {
					// If Login was validated execute this line.
					Main.userType = "[Customer]";
					Main.user = username;
					System.out.println(Main.userType + Main.user + " has logged in!");
				} else {
					// If login wasn't validated execute this line.
					System.out.println(Main.userType + Main.user + " failed to login");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

//Checks if Username is Founded, If founded return True, else false. 
	public boolean checkUserName() {
		Connection con = FlightDatabase.getConnect();
		String verifyLogin = "SELECT Count(1) from " + User.databaseName + " Where user_name = '" + this.userName
				+ "';";
		try {
			Statement statement = con.createStatement();
			ResultSet queryResult = statement.executeQuery(verifyLogin);
			while (queryResult.next()) {
				if (queryResult.getInt(1) >= 1) {
					// If username was found execute this line.
					System.out.println("Record of " + this.userName + " was found");
					return true;
				} else {
					// If username wasn't found.
					System.out.println("Record not found");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public static void main(String[] args) {
		Registration User = new Registration();
		User.userName = "JayMelon";
		System.out.println(User.checkUserName());
	}
}
