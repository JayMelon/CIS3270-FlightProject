package TestPackage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class sqlTest {

public static void main(String[]args) throws SQLException, ClassNotFoundException {
	//Loads the JDBC Driver
	//Class.forName("com.mysql.jdbc.Driver")l
	System.out.println("Driver Loaded");
	//Establish a Connection
	Connection con = DriverManager.getConnection
			("jdbc:sqlserver://flightappcis3270.database.windows.net:1433;database=CIS3270 - App project;user=jaylewis0618@flightappcis3270;password=FlashLight123!;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;");
	System.out.println("Connected");
	//Create a Statement
	PreparedStatement statement = con.prepareStatement("SELECT * FROM Person");
	// Execute a Statement
	ResultSet result = statement.executeQuery();
	//Iterate
	while(result.next()) {
		System.out.println(result.getString(1)+"\t" + result.getString(2)+"\t" +result.getString(3)+"\t"+result.getString(4));
	}
	}
}
