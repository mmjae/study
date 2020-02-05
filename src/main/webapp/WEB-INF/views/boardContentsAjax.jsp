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
			<td width="100" bgcolor="pink" align="center">글번호</td>
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
	<form name="rFrm" id="rFrm">
		<table>
			<tr>
				<td><textarea rows="3" cols="50" name="reply_contents" id="r_contents"></textarea></td>
				<td><input type="button" value="댓글 전송"
					onclick="replyInsert(${board.board_number})"
					style="width: 70px; height: 50px;"></td>
			</tr>
		</table>
	</form>

	<table id="rTable">
		<c:forEach var="r" items="${rList}">
			<tr height="25" align="center">
				<td width="100">${r.reply_id}</td>
				<td width="200">${r.reply_contents}</td>
				<td width="200">${r.reply_date}</td>
			</tr>
		</c:forEach>
	</table>
	<script type="text/javascript">
	function replyInsert(bNum) {
		
		var obj=$('#rFrm').serializeObject(); //자바스크립트 객체{속성:값}
		obj.board_number=bNum;
		console.log(obj);
		var json=JSON.stringify(obj);
		console.log(json);
		$.ajax({
			type: "post",
			url:'rest/replyinsert',
			//1.쿼리스트링 방식
			//data:{board_number:bNum,reply_contents:$('#r_contents').val()},
			//2.
			//data:$('#rFrm').serialize(), //폼전체 데이터 전송 
			//3.
			//data:json,
			data:obj,
			//쿼리스트링이 아닌 json바식으로 전송시 명시할것
			//contentType:'application/json',
			dataType:'json',
			success:function(data,status,xhr){
				console.log(data);
				console.log(status);
				console.log(xhr);
				jsonStr(data);
			},
			error:function(xhr,status){
				console.log(xhr);
				console.log(status);
			}
		});//에작스end
	}//fct end
	function jsonStr(data) {
		var str='';
			console.log(data.rList.length);
		for(var i=0;i<data.rList.length;i++){
			str+='<tr height="25" align="center">'
			str+='<td width="100">'+data.rList[i].reply_id+'</td>'
			str+='<td width="200">'+data.rList[i].reply_contents+'</td>'
			str+='<td width="200">'+data.rList[i].reply_date+'</td>'
			str+='</tr>'
		}
	$("#rTable").html(str);
	}
	
	
	
	</script>
</body>
</html>