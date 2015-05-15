
<%@page import="hibernate.general.HibernateFactory"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
<title>Puzzle CMS| Logout</title>
</head>
<body>
	<%
// 		User = HibernateFactory.getInstance().getUserDao().
	%>
	<br />
	<h1>
		User
		<%=session.getAttribute("firstname")%>
		<%=session.getAttribute("lastname")%>
		log out
	</h1>
	<jsp:useBean id="user" class="hibernate.tables.User"
		scope="application" />
	<br />
	<a href="/CMSPuzzle-1.0-SNAPSHOT/">Main page</a>
	<br />
	<a href="login.jsp">Log in</a>

</body>
</html>
