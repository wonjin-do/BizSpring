<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<html>
<head>
<title>Home</title>
<link rel="stylesheet" href="/resources/cal.css">
</head>
<body>
	<form name="frm" method="get">
		<c:set var="avg" value="avg"/>
		<select name="option" onchange="this.form.submit()">
			<c:forEach var="sub" items="${titleKeys }">
				<c:if test="${sub ne avg }">
					<option value='${sub }'	<c:if test="${option eq sub}">selected="selected"</c:if>>
						${translateMap[sub]}
					</option>
				</c:if>
			</c:forEach>
		</select>
	</form>
	<table>
		<caption style="font-size: 70px;"></caption>
		<thead>
			<tr>
				<th>name</th>
				<c:forEach var="sub" items="${titleKeys }">
					<th>${translateMap[sub]}</th>
				</c:forEach>
			</tr>
		</thead>
		<tbody>
			<!-- 학생 과목별 성적, 학생별 총계,학생별 평균 -->
			<c:forEach var="entry" items="${scoreBoard}">
				<tr>
					<td>${entry.key }</td>
					<c:set var="subMap" value="${entry.value }" />
					<c:forEach var="title" items="${titleKeys}">
						<c:set var="studentTitle" value="${entry.key }${title }" />
						<c:set var="titleRank" value="${title }rank" />
						<td class="score ${subMap[studentTitle]}">
							<div class="num">${subMap[title]}</div>
							<div class="mark">${subMap[titleRank] }</div>
						</td>
					</c:forEach>
				</tr>
			</c:forEach>

			<!-- 맨 아래 두줄: 과목별 성적결과 -->
			<c:forEach var="entry" items="${resultBoard }">
				<tr>
					<td class="res">${translateMap[entry.key] }</td>
					<c:set var="subMap" value="${entry.value }" />
					<c:forEach var="title" items="${titleKeys }">
						<td class="res"><div class="num">${subMap[title]}</div></td>
					</c:forEach>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
<script>
	function myFunction(op){
		document.frm.action = '/score?option='+op;
		//document.frm.submit();
	}
</script>
</html>
