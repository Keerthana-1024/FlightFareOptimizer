package com.keertha.fly.service;

import java.util.List;

import com.amadeus.Amadeus;
import com.amadeus.Params;
import com.amadeus.exceptions.ResponseException;
import com.amadeus.referencedata.Locations;
import com.amadeus.resources.Location;
import com.keertha.fly.beans.Airport;
import com.keertha.fly.beans.FlightRequest;
import com.keertha.fly.beans.FlightResponse;
import com.keertha.fly.constants.AmadeusConstants;
import com.keertha.fly.exceptions.FlightNotFoundException;

public class MyTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
System.out.println("----------------");

 


/*FlightSearchService flightSearchService = new FlightSearchService();
FlightRequest flightRequest = new FlightRequest();
flightRequest.setFromAirport("JFK");// JFK SYD JFK
flightRequest.setToAirport("MIA"); //LAS(363) BKK MIA
flightRequest.setStartDate("2024-02-01");
flightRequest.setReturnDate("2024-02-08");
flightRequest.setDuration(4);
flightRequest.setNoOfPass(1);
flightRequest.setNonStop(true);

try {
	FlightResponse flightResponse = flightSearchService.bestFlightFare(flightRequest);
	System.out.println(flightResponse.getTotalFare());
} catch (FlightNotFoundException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}

*/


//Amadeus amadeus = Amadeus
//.builder(AmadeusConstants.API_KEY,AmadeusConstants.API_SECRET)
//.build();
//Airport airport= null;
//Locations locations = amadeus.referenceData.locations;
//Location[] arrLocation;
//try {
//	arrLocation = locations.airports.get(Params
//		      .with("latitude", 49.0000)
//		      .and("longitude", 2.55));
//	for(int i=0;i<arrLocation.length;i++) {
//		
//		System.out.println(arrLocation[i].getIataCode() + " == " + 
//				arrLocation[i].getAddress().getCityName() + " = "  + arrLocation[i].getName());
//		airport = new Airport();
//		airport.setAirportName(arrLocation[i].getName());
//		airport.setIataCode(arrLocation[i].getIataCode());
//		//airportList.add(airport);
//	}
//} catch (ResponseException e) {
//	// TODO Auto-generated catch block
//	e.printStackTrace();
//}

AirportService as = new AirportService();
List<Airport> airportList = as.getAirports();
for(Airport a:airportList) {
	System.out.println(a.getAirportName());
	System.out.println(a.getIataCode());
}



	}

}
