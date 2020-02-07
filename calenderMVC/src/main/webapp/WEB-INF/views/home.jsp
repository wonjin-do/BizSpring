<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/resources/css/cal.css">
<script src="https://code.jquery.com/jquery-3.3.1.min.js"
	integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8="
	crossorigin="anonymous"></script>
</head>
<body style="width:; padding: 0 300px;">
	<c:set var="cal" value="${cldVO}" />
	<c:set var="size" value="${fn:length(cal.days)}" />
	<c:set var="width_tot" value="200" />
	<c:set var="width" value="${width_tot/7 }" />
	<div style="width: ${width_tot}; padding: 0 50px;">
		<form action="" method="post">
			<c:choose>
				<c:when test="${ empty customer }">
					<input name="id" type="text" placeholder="아이디">
					<input name="password" type="text" placeholder="비밀번호">
					<input id="login" type="submit" value="로그인" style="flaot: right">
					<a href="/join">회원가입</a>
				</c:when>
				<c:otherwise>
						${customer }님 환영합니다.
						<input id="logout" type="button" value="로그아웃" style="flaot: right">
				</c:otherwise>
			</c:choose>
		</form>
		<table>
			<caption style="font-size: 70px;">
				<input type="button" id="today" value="오늘"><a id="prev" href="">&lt;
				</a>${cal.year } ${cal.month }<a id="next" href=""> &gt;</a>
	
	
			</caption>
			<thead>
				<tr>
					<th>일</th>
					<th>월</th>
					<th>화</th>
					<th>수</th>
					<th>목</th>
					<th>금</th>
					<th>토</th>
				</tr>
			</thead>
			<tbody>
				<c:set var="cell" value="1" />
				<c:forEach var="week" begin="1" end="${cal.numOfRows }">
					<tr>
						<c:forEach var="day" begin="1" end="7">
							<c:choose>
								<c:when test="${cell < cal.dayOfWeek || cell >= cal.dayOfWeek + cal.endDay }">
									<td class="cell" style="background-color: #DCDCDC;opacity: 0.5;">
										<c:choose>
											<c:when test="${day eq 1 || day eq 7 || cal.days[cell][1] eq 1}">
												<div style="color: red; font-weight: bold;">${cal.days[cell][0]}</div>
											</c:when>
											<c:otherwise>
												<div style="color: ; font-weight: bold;">${cal.days[cell][0]}</div>
											</c:otherwise>
										</c:choose>
									</td>
								</c:when>
								<c:otherwise>
									<c:choose>
										<c:when
											test="${day eq 1 || day eq 7 || cal.days[cell][1] eq 1}">
											<td class="cell">
												<div style="color: red; font-weight: bold;">${cal.days[cell][0]}</div>
												<c:if test="${cal.days[cell][1] eq 1 }">
													<div>${cal.meaning[cell]}</div>
												</c:if> <input type="text" value="과제" readonly="readonly">
											</td>
										</c:when>
										<c:otherwise>
											<td class="cell">
												<div style="color:; font-weight: bold;">${cal.days[cell][0]}</div>
												<c:if test="${ !empty cal.meaning[cell] }">
													<div>${cal.meaning[cell]}</div>
												</c:if> <input class="schedule" type="text" value="과제"
												readonly="readonly">
	
											</td>
										</c:otherwise>
									</c:choose>
								</c:otherwise>
							</c:choose>
	
	
	
							<c:set var="cell" value="${cell+1}" />
						</c:forEach>
					</tr>
				</c:forEach>
	
	
			</tbody>
	
		</table>
		
	</div>

	

</body>

</html>
<script>
	var date = new Date();
	var year = date.getFullYear();
	var month = date.getMonth();

	var form = $("form");
	$("#today").click(function(){
		form.empty();
		var input1 = document.createElement('input');
		var input2 = document.createElement('input');
		
		input1.setAttribute("type","hidden");
		input1.setAttribute("name","year");
		input1.setAttribute("value", year);
		
		input2.setAttribute("type","hidden");
		input2.setAttribute("name","month");
		input2.setAttribute("value", month);
		
		form.append(input1);		
		form.append(input2);
		
		form.attr("action","/").attr("method","get").submit();
	})
	
	$("#login").click(function() {
		form.attr("action", "/login");
		form.submit();
	})
	
	$("#logout").click(function() {
		form.attr("action", "/logout").attr("method","get");
		form.submit();
	})
	
	$("#prev").click(function(e) {
		var prevYear = parseInt(${cal.year},10);
		var prevMonth = parseInt(${cal.month},10);
		if(prevMonth==1){
			prevYear--;
			prevMonth=12;
		}else{
			prevMonth--;
		}
		
		$("#prev").attr("href", "/?year="+prevYear+"&month="+prevMonth);
		$('#prev').get(0).click();	
	})
	
	$("#next").click(function(e) {
		//e.preventDefault();
		var nextYear = parseInt(${cal.year},10);
		var nextMonth = parseInt(${cal.month},10);
		if(nextMonth==12){
			nextYear++;
			nextMonth=1;
		}else{
			nextMonth++;
		}
		
		$("#next").attr("href", "/?year="+nextYear+"&month="+nextMonth);
		$('#next').get(0).click();	
	})


</script>
