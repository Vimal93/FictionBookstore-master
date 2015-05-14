<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ page import="model.User" %> 
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Search Results</title>
<link media="all" rel="stylesheet" type="text/css" href="css/search_result.css">
</head>
<body>
<jsp:include page="nav.jsp" /><br>
<%
ServletContext sc = getServletContext();
String path = sc.getContextPath();
String msg = (String) request.getAttribute("msg");
if(msg==null) msg ="";
User user=(User)session.getAttribute("userObj");
if (user == null) { user= new User(); }
%>

<!-- Search results -->
<div id="msg"><% if(msg.length()!=0) out.print(msg); %></div>
<c:forEach var="ids" items="${sessionScope.search }" varStatus="loop">
	<div id="alt_color">
		<div id="search_result">
			<div id="photo">
				<img src="images/${ids.getName()}.jpg"/>
			</div>
			<div id="result">
				<table>
					<tr>
					<th>Title</th>
					<th>
						<form action="<%=request.getContextPath()%>/ViewReviewServlet" method='post'>
							<input type="hidden" name="product_id" value="${ids.getItemId()}"/>
							<input type="hidden" name='name' value='${ids.getName()}'/>
							<input type="hidden" name='author' value='${ids.getAuthor()}'/>
							<!-- VIEW REVIEWS BUTTON -->
							<input id ="view_reviews" type='submit' align='right' name='View Review' value='${ids.getName()}'/>	
						</form>
					</th>
					</tr>
				</table>
					
				<table>
				<form action="cart" method="post">
					
			
					<tr><td>ISBN</td><td>${ids.getIsbn()}</td></tr> 
					<tr><td>Author</td><td>${ids.getAuthor()}</td></tr>  
					<tr><td>UnitPrice</td><td>$ ${ids.getUnitPrice() }</td></tr>
					<tr><td>Genre</td><td>${ids.getGenre() }</td></tr>
					<tr><td>Seller Name</td><td>${ids.getSellerName() }</td></tr>
					<input type="hidden" name="productId" value="${ids.getItemId()}"/>
					<input type="hidden" name="sellerId" value="${ids.getSellerId()}"/>
					
					<% 
					if((user!=null)&&(user.getaccountType()==1)) 
						/* QUANTITY BUTTON AND ADD TO CART BUTTON */
						out.print("<input type='text' name='quantity' placeholder='Enter Quantity'/><input id='cart' type='submit' align='right' name='Add' value='Add to Cart'/>");
					%>	
					
				</form>
				
				<form action="<%=request.getContextPath()%>/WishlistServlet" method="post">
					<input type="hidden" name="productId" value="${ids.getItemId()}"/>
					<input type="hidden" name="action" value="add"/>
			<% if((user!=null)&&(user.getaccountType()==1)) {%>
				<input id ='cart' type='submit' align='right' name='Add to Wishlist' value='Add to Wishlist'/>
				<input type="hidden" name="count" value="${loop.index }"/>
				
				<% } %>
				</form>
			
				</table>
			</div>
		</div>
	</div>
</c:forEach>
</body>
</html>