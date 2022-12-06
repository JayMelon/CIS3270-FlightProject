package Classes;

public class Airline {
private String airlineID;
private String airlineName;
public String getAirlineID() {
	return airlineID;
}
public void setAirlineID(String airlineID) {
	this.airlineID = airlineID;
}
public void setAirlineName(String airlineName) {
	this.airlineName = airlineName;
}

public String generateID(Airline x) {
	 String newId = this.airlineName.substring(0,1);
	newId+=(int)(Math.random()*900)+100;
	return newId;
}

public static void main(String[]args) {
	Airline air1 = new Airline();
	air1.setAirlineName("Elite");
	System.out.println(air1.generateID(air1));
}
}
