<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script type="text/javascript" src="js/jquery.serializeObject.js"></script>
<style>
html, body {
	height: 100%;
	margin: 0
}

#articleView_layer {
	display: none;
	position: fixed;
	position: absolute;
	top: 0;
	left: 0;
	width: 100%;
	height: 100%
}

#articleView_layer.open {
	display: block;
	color: red
}

#articleView_layer #bg_layer {
	position: absolute;
	top: 0;
	left: 0;
	width: 100%;
	height: 100%;
	background: #000;
	opacity: .5;
	filter: alpha(opacity = 50);
	z-index: 100
}

#contents_layer {
	position: absolute;
	top: 40%;
	left: 40%;
	width: 400px;
	height: 400px;
	margin: -150px 0 0 -194px;
	padding: 28px 28px 0 28px;
	border: 2px solid #555;
	background: #fff;
	font-size: 12px;
	z-index: 200;
	color: #767676;
	line-height: normal;
	white-space: normal;
	overflow: scroll
}
</style>
</head>
<body>
	
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
				<!-- #은 위로 #; 은 현재 위치 고수 --> 
				<td align="center">${board.board_id}</td>
				<td align="center">${board.board_date}</td>
				<td align="center">${board.b_views}</td>
			</tr>
		</c:forEach>
	</table>
	<!-- 페이징 출력 -->
	<div align="center">
		${paging}
	</div>
	<div id="articleView_layer">
		<div id="bg_layer"></div>
		<div id="contents_layer"></div>
	</div>
	<script type="text/javascript">
	function logout() {
		$('#logoutFrm').submit();
	}
	</script>
	<script type="text/javascript">
	function articleView(num) {
		$("#articleView_layer").addClass("open");
		$.ajax({
			type:'get',
			url:'contents',
			data:{bNum:num},
			dataType:'html', //boardContentsAjax.jsp
			success: function (data) {
				$("#contents_layer").html(data);
			},
			error: function (error) {
				console.log(error);
			}
		});//ajax end
	}//fct end
	//모달 박스 해제
	var $layerWindow=$('#articleView_layer');
	$layerWindow.find('#bg_layer').on('mousedown',function(event){
		console.log(event);
		$layerWindow.removeClass('open');
	});//on end
	$(document).keydown(function (event) {
		if(event.keyCode!=27){
			return;
		}else if($layerWindow.hasClass('open')) {
			$layerWindow.removeClass('open');
		}
	});//keydown End
	</script>
	
</body>
</html>