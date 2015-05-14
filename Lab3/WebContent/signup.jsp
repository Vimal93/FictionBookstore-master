<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Sign Up</title>
<link media="all" rel="stylesheet" type="text/css" href="css/signup.css">
</head>

<body onload="document.getElementsByTagName('input')[0].focus();">
<%-- <%@ include file="nav.jsp" %> --%>
<jsp:include page="nav.jsp" />
<%
String msg = (String) request.getAttribute("msg");
if(msg==null) msg ="";
String loggedIn = (String) session.getAttribute("loggedIn");
if(loggedIn == null) loggedIn = "";
%>


<%-- If not logged in, display the signup form --%>
	<%  if( !loggedIn.equalsIgnoreCase("true") ) { %>
	    <div id="both_div">
		    <h1>Sign Up Page</h1>
		       	<p><%= msg %></p>
       
       
<%-- If user is logged in, display the logout form instead --%>
       <%} else {%> <%@ include file = 'logout.jsp' %> <%}%>


<div class="tabs">
   
<div class="tab">
<input type="radio" id="tab-1" name="tab-group-1" checked>
<label for="tab-1">Signup as Buyer</label>

<div class="content">
	<form action="<%=request.getContextPath()%>/buyersignup" method="post" id="buyerSignUp">
		<input id= "check" type='text' name='username' placeholder="Username" size='16' maxlength='16'/>
		<input id= "check" type="submit" name='sub1' value='?'>
		
		<input type='text' name='firstName' placeholder="First Name" size='16' maxlength='16' />
		<input type='text' name='middleName' placeholder="Middle Name" size='16' maxlength='16' />
		<input type='text' name='lastName' placeholder="Last Name" size='16' maxlength='16' />
		
		<input type='text' name='email' placeholder="Email Address" size='16' maxlength='16' />
		
		<input type='password' name='password' placeholder="Password" size='16' maxlength='16' />
		<input type='password' name='password2' placeholder="Confirm Password" size='16' maxlength='16' />
		<input type='submit' name='sub2' value='Sign Up' /> <input type='reset' value='Reset' />
	</form>
</div> 
</div>
  
<div class="tab">
<input type="radio" id="tab-2" name="tab-group-1">
<label for="tab-2">Signup as Seller</label>

<div class="content">
	 <form action="<%=request.getContextPath()%>/sellersignup" method="post" id="sellerSignUp">
		<input id= "check" type='text' name='username' placeholder="Username" size='16' maxlength='16' />
		<input id= "check" type="submit" name='sub1' value='?'>
		
		<input type='text' name='firstName' placeholder="First Name" size='16' maxlength='16' />
		<input type='text' name='middleName' placeholder="Middle Name" size='16' maxlength='16' />
		<input type='text' name='lastName' placeholder="Last Name" size='16' maxlength='16' />
		
		<input type='text' name='email' placeholder="Email Address" size='16' maxlength='16' />
		
		<input type='text' name='routingNum' placeholder="Routing #" size='16' maxlength='16' />
		<input type='text' name='accountNum' placeholder="Account #" size='16' maxlength='16' />
		
		<input type='password' name='password' placeholder="Password" size='16' maxlength='16' />
		<input type='password' name='password2' placeholder="Confirm Password" size='16' maxlength='16' />
		<input type='submit' name='sub2' value='Sign Up' /> <input type='reset' value='Reset' />
	</form>
</div> 
</div>
   
</div>

</body>
</html>