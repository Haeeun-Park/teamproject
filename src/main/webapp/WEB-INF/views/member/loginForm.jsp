<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="/css/board.css">
<script src="/script/board.js"></script>
</head>

<body>
<form class="login-form" action="login" method="post" name="login">

<h2>Login</h2>

<div class="field">
  <label>USER ID</label><input type="text" name="userid" value="${dto.userid}"/>
</div>


<div class="field">
  <label>PASSWORD</label><input type="password" name="pwd"/>
</div>

<div class="login-button">
  <input type="submit" class="btn-login" value="log in" />
    <input type="button" class="btn-login" value="join" 
    onClick="location.href='joinForm'"/>
</div>
  <div class="sns-login">
    <input type="button" class="btn facebook" value="Facebook"/>
    <input type="button" class="btn twitter" value="Twitter"/>
    <input type="button" class="btn google" value="Google"/>
    <input type="button" class="btn kakao" value="Kakao" onclick="location.href='kakaostart'"/>
  </div>
<div>${msg}</div>
</form>

</body>
</html>