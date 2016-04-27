<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cluster Details</title>
</head>
<body>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.3.0/jquery.min.js"></script>
 <script type="text/javascript">
 var auto_refresh = setInterval(
 function ()
 {
 $('#ExtraMetrix').load('/HadoopMetrix/extrametrix').fadeIn("slow");
 $('#DetailNodeMetrix').load('/HadoopMetrix/detailnodemetrix').fadeIn("slow");
 }, 5000); // autorefresh the content of the div after
            //every 1000 milliseconds(1sec)
 </script>
 
 
<div id="clusterNodes" style="float:left;padding:10px;width:15%;border-right:1px solid black;">
<%-- 	 <h2>${message}</h2> --%>
 	 <jsp:include page="${template}"/> 
 	 
 	<div id="NodeMetrix" style="border-top:1px solid black;" >
 		<jsp:include page="${nodeMetrix}"/> 
	
	</div> 
</div>

<div id="DetailNodeMetrix" style="float:left;padding:10px;width:80%;border-bottom:1px solid black;">
	<jsp:include page="${detailnodeMetrix}"/> 
</div>
<div id="ExtraMetrix" style="float:left;padding:10px;width:80%;border-bottom:1px solid black;">
	 <jsp:include page="${extraMetrix}"/> 
</div> 
</body>
</html>