package Classes;
/*
 * Class that are related to the login, Methods and such..
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
public static void validateLogin(String username,String pass) {
	Connection con = FlightDatabase.getConnect();
	// Creates a string of a query replace asking to SELECT a count of 1 where usertext and password text equal - Jay
	String verifyLogin = "SELECT Count(1) from "+User.databaseName+" Where user_name = '"+username+"' AND password = '"+pass+"';";
	try {
		Statement statement = con.createStatement();
		ResultSet queryResult = statement.executeQuery(verifyLogin);
		while(queryResult.next()) {
			if(queryResult.getInt(1) == 1) {
				//If Login was validated execute this line.
				Main.userType = "Customer";
				Main.user = username;
				System.out.println(Main.userType + Main.user + " has logged in!");
			}else {
				//If login wasn't validated execute this line.
				LoginController.loginFailedLabel.setText("Please enter a correct username and password combination");
				System.out.println(Main.userType + Main.user + " failed to login");
			}
		}
	} catch(Exception e) {
		e.printStackTrace();
	}
}

public static void main(String[]args) {
	validateLogin("JayMelon","pass23");
}
}
