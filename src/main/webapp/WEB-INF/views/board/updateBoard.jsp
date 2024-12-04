<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
	  <form class="insertBoard" method="post" name="insertBoard" action="updateBoard">
		  <input type="hidden" name="num" value="${dto.num}"/>
		
		<div class="field">
		  <label>작성자</label>
		  <input type="text" name="userid" value="${dto.userid}" readonly/>
		</div>
		
		<div class="field">
		  <label>비밀번호</label>
		  <input type="password" name="pass"/>
		</div>
		<div class="field">
			<label>&nbsp;</label><div style="flex: 4;">${msg}</div>
		</div>
		
		<div class="field">
		  <label>이메일</label>
		  <input type="text" name="email" value="${dto.email}"/>
		</div>
		
		<div class="field">
		  <label>제목</label>
		  <input type="text" name="title" value="${dto.title}"/>
		</div>
		
		<div class="field">
		  <label>내용</label>
		  <textarea name="content" rows="10" cols="100">${dto.content}</textarea>
		</div>
		
		<div class="field">
	  	<div class="label">이미지</div>
	  	<div class="text">
	  		<c:choose>
	  		  <c:when test="${empty dto.savefilename}">
	  		  	<img src="upload/noname.jpg" width="100" id="previewimg"/>
				  <br/>
			  </c:when>
	  		  <c:otherwise>
	  		    <img src="upload/${dto.savefilename}" width="100" id="previewimg"/><br/>
	  		    ${board.image}
	  		  </c:otherwise>
	  		</c:choose>
			<input type="hidden" name="savefilename" value="${dto.savefilename}"/>
			<input type="hidden" name="image" value="${dto.image}"/>
			<div id="image">${dto.image}</div>
			<div id="savefilename">${dto.savefilename}</div>
	  	</div>

	  </div>

		  <div class="field">
			  <label>업데이트 할 이미지</label>
			  <input type="button" value="이미지 선택" onClick="selectImg()"/>
		  </div>


		  <div class="field">
			  <input type="submit" value="수정완료" />
			  <input type="submit" value="돌아가기" onClick="history.go(-1)"/>
		</div>
		
		
	  </form>
	</div>
</div>
</body>
</html>