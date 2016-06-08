<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ page import="edu.mayo.servlets.*" %>
<%@ page import="edu.mayo.utils.*" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Select Incubator Containers</title>
</head>
<body>
<h3>Select the components you would like to test in the incubator environment.</h3>
<hr>
<form action="ProcessRequest" method="GET">
<% GenerateUID uid = new GenerateUID();
	out.print("<input type=hidden name=UID value=" + uid.getUID() + ">");
%>
<table>
<tr><td>Tomcat: </td><td><input type="checkbox" name="tomcat"></td></tr>
<tr><td>Redis: </td><td><input type="checkbox" name="redis"></td></tr>
<tr><td>Mongo: </td><td><input type="checkbox" name="mongo"></td></tr>
<tr colspan="2"><td>
<input type="submit" value="Submit">
</td></tr>
</table>
</form>
</body>
</html>