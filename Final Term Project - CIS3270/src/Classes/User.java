package Classes;

public abstract class User {
	public String firstName;
	public String lastName;
	private String Email;
	private String phoneNumber;
	private String Address;

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
