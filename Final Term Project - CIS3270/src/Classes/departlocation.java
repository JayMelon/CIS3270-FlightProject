package Classes;
import java.util.*;
public class departlocation {

	private String Country;
	private String State;
	private String City;
	
	
	public departlocation(String Country,String City) {
		this.Country = Country;
		this.City = City;
		
	}
	public departlocation() {
		
	}
	
	public departlocation(String Country, String State, String City) {
		this.Country = Country;
		this.State = State;
		this.City = City;
		
		
	}
	
	public void setCountry(String Country) {
		
		this.Country = Country;
	}
	
	

	public void setState(String State) {
		
		this.State = State;
	}
	

	public void setCity(String City) {
		
		this.City = City;
	}
	
	public String getCountry() {
		return Country;
	}
	
	
	
	public String getState() {
		return State;
	}

	
	public String getCity() {
	
	
	return City;
}
	public static void add_departlocation(){
		
		departlocation d1 = new departlocation(null,null,null);
	Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter country of departure:");
		 d1.Country = sc.nextLine();
		
		System.out.println("Enter State of departure(if applicable):");
		d1.State = sc.nextLine();
		
		System.out.println("Enter City of departure:");
		 d1.City = sc.nextLine();
		
		 System.out.println(d1.City);
		
	}
	
	
	

	public static void main(String[] args) {
		
		
		add_departlocation();
		
		

	}
}
