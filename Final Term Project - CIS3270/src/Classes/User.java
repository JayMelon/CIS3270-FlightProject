package Classes;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.UUID;

import Database.FlightDatabase;

public abstract class User {
	protected String userID;
	protected String userName;
	protected String lastName;
	protected String firstName;
	protected String password;
	protected String email;
	protected String address;
	protected String zipcode;
	protected String phoneNumber;
	protected String socialSecurityNumber;
	protected int securityQuestion;
	protected String securityAnswer;
	public static final String databaseName = "Users";

//Checks user input and matches with password
	public boolean passwordCheck(String user, String password) {

		UUID.randomUUID();
		if (user.equals(password)) {
			return true;
		} else {
			return false;
		}
	}

//Creates random UserID return int to String
	public void createNewUserID() {
		// Generates random UUID
		this.userID = UUID.randomUUID().toString();
		System.out.println("Random USERID " + this.userID);
	}


	protected void checkUserName() {
		Connection con = FlightDatabase.getConnect();
		String verifyLogin = "SELECT Count(1) from " + User.databaseName + " Where user_name = '" + this.userName
				+ "';";
		try {
			Statement statement = con.createStatement();
			ResultSet queryResult = statement.executeQuery(verifyLogin);
			while (queryResult.next()) {
				if (queryResult.getInt(1) >= 1) {
					// If Duplicate was found execute this line.
					System.out.println("Duplicate user_name found");
				} else {
					// If Duplicate wasn't found this line.
					System.out.println("No duplicated user_name");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getSecurity_answer() {
		return securityAnswer;
	}

	public void setSecurity_answer(String security_answer) {
		this.securityAnswer = security_answer;
	}
}
