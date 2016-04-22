<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="java.net.URL"%>
<%@page import="org.jdom.Document"%>
<%@page import="org.jdom.Element"%>
<%@page import="org.jdom.input.*"%>
<%@page import="java.net.*"%>
<%@page import="java.io.*"%>
<%@page import="org.apache.commons.codec.binary.Base64"%>
<%@page import="java.security.cert.X509Certificate"%>
<%@page import="javax.net.ssl.X509TrustManager"%>
<%@page import="javax.net.ssl.TrustManager"%>
<%@page import="javax.net.ssl.SSLSession"%>
<%@page import="javax.net.ssl.SSLContext"%>
<%@page import="javax.net.ssl.HttpsURLConnection"%>
<%@page import="javax.net.ssl.HostnameVerifier"%>
<%@page import="org.json.JSONObject"%>
<%@page import="org.json.JSONException"%>
<%@page import="javax.xml.parsers.DocumentBuilderFactory"%>
<%@page import="javax.xml.parsers.DocumentBuilder"%>
<%@page import="javax.xml.parsers.SAXParserFactory"%>
<%@page import="javax.xml.parsers.SAXParser"%>
<%@page import="java.util.Properties"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cluster Details</title>
</head>
<%! 
public boolean isJSONValid(String test) {
    try {
        new JSONObject(test);
    } catch (JSONException ex) {
       	System.out.print("ERROR: Invalid Json.");
            return false;
       
    }
    return true;
}

%>


<body>
   <center><h1><b>Cluster Metrixs..</b></h1></center>
   
   <%
   String  pageapi="http://as000.cloudapp.net:8080/api/v1/clusters/multi-node-hdfs-yarn/services/HDFS/components/DATANODE";
   String name ="admin";
	String password ="admin";
	System.out.println("name: " + name);
	System.out.println("password: " + password);
	
	String authString = name + ":" + password;
	System.out.println("auth string: " + authString);
	byte[] authEncBytes = Base64.encodeBase64(authString.getBytes());
	String authStringEnc = new String(authEncBytes);
	System.out.println("Base64 encoded auth string: " + authStringEnc);

	URL url = new URL(pageapi);
	URLConnection urlConnection = url.openConnection();
	urlConnection.setRequestProperty("Authorization", "Basic " + authStringEnc);
	InputStream is = urlConnection.getInputStream();
	InputStreamReader isr = new InputStreamReader(is);

	int numCharsRead;
	char[] charArray = new char[1024];
	StringBuffer sb = new StringBuffer();
	while ((numCharsRead = isr.read(charArray)) > 0) {
		sb.append(charArray, 0, numCharsRead);
	}
	String result = sb.toString();
		JSONObject js = new JSONObject(sb);
		
	System.out.println("*** BEGIN ***");
	
	isJSONValid(result);%>
<b><%=result%></b>
	<%
	System.out.println("*** END ***");
   
   %>
</body>
</html>