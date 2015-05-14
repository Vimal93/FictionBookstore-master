<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Purchase Confirmation</title>
<link media="all" rel="stylesheet" type="text/css"
	href="css/purchase.css">
</head>
<body>

	<jsp:include page="nav.jsp" />

	<%-- I want a nice whitespeace buffer between nav bar and the following text --%>
	<div id="div_middle">

		<div id="div_title">
			<p>Order complete! Congrats on your purchase!</p>
			<p>You will receive an email notification soon!</p>
		</div>



		<div id="div_top">
			<table>
				<col span="1" style="width: 30%">
				<tr><th colspan="2">Your products will be shipped to the address</th></tr>
				<tr><td>Name</td><td>${sessionScope.userObj.getFirstName() } ${sessionScope.userObj.getLastName() }</td></tr>
				<tr><td>Address</td><td>${sessionScope.order.getShippingAddress() }</td></tr>
				<tr><td colspan="2">Please wait for your notification.</td></tr>
			</table>
		</div>

		<div id="div_bottom">
			<table>
				<col span="1" style="width: 30%">
				<tr>
					<th colspan="2">Here is a summary of what you'll be receiving
						in the mail</th>
				</tr>
				<tr>
					<td>Order ID</td>
					<td>${sessionScope.order.getOrderId() }</td>
				</tr>
				<!-- insert lising of products -->
				<tr>
					<td>Number of Products</td>
					<td>${sessionScope.order.getC().getProducts().size() }</td>
				</tr>
				<tr>
					<td>Total Cost</td>
					<td>${sessionScope.order.getC().getTotalCost() }</td>
				</tr>
				<tr>
					<td colspan="2">Please wait for your notification.</td>
				</tr>
			</table>
		</div>


	</div>

</body>
</html>