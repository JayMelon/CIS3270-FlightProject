package TestPackage;

import Database.FlightDatabase;

// Just a test to see if the repository is working correctly
final class jaysTest {
	//Simple method, adds two numbers 
private static int sum(int num1,int num2) {
	return num1+num2;
}
public static void main(String[] args) {
		int sum1 = sum(3,3);
		int sum2 = sum(1,3);
	System.out.println(sum(sum1,sum2));
	}

}
