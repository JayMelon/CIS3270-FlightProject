package Classes;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.UUID;

import Database.FlightDatabase;

public abstract class User {
	public String userID;
	public String userName;
	public String lastName;
	public String firstName;
	public String password;
	public String email;
	public String address;
	public String zipcode;
	public String state;
	public String socialSecurityNumber;
	public String securityQuestion;
	public String securityAnswer;
	public String userType = "C";

	public static final String databaseName = "Users";

//Checks user input and matches with password
	public boolean passwordCheck(String user, String password) {
		if (user.equals(password)) {
			return true;
		} else {
			return false;
		}
	}

//Creates random UserID return to String
	public void createNewUserID() {
		/* Generates random UUID(Universial Unique Identifer)
		 * String in format used as Primary Keys and Ids for DBS
		 * 123e4567-e89b-12d3-a456-426614174000
		 * xxxxxxxx-xxxx-Mxxx-Nxxx-xxxxxxxxxxxx
		 */
		this.userID = UUID.randomUUID().toString();
		System.out.println("Random USERID " + this.userID);
	}

//Checks for duplicated Username if Found returns true, else return false
	public boolean checkIfDuppedUserName() {
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
					return true;
				} else {
					// If Duplicate wasn't found this line.
					System.out.println("No duplicated user_name");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public String getUserID() {
		return userID;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getSocialSecurityNumber() {
		return socialSecurityNumber;
	}

	public void setSocialSecurityNumber(String socialSecurityNumber) {
		this.socialSecurityNumber = socialSecurityNumber;
	}

	public String getSecurityQuestion() {
		return securityQuestion;
	}

	public void setSecurityQuestion(String securityQuestion) {
		this.securityQuestion = securityQuestion;
	}

	public String getSecurityAnswer() {
		return securityAnswer;
	}

	public void setSecurityAnswer(String securityAnswer) {
		this.securityAnswer = securityAnswer;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

}
