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
<link media="all" rel="stylesheet" type="text/css"
	href="css/orderlist.css">
<title>Your Orders</title>
</head>

<%@ page import="model.User" %> 
<%
User user = (User) session.getAttribute("userObj");
if (user == null) user= new User();
%>

<body>
<jsp:include page="nav.jsp" />
<div id=open></div>
	<div id=div_main>
	
		<table>
			<tr><th>Order ID<th colspan="4">Number of Products</th></tr>
			<c:forEach var="ids" items="${sessionScope.orders}" varStatus="loop">
			<form action='<%=request.getContextPath()%>/ViewOrderServlet' method='post'>
			<tr>			
				<td>${ids.getOrderId()}</td>
				<td>${ids.getC().getProducts().size() }</td>
				<input type="hidden" name="orderId" value="${ids.getOrderId()}" />
                <td><input type="submit" name="sub1" value="View Order" /></td>
                <c:choose>
                <c:when test="${ids.getStatus() == 1}">
                <% if(user.getaccountType()==0) { %> <td><input type="submit" name="sub3" value="Not Shipped" disabled/></td></td> <%} %>
   				<% if(user.getaccountType()==1) { %> <td><input type="submit" name="sub2" value="Update Order"  /></td> <%} %>
   				<% if(user.getaccountType()==2) { %> <td><input type="submit" name="sub3" value="Mark as shipped" /></td> <%} %>
   				</c:when>
   				<c:otherwise><td><input type="submit" name="sub3" value="Shipped" disabled/></td></c:otherwise>
   				</c:choose>				
				<td><input type="submit" name="sub4" value="Cancel Order" /></td>
			</tr>
			</form>
			</c:forEach>
		</table>
	
	</div>
</body>
</html>