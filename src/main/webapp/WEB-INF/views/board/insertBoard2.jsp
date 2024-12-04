<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="/css/board.css"/>
<script src="/script/board.js"></script>
</head>
<body>
<div id="main_container">
	<h2>게시글 등록</h2>
	<div class="board">
	  <form class="insertBoard" method="post" name="insertBoard" 
	  action="insertBoard2" enctype="multipart/form-data">

		  <div class="field">
		  <label>작성자</label>
		  <input type="text" name="userid" value="${dto.userid}" readonly/>
		</div>
		
		<div class="field">
		  <label>비밀번호</label>
		  <input type="password" name="pass"/>
		</div>
		
		<div class="field">
		  <label>이메일</label>
		  <input type="text" name="email" value="${dto.email}"/>
		</div>
		
		<div class="field">
		  <label>제목</label>
		  <input type="text" name="title"/>
		</div>
		
		<div class="field">
		  <label>내용</label>
		  <textarea name="content" rows="10" cols="100"></textarea>
		</div>
		
		<div class="field">
		  <label>이미지</label><input type="file" name="image"/>
		 </div>

		
		<div class="field">
		  <input type="submit" value="작성완료" />
		  <input type="submit" value="돌아가기" onClick="location.href='board.do?command=main'"/>
		</div>
		
		
	  </form>
	</div>
</div>
</body>
</html>