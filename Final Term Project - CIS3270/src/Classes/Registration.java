package Classes;

import java.sql.*;

import Database.FlightDatabase;

public class Registration extends User {

//Going to create a method that registers people to database. 	
	public void registerUser() {
		Connection con = FlightDatabase.getConnect();
		// Sets the object with a random id;
		this.createNewUserID();
		// Creating Query Strings
		String insertFields = "INSERT INTO " + User.databaseName + " " + "(" + "user_id, " + "user_name, "
				+ "password, " + "first_name, " + "last_name, " + "email, "+ "address, " + "zipcode, " + "state, "
				+ "social_security_number, " + "security_question, " + "security_answer, " + "user_Type"+") " + "VALUES" + " ('";
		String insertValues = this.userID + "','" + this.userName + "','" + this.password + "','" + this.firstName
				+ "','" + this.lastName + "','" + this.email + "','" + this.address + "','" + this.zipcode + "','"
				+ this.state + "','" + this.socialSecurityNumber + "','" + this.securityQuestion + "','"
				+ this.securityAnswer + "','"+this.userType+"');";
		String insertToRegister = insertFields + insertValues;
		try {
			Statement statement = con.createStatement();
			statement.executeUpdate(insertToRegister);
			System.out.println("User has been registered successfully");

		} catch (Exception e) {
System.out.println("Register Failed");
e.printStackTrace();
		}
	}
}
