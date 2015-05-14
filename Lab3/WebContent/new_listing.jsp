<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>New Product</title>
<link media="all" rel="stylesheet" type="text/css" href="css/new_listing.css"/>
</head>
<body>
<jsp:include page="nav.jsp" />
<%
String accountType = (String) session.getAttribute("accountType");
if (accountType == null) accountType = "-1";
int accountType_num = Integer.parseInt(accountType);
String msg = (String) request.getAttribute("msg");
if(msg==null) msg ="";
session.setAttribute("PhotoUploaderMode", "Add");
%>

<%if ( accountType_num == 2 ) { %>
<div id=open></div>
<div id="msg"><% if(msg.length()!=0) out.print("<center><p><h4>"+msg+"</h4></p></center>"); %></div>
<div id="div_main">
	<h2>Add Product</h2>
	
	<form action="<%=request.getContextPath()%>/SellerProductListServlet" method="post">
		<table>
		<tr>
		<td>Title</td><td><input type="text" name="title" /></td>
		<td>ISBN</td><td><input type="text" name="isbn" /></td>
		</tr>
		<tr>
		<td>Author</td><td><input type="text" name="author" /></td>
		<td>Price</td><td><input type="text" name="price" /></td>
		<tr>
		<td>Genre</td><td><input type="text" name="genre" /> </td>
		</tr>
		<tr><td colspan="2">Description</td></tr>
		<tr><td colspan="3"><textarea name="desc" rows="8" cols="38"></textarea></td></tr>
		</table> 		
		<input type="submit" align="right" name="sub" value="Add Product"/>
	</form>
	<p align="center">Upload file selection</p>
	<form action="<%=request.getContextPath()%>/PhotoServlet" method="post" enctype="multipart/form-data">
		<input type="hidden" name="test" value="Add" />
		<table width="75%" border="1" align="center">		
		<tr><td><div align="center"><input type="file" name="myfile" size="30" /></div></td></tr>
		<tr><td><div align="center"><input type="submit" name="submit" value="Upload it" /></div></td></tr>
		</table><br><br>
	</form>
	
	 
</div>
<%} else { %>
	<p>Please login as a seller.</p>
<%}%>
</body>
</html>