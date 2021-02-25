<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<link rel="stylesheet" href="/css/index.css">
</head>
<body>
	<form action="/register" method="post">
		<div class="container_align">
		  <div class="container">
		    <h1>가계부</h1>
		    <hr>
		
		    <label for="regdate"><b>날짜</b></label>
		    <input type="text" placeholder="2021-01-01" name="regdate" id="regdate" required>
		    
		    <label for="content"><b>내용</b></label>
		    <input type="text" placeholder="내용을 입력하시오." name="content" id="content" required>
		    
		    <label for="price"><b>금액</b></label>
		    <input type="text" placeholder="금액을 입력하시오." name="price" id="price" required>
		  
		    <hr>
		    
		    <button type="submit" class="registerbtn">Register</button>
		  </div>
		</div>
	</form>
</body>
</html>