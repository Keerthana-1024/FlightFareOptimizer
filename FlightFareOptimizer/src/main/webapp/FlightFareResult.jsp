<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="com.keertha.fly.beans.FlightResponse"%>
<%@ page import="com.keertha.fly.beans.FlightRequest"%>
<%@ page import="com.keertha.fly.beans.Flight"%>
<%@ page import="java.util.List"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<script>
	document.body.style.cursor = 'default';
</script>
</head>
<body>
	<h1>
		<p align="center">Flight Fare Optimizer</p>
	</h1>
	<%
	String errorMsg = (String) request.getAttribute("ERROR_MSG");
	FlightResponse flightResponse = (FlightResponse) request.getAttribute("flightResponse");
	FlightRequest flightRequest = (FlightRequest) request.getAttribute("flightRequest");
	List<Flight> flightList = null;
 	Flight flight=null;
 	if(flightResponse != null) flightList = flightResponse.getSegmentsList();
	%>

		<table width="60%" align="center" border="solid">
			<tr>
				<td colspan="2" align="center"><h2>Travel Details Entered</h2></td>
			</tr>
			<tr>
				<td width="50%" align="right">From Airport</td>
				<td><%=flightRequest.getFromAirport()%></td>
			</tr>

			<tr>
				<td width="50%" align="right">To Airport</td>
				<td><%=flightRequest.getToAirport()%></td>
			</tr>

			<tr>
				<td width="50%" align="right">Travel Start Date(yyyy-mm-dd
					format)</td>
				<td><%=flightRequest.getStartDate()%></td>
			</tr>

			<tr>
				<td width="50%" align="right">Travel Return Date(yyyy-mm-dd
					format)</td>
				<td><%=flightRequest.getReturnDate()%></td>
			</tr>

			<tr>
				<td width="50%" align="right">No of passengers</td>
				<td><%=flightRequest.getNoOfPass()%></td>
			</tr>
			
			<tr>
				<td width="50%" align="right">Duration</td>
				<td><%=flightRequest.getDuration() %></td>
			</tr>

			<tr>
				<td width="50%" align="right">Non Stop Flights Only</td>
				<td><%=flightRequest.getNonStop()%></td>
			</tr>

		 </table>
	<br>
	<br>

	<table width="70%" align="center" border="solid">
	<% if(errorMsg==null || errorMsg=="") {%>
		<tr>
			<td colspan="2" align="center"><h2>Best Fare & Itieranery
					Details</h2></td>
		</tr>
 
 		<tr>
			<td width="50%" align="right">Suggested Travel Start Date</td>
			<td><%=flightResponse.getTraStartDate()%></td>
		</tr>
		 		<tr>
			<td width="50%" align="right">Suggested Travel Return Date</td>
			<td><%=flightResponse.getTraReturnDate()%></td>
		</tr>
		<tr>
			<td width="50%" align="right">Total Fare</td>
			<td><%=flightResponse.getTotalFare()%>&nbsp;<%=flightResponse.getCurrCode()%></td>
		</tr>

		<tr>
			<td width="50%" align="right">Base Fare</td>
			<td><%=flightResponse.getBaseFare()%>&nbsp;<%=flightResponse.getCurrCode()%></td>
		</tr>
		<tr>
			<td colspan="2" align="center">Itiernary Details</td>
		</tr>
		<tr>
			<td colspan="2" align="center">
			<table width="100%" border="solid">
			<tr>
				<td>Seg.No</td>
				<td>Carrier Code</td>
				<td>Dept. Airport</td>
				<td>Dept. DateTime</td>
				<td>Arr. Airport</td>
				<td>Arr. DateTime</td>
			</tr>
			<tr>
			<%
				if(flightList !=null && flightList.size()>0) {
					for(int i=0;i<flightList.size();i++) {
						flight = flightList.get(i);
		 	%>	<tr>
				<td><%=i+1 %></td>
				<td><%= flight.getCarrierCode() %></td>
				<td><%= flight.getDepartAirport() %></td>
				<td><%= flight.getDepartDateTime() %></td>
				<td><%= flight.getArrivalAirport() %></td>
				<td><%= flight.getArrivalDateTime() %></td>
				</tr>		
			<%  
					}
				} else {
			%>
				<td colspan="6">No Iternary Returned</td>
			<%  
				}
			%>
			</tr>
			</table>
			</td>
		</tr>
		<%
			} else {
		%>
			<tr>
				<td colspan="2" align="center"><%= errorMsg %></td>
			</tr>
		<% 
	 	}
		%>
		</table>


</body>
</html>