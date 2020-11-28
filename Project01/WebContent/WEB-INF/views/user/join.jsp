<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "form" uri = "http://www.springframework.org/tags/form" %>
<c:set var = "root" value = "${pageContext.request.contextPath }/" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
<script>
	function checkUserIdExist() {
		
		var user_id = $("#user_id").val();
		
		if(user_id.length == 0) {
			alert("아이디를 입력하세요");
			return;
		}
		
		$.ajax({
			url : "${root}user/checkUserIdExist/" + user_id,
			type : "GET",
			dataType : "text",
			success : function(result) {
				if(result.trim() == "true") {
					alert("사용 가능한 아이디입니다");
					$("#userIdExist").val("true");
				}else {
					alert("사용 불가능한 아이디입니다");
					$("#userIdExist").val("false");
				}
			}
		})
		
	}
	
	function resetUserIdExist() {
		$("#userIdExist").val("false");
	}
</script>
</head>
<body>
	<c:import url = "/WEB-INF/views/include/top_menu.jsp" />
	
	<div>
		<div>
			<form:form action = "${root }user/join_pro" method = "POST" autocomplete = "off" modelAttribute = "joinUserBean">
				<form:hidden path = "userIdExist" />
				<form:label path = "user_name">이름</form:label>
				<form:input path = "user_name" /><br/>
				<form:errors path = "user_name" style = "color:red" /><br/>
				<form:label path = "user_id">아이디</form:label>
				<form:input path = "user_id" onkeydown = "resetUserIdExist()" />
				<button type = "button" onclick = "checkUserIdExist()">확인</button><br/>
				<form:errors path = "user_id" style = "color:red" /><br/>
				<form:label path = "user_pw">비밀번호</form:label>
				<form:password path = "user_pw" /><br/>
				<form:errors path = "user_pw" style = "color:red" /><br/>
				<form:label path = "user_pw2">비밀번호 확인</form:label>
				<form:password path = "user_pw2" /><br/>
				<form:errors path = "user_pw2" style = "color:red" /><br/>
				<form:button>전송</form:button>
			</form:form>
		</div>
	</div>
	
	<c:import url = "/WEB-INF/views/include/bottom.jsp" />
</body>
</html>