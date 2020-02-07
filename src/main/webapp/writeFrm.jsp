<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<style type="text/css">
table {
	width: 100%;
}

table, td, th {
	border: 1px solid;
	border-collapse: collapse;
	padding: 5px;
}

input[type='text'] {
	width: 100%;
}

textarea {
	width: 100%;
	resize: none;
}
</style>
</head>
<body>
	<h3>글쓰기</h3>
	<form name="frm" id="frm" action="boardwrite" method="post" enctype="multipart/form-data">
		<table border="1">
			<tr>
				<td>제목</td>
				<td><input type="text" name="board_title" id="board_title" /></td>
			</tr>
			<tr>
				<td>내용</td>
				<td><textarea rows="20" name="board_contents"
						id="board_contents"></textarea></td>
			</tr>
			<tr>
				<td>파일첨부</td>
				<td><input type="file" name="files" id="files"
					onchange="fileChecka(this)" multiple="multiple" /> <input
					type="hidden" id="fileCheck" name=fileCheck value="0" /></td>
			</tr>
			<tr>
				<td colspan="2" align="center">
				<input type="submit" value="글작성">
				<input type="button" onclick="formData()" value="formData">
				<input type="reset" id="reset" value="취소" /> <input type="button"
				onclick="location.href='./boardlist'" value="리스트 보기" /></td>
			</tr>
		</table>
	</form>
	<script type="text/javascript">
		$('#reset').click(function() {
			//console.log('empty');
			$('#fileCheck').val(0);
			//console.log($('#fileCheck').val());
		});
		function fileChecka(elem) {
			//console.dir(elem);
			if (elem.value === '') {
				//console.log('empty');
				$('#fileCheck').val(0); //미첨부
			} else {
				//console.log('not empty');
				$('#fileCheck').val(1); //첨부
			}
			console.log($('#fileCheck').val());
		}
	</script>
	<script type="text/javascript">
	function formData() {
		var $obj=$('#files'); //제이쿼리 객체 
		//console.dir($obj[0]); //폼데이터 전송시 제발 확인 해보고 실행할것 자바스크립트 객체
		var obj=document.getElementById('files');
		//console.dir(obj);
		//console.dir($obj[0].files);//첨부된 파일 리스트
		//console.dir($obj[0].files.length);// 첨부된 파일리스트 개수
		//console.dir($obj[0].files[0]);
		//console.dir($obj[0].files[1]);
		
		//사용목적 
		//1.mulitpart/form-data 를 전송시 무조건 사용(파일업로드)
		//2.ajax를 이용한 restFul에서 사용함
		//3.FormData객체는 form의 일부데이터만 서버에 전송할때도 좋다.
		
		//var formData= new FormData(document.getElementById('frm'));
		//console.log(formData.get('board_title'));
		//console.log(formData.get('board_contents'));
		var formData= new FormData();
		formData.append('board_title',$('#board_title').val());
		formData.append('board_contents',$('#board_contents').val());
		formData.append('fileCheck',$('#fileCheck').val());
		var files=$obj[0].files;
		//console.dir(files);
		for(var i = 0 ; i<files.length;i++){
			formData.append('files',files[i]); //Map과 달리 속성(키) 같아도 중복저장 
		}
		console.dir(formData.get('board_contents'));
		console.dir(formData.getAll('files')); //get으로 할경우 덮어쓰기함
		
		$.ajax({
			url:'rest/boardwrite',
			type: "post", //enctype multipart/form-data 사용시 post
			data:formData,
			processData:false,
			contentType:false,
			dataType:'json',
			success:function(data){
				console.log(data);
			},
			error: function(error) {
				console.log(error);
			}
		});//ajaxEnd
		
		
	}
	
	</script>
</body>
</html>