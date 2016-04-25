<%@page import="org.json.JSONObject"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="org.json.JSONArray"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cluster Details</title>
</head>
<body>

 <h2>${message}</h2>

<% String s = request.getAttribute("content").toString();

	JSONObject jo = new JSONObject(s);
	JSONArray arr = jo.getJSONArray("clusters");

	for (int i = 0; i < arr.length(); i++)
	{
	   
	    String clusterName = arr.getJSONObject(i).getString("name");
	    String href = arr.getJSONObject(i).getString("href");
	    %>
	    <div id="<%=clusterName%>" title="<%=href%>">
	    <p><a href="#"><%=clusterName %></a></p>
	    <%
	    JSONArray nodearray = arr.getJSONObject(i).getJSONArray("Nodes");	
	    %>
	    <ul style="list-style-type:disc">
	    <%
			    for (int j = 0; j< nodearray.length(); j++)
				{
			    	String nodename =  nodearray.getJSONObject(j).getString("hostname");
			    	String ambarinodehref =  nodearray.getJSONObject(j).getString("ambarihref");
					%>
					<li id="<%=nodename%>" title="<%=ambarinodehref%>"><a href="#"><%=nodename%></a> </li>
					<%
				}
	    %>
	    </ul>
	    </div>
	<%}%>

 
</body>
</html>