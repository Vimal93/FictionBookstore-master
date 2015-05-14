<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link media="all" rel="stylesheet" type="text/css" href="css/edit_user.css"/>
<title>Edit User</title>
</head>
<body>
<jsp:include page="nav.jsp" /><br>

<div id="div_main">
	<%-- 
	<h2>Edit Listing Titled ${session.currentProduct.title} with ISBN ${session.currentProduct.title}</h2>
	<form action="SellerProductListServlet" method="post">
		<table>
		<tr><td>Title</td><td><label>${sessionScope.currentProduct.getName()}</label></td></tr>
		<tr><td>ISBN</td><td><label>${sessionScope.currentProduct.getIsbn()}</label></td></tr>
		<tr><td>Author</td><td><label>${sessionScope.currentProduct.getAuthor()}</label></td></tr>
		<tr><td>Unit Price</td><td><input type="text" name="price5" value="${sessionScope.currentProduct.getUnitPrice()}" /></td></tr>
		</table>
		<input type="hidden" name="editedProduct" value="${sessionScope.currentProduct.getName() }" />
		<input type="hidden" name="isbn5" value="${sessionScope.currentProduct.getIsbn() }" />
		<input type="submit" align="right" name="sub1" value="Save Product"/>
	</form>
	 --%>
	 <h2>Edit User ${session.username}</h2>
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
</div>
</body>
</html>