<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ page import="edu.mayo.test.*" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<p>
Docker test results...
</p>
<% 	
	DockerSearch ds = new DockerSearch(); 
	out.print(ds.getSomething("http://192.168.1.91:4243/images/search?term=elasticsearch"));
%>
</body>
</html>