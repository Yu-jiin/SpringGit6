<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" contentType="text/html; charset=UTF-8"%>
<html>
<head>
	<title>Home</title>
	 <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
	<script type="text/javascript">
		$(document).ready(function(){
			// 버튼 클릭시, 데이터 생성 + 전송 
			$("#btnSend").click(function(){
				alert("버튼 클릭 !");
				
				//객체 생성
				var member = {
					"userid":"ITWILL",
					"username":"아이티윌",
					"userpw":"12345",
					"useremail":"itwill@itwill.com"
				};
				// console.log(member);
				// ajax를 사용한 정보 전달
				$.ajax({
					type:"post",
					url:"/test/add",
					contentType:"application/json",
					data: JSON.stringify(member), // JSON객체 -> JSON문자열 변환
					success:function(){
						alert(" /test/add 페이지 다녀옴 ! ");
					}
				});
				
			});// click
			
		});
	</script>
</head>
<body>
<h1>
	Hello world!  
</h1>

<P>  The time on the server is ${serverTime}. </P>

<input id="btnSend" type="button" value="회원정보 전송하기">













</body>
</html>
