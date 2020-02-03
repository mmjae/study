
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
	<style type="text/css">

	</style>
</head>
<body>
<h1 align="center">
	로그인 페이지
</h1>
<form action="access" method="post" name="logFrm">
	<table border="1">
	<tr>
	<td colspan="2" align="center" bgcolor="skyblue">로그인</td>
	</tr>
	<tr>
		<td><input type="text" name="id">  </td>
		<td rowspan="2"><input type="submit" value="로그인"></td>
	</tr>
	<tr>
	<td>
		
		<input type="password" name="password">
	</td>
	</tr>
	<tr>
	<td colspan="2" align="center" bgcolor="skyblue">com.board.ma</td>
	
	</tr>
	
	<tr>
	<td colspan="2" align="center" ><a href="joinfrm">회원가입</a></td>
	</tr>
	
	
	
	</table>
</form>
<script type="text/javascript">
	var chk=${check};
	if(chk==1){
		alert("회원가입성공");
	}
	if(chk==2){
		alert("로그인실패");
	}
	if(chk==3){
		alert("로그인후 이용해주세요.");
	}
</script>
</body>

</html>
