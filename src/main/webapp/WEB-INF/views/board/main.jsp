<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Board Main</title>
<link rel="stylesheet" type="text/css" href="css/board.css"/>
<script src="script/board.js"></script>
</head>
<body>
<div id="main_container">
  <h2>Board List</h2> <!-- 페이지 상단의 제목 -->
  
  <div class="logininfo"> 
  	<div class="user"> <!-- 페이지 상단 왼쪽의 사용자 정보 -->
  		${loginUser.name}(${loginUser.userid})님이 로그인하셨습니다
  		<input type="button" value="회원정보수정" onClick="location.href='updateMemberForm'"/>
  		<input type="button" value="로그아웃" onClick="location.href='logout'"/>
  		<input type="button" value="회원탈퇴" onClick="deleteConfirm()"/>
  	</div>
  	<div class="writebutton"> <!-- 페이지 상단 오른쪽의 새글쓰기 버튼 -->
  		<input type="button" value="게시글 등록" onClick="location.href='insertBoardForm'"/>
		<input type="button" value="게시글 등록 old" onClick="location.href='insertBoardForm2'"/>
  	</div>
  </div>
  
  <div class="board">
  	<div class="title_row">
  		<div class="title_col">번호</div><div class="title_col">제목</div>
  		<div class="title_col">작성자</div><div class="title_col">작성일</div>
  		<div class="title_col">조회수</div>
  	</div>
  	
  	<c:forEach items="${boardList}" var="board">
  		<div class="row">
  			<div class="col">${board.num}</div>
  			<div class="col">
  			<a href="boardView?num=${board.num}" style="text-decoration: none">
  			${board.title}
  			</a>
  			&nbsp;
  			<c:if test="${not empty board.savefilename}">
  				<span style="color:blue; font-weight:bold; font-size:90%">[img]</span>
  			</c:if>
  			&nbsp;
  			<c:if test="${board.replycnt>0}">
  				<span style="color:red; font-weight:bold">[${board.replycnt}]</span>
  			</c:if>
  			</div>
  			<div class="col">${board.userid}</div>
  			<div class="col"><fmt:formatDate value="${board.writedate}"/></div>
  			<div class="col">${board.readcount}</div>
  		</div>
  	</c:forEach>
  </div>
  
  
  
  <div class="paging">
  	<!-- prev 버튼의 표시 여부 -->
  	<c:if test="${paging.prev}">
  	  <a href="main?page=${paging.beginPage-1}">◀</a>&nbsp;
  	</c:if>
  	
  	
  	<!-- beginPage 부터 endPage 까지 일렬로 페이지를 표시 -->
  	<c:forEach begin="${paging.beginPage}" end="${paging.endPage}" var="index">
  	<a href="main?page=${index}">
  		&nbsp;${index}&nbsp;
    </a>
  	</c:forEach>


  	<!-- next 버튼 표시 여부 -->
  	<c:if test="${paging.next}">
  		<a href="main?page=${paging.endPage+1}">▶</a>&nbsp;
  	</c:if>
  	
  </div>
</div>
</body>
</html>









