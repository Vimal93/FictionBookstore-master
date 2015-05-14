<link media="all" rel="stylesheet" type="text/css" href="css/nav.css">
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>


<% //variable path used to locate the webpage links
ServletContext sc = getServletContext();
String path = sc.getContextPath();
%>

<%@ page import="model.User" %> 
<%@ page import="model.Administrator" %>
<%@ page import="model.Buyer" %> 
<%@ page import="model.Seller" %> 

<%
//String username = (String) session.getAttribute("username");
String accountType = (String) session.getAttribute("accountType");
String loggedIn = (String) session.getAttribute ("loggedIn");
//if (username==null) username= "";
if (accountType == null) accountType = "-1";
if (loggedIn == null) loggedIn = "";
int accountType_num = Integer.parseInt(accountType);

User user;
if (accountType_num==0) { user = (Administrator) session.getAttribute("userObj");}
else if (accountType_num==1) { user = (Buyer) session.getAttribute("userObj");}
else if (accountType_num==2) { user = (Seller) session.getAttribute("userObj");}
else { user = (User) session.getAttribute("userObj"); }
if (user == null) { user= new User(); }
%>

<ul id="navStl">
<%-- Logic for nav bar switching here --%>

<%-- 0 = Admin --%>
<% if ( user.getaccountType()==0 ) { %>
	<li><a href="${pageContext.request.contextPath}/logout">Logout</a></li>
	<li><a href="<%=path %>/index.jsp">Home</a></li>
	<li><a href="<%=path %>/productlist.jsp">View Products</a></li>
	<li><a href="<%=path %>/orderlist.jsp">View Orders</a></li>
	<li><a href="<%=path %>/user_list.jsp">View Users</a></li>
	<li><a<%--  href="<%=path %>/edit_user.jsp" --%>><%=user.getUserName() %></a></li>
	
<%-- 1 = Buyer --%>
<% } else if ( user.getaccountType()==1 ) { %>
	<li><a href="${pageContext.request.contextPath}/logout">Logout</a></li>
	<c:choose>
    <c:when test="${sessionScope.updateOrder==null}">
	<li><a href="<%=path %>/cart.jsp">Cart(${sessionScope.userObj.getCart().getProducts().size()})</a></li>
	</c:when>
	<c:otherwise>
	<li><a href="<%=path %>/update_order.jsp">Cart(${sessionScope.userObj.getCart().getProducts().size()})</a></li>
	</c:otherwise>
	</c:choose>
	<li><a href="<%=path %>/wishlist.jsp">Wishlist</a></li>
	<li><a href="<%=path %>/orderlist.jsp">View Orders</a></li>
	<li><a href="<%=path %>/index.jsp">Home</a></li>
	<li><a<%--  href="<%=path %>/edit_user.jsp" --%>><%=user.getUserName() %></a></li>
	
<%-- 2 = Seller --%>
<%} else if ( user.getaccountType()==2 ) { %>
	<li><a href="${pageContext.request.contextPath}/logout">Logout</a></li>
	<li><a href="<%=path %>/orderlist.jsp">View Orders</a></li>
	<li><a href="<%=path %>/productlist.jsp">View Products</a></li>
	<li><a href="<%=path %>/index.jsp">Home</a></li>
	<li><a href="<%=path %>/update_user.jsp"><%=user.getUserName() %></a></li>
	
<%} else { %>
	<li><a href="<%=path %>/signup.jsp">Signup</a></li>
	<li><a href="<%=path %>/login.jsp">Login</a></li>
	<li><a href="<%=path %>/index.jsp">Home</a></li>
<%}%>			
</ul>

