package Classes;

import java.sql.*;

import Database.FlightDatabase;

public class Registration extends User {

	
//Going to create a method that registers people to database. 	
	public void registerUser() {
		//DELETE THIS LINE WHEN YOU INSERT THE REFERENCES Jackson - JL
		String temp = "null";
		Connection con = FlightDatabase.getConnect();
		//Sets the object with a random id;
		this.createNewUserID();
		//Once textfields are created replace them with temp Jackson - JL
		this.userName = temp;
		this.lastName = temp;
		this.firstName = temp;
		this.email = temp;
		this.address = temp;
		this.zipcode = temp;
		this.phoneNumber = temp;
		this.socialSecurityNumber = temp;
		this.securityQuestion = 0;
		this.securityAnswer = temp;
		//Creating Query Strings
		String insertFields = "INSERT INTO "+User.databaseName+" "
				+ "("
				+ "user_id, "
				+ "user_name, "
				+ "password, "
				+ "last_name, "
				+ "first_name, "
				+ "email, address, "
				+ "zipcode, "
				+ "phone_number, "
				+ "social_security_number, "
				+ "security_question, "
				+ "security_answer"
				+ ") "
				+"VALUES"
				+" ('";
		String insertValues = 
		this.userID + "','" 
		+ this.userName + "','" 
		+ this.password + "','"
		+ this.firstName + "','"
		+ this.lastName + "','"
		+ this.email + "','" 
		+ this.address + "','" 
		+ this.zipcode + "','" 
		+ this.phoneNumber + "','" 
		+ this.socialSecurityNumber + "','" 
		+ this.securityQuestion + "','" 
		+ this.securityAnswer + "');";
		String insertToRegister = insertFields + insertValues;
		//Checks this current object for a duplicate id.
		this.checkDuplicateID();
		//Checks this current object for a duplicate username
		this.checkUserName();
		try {
			Statement statement = con.createStatement();
			statement.executeUpdate(insertToRegister);
			System.out.println("User has been registered successfully");
			
		}catch(Exception e) {
			e.printStackTrace();
			e.getCause();
		}
	}
	public static void main(String[]args) throws Exception {
		Registration c1 = new Registration();
		c1.registerUser();
		
	}

}



