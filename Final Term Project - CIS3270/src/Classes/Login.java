package Classes;

public class Login {
	

	private String userName;
	private String Password;
	public Customer customer;
	private int customerId;

	
	public Login(String userName, String Password, Customer customer, int customerID ) {
		
		this.userName = userName;
		this.Password = Password;
		this.customer =customer;
		this.customerId = customerId;
		
		
	}
	public void setuserName(String userName) {
		
		this.userName = userName;
	}
	public void setPassword(String Password) {
		
		this.Password = Password;
	}
	
	public void setcustomer(Customer customer) {
		
		this.customer = customer;
	}
	
	public void setcustomerId(int customerId) {
		
		this.customerId = customerId;
	}
	
	public String getuserName() {
		
		return userName;
	}
	
	public String getPassword() {
		
		return Password;
	}
	
	public Customer getcustomer() {
		return customer;
		
	}
	
	public int getcustomerId() {
		
		return customerId;
	}
	
	
	
	
	

}
