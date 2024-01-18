<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ page import="java.util.ArrayList"%>   
 <%@ page import="com.keertha.fly.beans.Airport"%>
 <%@ page import="com.keertha.fly.service.AirportService" %>
 
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">	
<title>Insert title here</title>
</head>
<body>
<h1><p align="center">Flight Fare Optimizer</p></h1>

<%
	String fromAirport = request.getParameter("fromAirport");
	String toAirport = request.getParameter("toAirport");
	String startDate = request.getParameter("startDate");
	if(startDate==null) startDate="";
	String returnDate = request.getParameter("returnDate");
	if(returnDate==null) returnDate="";
	String duration = request.getParameter("duration");
	if(duration==null) duration="";
	String noOfPass = request.getParameter("noOfPass");
	if(noOfPass==null) noOfPass="";
	
	
	AirportService airportService = new AirportService();
	ArrayList<Airport> airportList =  airportService.getAirports();
	Airport airport = null;

%>
<form action="getOptimizedFare" method="post">


<table width="60%" align="center" border="solid">
<tr>
<td colspan="2" align="center"><h2>Enter the travel details</h2></td>
</tr>
<tr>
<td width="50%" align="right">From Airport</td>
<td>
<select name="fromAirport">
	<% 
	if(airportList != null && airportList.size()>0) {
		for(int i=0;i<airportList.size();i++) {
			airport = airportList.get(i);
			if(airport.getIataCode().equals(fromAirport)) 
				out.println("<option value=" + airport.getIataCode() + " selected>" + airport.getAirportName() +"</option>");
			else
				out.println("<option value=" + airport.getIataCode() + ">" + airport.getAirportName() +"</option>");
		}
	}
	%>
</select>
</td>
</tr>

<tr>
<td width="50%" align="right">To Airport</td>
<td>

<select name="toAirport">
	<% 
	if(airportList != null && airportList.size()>0) {
		for(int i=0;i<airportList.size();i++) {
			airport = airportList.get(i);
			if(airport.getIataCode().equals(toAirport)) 
				out.println("<option value=" + airport.getIataCode() + " selected>" + airport.getAirportName() +"</option>");
			else
				out.println("<option value=" + airport.getIataCode() + ">" + airport.getAirportName() +"</option>");
		}
	}
	%>
</select>
</td>
</tr>

<tr>
<td width="50%" align="right">Tentative Search Start Date(yyyy-mm-dd format)</td>
<td>
 <input type="text" name="startDate" value="<%= startDate%>"/>
 </td>
</tr>

<tr>
<td width="50%" align="right">Tentative Search End Date(yyyy-mm-dd format)</td>
<td>
 <input type="text" name="returnDate" value="<%= returnDate %>"/>
 </td>
</tr>

<tr>
<td width="50%" align="right">Travel Duration</td>
<td>
 <input type="text" name="duration" value="<%=  duration  %>"/>
 </td>
</tr>


<tr>
<td width="50%" align="right">No of passengers</td>
<td>
 <input type="text" name="noOfPass" value="<%= noOfPass %>"/>
 </td>
</tr>
 
<tr>
<td width="50%" align="right">Non Stop Flights Only</td>
<td>
 <input type="checkbox" name="nonStop" value="true"/>
 </td>
</tr>
<tr>
<tr>
<td width="50%" align="right">Currency</td>
<td>
<select name="currency">
  	<option value="INR">Indian Rupee</option>
  	<option value="EUR">Euro</option>
  	<option value="USD">US Dollar</option> 
</select>
 </td>
</tr>
<tr>

<tr>
 <td colspan="2" align="center">
  <input type="submit" name="submit"/>
 </td>
</tr>


</table>
</form>
<% if(request.getAttribute("ERROR_MSG")!=null && ((String)request.getAttribute("ERROR_MSG")).length()>0) {%>
<p align="center"><%=request.getAttribute("ERROR_MSG") %>
<% } %>
</body>
</html>