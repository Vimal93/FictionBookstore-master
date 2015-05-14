<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Reviews for ${name}</title>
<link media="all" rel="stylesheet" type="text/css" href="css/view_reviews.css"/>
</head>
<body>
<%@ page import="model.Product" %>
<jsp:include page="nav.jsp" />

<%
ServletContext sc = getServletContext();
String path = sc.getContextPath();
String msg = (String) request.getAttribute("msg");
if(msg==null) msg ="";
String name = (String) request.getAttribute("name");
if(name==null) name ="";
String author = (String) request.getAttribute("author");
if(author==null) name ="";
Product p=(Product)session.getAttribute("currentReviewProduct");
%>

<div id=open></div>

<h1>${name}<label> by ${author}</label></h1>
<div id=photo><img src="images/<%=p.getName() %>.jpg"/></div>   <%-- Save image URL to session variable and print it here --%>
<div id="casing">

<%--
	<!-- PLACE HOLDER -->
	<div id=photo><img src="images/HP.jpg"/></div>
	<div id="review">
		<label>Pothead reviewed this</label>
		<div id=text>This is arguably the most "hyped" book in history, and if J.K. Rowling had to sneak down 
		to the kitchen for a glass of red wine to calm her nerves while writing The Goblet of Fire (as she said
		she did), one wonders what assuaged her while writing Harry Potter and the Deathly Hallows. The collective
		 breath of tens of millions of readers has been held for two years...and now...was it worth the wait? Did Ms.
		  Rowling live up to the hype? (For that, amongst hundreds of questions, is really the only question that matters.)</div>
	</div>
--%>
	
	<!-- EDIT DISPLAY CODE HERE AND REMOVE THE TOP PART -->
	<c:forEach var="ids" items="${sessionScope.reviews}" varStatus="loop">
		<%-- <div id=photo><img src="${session.reviews[loop.index].photoLink}"/></div> --%>
		<div id="review">
			<label>${ids.getReviewer()} reviewed this - </label> 
			
			<c:choose>
      			<c:when test="${ids.getLikedislike()=='1'}">Liked</c:when>
				<c:otherwise>Disliked</c:otherwise>
			</c:choose>
			
			<div id=text>${ids.getComment()}</div>
		</div>
	</c:forEach>
	
	<%-- Add if statement for like/dislike --%>
	
	
</div>
</body>
</html>