<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Review Product</title>
<link media="all" rel="stylesheet" type="text/css" href="css/review.css">
</head>

<body>

<jsp:include page="nav.jsp" />

<%	
	String msg = (String) request.getAttribute("msg");
	if(msg==null) msg ="";
	String title = (String) session.getAttribute("productTitle");
	String itemId = (String) session.getAttribute("itemId");
	String accountType = (String) session.getAttribute("accountType");
	
	if(title==null) title ="Placeholder Title if Title is NULL.";
	if(itemId==null) title ="-1";
	if (accountType == null) accountType = "-1";
	
	int accountType_num = Integer.parseInt(accountType);
	//int itemId_num = Integer.parseInt(itemId);
	System.out.println("Item ID:  "+ itemId);
%>

<%if ( accountType_num == 1 ) { %>

<p><% if(msg.length()!=0) out.print("<center><p>"+msg+"</p></center>"); %></p>

	<div id="main_div">
		<h1>
			Add a review for "<%= title %>"
		</h1>
		<form action='<%=request.getContextPath()%>/review' method='post'>
			<input id="like" type="radio" name="radio" value="like"><label
				for="like">Like</label> OR <input id="dislike" type="radio"
				name="radio" value="dislike"><label for="dislike">Dislike</label>

			<br>
			<br>
			<p>Review (Max 255 characters)</p>
			<textarea rows="2" cols="20" name='review'
				placeholder="Type your review here . . ." maxlength='255' /></textarea>
				<input type="hidden" name="itemId" value="${itemId}"/>
			<input type='submit' value='Submit'>
		</form>
	</div>

<%} else { %>
	<p>Please login as a buyer.</p>
<%}%>	

</body>
</html>