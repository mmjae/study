<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>BOARD * REPLY CONTENTS</h3>
<a href="boardDelete?bNum=${board.board_number}">삭제</a>
<table>
	<tr>
		<td  width="100" bgcolor="pink" align="center">글번호</td>
		<td colspan="5">${board.board_number}</td>
		</tr>
	<tr height="30">
		<td bgcolor="pink" align="center">날짜</td>
		<td width="150">${board.board_date}</td>
		<td bgcolor="pink" align="center">글번호</td>
		<td width="150">${board.b_views}</td>
	</tr>
	<tr height="30">
		<td bgcolor="pink" align="center">제목</td>
		<td colspan="5" align="center">${board.board_title}</td>
	</tr>
	<tr height="30">
		<td bgcolor="pink" align="center">내용</td>
		<td colspan="5" align="center">${board.board_contents}</td>
	</tr>
</table>
	<form action="" name="rFrm" id="rFrm">
		<table>
			<tr>
				<td><textarea rows="3" cols="50" name="r_contents"></textarea></td>			
				<td><input type="button" value="댓글 전송" style="width:70px;height:50px;"></td>
			</tr> 
		</table>
	
	</form>

<table>
	<c:forEach var="r" items="${rList}">
		<tr height="25" align="center">
			<td width="100">${r.reply_id}</td>
			<td width="200">${r.reply_contents}</td>
			<td width="200">${r.reply_date}</td>
		</tr>
	</c:forEach>
</table>
</body>
</html>