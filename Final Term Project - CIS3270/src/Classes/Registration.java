package Classes;

import java.sql.*;

import Database.FlightDatabase;

public class Registration extends User {

	
//Going to create a method that registers people to database. 	
	public void registerUser(
			String userName,
			String lastName,
			String firstName,
			String email,
			String address,
			String zipcode,
			String phoneNumber,
			String SSN,
			String securityAnswer
			) {
		Connection con = FlightDatabase.getConnect();
		//Sets the object with a random id;
		this.createNewUserID();
		//Once textfields are created replace them with temp Jackson - JL
		this.userName = userName;
		this.lastName = lastName;
		this.firstName = firstName;
		this.email = email;
		this.address = address;
		this.zipcode = zipcode;
		this.phoneNumber = phoneNumber;
		this.socialSecurityNumber = SSN;
		this.securityQuestion = 0;
		this.securityAnswer = securityAnswer;
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



