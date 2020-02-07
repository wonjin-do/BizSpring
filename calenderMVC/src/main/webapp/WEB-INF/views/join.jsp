<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/resources/css/join.css">
</head>
<body>
	<div id="stylized" class="myform">
		<form id="form" name="form" action="/join" method="post">
			<h1>글쓰기 폼</h1>
			<p>기본적인 입력폼입니다.</p>

			<label>Id <span class="small">아이디 입력</span></label>
			 <input type="text" name="id" id="id" />
			 
			 <label>Password <span class="small">패스워드 </span></label>
			 <input type="text" name="password" id="password" />

			<button type="submit">Sign-up</button>
			<div class="spacer"></div>

		</form>
	</div>
</body>
</html>