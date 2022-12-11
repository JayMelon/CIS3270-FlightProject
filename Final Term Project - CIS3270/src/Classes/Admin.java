package Classes;

public class Admin extends User  {

	//Check if User is admin
public boolean checkAdmin() {
	if((this.userID).equals("A")) {
		return true;
	}else {
		return false;
	}
		
}
}

