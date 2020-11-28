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
	
	<form:form action = "${root }board/write_pro" method = "POST" autocomplete = "off" modelAttribute = "writeContentBean" enctype = "multipart/form-data">
		<form:hidden path = "content_board_idx" />
		<form:label path = "content_subject">제목</form:label>
		<form:input path = "content_subject" /><br/>
		<form:errors path = "content_subject" style = "color:red" /><br/>
		<form:label path = "content_text">내용</form:label><br/>
		<form:textarea path = "content_text" rows = "10" style = "resize:none" /><br/>
		<form:errors path = "content_text" style = "color:red" /><br/>
		<form:label path = "upload_file">첨부 이미지</form:label><br/>
		<form:input multiple = "multiple" type = "file" path = "upload_file" accept = "image/*" /><br/><br/>
		<form:button class = "btn btn-primary">전송</form:button>
	</form:form>
	
	<c:import url = "/WEB-INF/views/include/bottom.jsp" />

</body>
</html>