package com.keertha.fly.service;

import java.util.ArrayList;

import com.amadeus.Amadeus;
import com.amadeus.Params;
import com.amadeus.exceptions.ResponseException;
import com.amadeus.referencedata.Locations;
import com.amadeus.resources.Location;
import com.keertha.fly.beans.Airport;
import com.keertha.fly.constants.AmadeusConstants;

public class AirportService {

	public ArrayList<Airport> getAirports() {
		ArrayList<Airport> airportList = new ArrayList<Airport>();
		Airport airport = null;
		Amadeus amadeus = Amadeus
				.builder(AmadeusConstants.API_KEY,AmadeusConstants.API_SECRET)
				.build();
		Locations locations = amadeus.referenceData.locations;
		Location[] arrLocation;
		try {
			arrLocation = locations.airports.get(Params
					.with("latitude", 49.0000)
					.and("longitude", 2.55));
			for(int i=0;i<arrLocation.length;i++) {
				airport = new Airport();
				airport.setAirportName(arrLocation[i].getAddress().getCityName() + " ("  + arrLocation[i].getName() + ")");
				airport.setIataCode(arrLocation[i].getIataCode());
				airportList.add(airport);
			}
		} catch (ResponseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return airportList;

	}
}
