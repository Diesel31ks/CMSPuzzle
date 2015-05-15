<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Puzzle CMS| Login</title>
</head>

<BODY>
<H1>Login to PuzzleCms</H1>
<h2>Please enter username and password</h2>
<%
    String myname =  (String)session.getAttribute("username");

    if(myname!=null)
    {
        out.println("Welcome  "+myname+"  , <a href=\"logout.jsp\" >Logout</a>");
    }
    else
    {
%>
<form action="checkLogin.jsp" method="post">
    <table>
        <tr>
            <td> User : </td><td> <input name="username" size=15 type="text" /> </td>
        </tr>
        <tr>
            <td> Password : </td><td> <input name="password" size=15 type="text" /> </td>
        </tr>
    </table>
    <BR><BR>
    <input type="submit" value="LOGIN" />
</form>
<%
    }
%>
</BODY>
</html>