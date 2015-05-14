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
<title>Your Wishlist</title>
<link media="all" rel="stylesheet" type="text/css" href="css/wishlist.css">
</head>

<%@ page import="model.User" %> 
<%
User user = (User) session.getAttribute("userObj");
String accountType = (String) session.getAttribute("accountType");
if (user == null) user= new User();
if (accountType == null) accountType = "-1";
int accountType_num = Integer.parseInt(accountType);
%>

<body>
<jsp:include page="nav.jsp" /><br>

<%if ( accountType_num == 1 ) { %>
<c:forEach var="ids" items="${sessionScope.wishlistproducts }" varStatus="loop">
<div id="alt_color">
		<div id="search_result">
		<div id="photo">
				<img src="images/${ids.getName() }.jpg"/>
			</div>
			<div id="result">
<form action="<%=request.getContextPath()%>/WishlistServlet" method="post">
					<table>
					<tr>
						<th>Title</th>
						<th colspan="3">${ids.getName()}</th>
					</tr>			
					<tr><td>ISBN</td><td>${ids.getIsbn()}</td></tr> 
					<tr><td>Author</td><td>${ids.getAuthor()}</td></tr>  
					<tr><td>Genre</td><td>${ids.getGenre() }</td></tr>
					<input id ='cart' type='submit' align='right' name='Remove from Wishlist' value='Remove from Wishlist'/>
					<input type="hidden" name="productId" value="${ids.getItemId()}"/>
					<input type="hidden" name="action" value="remove"/>
					<input type="hidden" name="count" value="${loop.index }"/>
					
					</table>
				</form>
				</div>
				</div>
				</div>

</c:forEach>



<%} else { %>
	<p>Please login as a buyer.</p>
<%}%>

</body>
</html>