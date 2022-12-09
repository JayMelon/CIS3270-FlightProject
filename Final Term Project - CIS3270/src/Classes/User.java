package Classes;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import Database.FlightDatabase;

public abstract class User {
	protected String userID;
	protected String userName;
	protected String lastName;
	protected String firstName;
	protected String email;
	protected String address;
	protected String zipcode;
	protected String phoneNumber;
	protected String socialSecurityNumber;
	protected String securityQuestion;
	protected String securityAnswer;
	public static final String databaseName = "Users";
	
//Checks user input and matches with password
public boolean passwordCheck(String user,String password) {
	if (user.equals(password)){
		return true;
	}else {
		return false;
	}
}

//Creates random UserID return int to String
public void createNewUserID() {
	//Generates random number 100-999 - Jay
	int x = (int)((Math.random()*999)+100);
	//Transfer to String
	this.userID = x+"";
}



//Checks User DBS for Duplicate User_ID
protected void checkDupicateID() {
	Connection con = FlightDatabase.getConnect();
	String verifyLogin = "SELECT Count(1) from "+User.databaseName+" Where user_id = '"+this.userID+"';";
	try {
		Statement statement = con.createStatement();
		ResultSet queryResult = statement.executeQuery(verifyLogin);
		while(queryResult.next()) {
			if(queryResult.getInt(1) == 1) {
				//If Duplicate was found execute this line.
				System.out.println("Duplicate user_id found");
			}else {
				//If Duplicate wasn't found this line.
				System.out.println("No duplicated user_id");
			}
		}
	} catch(Exception e) {
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
