<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Order Details</title>
<%@ page import="model.User" %> 
</head>
<body>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Shopping Cart</title>
<link media="all" rel="stylesheet" type="text/css" href="css/order.css"/>
</head>
<body>
<jsp:include page="nav.jsp" /><br>

<!-- Search results -->
<div id="date">
<%
User user=(User)session.getAttribute("userObj");
if (user == null) user= new User(); 
if((user!=null)&&(user.getaccountType()==1)){%>Your orders purchased on Date: ${sessionScope.viewOrder.getDateTime()}
<%}else{%>Buyer: "${sessionScope.viewOrder.getC().getUserName()}" has purchased on Date: ${sessionScope.viewOrder.getDateTime()}<%}%>
</div>
<c:forEach var="ids" items="${sessionScope.viewOrder.getC().getProducts()}" varStatus="loop">
	<div id="alt_color">
		<div id="order_product">
			<div id="photo">
				<img src="images/${ids.getName() }.jpg"/>
			</div>
			<div id="result">
				<table>
					<tr><th colspan="2">${ids.getName()}<th></tr>
					<tr><td>Quantity</td><td>${ids.getQuantity()}</td></tr>
					<tr><td>Unit Price</td><td>${ids.getUnitPrice()}</td></tr>
					<tr><td>Seller Name</td><td>${ids.getSellerName()}</td></tr>
				</table>
			</div>
			
			<form action="<%=request.getContextPath()%>/reviewpage" method='post'>
				<table>
					<input type="hidden" name="productTitle" value="${ids.getName()}"/>
					<input type="hidden" name="itemId" value="${ids.getItemId()}"/>
					<% if((user!=null)&&(user.getaccountType()==1)) out.print("<input type='submit' align='right' name='Remove' value='Review'/>");%>
				</table>
			</form>
		</div>
	</div>
</c:forEach>


<div id="subtotal">
<label>Total Cost: $${sessionScope.viewOrder.getC().getTotalCost() }</label>
</div>
</body>
</html>

