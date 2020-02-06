<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
<body>
	<c:set var="cal" value="${cldVO}"/>
	<c:set var="size" value="${fn:length(cal.days)}"/>
	<c:set var="width_tot" value="490"/> 
	<c:set var="width" value="${width_tot/7 }"/>
	
	<div style="">
		<div id=head style="width: ${width_tot}px"><input type="button" value="오늘"><a id="prev" href="">&lt; </a>${cal.year } ${cal.month }<a id="next"  href=""> &gt;</a></div>
		<div class="dayName" style="width: ${width}px" >일</div>
		<div class="dayName" style="width: ${width}px" >월</div>
		<div class="dayName" style="width: ${width}px" >화</div>
		<div class="dayName" style="width: ${width}px" >수</div>
		<div class="dayName" style="width: ${width}px" >목</div>
		<div class="dayName" style="width: ${width}px" >금</div>
		<div class="dayName" style="width: ${width}px" >토</div>
		<br><br>
	
	
	<c:forEach var="day" begin="1" end="${size-1}">
			
			<div class="day" style="width: ${width}px" >
				${cal.days[day]}
			</div>
			<c:if test="${day % 7 eq 0}"><br><br><br></c:if>
		
		
	</c:forEach>
	</div>
		
</body>

</html>
<script>
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
