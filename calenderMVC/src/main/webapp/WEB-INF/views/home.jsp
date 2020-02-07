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
					&gt;</a> <input type="date" id="move">


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
									<td class="cell"
										style="background-color: #DCDCDC; opacity: 0.5;"><c:choose>
											<c:when
												test="${day eq 1 || day eq 7 || cal.days[cell][1] eq 1}">
												<div style="color: red; font-weight: bold;">${cal.days[cell][0]}</div>
											</c:when>
											<c:otherwise>
												<div style="color:; font-weight: bold;">${cal.days[cell][0]}</div>
											</c:otherwise>
										</c:choose></td>
								</c:when>
								<c:otherwise>
									<c:choose>
										<c:when
											test="${day eq 1 || day eq 7 || cal.days[cell][1] eq 1}">
											<td class="cell">
												<div style="color: red; font-weight: bold;">${cal.days[cell][0]}</div>
												<c:if test="${cal.days[cell][1] eq 1 }">
													<div>${cal.meaning[cell]}</div>
												</c:if> <input class="schedule" type="text" value="과제" readonly="readonly">
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

	<!-- Modal -->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">REPLY MODAL</h4>
				</div>
				<div class="modal-body">
					<div class="form-group">
						<label>Reply</label> <input class="form-control" name='reply'
							value='New Reply!!!!'>
					</div>
					<div class="form-group">
						<label>Replyer</label> <input class="form-control" name='replyer'
							value='replyer'>
					</div>
					<div class="form-group">
						<label>Reply Date</label> <input class="form-control"
							name='replyDate' value='2018-01-01 13:13'>
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
		console.log("test");
		$("#next").attr("href", "/?year="+nextYear+"&month="+nextMonth);
		$('#next').get(0).click();	
	})


	   var modal = $(".modal");
    var modalInputReply = modal.find("input[name='reply']");
    var modalInputReplyer = modal.find("input[name='replyer']");
    var modalInputReplyDate = modal.find("input[name='replyDate']");
    
    var modalModBtn = $("#modalModBtn");
    var modalRemoveBtn = $("#modalRemoveBtn");
    var modalRegisterBtn = $("#modalRegisterBtn");
    
    $("#modalCloseBtn").on("click", function(e){
    	
    	modal.modal('hide');
    });
    
    $(".schedule").on("click", function(e){
      console.log("스케줄");
      modal.find("input").val("");
      modalInputReplyDate.closest("div").hide();
      modal.find("button[id !='modalCloseBtn']").hide();
      
      modalRegisterBtn.show();
      
      $(".modal").modal("show");
      
    });
    

    modalRegisterBtn.on("click",function(e){
      
      var reply = {
            reply: modalInputReply.val(),
            replyer:modalInputReplyer.val(),
            bno:bnoValue
          };
      replyService.add(reply, function(result){
        
        alert(result);
        
        modal.find("input").val("");
        modal.modal("hide");
        
        //showList(1);
        showList(-1);
        
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
  
    
/*     modalModBtn.on("click", function(e){
      
      var reply = {rno:modal.data("rno"), reply: modalInputReply.val()};
      
      replyService.update(reply, function(result){
            
        alert(result);
        modal.modal("hide");
        showList(1);
        
      });
      
    });

    modalRemoveBtn.on("click", function (e){
    	  
  	  var rno = modal.data("rno");
  	  
  	  replyService.remove(rno, function(result){
  	        
  	      alert(result);
  	      modal.modal("hide");
  	      showList(1);
  	      
  	  });
  	  
  	}); */

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
