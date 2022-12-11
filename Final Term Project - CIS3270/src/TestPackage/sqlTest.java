package TestPackage;

import Classes.Registration;

import java.sql.SQLException;

public class sqlTest {

public static void main(String[]args) throws SQLException, ClassNotFoundException {
		Registration newUserTest = new Registration();
			newUserTest.registerUser();
		
	}
}
