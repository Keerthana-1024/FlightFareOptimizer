package com.keertha.fly.beans;

import java.util.Date;

public class FlightRequest {
	
	private String fromAirport;
	private String toAirport;
	private String startDate;
	private String returnDate;
	private Boolean nonStop;
	private Integer noOfPass;
	private Integer noOfDays;
	private Integer duration;
	private String currency;
	public String getFromAirport() {
		return fromAirport;
	}
	public void setFromAirport(String fromAirport) {
		this.fromAirport = fromAirport;
	}
	public String getToAirport() {
		return toAirport;
	}
	public Integer getNoOfPass() {
		return noOfPass;
	}
	public void setNoOfPass(Integer noOfPass) {
		this.noOfPass = noOfPass;
	}
	public void setToAirport(String toAirport) {
		this.toAirport = toAirport;
	}
 
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public Integer getNoOfDays() {
		return noOfDays;
	}
	public void setNoOfDays(Integer noOfDays) {
		this.noOfDays = noOfDays;
	}
	public String getReturnDate() {
		return returnDate;
	}
	public void setReturnDate(String returnDate) {
		this.returnDate = returnDate;
	}
	public void setDuration(Integer duration) {
		this.duration=duration;
	}
	public Integer getDuration() {
		return duration;
	}
	public Boolean getNonStop() {
		return nonStop;
	}
	public void setNonStop(Boolean nonStop) {
		this.nonStop = nonStop;
	}
	
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	@Override
	public String toString() {
		return "FlightRequest [fromAirport=" + fromAirport + ", toAirport=" + toAirport + ", startDate=" + startDate
				+ ", returnDate=" + returnDate + ", nonStop=" + nonStop + ", noOfPass=" + noOfPass + ", noOfDays="
				+ noOfDays + ", duration=" + duration + ", currency=" + currency + "]";
	}
 
	
	
	

}
