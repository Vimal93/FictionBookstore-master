<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link media="all" rel="stylesheet" type="text/css" href="css/checkout.css"/>
<title>CheckOut</title>
</head>
<body>
<jsp:include page="nav.jsp" />


<%	
String accountType = (String) session.getAttribute("accountType");
if (accountType == null) accountType = "-1";
int accountType_num = Integer.parseInt(accountType);
String msg = (String) request.getAttribute("msg");
if(msg==null) msg ="";
%>

<%if ( accountType_num == 1 ) { %>

	<div id=open></div>
	<div id="div_main">
	<h1>CHECKOUT</h1>
	<p><% if(msg.length()!=0) out.print("<center><p>"+msg+"</p></center>"); %></p>
	<form action="<%=request.getContextPath()%>/order" method="post">
		<div id='shippingform'>
				<p id="title">Shipping Details</p>
				<input type="text" size="50" name="shippingaddr" placeholder="Shipping Address" value="${shippingaddr }">
				<center><div style="color: #A10202;">${errorShippingAddr}</div></center>
				<input type="text" id="widthOf" size="15" name="city" placeholder="City" value="${city }">
				<select name="state">
					<option value="AL">AL</option>
					<option value="AK">AK</option>
					<option value="AZ">AZ</option>
					<option value="AR">AR</option>
					<option value="CA">CA</option>
					<option value="CO">CO</option>
					<option value="CT">CT</option>
					<option value="DE">DE</option>
					<option value="DC">DC</option>
					<option value="FL">FL</option>
					<option value="GA">GA</option>
					<option value="HI">HI</option>
					<option value="ID">ID</option>
					<option value="IL">IL</option>
					<option value="IN">IN</option>
					<option value="IA">IA</option>
					<option value="KS">KS</option>
					<option value="KY">KY</option>
					<option value="LA">LA</option>
					<option value="ME">ME</option>
					<option value="MD">MD</option>
					<option value="MA">MA</option>
					<option value="MI">MI</option>
					<option value="MN">MN</option>
					<option value="MS">MS</option>
					<option value="MO">MO</option>
					<option value="MT">MT</option>
					<option value="NE">NE</option>
					<option value="NV">NV</option>
					<option value="NH">NH</option>
					<option value="NJ">NJ</option>
					<option value="NM">NM</option>
					<option value="NY">NY</option>
					<option value="NC">NC</option>
					<option value="ND">ND</option>
					<option value="OH">OH</option>
					<option value="OK">OK</option>
					<option value="OR">OR</option>
					<option value="PA">PA</option>
					<option value="RI">RI</option>
					<option value="SC">SC</option>
					<option value="SD">SD</option>
					<option value="TN">TN</option>
					<option value="TX">TX</option>
					<option value="UT">UT</option>
					<option value="VT">VT</option>
					<option value="VA">VA</option>
					<option value="WA">WA</option>
					<option value="WV">WV</option>
					<option value="WI">WI</option>
					<option value="WY">WY</option>
				</select>
				<center><div style="color: #A10202;">${errorCity}</div></center>
				<center><div style="color: #A10202;">${errorState}</div></center>
				<input type="text" size="10" name="zip" placeholder="Zip Code" value="${zip }">
				<center><div style="color: #A10202;">${errorZip}</div></center>
				<input type="text" size="50" name="country" placeholder="Country" value="${country }">
				<center><div style="color: #A10202;">${errorCountry}</div></center>
				<input type="text" size="50" name="phoneno" placeholder="Phone number" value="${phoneno }">
				<center><div style="color: #A10202;">${errorPhoneNumber}</div></center>
			</div>
		
		<div id="ordersummary" style="background-color:#7cfca4"><p id="title">Order Summary</p></div>
		<div id="ordersummary">
			<div id="left">
			<%if(session.getAttribute("updateOrder")!=null) { %>Order Id <% } %>
				<p>Items</p>
				<p>Tax</p>
				<p>Shipping Cost</p>
				<p>Subtotal</p>
				<p>Total Cost</p>
			</div>
			<div id="right">
			<%if(session.getAttribute("updateOrder")!=null) { %>${sessionScope.updateOrder.getOrderId()} <% } %>
				<p>${sessionScope.userObj.getCart().getProducts().size()}</p>
				<p>${sessionScope.userObj.getCart().getTax()}</p>
				<p>Free Shipping</p>
				<p>${sessionScope.userObj.getCart().getTotalPrice()}</p>
				<p>${sessionScope.userObj.getCart().getTotalCost()} </p>
			</div>
			<input type="image" src="images/paypal.png" alt="Submit">
			<input type="hidden" name="updateorder" value="${sessionScope.update }">
		</div>
		</form>
		
	</div>

<%} else { %>
	<p>Please login as a buyer.</p>
<%}%>	

</body>
</html>