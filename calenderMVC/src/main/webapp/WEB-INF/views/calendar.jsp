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
<!-- Bootstrap Core CSS -->
<link href="/resources/vendor/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">

<script src="https://code.jquery.com/jquery-3.3.1.min.js"
	integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8="
	crossorigin="anonymous"></script>
<!-- Bootstrap Core JavaScript -->
<script src="/resources/vendor/bootstrap/js/bootstrap.min.js"></script>

<!-- Metis Menu Plugin JavaScript -->
<script src="/resources/vendor/metisMenu/metisMenu.min.js"></script>

</head>
<body style="width:; padding: 0 300px;">
	<c:set var="width_tot" value="200" />
	<c:set var="width" value="${width_tot/7 }" />
	<div style="width: ${width_tot}; padding: 0 50px;">
		<form action="" method="post">
			<c:choose>
				<c:when test="${ empty customer }">
					<input name="id" type="text" placeholder="아이디">
					<input name="password" type="text" placeholder="비밀번호">
					<input id="login" type="submit" value="로그인" style="flaot: right">
					<a href="/join?year=${calendar.year }&month=${calendar.month }">회원가입</a>
				</c:when>
				<c:otherwise>
						${customer }님 환영합니다.
						<input id="logout" type="button" value="로그아웃" style="flaot: right">
				</c:otherwise>
			</c:choose>
			<input name="year" type=hidden value="${calendar.year }">
			<input name="month" type="hidden" value="${calendar.month }">
		</form>
		<table>
			<caption style="font-size: 70px;">
				<input type="button" id="today" value="오늘"><a id="prev"
					href="">&lt; </a>${calendar.year } ${calendar.month }<a id="next"
					href=""> &gt;</a>
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
			<!-- 달력출력  -->
			<tbody>
				<c:forEach var="week" items="${calendar.days2D }">
					<tr>
						<c:forEach var="day" varStatus="status" items="${week}">
							<c:set var="holidayListPerOneDay" value="${holidayMap[day.oneDaydate]}" />
							<c:set var="isContainHoliday"
								value="${holidayMap.containsKey(day.oneDaydate) }" />
							<!--Holiday.txt 명단에 있는 날짜인지-->
							<c:set var="isHoliday" value="false" />
							<!--휴일 인지 아닌지 -->
							<c:if test="${isContainHoliday }">
								<c:forEach var="holiday_part" items="${holidayListPerOneDay }">
									<c:if test="${not doneLoop}">
										<c:if test="${holiday_part.isHoliday eq 'Y' }">
											<c:set var="isHoliday" value="true" />
										</c:if>
									</c:if>
								</c:forEach>
							</c:if>
							<td
								style="<c:if test="${day.month ne calendar.month  }">background-color: #DCDCDC; opacity: 0.5;</c:if>">
								<div class="day <c:if test="${ isHoliday || status.index%7 == 0 || status.index%7 == 6 }">holiday</c:if>">
									${day.day}
								</div>
								<c:forEach var="holiday_part" items="${holidayListPerOneDay }">
									<div style="<c:if test="${holiday_part.isHoliday eq 'Y' }">color: red;</c:if>">
										${holiday_part.meaning }
									</div>
								</c:forEach>
								<div class="scheduleList"> 
									<c:if test="${scheduleMap.containsKey(day.oneDaydate) }">
											<c:forEach var="schedule" items="${scheduleMap[day.oneDaydate]}">
												<div class="schedule" data-idx="${schedule.idx }">${schedule.title }</div>
											</c:forEach>
									</c:if>
								</div>
							</td>
						</c:forEach>
					</tr>
				</c:forEach>
					
								
				
			</tbody>

		</table>
		<div style="height: 60px;">2D List </div>
			
		<table>
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
				<!-- sublist 이용 -->
				<c:forEach var="i" begin="1" end="${calendar.numOfweeks }">
					<tr>
						<c:forEach var="day" varStatus="status" items="${calendar.days1D.subList(7 * (i-1), 7 * i)}">
							<c:set var="holidayListPerOneDay" value="${holidayMap[day.oneDaydate]}" />
							<c:set var="isContainHoliday"
								value="${holidayMap.containsKey(day.oneDaydate) }" />
							<!--Holiday.txt 명단에 있는 날짜인지-->
							<c:set var="isHoliday" value="false" />
							<!--휴일 인지 아닌지 -->
							<c:if test="${isContainHoliday }">
								<c:forEach var="holiday_part" items="${holidayListPerOneDay }">
									<c:if test="${not doneLoop}">
										<c:if test="${holiday_part.isHoliday eq 'Y' }">
											<c:set var="isHoliday" value="true" />
										</c:if>
									</c:if>
								</c:forEach>
							</c:if>
							<td
								style="<c:if test="${day.month ne calendar.month  }">background-color: #DCDCDC; opacity: 0.5;</c:if>">
								<div class="day <c:if test="${ isHoliday || status.index%7 == 0 || status.index%7 == 6 }">holiday</c:if>">
									${day.day}
								</div>
								<c:forEach var="holiday_part" items="${holidayListPerOneDay }">
									<div style="<c:if test="${holiday_part.isHoliday eq 'Y' }">color: red;</c:if>">
										${holiday_part.meaning }
									</div>
								</c:forEach>
								<div class="scheduleList"> 
									<c:if test="${scheduleMap.containsKey(day.oneDaydate) }">
											<c:forEach var="schedule" items="${scheduleMap[day.oneDaydate]}">
												<div class="schedule" data-idx="${schedule.idx }">${schedule.title }</div>
											</c:forEach>
									</c:if>
								</div>
							</td>
						</c:forEach>
					
					</tr>
				</c:forEach>
			</tbody>
		
		</table>
		<div style="height: 60px;">1D List (List 인터페이스 subList 메소드 이용 )</div>



	</div>

	<!-- Modal -->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<!--user, startdate, enddate, title, content  -->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">스케줄 등록</h4>
				</div>
				<div class="modal-body">
					<div class="form-group">
						<label>Title</label> <input class="form-control" name='title'
							value='' placeholder="일정 작성해주세요">
					</div>
					<div class="form-group">
						<label>시작일</label> <input class="form-control" name='startDate'
							value='' type="date">
					</div>
					<div class="form-group">
						<label>종료일</label> <input class="form-control" name='endDate'
							value='' type="date">
					</div>
					<div class="form-group">
						<label>설명</label>
						<textarea rows="3" cols="70" name='content'></textarea>
					</div>
					<div class="form-group">
						<label></label>
						<input class="form-control" type="hidden" name='idx' value=''>		
					</div>

				</div>
				<div class="modal-footer">
					<button id='modalModBtn' type="button" class="btn btn-warning">Modify</button>
					<button id='modalRemoveBtn' type="button" class="btn btn-danger">Remove</button>
					<button id='modalRegisterBtn' type="button" class="btn btn-primary">Register</button>
					<button id='modalCloseBtn' type="button" class="btn btn-default">Close</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->
<!-- 
<tbody>
				<c:forEach var="week" items="${calendar.days2D }">
					<tr>
						<c:forEach var="day" varStatus="status" items="${week}">
							<c:set var="holidayListPerOneDay" value="${holidayMap[day.oneDaydate]}"/>
							<c:choose>
								<c:when test="${day.month ne calendar.month  }">
									<td  style="background-color: #DCDCDC; opacity: 0.5;">
										${day.day}
									</td>
								</c:when>
								<c:when test="${status.index%7 == 0 || status.index%7 == 6 }">
									<td class="holiday day">
										<div>${day.day}</div>
										<c:forEach var="holiday_part" items="${holidayListPerOneDay }">
											<div>${holiday_part.meaning }</div>
										</c:forEach>
										
									</td>
								</c:when>
								<c:when test="${holidayMap.containsKey(day.oneDaydate)}">
									<c:set var="doneLoop" value="false"/>
									<c:forEach var="holiday_part" items="${holidayListPerOneDay }">
									   <c:if test="${not doneLoop}">
										<c:if test="${holiday_part.isHoliday eq 'Y' }">
											<td class="holiday day">
												<div>${day.day}</div> 
												<c:forEach var="holiday_part" items="${holidayListPerOneDay }">
													<div>${holiday_part.meaning }</div>
												</c:forEach>
											</td>
											<c:set var="doneLoop" value="true"/>
										</c:if>
								 	   </c:if>
									</c:forEach>
								</c:when>
								<c:otherwise>
									<td class="day">
										${day.day}
									</td>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</tr>					
				</c:forEach>
			</tbody>
 -->

</body>
<script type="text/javascript" src="/resources/js/reply.js"></script>
<script type="text/javascript" src="/resources/js/cal.js"></script>
<script type="text/javascript">
	var date = new Date();
	var todayYear = date.getFullYear();
	var todayMonth = date.getMonth();
	
	
	//화면상 보고있는 연도 , 달
	var curYear = parseInt(${calendar.year},10);
	var curMonth = parseInt(${calendar.month},10);
	
	var form = $("form");
	
	$("#today").click(function(){
		form.empty();
		var input1 = document.createElement('input');
		var input2 = document.createElement('input');
		
		input1.setAttribute("type","hidden");
		input1.setAttribute("name","year");
		input1.setAttribute("value", todayYear);
		
		input2.setAttribute("type","hidden");
		input2.setAttribute("name","month");
		input2.setAttribute("value", todayMonth + 1);
		
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
		var prevYear, prevMonth;
		if(curMonth==1){
			prevYear = (curYear - 1);
			prevMonth=12;
		}else{
			prevYear = curYear;
			prevMonth = (curMonth - 1);
		}
		
		$("#prev").attr("href", "/?year="+prevYear+"&month="+prevMonth);
		$('#prev').get(0).click();	
	})
	
	$("#next").click(function(e) {
		// e.preventDefault();
		var nextYear = parseInt(${calendar.year},10);
		var nextMonth = parseInt(${calendar.month},10);
		if(nextMonth==12){
			nextYear++;
			nextMonth=1;
		}else{
			nextMonth++;
		}
		$("#next").attr("href", "/?year="+nextYear+"&month="+nextMonth);
		$('#next').get(0).click();	
	})
	
	
	var modal = $(".modal");
	var modalInputtitle = modal.find("input[name='title']");
	var modalInputstartDate = modal.find("input[name='startDate']");
	var modalInputendDate = modal.find("input[name='endDate']");
	var modalInputcontent = modal.find("textarea[name='content']");
	var modalInputidx = modal.find('input[name="idx"]');
	
	var modalModBtn = $("#modalModBtn");// 변경
	var modalRemoveBtn = $("#modalRemoveBtn");// 삭제
	var modalRegisterBtn = $("#modalRegisterBtn");// 등록
	
	$("#modalCloseBtn").on("click", function(e){
		
		modal.modal('hide');
	});
	
	function pad(n, width) {
		  n = n + '';
		  return n.length >= width ? n : new Array(width - n.length + 1).join('0') + n;
	}
	
	var currSchedule;
	
	var tag;
	
	//일정등록 모달창 띄우기
	$(".day").on("click", function(e){
		tag = $(this);
		if(${empty customer}){
			alert("로그인하세요");
			$("input[name='id']").focus();
			return;
		}
		
		var year = parseInt(${calendar.year},10);
		var month = parseInt(${calendar.month},10);
		var day = parseInt(this.textContent,10);
		month = pad(month, 2);
		day = pad(day,2);
		var date = year + '-'+month + '-'+ day;
		modalInputstartDate.val(date);
		modalInputendDate.val(date);
				
	    modal.find("button[id !='modalRegisterBtn']").hide();
	    modalRegisterBtn.show();
	    $(".modal").modal("show");   	
	})
			
	//일정 등록
	modalRegisterBtn.on("click",function(e){
		var schedule = {
			title: modalInputtitle.val(),
			startdate: modalInputstartDate.val(),
			enddate: modalInputendDate.val(),
			content: modalInputcontent.val()
		};
		replyService.add(schedule, function(result){
			
			var scheduleList = tag.next();
			
			var div = document.createElement('div');
			replyService.getByDate(modalInputstartDate.val(), function(idx){
				div.innerHTML = result.title;
				div.setAttribute("class", "schedule");
				div.setAttribute("data-idx", idx);
				scheduleList.append(div);
				modal.find(":input").val("");
				modal.modal("hide");
			});
		});
    })
    
    //변경
     modalModBtn.on("click", function(e){
		var schedule = {
				title: modalInputtitle.val(),
				startdate: modalInputstartDate.val(),
				enddate: modalInputendDate.val(),
				content: modalInputcontent.val(),
				idx: modalInputidx.val()
		};
	  	  replyService.update(schedule, function(result){
		  	  currSchedule.innerHTML = result.title;
			  modal.find("input").val("");
		  	  modal.modal("hide");
		  });
	  
	})
	
	//제거
	modalRemoveBtn.on("click", function (e){
	  replyService.remove(modalInputidx.val(), function(result){
	  	  currSchedule.innerHTML = "";
     	  modal.find("input").val("");
	      modal.modal("hide");
	  });
	  
	})
	
    
    
	
	//일정 조회
	 $(".scheduleList").on("click",".schedule" ,function(e){
		 	currSchedule = this;
			var idx = $(this).data("idx");
			replyService.get(idx, function(scheduleVO){
	    		modalInputtitle.val(scheduleVO.title);
	    		modalInputstartDate.val(scheduleVO.startdate);
	    		modalInputendDate.val(scheduleVO.enddate);
	    		modalInputcontent.val(scheduleVO.content);
	    		modalInputidx.val(scheduleVO.idx);
	    	/*
			 * modal.find(':input').each(function(){
			 * $(this).attr("readonly",true); })
			 */
			    modal.find("button[id !='modalRegisterBtn']").show();
	    		modalRegisterBtn.hide();
	      		$(".modal").modal("show");   	
		});
	});
		
   
 // 댓글 조회 클릭 이벤트 처리
	/*
	 * $(".chat").on("click", "li", function(e){
	 * 
	 * var rno = $(this).data("rno");
	 * 
	 * replyService.get(rno, function(reply){
	 * 
	 * modalInputReply.val(reply.reply); modalInputReplyer.val(reply.replyer);
	 * modalInputReplyDate.val(replyService.displayTime( reply.replyDate))
	 * .attr("readonly","readonly"); modal.data("rno", reply.rno);
	 * 
	 * modal.find("button[id !='modalCloseBtn']").hide(); modalModBtn.show();
	 * modalRemoveBtn.show();
	 * 
	 * $(".modal").modal("show");
	 * 
	 * }); })
	 */
</script>

</html>
