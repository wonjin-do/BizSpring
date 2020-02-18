<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
	<title>Home</title>
	<link rel="stylesheet" href="/controller/resources/cal.css">
</head>
<body>
	학생별 
	<table>
			<caption style="font-size: 70px;">
			
			</caption>
			<thead>
				<tr>
					<th>name</th>
					<th>english</th>
					<th>math</th>
					<th>korean</th>
				</tr>
			</thead>
			<!-- 달력출력  -->
			
			<tbody>
				<c:forEach var="studentEntry" varStatus="status" begin="0" items="${unsortedBySTU }">
					<tr>
						<td>${studentEntry.key } </td>
						<c:forEach var="subjectEntry" varStatus="status" items="${studentEntry.value.scoreMap}">
							<td>
								${subjectEntry.value.score }	
							</td>
						</c:forEach>
					</tr>
				</c:forEach>
				<tr>
					<td>계</td>
					<c:forEach var="subjectEntry" items="${unsortedBySub }">
						<td>
							${subjectEntry.value.total }						
						</td>
					</c:forEach>
				</tr>
				<tr>
					<td>응시인원</td>
					<c:forEach var="subjectEntry" items="${unsortedBySub }">
						<td>
							${subjectEntry.value.numOfStu }						
						</td>
					</c:forEach>
				</tr>
				<tr>
					<td>평균</td>
					<c:forEach var="subjectEntry" items="${unsortedBySub }">
						<td>
							${subjectEntry.value.avg }						
						</td>
					</c:forEach>
				</tr>
			</tbody>
		</table>
		
		
		option: ${option }
		<table>
			<caption style="font-size: 70px;">
			
			</caption>
			<thead>
				<tr>
					<th>name</th>
					<th>english</th>
					<th>math</th>
					<th>korean</th>
				</tr>
			</thead>
			<!-- 달력출력  -->
			<tbody>
				<c:forEach var="studentEntry" varStatus="status" items="${sortedResBySTU }">
					<tr>
						<td>${studentEntry.key }
						 	<c:choose>
						 		<c:when test="${status.index == 0 }">+</c:when>
						 		<c:when test="${status.index == 9 }">-</c:when>
						 	</c:choose>      
						 </td>
						<c:forEach var="subjectEntry" varStatus="status" items="${studentEntry.value.scoreMap}">
							<td>
								${subjectEntry.value.score }	
							</td>
						</c:forEach>
					</tr>
				</c:forEach>
				<tr>
					<td>계</td>
					<c:forEach var="subjectEntry" items="${unsortedBySub }">
						<td>
							${subjectEntry.value.total }						
						</td>
					</c:forEach>
				</tr>
				<tr>
					<td>응시인원</td>
					<c:forEach var="subjectEntry" items="${unsortedBySub }">
						<td>
							${subjectEntry.value.numOfStu }						
						</td>
					</c:forEach>
				</tr>
				<tr>
					<td>평균</td>
					<c:forEach var="subjectEntry" items="${unsortedBySub }">
						<td>
							${subjectEntry.value.avg }						
						</td>
					</c:forEach>
				</tr>
			</tbody>
		</table>
</body>
</html>
