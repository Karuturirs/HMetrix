<%@page import="org.json.JSONObject"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="org.json.JSONArray"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<!-- <meta http-equiv="refresh" content="30" > -->
<title>Details of Node Metrix</title>
</head>
<body>
<style>
table, th, td {
    border: 1px solid black;
    border-collapse: collapse;
}
th, td {
    padding: 5px;
}
</style>
<% String s = request.getAttribute("content").toString();

	JSONObject jo = new JSONObject(s);
	String name = jo.getString("cluster");
	%>
	    <p id="clusterstatus" title="<%=name%>"><b> ${message}</b></p>
	<%
		System.out.println("Recomputing Heath check on cluster: "+ name);
	 JSONObject healthjo= jo.getJSONObject("health_report");
	  int status_alerts = healthjo.getInt("Host/host_status/ALERT");
	  int status_healthy = healthjo.getInt("Host/host_status/HEALTHY");
	  int status_unknown = healthjo.getInt("Host/host_status/UNKNOWN");
	  int state_init = healthjo.getInt("Host/host_state/INIT");
	  int stale_config = healthjo.getInt("Host/stale_config");
	  int state_unhealthy = healthjo.getInt("Host/host_state/UNHEALTHY");
	  int status_unhealthy = healthjo.getInt("Host/host_status/UNHEALTHY");
	  int state_healthy = healthjo.getInt("Host/host_state/HEALTHY");
	  int state_Heartbeatlost = healthjo.getInt("Host/host_state/HEARTBEAT_LOST");
	  int maintenance_state = healthjo.getInt("Host/maintenance_state");
	  %>
	  <table>
	  	<tr>
		  	<th>Alerts</th>
		  	<th>Status Healthy</th>
		  	<th>Status Unknown</th>
		  	<th>Status Unhealthy</th>
		  	<th>State Init</th>
		  	<th>Stale Config</th>
		  	<th>State Healthy</th>
		  	<th>State Unhealthy</th>
		  	<th>Heartbeat Lost</th>
		  	<th>Maintenance state</th>
		  	
	  	</tr>
	  	<tr>
		  	<td><%=status_alerts %></td>
		  	<td><%=status_healthy %></td>
		  	<td><%=status_unknown %></td>
		  	<td><%=status_unhealthy %></td>
		  	<td><%=state_init %></td>
		  	<td><%=stale_config %></td>
		  	<td><%=state_healthy %></td>
		  	<td><%=state_unhealthy %></td>
		  	<td><%=state_Heartbeatlost %></td>
		  	<td><%=maintenance_state %></td>
	  	</tr>
	  </table>
	  
	
	    </ul>
</body>
</html>