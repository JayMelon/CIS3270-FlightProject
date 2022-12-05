package Classes;
//Need to make smaller, Customer class is inherited by the User. 

public class Customer {
	public String firstName;
	public String lastName;
	private String Email;
	private String phoneNumber;
	private String Address;
	
	public Customer(String firstName, String lastName, String Email, String phoneNumber, String Address) {
		
		this.firstName = firstName;
		this.lastName = lastName;
		this.Email = Email;
		this.phoneNumber = phoneNumber;
		this.Address = Address;
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
	
	

	
}
