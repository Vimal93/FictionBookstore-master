<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link media="all" rel="stylesheet" type="text/css" href="css/edit_listing.css"/>
<title>Edit Product</title>
</head>
<body>
<jsp:include page="nav.jsp" />
<%
String accountType = (String) session.getAttribute("accountType");
if (accountType == null) accountType = "-1";
int accountType_num = Integer.parseInt(accountType);
String msg = (String) request.getAttribute("msg");
if(msg==null) msg ="";
session.setAttribute("PhotoUploaderMode", "Edit");
%>

<%if ( accountType_num == 2 ) { %>

<div id=open></div>
<div id="msg"><% if(msg.length()!=0) out.print("<center><p><h4>"+msg+"</h4></p></center>"); %></div>
<div id="div_main">
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
	<p align="center">Upload file selection</p>
	<form method="post" action="<%=request.getContextPath()%>/PhotoServlet" enctype="multipart/form-data">
		<table width="75%" border="1" align="center">		
		<tr><td><div align="center"><input type="file" name="myfile" size="30"></div></td></tr>
		<tr><td><div align="center"><input type="hidden" name="test" value="Edit" /><input type="submit" name="Submit" value="Upload it"></div></td></tr>
		</table>
	</form>
</div>

<%} else if ( accountType_num == 0 ) { %>
	<p>Please contact seller to edit this product.</p>

<%} else { %>
	<p>Please login as a seller.</p>
<%}%>

</body>
</html>