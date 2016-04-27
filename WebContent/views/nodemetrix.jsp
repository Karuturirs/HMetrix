<%@page import="org.json.JSONObject"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="org.json.JSONArray"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<!-- <meta http-equiv="refresh" content="1" > -->
<title>Node Metrix</title>
</head>
<body>

<% String s = request.getAttribute("content").toString();

	JSONObject jo = new JSONObject(s);
	String name = jo.getString("cluster");
	%>
	    <p><b><%=name %> ${message}</b></p>
	<%
	JSONArray arr = jo.getJSONArray("services");
	  %>
	    <ul style="list-style-type:disc">
	    <%
	for (int i = 0; i < arr.length(); i++)
	{
	   
	    String servicename = arr.getJSONObject(i).getString("service_name");
	    String href = arr.getJSONObject(i).getString("servicehref");
			%>
					<p id="<%=servicename%>" title="<%=href%>"><%=servicename%> </p>
			<%
	 }%>
	    </ul>


</body>
</html>