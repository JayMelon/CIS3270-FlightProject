package Classes;

public abstract class User {
	private String userID;
//Checks user input and matches with password
public boolean passwordCheck(String user,String password) {
	if (user.equals(password)){
		return true;
	}else {
		return false;
	}
}
public String getUserID() {
	return userID;
}
public void setUserID(String userID) {
	this.userID = userID;
}
}
