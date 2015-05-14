<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Index</title>
<link media="all" rel="stylesheet" type="text/css" href="css/index.css"/>
</head>
<body>
<jsp:include page="nav.jsp" /><br>

<%	
String msg = (String) request.getAttribute("msg");
if(msg==null) msg ="Hello! Welcome to the bookstore!";
String loggedIn = (String) session.getAttribute("loggedIn");
if(loggedIn == null) loggedIn = "";
%>

<div id="msg"><% if(msg.length()!=0) out.print(msg); %></div>
<form action='<%=request.getContextPath()%>/search' method='post'>
	<div id="searchbox">
		<input id="search1" name='search' type='submit' value="&#x2714">
		<input type='text' name='searchbox' placeholder='Enter search query here . . .'size='50' maxlength='80' />
		<select name="srcCat">
			<option value="1" >by Title &#x21AF</option>
			<option value="2" >by ISBN &#x21AF</option>
			<option value="3" >by Author &#x21AF</option>
			<option value="4" >by Genre &#x21AF</option>
		</select>
		<input id="search2" name='search' type='submit' value='search'>
	</div>
</form>


<div id="popBooks">
	<img src="images/HP.jpg"/>
	<img src="images/LR.jpg"/>
	<img src="images/CL.jpg"/>
	<img src="images/GB.jpg"/>
	<img src="images/NC.jpg"/>
</div>
</body>
</html>