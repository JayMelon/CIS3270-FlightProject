package Classes;
import java.sql.*;
import java.util.*;


import Database.FlightDatabase;


public class Flight extends Airline {
public static final String databaseName = "Flight";
private String flightID;
private String departCountry;
private String departCity;
private String departState;
private String departureTime;
private String departureDate;
private String arrivalCountry;
private String arrivalCity;
private String arrivalState;
private String arrivalTime;
private String arrivalDate;

private int seats;
private int capacity;
public String[] flightsCode = {"ATL","PEK","LHR","ORD","HND","LAX","CDG","DFW","FRA","HKG","DEN","DXB","CGK","AMS","MAD",
		"BKK","JFK","SIN","CAN","LAS","PVG","SFO","PHX","IAH","CLT","MIA","MUC","KUL","FCO","IST","SYD","MCO","ICN",
		"DEL","BCN","LGW","EWR","YYZ","SHA","MSP","SEA","DTW","PHL","BOM","GRU","MNL","CTU","BOS","SZX","MEL","NRT",
		"ORY","MEX","DME","AYT","TPE","ZRH","LGA","FLL","IAD","PMI","CPH","SVO","BWI","KMG","VIE","OSL","JED","BNE",
		"SLC","DUS","BOG","MXP","JNB","ARN","MAN","MDW","DCA","BRU","DUB","GMP","DOH","STN","HGH","CJU","YVR","TXL",
		"SAN","TPA","CGH","BSB","CTS","XMN","RUH","FUK","GIG","HEL","LIS","ATH","AKL"};	
public String[] flightsCity = {"ATLANTA GA, US (ATL)","BEIJING, CN (PEK)","LONDON, GB (LHR)","CHICAGO IL, US (ORD)","TOKYO, JP (HND)",
		"LOS ANGELES CA, US (LAX)","PARIS, FR (CDG)","DALLAS/FORT WORTH TX, US (DFW)","FRANKFURT, DE (FRA)","HONG KONG, HK (HKG)",
		"DENVER CO, US (DEN)","DUBAI, AE (DXB)","JAKARTA, ID (CGK)","AMSTERDAM, NL (AMS)","MADRID, ES (MAD)","BANGKOK, TH (BKK)",
		"NEW YORK NY, US (JFK)","SINGAPORE, SG (SIN)","GUANGZHOU, CN (CAN)","LAS VEGAS NV, US (LAS)","SHANGHAI, CN (PVG)",
		"SAN FRANCISCO CA, US (SFO)","PHOENIX AZ, US (PHX)","HOUSTON TX, US (IAH)","CHARLOTTE NC, US (CLT)","MIAMI FL, US (MIA)",
		"MUNICH, DE (MUC)","KUALA LUMPUR, MY (KUL)","ROME, IT (FCO)","ISTANBUL, TR (IST)","SYDNEY, AU (SYD)","ORLANDO FL, US (MCO)",
		"INCHEON, KR (ICN)","NEW DELHI, IN (DEL)","BARCELONA, ES (BCN)","LONDON, GB (LGW)","NEWARK NJ, US (EWR)","TORONTO ON, CA (YYZ)",
		"SHANGHAI, CN (SHA)","MINNEAPOLIS MN, US (MSP)","SEATTLE WA, US (SEA)","DETROIT MI, US (DTW)","PHILADELPHIA PA, US (PHL)",
		"MUMBAI, IN (BOM)","SÃO PAULO, BR (GRU)","MANILA, PH (MNL)","CHENGDU, CN (CTU)","BOSTON MA, US (BOS)","SHENZHEN, CN (SZX)",
		"MELBOURNE, AU (MEL)","TOKYO, JP (NRT)","PARIS, FR (ORY)","MEXICO CITY, MX (MEX)","MOSCOW, RU (DME)","ANTALYA, TR (AYT)",
		"TAIPEI, TW (TPE)","ZURICH, CH (ZRH)","NEW YORK NY, US (LGA)","FORT LAUDERDALE, FL, US (FLL)","WASHINGTON, DC, US (IAD)",
		"PALMA DE MALLORCA, ES (PMI)","COPENHAGEN, DK (CPH)","MOSCOW, RU (SVO)","BALTIMORE MD, US (BWI)","KUNMING, CN (KMG)",
		"VIENNA, AT (VIE)","OSLO, NO (OSL)","JEDDAH, SA (JED)","BRISBANE, AU (BNE)","SALT LAKE CITY UT, US (SLC)","DÜSSELDORF, DE (DUS)",
		"BOGOTA, CO (BOG)","MILAN, IT (MXP)","JOHANNESBURG, ZA (JNB)","STOCKHOLM, SE (ARN)","MANCHESTER, GB (MAN)","CHICAGO IL, US (MDW)",
		"WASHINGTON DC, US (DCA)","BRUSSELS, BE (BRU)","DUBLIN, IE (DUB)","SEOUL, KR (GMP)","DOHA, QA (DOH)","LONDON, GB (STN)",
		"HANGZHOU, CN (HGH)","JEJU, KR (CJU)","VANCOUVER BC, CA (YVR)","BERLIN, DE (TXL)","SAN DIEGO CA, US (SAN)","TAMPA FL, US (TPA)",
		"SÃO PAULO, BR (CGH)","BRASILIA, BR (BSB)","SAPPORO, JP (CTS)","XIAMEN, CN (XMN)","RIYADH, SA (RUH)","FUKUOKA, JP (FUK)",
		"RIO DE JANEIRO, BR (GIG)","HELSINKI, FI (HEL)","LISBON, PT (LIS)","ATHENS, GR (ATH)","AUCKLAND, NZ (AKL)"};


//Check if Flight is full, If full returns true else false. 
public boolean checkFull() {
	Connection con = FlightDatabase.getConnect();
	String check = "SELECT Seats,capacity FROM "+Flight.databaseName+" WHERE FlightID='"+this.flightID+"';";
	try {
		System.out.println(check);
		Statement statement = con.createStatement();
		ResultSet queryResult = statement.executeQuery(check);
		while (queryResult.next()) {
			//If the seats is greater then Capacity return false
			if(queryResult.getInt(2)<queryResult.getInt(1)) {
				System.out.println("Searched flight has room");
				return false;
			}else {
				System.out.println("Searched flight is full");
				return true;
			}
		}
	}catch (Exception e ) {
		e.printStackTrace();
	}return false;
}


public void AddFlight() {
	
}
public void EditFlight() {
	
}
public void DeleteFlight() {
	
}

public static void main(String[]arg) {
	Flight test = new Flight();
		test.flightID = "F1231";
	System.out.println(test.checkFull());
}
}
