package com.keertha.fly.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.keertha.fly.beans.FlightRequest;
import com.keertha.fly.beans.FlightResponse;
import com.keertha.fly.exceptions.FlightNotFoundException;
import com.keertha.fly.service.FlightSearchService;

/**
 * Servlet implementation class AmadeusController
 */
public class FlightFareController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor. 
	 */
	public FlightFareController() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String errorMsg = "";
		System.out.println("==========doget================= >> ");

		FlightSearchService flightSearchService = new FlightSearchService(); 
		FlightResponse flightResponse = null;

		FlightRequest flightRequest =validateRequest(request, response);
		if(flightRequest !=null) {
	
			try {
				flightResponse = flightSearchService.bestFlightFare(flightRequest);
			} catch (FlightNotFoundException e) {
				System.out.println("==== Controller exception cauthgt : " + e.toString());
				errorMsg = e.getMessage();
			}
			request.setAttribute("ERROR_MSG", errorMsg);
			request.setAttribute("flightRequest", flightRequest);
			request.setAttribute("flightResponse", flightResponse);
	
			request.getRequestDispatcher("FlightFareResult.jsp").forward(request, response);
			
		} else {
			request.getRequestDispatcher("‌FlightFareSearch.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("==========doposts================= >> ");
		doGet(request, response);
	}
	
	private FlightRequest validateRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String validationError=null;
		FlightRequest flightRequest = new FlightRequest();
		
		if(request.getParameter("fromAirport")==null && request.getParameter("fromAirport").length()==0) 
			validationError = "Input validation failed : fromAirport cannot be empty"; 
		if(validationError == null && request.getParameter("toAirport")==null && request.getParameter("toAirport").length()==0) 
			validationError = "Input validation failed : toAirport cannot be empty"; 
  		if(validationError == null && request.getParameter("fromAirport").equals(request.getParameter("toAirport")))
  			validationError = "Input validation failed : Both Departure and Arrival Airports cannot be same";
  		if(validationError == null && request.getParameter("startDate")==null || request.getParameter("startDate").length()==0) 
  			validationError = "Input validation failed : Start Date cannot be empty";
  		if(validationError == null && request.getParameter("returnDate")==null || request.getParameter("returnDate").length()==0) 
  			validationError = "Input validation failed : Return Date cannot be empty";
  		if(validationError == null && request.getParameter("noOfPass")==null || request.getParameter("noOfPass").length()==0) 
  			validationError = "Input validation failed : noOfPass cannot be empty";
  		if(validationError == null && request.getParameter("duration")==null || request.getParameter("duration").length()==0) 
  			validationError = "Input validation failed : duration cannot be empty";
  		Integer noOfPass = null;
  		Integer duration = null;
  		if(validationError == null) {
	  		try {
	  			noOfPass = Integer.parseInt(request.getParameter("noOfPass"));
	  		} catch (Exception ex) {
	  			validationError = "Input validation failed : invalid numerical value in noOfPass";
	  		}
  		}
  		if(validationError == null) {
  		try {
  			duration = Integer.parseInt(request.getParameter("duration"));
  		} catch (Exception ex) {
  			validationError = "Input validation failed : invalid numerical value in duration";
  		}
  		}
  		if (validationError==null) {
			flightRequest.setFromAirport(request.getParameter("fromAirport"));
			flightRequest.setToAirport(request.getParameter("toAirport"));
			flightRequest.setStartDate(request.getParameter("startDate"));
			flightRequest.setReturnDate(request.getParameter("returnDate"));
			flightRequest.setNoOfPass(noOfPass);
			flightRequest.setDuration(duration);
			flightRequest.setNonStop(Boolean.parseBoolean(request.getParameter("nonStop")));
			flightRequest.setCurrency(request.getParameter("currency"));
			return flightRequest;
  		} else {
  			request.setAttribute("ERROR_MSG", validationError);
  			return null;
  		}
 	}

}
