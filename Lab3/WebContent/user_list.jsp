<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link media="all" rel="stylesheet" type="text/css" href="css/user_list.css">
<title>User list</title>
<%@ page import="model.User" %>
</head>
<body>
<jsp:include page="nav.jsp" />
<div id=open></div>

<div id=div_main>

<%	
String msg = (String) request.getAttribute("msg");
if(msg==null) msg ="";
String accountType = (String) session.getAttribute("accountType");
if (accountType == null) accountType = "-1";
int accountType_num = Integer.parseInt(accountType);
User user=(User)session.getAttribute("userObj");
if (user == null) user= new User();
%>

<p><% if(msg.length()!=0) out.print("<center><p>"+msg+"</p></center>"); %></p>

<h2>List of users registered in our marketplace</h2>

<%if ( accountType_num == 0 ) { %>

      <%-- Have to add a servlet name here after its creation --%>
<table>
	<tr>
		<th>Username</th>
		<th>First Name</th>
		<th>Last Name</th>
		<th>Account Type</th>
		<th colspan="3"></th>
	</tr>
	
	<c:forEach var="ids" items="${sessionScope.users}" varStatus="loop">
	    <tr>
		    <td>${ids.getUserName() } </td>
		    <td>${ids.getFirstName() } </td>
		    <td>${ids.getLastName() } </td>
		    <td>${ids.getaccountType() } </td> 
		    <td><input type="submit" name="sub1" value="Contact User" /></td>
		    <form action="<%=request.getContextPath()%>/DeleteUserServlet" method='post'>
				<input type="hidden" name="user_id" value="${ids.getUserId() }"/>
				<input type="hidden" name="accountType" value="<%=accountType_num%>"/>
				<input type="hidden" name="other_accountType" value="${ids.getaccountType() }"/>
			<td><input type='submit' align='right' name='Delete Account' value='Delete Account'/> </form> </td>
	    </tr>
	</c:forEach>
</table>
</form>
</div>

<%} else { %>
	<p>Please login as an administrator.</p>
<%}%>	

</body>
</html>