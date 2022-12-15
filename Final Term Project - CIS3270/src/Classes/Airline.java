package Classes;

public class Airline {
private String airlineID;
private String airlineName;
public String getAirlineID() {
	return airlineID;
}


public String generateID(Airline x) {
	 String newId = this.airlineName.substring(0,1);
	newId+=(int)(Math.random()*900)+100;
	return newId;
}

public static void main(String[]args) {
}
}
