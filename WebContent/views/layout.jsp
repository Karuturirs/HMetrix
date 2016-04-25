<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<html>  
<head>  
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">  
<title>Hadoop Cluster Metrix</title>  
</head>  
<body>  
        <div><h1>Cluster Metrix</h1></div>  
        <div style="float:left;padding:10px;width:15%;border-right:1px solid black;">
        		<tiles:insertAttribute name="menu" />
        </div>  
        <div style="float:left;padding:10px;width:80%;border-bottom:1px solid black;">  
        	<tiles:insertAttribute name="body1" />
        </div> 
        <div style="float:left;padding:10px;width:80%;border-bottom:1px solid black;">  
        	<tiles:insertAttribute name="body2" />
        </div>
        <div style="float:left;padding:10px;width:80%;border-bottom:1px solid black;">  
        	<tiles:insertAttribute name="body3" />
        </div> 
        <div style="clear:both">
        	<tiles:insertAttribute name="footer" />
        </div>  
  
</body>  
</html>  