package TestPackage;
import java.util.Scanner;

import Classes.Customer;
public class customertest {

	
	//firstName
	//lastName
	//Email
	//phoneNumber
	//Address
	
	
	
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		//
		System.out.println("Welcome!!! please register to begin booking your next flight");
		System.out.println("");
		System.out.println("Enter your first name...");
		String firstName = input.next();
		System.out.println("Enter your last name...");
		String lastName = input.next();
		System.out.println("Enter your email...");
		String Email = input.next();
		System.out.println("Enter your phone number");
		String phoneNumber = input.next();
		System.out.println("Enter your address");
		String Address = input.next();
		
		
		
		
		
		
		
		
		
		Customer sarah = new Customer(firstName, lastName, Email, phoneNumber, Address); 
			
		System.out.println("Hello " + sarah.firstName + " " + sarah.lastName + " thank you for choosing us to book your flight with. "
				+ " to keep your information safe please create a username and password for your account.");
			
			
			
			
		
		
		
		
		
		
		
		
		
		
	}
	
	
	
}
