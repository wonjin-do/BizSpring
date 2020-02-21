<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
<html>
<head>
	<title>Home</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
	
</head>
<body>




<nav class="navbar navbar-expand-lg navbar-light bg-light">
  <a class="navbar-brand" href="#">Navbar</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>

		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav mr-auto">
				<c:forEach var="childNode" items="${root.childrenNodes}">
					<li class="nav-item dropdown">
						<a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
						 	${childNode.title } 
						 </a>
						<div class="dropdown-menu" aria-labelledby="navbarDropdown">
							<c:forEach var="grandChildNode" items="${childNode.childrenNodes}">
								<a class="dropdown-item" href="#">${grandChildNode.title }</a>
							</c:forEach>
						</div>
					</li>
				</c:forEach>
			</ul>
		</div>
	</nav>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
  <a class="navbar-brand" href="#">Navbar</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>

		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav mr-auto">
				<c:forEach var="childNode" items="${nodeList}">
					<li class="nav-item dropdown">
						<a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
						 	${childNode.title } 
						 </a>
						<div class="dropdown-menu" aria-labelledby="navbarDropdown">
							<c:forEach var="grandChildNode" items="${childNode.childrenNodes}">
								<a class="dropdown-item" href="#">${grandChildNode.title }</a>
							</c:forEach>
						</div>
					</li>
				</c:forEach>
			</ul>
		</div>
	</nav>
<!-- non재귀함수 -->
<!-- <ul> -->
<%-- 	<c:forEach var="navi" varStatus="status" items="${menuMap[(0).intValue()]}"> --%>
<%-- 		<li>${navi.title } --%>
<!-- 			<ul> -->
<%-- 				<c:forEach var="detail" items="${menuMap[(status.index + 1).intValue()]}">  --%>
<%-- 					<li>${detail.title }</li> --%>
<%-- 				</c:forEach> --%>
<!-- 			</ul> -->
<!-- 		</li> -->
<%-- 	</c:forEach> --%>
	
<!-- </ul> -->

<!-- HashMap의 root노드를 기점 -->
<!-- <ul> -->
<%-- 	<c:forEach var="childNode" items="${root.childrenNodes}"> --%>
<%-- 		<li>${childNode.title } --%>
<!-- 			<ul> -->
<%-- 				<c:forEach var="grandChildNode" items="${childNode.childrenNodes}">  --%>
<%-- 					<li>${grandChildNode.title }</li> --%>
<%-- 				</c:forEach> --%>
<!-- 			</ul> -->
<!-- 		</li> -->
<%-- 	</c:forEach> --%>
<!-- </ul> -->
</body>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</html>
