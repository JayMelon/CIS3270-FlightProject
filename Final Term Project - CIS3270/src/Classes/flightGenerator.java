package Classes;
import java.util.*;
import java.io.PrintStream;
import java.time.LocalTime;
import java.time.format.*;
import java.text.*;


public  class flightGenerator {

	Random num = new Random();
	 SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy"); 
	 
 public int flightId = num.nextInt(999);
	
	public String[]	airlines = {"Atlantis", "Flywings", "Icuras", "Aria air", "Runaway"};
	
	public String airlineName = airlines[num.nextInt(airlines.length)];
	
	public String departLocation; 
		
	
	

	public String arriveLocation;
	

	Random random = new Random();
	long seed2 = random.nextLong();
	Random generator2 = new Random(seed2);
	
	SimpleDateFormat dateFormat = new SimpleDateFormat("MM-d-yyyy");
	
	
		public String departDate;
		
		public String arriveDate;
		
		
		SimpleDateFormat timeform = new SimpleDateFormat("h:mm:a");
	
		public String arriveTime = timeform.format(seed2);
		
		
		
		
		
		
		
		public String departTime = timeform.format(seed2);
		
		
	
	//
	
	public int getFlightId() {
		return flightId;
	}


	public void setFlightId(int flightId) {
		this.flightId = flightId;
	}


	public String getAirlineName() {
		return airlineName;
	}


	public void setAirlineName(String airlineName) {
		this.airlineName = airlineName;
	}


	public String getDepartDate() {
		return dateFormat.format(departDate);
	}


	public void setDepartDate(String departDate) {
		this.departDate = departDate;
	}


	public String getarriveDate() {
		return dateFormat.format(arriveDate);
	}


	public void setarriveDate(String arriveDate) {
		this.arriveDate = arriveDate;
	}

		
		
		
		public String getarriveTime() {
			return arriveTime;
		}


		public void setarriveTime(String arriveTime) {
			this.arriveTime = arriveTime;
		}


		public String getDepartTime() {
			return departTime;
		}


		public void setDepartTime(String departTime) {
			this.departTime = departTime;
		}


		
		
		public flightGenerator() {
		}
		public flightGenerator(int flightId, String airlineName, String departLocation, String arriveLocation, String arriveDate, String departDate, Random arriveTime, Random departTime){
			
			
		}
		
		public flightGenerator(String departLocation, String arriveLocation,String departDate, String arriveDate){
			
		}
		
		
	
	
	
public static void main(String[] args) {
	flightGenerator f1 = new flightGenerator();
	f1.arriveDate = "12-10-2020";
	
	System.out.println(f1.arriveDate);
	
	
	
	
}




}
