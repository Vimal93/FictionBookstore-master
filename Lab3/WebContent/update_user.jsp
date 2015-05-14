<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link media="all" rel="stylesheet" type="text/css" href="css/update_user.css">
<title>Your Account Page</title>
<%@ page import="model.User" %>
</head>
<body>
<jsp:include page="nav.jsp" /><br>

<%	
String msg = (String) request.getAttribute("msg");
if(msg==null) msg ="";
String loggedIn = (String) session.getAttribute("loggedIn");
if(loggedIn == null) loggedIn = "";


String accountType = (String) session.getAttribute("accountType");
if (accountType == null) accountType = "-1";
int accountType_num = Integer.parseInt(accountType);
User user=(User)session.getAttribute("userObj");
if (user == null) user= new User();
//System.out.println("Account type: "+ accountType_num);
//System.out.println("Account ID: "+ user.getUserId());
%>

<%if ( accountType_num == 2 ) { %>





<h2>Edit Details for User ${session.username}</h2>
	 <form action="UserListServlet" method="post">
		<table>
		<!-- first name, middle name,  last name, email, address, phone num, routing num, bank account num -->
		<tr><td>First Name</td><td><input type="text" name="first" value="" /></td></tr>
		<tr><td>Middle Name</td><td><input type="text" name="middle" value="" /></td></tr>
		<tr><td>Last Name</td><td><label><input type="text" name="last" value="" /></label></td></tr>
		<tr><td>Email</td><td><label><input type="text" name="email" value="" /></label></td></tr>
		<tr><td>Address</td><td><label><input type="text" name="address" value="" /></label></td></tr>
		<tr><td>Phone Number</td><td><label><input type="text" name="phone" value="" /></label></td></tr>
		<tr><td>Routing Number</td><td><label><input type="text" name="routing" value="" /></label></td></tr>
		<tr><td>Bank Account Number</td><td><label><input type="text" name="account" value="" /></label></td></tr>
		
		
		</table>
		<%-- <input type="hidden" name="title" value="${ids.getItemId() }" />
	    <input type="hidden" name="isbn" value="${ids.getItemId() }" />
	    <input type="hidden" name="currentId" value="${ids.getItemId() }" />
		<input type="hidden" name="editedProduct" value="${sessionScope.currentProduct.getName() }" />
		<input type="hidden" name="isbn5" value="${sessionScope.currentProduct.getIsbn() }" /> --%>
		<input type="hidden" name="user_id" value="${ids.getUserId() }" />
		<input type="submit" align="right" name="edit_user" value="Save User Info"/>
	</form>
	<form action="<%=request.getContextPath()%>/DeleteUserServlet" method='post'>
	<table>
		<input type="hidden" name="user_id" value="<%=user.getUserId()%>"/>
		<input type="hidden" name="accountType" value="<%=accountType_num%>"/>
		<% if((user!=null)&&(user.getaccountType()==2)) out.print("<input type='submit' align='right' name='Delete Account' value='Delete Account'/>");%>
	</table>
</form>


<%} else { %>
	<p>Please login as a seller.</p>
<%}%>	