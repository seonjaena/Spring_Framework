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
</head>
<body>

	<c:import url = "/WEB-INF/views/include/top_menu.jsp" />
	
	<form:form action = "${root }user/modify_pro" method = "POST" autocomplete = "off" modelAttribute = "modifyUserBean">
		<form:label path = "user_name">이름</form:label>
		<form:input path = "user_name" readonly = "true" /><br/><br/>
		<form:label path = "user_id">아이디</form:label>
		<form:input path = "user_id" readonly = "true" /><br/><br/>
		<form:label path = "user_pw">비밀번호</form:label>
		<form:password path = "user_pw" /><br/>
		<form:errors path = "user_pw" style = "color:red" /><br/>
		<form:label path = "user_pw2">비밀번호 확인</form:label>
		<form:password path = "user_pw2" /><br/>
		<form:errors path = "user_pw2" style = "color:red" /><br/>
		<form:button>수정</form:button>
	</form:form>
	
	<c:import url = "/WEB-INF/views/include/bottom.jsp" />

</body>
</html>