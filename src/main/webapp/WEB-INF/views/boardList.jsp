<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
</head>
<body>
	<h1>boardList.jsp</h1>
	
	<h2>게시판 리스트</h2>
	<c:if test="${!empty id}">
		<div align="right">
			<form name="logoutFrm" id="logoutFrm" method="post" action="logout">
			
				<a href="javascript:logout()">로그아웃</a>
			
			</form>
		 </div>
		
	
	</c:if>
	<table id="one_table">
		<tr height="30">
		<td width="80" bgcolor="pink" align="center">ID
		</td>
		<td>${mb.id}</td>
		</tr>
		<tr height="30">
		<td width="80" bgcolor="pink" align="center">NAME
		</td>
		<td>${mb.name}</td>
		</tr>
		<tr height="30">
		<td width="80" bgcolor="pink" align="center">POINT
		</td>
		<td>${mb.point}</td>
		</tr>
		<tr height="30">
		<td width="80" bgcolor="pink" align="center">GRADE
		</td>
		<td>${mb.grade_name}</td>
		</tr>
	</table>
	<table>
		<tr bgcolor="skyblue">
			<th width="100">번호</th>
			<th width="100">제목</th>
			<th width="100">작성자</th>
			<th width="100">작성일</th>
			<th width="100">조회수</th>
		</tr>
		<c:forEach var="board" items="${bList}">
			<tr height="25">
				<td align="center">${board.board_number}</td>
				<td align="center"><a href="#" onclick="articleView(${board.board_number})">${board.board_title}</a></td>
				<td align="center">${board.board_id}</td>
				<td align="center">${board.board_date}</td>
				<td align="center">${board.b_views}</td>
			</tr>
		</c:forEach>
	</table>
	
	<script type="text/javascript">
	function logout() {
		$('#logoutFrm').submit();
	}
	</script>
	<script type="text/javascript">
	function articleView(num) {
		$.ajax({
			type:'get',
			url:'contents'
			data:{bNum:num},
			dataType:'html',
			success: function (data) {
				alert(data);
			},
			error: function (error) {
				alert(error);
			}
		});//ajax end
	}//fct end
	
	</script>
</body>
</html>