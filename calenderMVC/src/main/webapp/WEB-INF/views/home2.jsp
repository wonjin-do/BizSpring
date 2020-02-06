<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/resources/css/cal.css">
</head>
<body>
	<c:set var="cal" value="${calendarVO}"/>

	<div>
		<div id=head>
			
		</div><br>
		<c:forEach var="j" begin="1" end="7">
			<div class="dayName" ></div>
			<c:if test="${j eq 7}"><br></c:if>
		</c:forEach>
		
	<c:set var="cell" value="1"/>
	<c:forEach var="i" begin="1" end="5">
		<c:forEach var="j" begin="1" end="7">
			<div class="day" >
			</div>
			<c:set var="cell" value="${cell+1 }"/>
			<c:if test="${j eq 7}"><br></c:if>
		</c:forEach>
		
	</c:forEach>
	</div>
	
	
</body>
</html>
