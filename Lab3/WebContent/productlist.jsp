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
<link media="all" rel="stylesheet" type="text/css" href="css/productlist.css">
<title>Products</title>
</head>
<body>
<jsp:include page="nav.jsp" /><br>
<%@ page import="model.User" %> 

<%
String msg = (String) request.getAttribute("msg");
if(msg==null) msg ="";
User user=(User)session.getAttribute("userObj");
%>
<div id=div_main>
		
		<div id="msg"><% if(msg.length()!=0) out.print(msg); %></div>
		
		<table>
			<tr>
				<th>Name</th>
				<th>Item ID</th>
				<th>Unit price</th>
				<th colspan="3">ISBN</th>
			</tr>
			<c:forEach var="ids" items="${sessionScope.products}" varStatus="loop">
				<tr>
					<td>${ids.getName()}</td>
					<td>${ids.getItemId() }</td>
					<td>${ids.getUnitPrice() }</td>
					<td>${ids.getIsbn() }</td>
					<%-- <%if(user.getaccountType()==0) { %><td>${ids.getSellerName() }<% } %> --%>
					<form action='<%=request.getContextPath()%>/DeleteProductServlet' method='post'>
					    <input type="hidden" name="product_id" value="${ids.getItemId() }" />
					    <input type="hidden" name="seller_id" value="${ids.getSellerId() }" />
					    <td><input type="submit" name="sub1" value="Delete" /></td>
					 </form>
					 
					
					<form action='<%=request.getContextPath()%>/SellerProductListServlet' method='post'>
					    <input type="hidden" name="title" value="${ids.getItemId() }" />
					    <input type="hidden" name="isbn" value="${ids.getItemId() }" />
					    <input type="hidden" name="currentId" value="${ids.getItemId() }" />
						<td><input type="submit" name="sub2" value="Edit" /></td>
					</form>
				</tr>
			</c:forEach>
			<tr>
		</table>
	<form action='<%=request.getContextPath()%>/new_listing.jsp' method='post'>
		<input id="add" type="submit" name="sub3" value="Add Product" />
	</form>
</div>

</body>
</html>