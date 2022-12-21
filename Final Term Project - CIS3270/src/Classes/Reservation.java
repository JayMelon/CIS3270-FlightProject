package Classes;

public class Reservation {
public static final String databaseName = "Reservation";
public static final String flightIdColName = "flight_id";
public static final String userIdColName = "user_id";
public static final String orderId = "order_id";
public static int randomID() {
	return (int)(Math.random()*1000000);
}
public static void main(String[]arg) {
	
	System.out.println(randomID());
}

}
