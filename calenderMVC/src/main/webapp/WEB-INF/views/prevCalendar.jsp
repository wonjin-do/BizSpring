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
    <link href="/resources/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

<script src="https://code.jquery.com/jquery-3.3.1.min.js"
	integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8="
	crossorigin="anonymous"></script>
<!-- Bootstrap Core JavaScript -->
<script src="/resources/vendor/bootstrap/js/bootstrap.min.js"></script>

<!-- Metis Menu Plugin JavaScript -->
<script src="/resources/vendor/metisMenu/metisMenu.min.js"></script>

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
				<input type="button" id="today" value="오늘"><a id="prev"
					href="">&lt; </a>${cal.year } ${cal.month }<a id="next" href="">
					&gt;</a> 
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
								<c:when
									test="${cell < cal.dayOfWeek || cell >= cal.dayOfWeek + cal.endDay }">
									<td class="cell" style="background-color: #DCDCDC; opacity: 0.5;">
									<c:choose>
											<c:when
												test="${day eq 1 || day eq 7 || cal.days[cell][1] eq 1}">
												<!--이전달, 다음달의 날짜 음영처리 -->
												<!--토요일, 일요일-->
												<div class="holiday day">${cal.days[cell][0]}</div>
											</c:when>
											<c:otherwise>
												<div class="day">${cal.days[cell][0]}</div>
											</c:otherwise>
										</c:choose></td>
								</c:when>
								<c:otherwise>
									
									<td class="cell" id="${cell-cal.dayOfWeek +1}">
										<c:choose>
											<c:when	test="${day eq 1 || day eq 7 || cal.days[cell][1] eq 1}">
												<!--현재 달 날짜-->
												<!--토요일, 일요일-->
												<div  class="holiday day">${cal.days[cell][0]}</div>
											</c:when>
											<c:otherwise>
												<div class="day">${cal.days[cell][0]}</div>		
											</c:otherwise>
										</c:choose>
										<c:if test="${ !empty cal.meaning[cell] }">
											<div>${cal.meaning[cell]}</div>
										</c:if>
										<c:if test="${ empty cal.meaning[cell] }">
											<div></div>
										</c:if>
										
										<div class="schedule">
											<div id="1">과제</div>
											<div id="1">과제</div>
										</div>
										<div class="more" style="text-align: left; ; font-weight: bold">+3</div>
									</td>
								</c:otherwise>
							</c:choose>
							<c:set var="cell" value="${cell+1}" />
						</c:forEach>
					</tr>
				</c:forEach>


			</tbody>

		</table>

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
						<label>설명</label> <textarea rows="3" cols="70" name='content'></textarea>
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


</body>
<script type="text/javascript" src="/resources/js/reply.js"></script>
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
		console.log("test");
		$("#next").attr("href", "/?year="+nextYear+"&month="+nextMonth);
		$('#next').get(0).click();	
	})


	var modal = $(".modal");
    var modalInputtitle = modal.find("input[name='title']");
    var modalInputstartDate = modal.find("input[name='startDate']");
    var modalInputendDate = modal.find("input[name='endDate']");
    var modalInputcontent = modal.find("textarea[name='content']");
    
    var modalModBtn = $("#modalModBtn");//변경
    var modalRemoveBtn = $("#modalRemoveBtn");//삭제
    var modalRegisterBtn = $("#modalRegisterBtn");//등록
    
    $("#modalCloseBtn").on("click", function(e){
    	
    	modal.modal('hide');
    });
    
    function pad(n, width) {
    	  n = n + '';
    	  return n.length >= width ? n : new Array(width - n.length + 1).join('0') + n;
    	}
    
    $(".day").on("click", function(e){
    	
    	if(${empty customer}){
    		alert("로그인하세요");
    		$("input[name='id']").focus();
    		return;
    	}
    	
    	var year = parseInt(${cal.year},10);
		var month = parseInt(${cal.month},10);
		var day = parseInt(this.textContent,10);
		month = pad(month, 2);
		day = pad(day,2);
		var date = year + '-'+month + '-'+ day;
    	modal.find("input[name = 'startDate']").val(date);
    	modal.find("input[name = 'endDate']").val(date);
	    //modalInputReplyDate.closest("div").hide();
	    modal.find("button[id !='modalRegisterBtn']").hide();
	      
	      modalRegisterBtn.show();
	      
	      $(".modal").modal("show");   	
      
    });
    
    

    modalRegisterBtn.on("click",function(e){
      console.log(modalInputstartDate.val());
      var schedule = {
    		title: modalInputtitle.val(),
   			startdate: modalInputstartDate.val(),
			enddate: modalInputendDate.val(),
			content: modalInputcontent.val()
          };
	    replyService.add(schedule, function(result){
        
        alert(result);
        if(result == "success"){
        	
        }
        
        modal.find("input").val("");
        modal.modal("hide");
        
      });
      
    });


  //댓글 조회 클릭 이벤트 처리 
    $(".chat").on("click", "li", function(e){
      
      var rno = $(this).data("rno");
      
      replyService.get(rno, function(reply){
      
        modalInputReply.val(reply.reply);
        modalInputReplyer.val(reply.replyer);
        modalInputReplyDate.val(replyService.displayTime( reply.replyDate))
        .attr("readonly","readonly");
        modal.data("rno", reply.rno);
        
        modal.find("button[id !='modalCloseBtn']").hide();
        modalModBtn.show();
        modalRemoveBtn.show();
        
        $(".modal").modal("show");
            
      });
    });
  
    
    modalModBtn.on("click", function(e){
    	  
   	  var reply = {rno:modal.data("rno"), reply: modalInputReply.val()};
   	  
   	  replyService.update(reply, function(result){
   	        
   	    alert(result);
   	    modal.modal("hide");
   	    showList(pageNum);
   	    
   	  });
   	  
   	});


   	modalRemoveBtn.on("click", function (e){
   	  
   	  var rno = modal.data("rno");
   	  
   	  replyService.remove(rno, function(result){
   	        
   	      alert(result);
   	      modal.modal("hide");
   	      showList(pageNum);
   	      
   	  });
   	  
   	});

 
</script>

</html>
