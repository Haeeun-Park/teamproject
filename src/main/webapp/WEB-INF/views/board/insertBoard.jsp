<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
	  action="insertBoard">

		  <div class="field">
		  <label>작성자</label>
		  <input type="text" name="userid" value="${loginUser.userid}" readonly/>
		</div>
		
		<div class="field">
		  <label>비밀번호</label>
		  <input type="password" name="pass"/>
		</div>
		
		<div class="field">
		  <label>이메일</label>
		  <input type="text" name="email" value="${loginUser.email}"/>
		</div>
		
		<div class="field">
		  <label>제목</label>
		  <input type="text" name="title" value="${loginUser.title}"/>
		</div>
		
		<div class="field">
		  <label>내용</label>
		  <textarea name="content" rows="10" cols="100"></textarea>
		</div>
		
		<div class="field">
		  <label>이미지</label><input type="button" name="image" value="이미지 선택" onClick="selectImg()"/>
		 </div>
		<c:choose>
			<c:when test="${empty dto.image}">
		  <div class="field">
			  <label>이미지 미리보기</label>
			  <div style="flex:4">
				  <img src="" id="previewimg" width="150" style="display:none"/>
				  <input type="hidden" name="savefilename">
				  <input type="hidden" name="image">
				  <div id="image"></div>
				  <div id="savefilename"></div>
			  </div>
		  </div>
			</c:when>
			<c:otherwise>
				<div class="field">
					<label>이미지 미리보기</label>
					<div style="flex:4">
						<c:choose>
						<img src="/upload/${dto.savefilename}" id="previewimg" width="150" style="display:none"/>

						<c:when test="${empty dto.savefilename}">
							<img src="" id="previewimg" width="150" style="display:none"/>
						</c:when>
						<c:otherwise>
							<img src="/upload/${dto.savefilename}" id="previewimg" width="150" style="display: inline"/>
						</c:otherwise>
						</c:choose>
						<input type="hidden" name="savefilename" value="${dto.savefilename}">
						<input type="hidden" name="image" value="${dto.image}">
						<div id="image">${dto.image}</div>
						<div id="savefilename">${dto.savefilename}</div>
					</div>
				</div>
			</c:otherwise>
		</c:choose>

		
		<div class="field">
		  <input type="submit" value="작성완료" onClick=""/>
		  <input type="submit" value="돌아가기" onClick=""/>
		</div>
	  </form>
		<div calss="field">${msg}</div>
	</div>
</div>
</body>
</html>