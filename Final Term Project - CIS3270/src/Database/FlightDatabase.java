package Database;
import java.sql.*;

import Classes.Customer;
public class FlightDatabase {
// Connection method to DBS
public static Connection getConnect() {
	try {
		Connection con = DriverManager.getConnection
				("jdbc:sqlserver://flightappcis3270.database.windows.net:1433;database=CIS3270 - App project;user=jaylewis0618@flightappcis3270;password=FlashLight123!;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;");
			System.out.println("Connected");
		return con;
	} catch (SQLException e) {
		e.printStackTrace();
		return null;
	}
	
}
//Creates a Table
public static void createTable(String tablename) throws Exception{
	try {
		Connection con = getConnect();
		//Preparing Statement
		PreparedStatement create = con.prepareStatement("CREATE TABLE "+tablename+" (FirstName varchar(255), LastName varchar(255))");
		//Executing
		create.executeUpdate();
		System.out.println("Execution of "+con+" Completed");
	}catch(Exception e) {
		System.out.println(e); 
	}
	System.out.println("Completed");
}
//Drop a table
public static void DropTable(String tablename) throws Exception{
	try {
		Connection con = getConnect();
		//Preparing Statement
		PreparedStatement create = con.prepareStatement("DROP TABLE "+tablename);
		//Executing
		create.executeUpdate();
		System.out.println("Execution of "+con+" Completed");
	}catch(Exception e) {
		System.out.println(e); 
	}
	System.out.println("Completed");
}
//Insert Table stuff
public static void Insert(String tablename, String Columns, String values ) throws Exception{
	String Tablename = tablename;
	try {
		Connection con = getConnect();
		PreparedStatement posted = con.prepareStatement("INSERT INTO "+Tablename+"("
		+Columns+")"+"VALUES ("+values+")");
	posted.executeUpdate();
	System.out.println("Insertion succeed");
	}catch(Exception e){
		System.out.println(e);
		/*
		 * 	FlightDatabase.Insert(
			//Table name
			"Person",
			//Columns
			"LastName, FirstName, Address, City",
			//Rows 
			"'Thomas', 'Good', '32 Arborae Cir', 'Atlanta'"
		 */
	}

}
//Delete Row
public static void Delete(String tablename, String column, String value) throws Exception {
	try {
		Connection con = getConnect();
		PreparedStatement posted = con.prepareStatement("DELETE FROM "+tablename
		+" WHERE "+column+"= "+"'"+value+"';");
	posted.executeUpdate();
	System.out.println("Delete succeed");
	}catch(Exception e){
		System.out.println(e);
	}

}	



}
