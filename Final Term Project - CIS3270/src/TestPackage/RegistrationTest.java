package TestPackage;

import Classes.Registration;

public class RegistrationTest {
public static void main(String[]args) {
	//Registers user by assigning to Object and referencing from it. 
	Registration newUser = new Registration();
	newUser.userName = "JayMelon";
	newUser.password = "pass123";
	newUser.firstName = "Jalen";
	newUser.lastName = "Lewis";
	newUser.address = "123 circle str";
	newUser.email = "j@gmail.com";
	newUser.zipcode = "12345";
	newUser.state = "GA";
	newUser.socialSecurityNumber = "30080";
	newUser.securityQuestion = "What city were you born in?";
	newUser.securityAnswer = "ATL";
	//If there is no duplicate of username
	if(newUser.checkUserName()) {
		newUser.registerUser();
	}
}
}
