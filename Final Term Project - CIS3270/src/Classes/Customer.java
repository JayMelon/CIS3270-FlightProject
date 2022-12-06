package Classes;

import java.sql.Connection;
import java.sql.PreparedStatement;

import Database.FlightDatabase;

//Need to make smaller, Customer class is inherited by the User. 

public class Customer {
	private String CustomerID;
	private String firstName;
	private String lastName;
	private String Email;
	private String phoneNumber;
	private String Address;


	public Customer() {
		this.CustomerID = ""+(int)(Math.random()*1000);
	}

	public Customer(String firstName, String lastName, String Email, String phoneNumber, String Address) {

		this.firstName = firstName;
		this.lastName = lastName;
		this.Email = Email;
		this.phoneNumber = phoneNumber;
		this.Address = Address;
	}

	public String getCustomerID() {
		return CustomerID;
	}

	public void setCustomerID(String customerID) {
		CustomerID = customerID;
	}

	public void setfirstName(String firstName) {

		this.firstName = firstName;

	}

	public void setlastName(String lastName) {

		this.lastName = lastName;
	}

	public void setEmail(String Email) {

		this.Email = Email;
	}

	public void setphoneNumber(String phoneNumber) {

		this.phoneNumber = phoneNumber;
	}
	public String getAddress() {
		return this.Address;
	}
	public void setAddress(String Address) {

		this.Address = Address;
	}

	public String getfirstName() {

		return firstName;
	}

	public String getlastName() {

		return lastName;
	}

	public String getEmail() {

		return Email;
	}

	public String getphoneNumber() {
		return phoneNumber;
	}
	
	//Generates Query Statement
private String QueryGen
	(String custID
	,String lastName
	,String firstName
	,String email
	,String phoneNumber
	,String Address) {
	
	String Statement = "(CustomerID, LastName, FirstName, Email, Address, phoneNumber)"
			+ "VALUES ("+
			"'"+custID+"', "+
			"'"+lastName+"', "+
			"'"+firstName+"', "+
			"'"+email+"', "+
			"'"+Address+"', "+
			"'"+phoneNumber+"'"
			+");";
	System.out.println(Statement);
	
	return Statement;
}
	//Adds a Customer values 
public void addCustomer() throws Exception {
	String Tablename = "Customer";
	try {
		Connection con = FlightDatabase.getConnect();
		PreparedStatement posted = con.prepareStatement("INSERT INTO "+Tablename+QueryGen(
				getCustomerID(),
				getlastName(),
				getfirstName(),
				getEmail(),
				getphoneNumber(),
				getAddress())

		);
	posted.executeUpdate();
	System.out.println("Added Customers");
	}catch(Exception e) {
		System.out.println(e);
	}
	
	}
public static void main(String[]arg) throws Exception {
	Customer c1 = new Customer();
	c1.setAddress("Test 32");
	c1.setCustomerID((int)(Math.random()*100)+"");
	c1.setfirstName("Please3");
	c1.setEmail("Work@gmail.com");
	c1.setphoneNumber("911");
	c1.setlastName("Work");
	c1.addCustomer();
	
	
}
}
