package com.keertha.fly.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.amadeus.Amadeus;
import com.amadeus.Params;
import com.amadeus.exceptions.ResponseException;
import com.amadeus.resources.FlightOfferSearch;
import com.amadeus.resources.FlightOfferSearch.Itinerary;
import com.amadeus.resources.FlightOfferSearch.SearchSegment;
import com.keertha.fly.beans.Flight;
import com.keertha.fly.beans.FlightRequest;
import com.keertha.fly.beans.FlightResponse;
import com.keertha.fly.constants.AmadeusConstants;
import com.keertha.fly.exceptions.FlightNotFoundException;

public class FlightSearchService {

	public FlightResponse bestFlightFare(FlightRequest flightRequest) throws FlightNotFoundException {
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); 
		
		Integer duration = flightRequest.getDuration();  
 		FlightResponse flightResponse = null;
 		FlightResponse tmpFlightResponse = null;
		String trStartDate=null;
		String trReturnDate=null;
		
		Amadeus amadeus = Amadeus
				.builder(AmadeusConstants.API_KEY,AmadeusConstants.API_SECRET)
				.build();
		
 
 		LocalDate startLocalDate = LocalDate.parse(flightRequest.getStartDate());  
 		LocalDate returnLocalDate = LocalDate.parse(flightRequest.getReturnDate());
 		
 	 	LocalDate calcStartLocalDate = startLocalDate;
 		LocalDate calcReturnLocalDate = calcStartLocalDate.plusDays(duration);
 		
 		while(calcReturnLocalDate.isBefore(returnLocalDate) || 
 				calcReturnLocalDate.equals(returnLocalDate)) {
 			
 			System.out.println("START DT:" + calcStartLocalDate + " END DT : " +calcReturnLocalDate);
 			trStartDate =calcStartLocalDate.format(formatter); 
 			trReturnDate = calcReturnLocalDate.format(formatter);
 			
 			tmpFlightResponse = bestFlightFareForADate(amadeus, flightRequest, trStartDate, trReturnDate);
 			
 			if(flightResponse ==null || 
 					(Double.parseDouble(flightResponse.getTotalFare())>Double.parseDouble(tmpFlightResponse.getTotalFare()))) {
 				System.out.println("Prev Value Overwrittn : " + tmpFlightResponse.getSegmentsList().get(0).getDepartDateTime());
 				flightResponse = tmpFlightResponse;
 				flightResponse.setTraStartDate(trStartDate);
 				flightResponse.setTraReturnDate(trReturnDate);
 				
 			}
 				
 
 			calcStartLocalDate= calcStartLocalDate.plusDays(1);
 			calcReturnLocalDate = calcStartLocalDate.plusDays(duration);
  		}
		return flightResponse;
	}



	private FlightResponse bestFlightFareForADate(Amadeus amadeus, FlightRequest flightRequest, String trStartDate, String trReturnDate) throws FlightNotFoundException {
		FlightResponse flightResponse = null;
		FlightOfferSearch[] flightOffersSearches;
		try {
			flightOffersSearches = amadeus.shopping.flightOffersSearch.get(
					Params.with("originLocationCode", flightRequest.getFromAirport())
					.and("destinationLocationCode", flightRequest.getToAirport())
					.and("departureDate", trStartDate)
					.and("returnDate", trReturnDate)
					.and("adults", flightRequest.getNoOfPass())
					.and("nonStop", flightRequest.getNonStop())
					.and("currencyCode", flightRequest.getCurrency())
					.and("max", 10));


			if(flightOffersSearches !=null && flightOffersSearches.length > 0) {
				flightResponse = populateFlightResponse(flightResponse, flightOffersSearches);
			} else {
				System.out.println("===================== flightOffersSearches is null or length is 0 ");
				throw new FlightNotFoundException("No Flight Found for " + flightRequest.toString() );
			} 
		} catch (ResponseException e) {
			System.out.println("Exception thrown from amadeus : " + e.toString());
			// TODO Auto-generated catch block
			throw new FlightNotFoundException("<h2>GDS API throws error - May be invalid request payload. </h2><br>(Request payload ==> " + flightRequest.toString() + "<==)");
		}
		return flightResponse;
	}
	private FlightResponse populateFlightResponse(FlightResponse flightResponse, FlightOfferSearch[] flightOffersSearches) {
		
		Boolean updateTotal=null;
		
		for(int i=0;i<flightOffersSearches.length;i++) {
			updateTotal = true;
			System.out.println("i : " + i + " price : " + flightOffersSearches[i].getPrice().getTotal());
			if(flightResponse == null) {
				flightResponse = new FlightResponse();
			} else if (Double.parseDouble(flightResponse.getTotalFare())<=Double.parseDouble(flightOffersSearches[i].getPrice().getTotal())) {
				updateTotal = false;
			}	
			if(updateTotal) { 
				System.out.println("i : " + i + "flightResponse object updated");
				flightResponse.setTotalFare(flightOffersSearches[i].getPrice().getTotal());
				flightResponse.setBaseFare(flightOffersSearches[i].getPrice().getBase());
				flightResponse.setCurrCode(flightOffersSearches[i].getPrice().getCurrency());
				flightResponse.setSegmentsList(populateFlightResponseItinerary( flightOffersSearches[i].getItineraries()));
			}
		}
		return flightResponse;
	}
	
	private List<Flight> populateFlightResponseItinerary( Itinerary[] itineraryArr) {
		Itinerary itinerary = null;
		SearchSegment searchSegment = null;
		List<Flight> segmentList = new ArrayList<Flight>();
		Flight flight=null;
		if(itineraryArr != null && itineraryArr.length>0) {
			for(int j=0;j<itineraryArr.length;j++) {
				itinerary = itineraryArr[j];
				SearchSegment[] searchSegmentArr =  itinerary.getSegments();
				if(searchSegmentArr != null && searchSegmentArr.length>0) {
					for(int i=0;i< searchSegmentArr.length;i++) {
						searchSegment = searchSegmentArr[i];
						flight = new Flight();
						flight.setDepartAirport(searchSegment.getDeparture().getIataCode());
						flight.setDepartDateTime(searchSegment.getDeparture().getAt());
						flight.setArrivalAirport(searchSegment.getArrival().getIataCode());
						flight.setArrivalDateTime(searchSegment.getArrival().getAt());
						flight.setCarrierCode(searchSegment.getCarrierCode());
						segmentList.add(flight);
					}
					
				}
			}
		}
		return segmentList;
	}
}
