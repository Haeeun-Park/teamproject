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
<form name="join" class="login-form" action="updateMember" method="post">

  
  <h2>Join</h2>
  
  <div class="field">
  	<label>User ID</label><input type="text" name="userid" value="${dto.userid}" readonly/>
  </div>

  <div class="field">
  	<label>Password</label><input type="password" name="pwd">
  </div>
  
  <div class="field">
    <label>Retype Pw</label><input type="password" name="pwd_check">
  </div>
  
  <div class="field">
    <label>Name</label><input type="text" name="name" value="${dto.name}">
  </div>
  
  <div class="field">
    <label>Email</label><input type="text" name="email" value="${dto.email}">
  </div>
  
  <div class="field">
    <label>Phone</label><input type="text" name="phone" value="${dto.phone}">
  </div>
  
  <div class="login-button">
  <input type="submit" class="btn-login" value="Update"/>
    <input type="button" class="btn-login" value="Back" 
    onClick="location.href='main'"/>
</div>
  <div clas="field">${msg}</div>
</form>
</body>
</html>