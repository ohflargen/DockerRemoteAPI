<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ page import="edu.mayo.docker.*" %>

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
	Container cc = new Container(); 
	out.print(cc.create("mytomcat","tomcat","http://192.168.1.97:4243"));
	out.print(cc.start("mytomcat","http://192.168.1.97:4243"));
	
	out.print(cc.create("myredis","redis","http://192.168.1.97:4243"));
	out.print(cc.start("myredis","http://192.168.1.97:4243"));
	
	out.print(cc.create("mymongo","mongo","http://192.168.1.97:4243"));
	out.print(cc.start("mymongo","http://192.168.1.97:4243"));
//out.print(""+cc.stop("mouse100","http://192.168.1.97:4243"));
	//out.print(""+cc.createImage("tomcat"));
    //out.print(ds.getSomething("http://192.168.1.91:4243/images/search?term=elasticsearch"));
%>
</body>
</html>