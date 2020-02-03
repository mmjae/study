<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form name="joinFrm" action="memberjoin" method="post"
		onsubmit="return check()">
		<table border="1">
			<tr>
				<td colspan="2">회원가입</td>
			</tr>
			<tr>
				<td width="100">ID</td>
				<td><input type="text" name="id"></td>
			</tr>
			<tr>
				<td width="100">PASSWORD</td>
				<td><input type="password" name="password"></td>
			</tr>
			<tr>
			<tr>
				<td width="100">NAME</td>
				<td><input type="text" name="name"></td>
			</tr>
			<tr>
			<td width="100">BIRTH</td>
			<td><input type="text" name="birth"></td>
			</tr>
			<tr>
				<td width="100">ADDRESS</td>
				<td><input type="text" name="address"></td>
			</tr>
			<tr>
				<td width="100">PHONE</td>
				<td><input type="text" name="phone_number"></td>
			</tr>
			<tr>
				<td colspan="2" align="center"><input type="submit"
					value="회원가입"></td>
			</tr>
		</table>

	</form>
	<script type="text/javascript">
	
	function check() {
		var frm=document.joinFrm;
		var length=frm.length-1;
		for(var i=0;i<length;i++){
			if(frm[i].value==""){
				alert(frm[i].name+"입력하세요!!");
				frm[i].focus();
				return false;
			}
		}
		return true;
	}
	
	
	</script>
</body>
</html>