package com.keertha.fly.beans;

import java.util.Arrays;
import java.util.List;

import com.amadeus.resources.FlightOfferSearch.Itinerary;

public class FlightResponse {
	private List<Flight> segmentsList;
	private String totalFare;
	private String baseFare;
	private String currCode;
	private String traStartDate;
	private String traReturnDate;
 
	public String getTotalFare() {
		return totalFare;
	}
	public void setTotalFare(String totalFare) {
		this.totalFare = totalFare;
	}
	public String getBaseFare() {
		return baseFare;
	}
	public void setBaseFare(String baseFare) {
		this.baseFare = baseFare;
	}
	public String getCurrCode() {
		return currCode;
	}
	public void setCurrCode(String currCode) {
		this.currCode = currCode;
	}
	
 
	public List<Flight> getSegmentsList() {
		return segmentsList;
	}
	public void setSegmentsList(List<Flight> segmentsList) {
		this.segmentsList = segmentsList;
	}
	public String getTraStartDate() {
		return traStartDate;
	}
	public void setTraStartDate(String traStartDate) {
		this.traStartDate = traStartDate;
	}
	public String getTraReturnDate() {
		return traReturnDate;
	}
	public void setTraReturnDate(String traReturnDate) {
		this.traReturnDate = traReturnDate;
	}
 
	 
}
