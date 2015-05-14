<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Shopping Cart</title>
<link media="all" rel="stylesheet" type="text/css" href="css/cart.css"/>
</head>
<body>
<jsp:include page="nav.jsp" /><br>

<%	
String subtotal = (String) session.getAttribute("subtotal");
String accountType = (String) session.getAttribute("accountType");
if (accountType == null) accountType = "-1";
int accountType_num = Integer.parseInt(accountType);
String msg = (String) request.getAttribute("msg");
if(msg==null) msg ="";
%>

<%if ( accountType_num == 1 ) { %>
<p><% if(msg.length()!=0) out.print("<center><p><h4>"+msg+"</h4></p></center>"); %></p>
	
	<c:forEach var="ids" items="${sessionScope.userObj.getCart().getProducts()}" varStatus="loop">
		<div id="alt_color">
			<div id="cart_content">
				<div id="photo">
					<img src="images/${ids.getName() }.jpg"/>
				</div>
			<div id="result">
			<form action='<%=request.getContextPath()%>/cart' method='post'>
				<table>
					<tr>
						<th>Title</th>
						<th colspan="3">${ids.getName()}</th>
					</tr>
					<tr>
						<td>Quantity</td><td>${ids.getQuantity()}</td>
						<td rowspan="2">
						<input type="submit" align="right" name="Update" value="Update"/>
						<input type="text" name="updatequantity" placeholder="Update Quantity"/>
						<input type="submit" align="right" name="Remove" value="Remove"/>
						</td>
					</tr>
					<tr><td>Unit Price</td><td>$ ${ids.getUnitPrice()}</td></tr>
					<tr><td>Genre</td><td>${ids.getGenre()}</td></tr>
					<tr><td>Seller Name</td><td>${ids.getSellerName()}</td></tr>
					<tr><td>Seller Id</td><td>${ids.getSellerId()}</td></tr>
					<input type="hidden" name="productId" value="${ids.getItemId()}"/>
					<input type="hidden" name="sellerId" value="${ids.getSellerId()}"/>
					
					</table>
				</form>
			</div>
		</div>
	</c:forEach>
	
	<div id="subtotal">
	<label>Subtotal: $ ${sessionScope.userObj.getCart().getTotalPrice()}</label>
	<a href="<%=request.getContextPath()%>/checkout.jsp"><button type=submit>Checkout</button></a>
	<a href="<%=request.getContextPath()%>/index.jsp"><button type=submit>Continue Shopping</button></a>
	</div>

<%} else { %>
	<p>Please login as a buyer.</p>
<%}%>	

</body>
</html>