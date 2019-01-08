<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
	if (session.getAttribute("email") == null) {
		out.print("You are not logged in.");
		return;
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Services</title>
</head>
<body>
	<form action="LogoutController" method="get">
		<input type="submit" value="Logout">
	</form>
	<a href="#">Storage Service</a>
	<a href="compiler.html">Java Compiler Service</a>
</body>
</html>
