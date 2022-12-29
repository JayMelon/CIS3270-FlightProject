package Classes;

/*
 * Class that are related to the login & forgot password Methods and such..
 */
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Database.FlightDatabase;
import gui.Main;

public class Login extends User {

//Validates login by checking Database for specific username and password - Jay
	public void validateLogin(String username, String pass) {
		List<String> userData = new ArrayList<String>();
		Connection con = FlightDatabase.getConnect();
		// Creates a string of a query replace asking to SELECT a count of 1 where
		// usertext and password text equal - Jay
		String verifyLogin = "SELECT * from " + User.databaseName + " Where user_name = '" + username
				+ "'";
		try {
			Statement statement = con.createStatement();
			ResultSet queryResult = statement.executeQuery(verifyLogin);
			while (queryResult.next()) { 
				userData.add(queryResult.getString("user_id"));
				userData.add(queryResult.getString("user_name"));
				userData.add(queryResult.getString("password"));
				userData.add(queryResult.getString("user_Type"));
			} if(pass.equals(userData.get(2))) {
				//If entered password equals to the User
				Main.user = userData.get(1);
				System.out.println(Main.user+" has logged in");
				System.out.println(userData);
				//Sets the main to know that the userData.
				Main.userType = userData.get(3); 
				Main.userID = userData.get(0);
				if(Main.userType.equals("A")) 
					Main.userType = "[Admin]";
				else
					Main.userType = "[Customer]";
				
			}
			else {
				System.out.println("Failed");
			}
		} catch (Exception e) {
			System.out.println("User Failed to login");;
		}

	}
//Checks if Username is Founded, If founded return True, else false. 
	public boolean checkCurrentUserName() {
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

//Gets the requested user's security question. 
	public String getCurrentUserSecurityQuestion() {
		Connection con = FlightDatabase.getConnect();
		String getSecurityQuestion = "SELECT security_question FROM " + User.databaseName + " WHERE user_name = '"
				+ this.userName + "';";
		try {
			Statement statement = con.createStatement();
			ResultSet queryResult = statement.executeQuery(getSecurityQuestion);

			while (queryResult.next()) {
				return queryResult.getString("security_question");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

//Gets the requested user's security answer.
	public String getCurrentUserSecurityAnswer() {
		Connection con = FlightDatabase.getConnect();
		String getSecurityAnswer = "SELECT security_answer FROM " + User.databaseName + " WHERE user_name = '"
				+ this.userName + "';";
		try {
			Statement statement = con.createStatement();
			ResultSet queryResult = statement.executeQuery(getSecurityAnswer);

			while (queryResult.next()) {
				return queryResult.getString("security_answer");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

//Get the requested user's security answer. 
	public String getCurrentUserPassword() {
		Connection con = FlightDatabase.getConnect();
		String getPassword = "SELECT password FROM " + User.databaseName + " WHERE user_name = '" + this.userName
				+ "';";
		try {
			Statement statement = con.createStatement();
			ResultSet queryResult = statement.executeQuery(getPassword);

			while (queryResult.next()) {
				return queryResult.getString("password");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) {
		Login user = new Login();
		user.validateLogin("JayMelon","pass123");
		}
	}

