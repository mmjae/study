<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
.subject {
	text-align: center;
	height: 50px;
}
</style>
<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
</head>
<body>
	<form name="joinFrm" action="memberjoin" method="post"
		onsubmit="return check()">
		<table border="1">
			<tr>
				<td colspan="2" class="subject">회원가입</td>
			</tr>
			<tr>
				<td width="100">ID</td>
				<td><input type="text" id="id" name="id"> <input
					type="button" id="checkId" value="중복검사" />
					<div id="result"></div></td>
			</tr>
			<tr>
				<td width="100">PWD</td>
				<td><input type="password" name="password"></td>
			</tr>
			<tr>
				<td width="100">NAME</td>
				<td><input type="text" name="name"></td>
			</tr>
			<tr>
				<td width="100">BIRTH</td>
				<td><input type="text" name="birth"></td>
			</tr>
			<tr>
				<td width="100">ADDR</td>
				<td><input type="text" name="address"></td>
			</tr>
			<tr>
				<td width="100">PHONE</td>
				<td><input type="text" name="phone_number"></td>
			</tr>
			<tr>
				<td colspan="2" class="subject"><input type="submit"
					value="회원가입"></td>
			</tr>
		</table>
	</form>
	
	<script>
		$('#checkId').on('click',function() {
			alert("들어옴");
					if ($('#id').val() != '') {
						$.ajax({
							type : 'get',
							url : 'user/userid',
							data : 'id=' + $('#id').val(),
							//dataType : 'html',
							success : function(data, status, xhr) {
								$('#result').html(data).css('color', 'red');
								console.log("data=", data);
								console.log("status=", status);
								console.log("xhr=", xhr);

							},
							error : function(xhr, status) {
								$('#result').html(xhr.responseText).css(
										'color', 'red');
								console.log("xhr=", xhr);
								console.log("status=", status);
							}
						});
					}
				});

		function check() {
			var frm = document.joinFrm;
			var length = frm.length - 1;
			for (var i = 0; i < length; i++) {
				if (frm[i].value == "") {
					alert(frm[i].name + "을 입력하세요!!!");
					frm[i].focus();
					return false; //실패시
				}
			}
			return true; //성공시 서버로 전송
		}
	</script>
</body>
</html>