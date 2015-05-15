<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Registration was successful!</title>
</head>
<body>
	<%
		String firstName = (String) request.getAttribute("firstname");
		String lastName = (String) request.getAttribute("lastname");
		session.setAttribute("firstname", firstName);
		session.setAttribute("lastname", lastName);
		// 		out.println(login + " "+password);
	%>
	<table>
		<tr>
			<td>First name:</td>
			<td><%=firstName%></td>
		</tr>
		<tr>
			<td>Last name:</td>
			<td><%=lastName%></td>
		</tr>
	</table>
	<%out.println("Welcome  "+firstName +" "+lastName+"<br/> <a href=\"logout.jsp\" >Logout</a>");%>
	<br/>
	<a href="./">Main page</a>
</body>
</html>