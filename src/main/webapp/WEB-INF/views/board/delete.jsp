<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<c:choose>
	<c:when test="${result==1}">
		<script type="text/javascript">
		  opener.location.href='deleteBoard?num=${num}';
		  
		</script>
	</c:when>
	<c:otherwise>
	<script type="text/javascript">
		  alert("비밀번호가 일치하지 않습니다.");
		  
	</script>
	</c:otherwise>
</c:choose>

<script type="text/javascript">
self.close();
</script>
</body>
</html>