<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Log in</title>
</head>
<body>
	<form action="login" method="post">
		<h3>Please enter user name and password</h3>
		<table border="1">
			<tr>
				<td>Login</td>
				<td><input name="login" type="text"></input></td>
			</tr>
			<tr>
				<td>Password</td>
				<td><input name="password" type="password"></input></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="submit"> </input></td>
			</tr>
		</table>
	</form>
	<br/>
	<a href="restorePassword.jsp" >Forgot password?</a>
	
</body>
</html>