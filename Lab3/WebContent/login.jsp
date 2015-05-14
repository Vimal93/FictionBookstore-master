<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login</title>
<link media="all" rel="stylesheet" type="text/css" href="css/login.css">
</head>

<body background = "images/cat_read.png" onload="document.getElementsByTagName('input')[0].focus();">
<%-- <%@ include file="nav.jsp" %> --%>
<jsp:include page="nav.jsp" />

<%
String msg = (String) request.getAttribute("msg");
if(msg==null) msg ="";
String loggedIn = (String) session.getAttribute("loggedIn");
if(loggedIn == null) loggedIn = "";
%>

<%@ page import="model.User" %> 
<%
User user = (User) session.getAttribute("userObj");
if (user == null) user= new User();
%>

<br>
<div id="div_left">
</div>
<div id="div_right">
	<%-- If not logged in, display login form --%>
<%
	if( !loggedIn.equalsIgnoreCase("true") ) {
		out.print("<center>" +
				"<h2>Login Page" +
				"<p>" + msg + "</p>" +
				"<form action='login' method='post'>" +
				"<label></label><input type='text' name='username' placeholder=\"Username\" size='16' maxlength='16' />" +
				"<label></label><input type='password' name='password' size='16' placeholder=\"Password\" maxlength='16' />" +
				"<input type='submit' value='Log In' />" +
				"</form>" +
				"</center>"
				);
	} //End If

	/**  If user is logged in, display the following:  */
	else {
		out.print("<center>");
		out.print("<p>You are currently logged in as "+user.getUserName()+".</p>"); //Later, print out the user's first and last name using session variables
		out.print("</center>");
%> 		<%@ include file = 'logout.jsp' %> <%}%>

</div>
<%--
<% if ( loggedIn.equalsIgnoreCase("true") ) { %> <%@ include file = 'logout.jsp' %> <%}%>
--%>


</body>
</html>

