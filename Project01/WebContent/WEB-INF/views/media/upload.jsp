<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "form" uri = "http://www.springframework.org/tags/form" %>
<c:set var = "root" value = "${pageContext.request.contextPath }/" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>media/updateForm.jsp</title>
</head>
<body>
	<c:choose>
		<c:when test = "${requestScope.media_info_idx == 1 }">
			<form:form action = "${root }media/upload_pro" method = "POST" autocomplete = "off" modelAttribute = "writeMediaBean" enctype = "multipart/form-data">
				<form:label path = "media_subject">제목</form:label>
				<form:input path = "media_subject" /><br/>
				<form:errors path = "media_subject" style = "color:red" /><br/>
				<form:label path = "media_posterMF">앨범포스터</form:label>
				<form:input type = "file" path = "media_posterMF" accept = "image/*" /><br/><br/>
				<form:label path = "media_fileMF">음원</form:label>
				<form:input type = "file" path = "media_fileMF" accept = "audio/*" /><br/>
				<form:errors path = "media_fileMF" style = "color:red" /><br/>
				<form:button>업로드</form:button>
			</form:form>
		</c:when>
		<c:otherwise>
			<form:form action = "${root }media/upload_pro" method = "POST" autocomplete = "off" modelAttribute = "writeMediaBean" enctype = "multipart/form-data">
				<form:label path = "media_subject">제목</form:label>
				<form:input path = "media_subject" /><br/>
				<form:errors path = "media_subject" style = "color:red" /><br/>
				<form:label path = "media_posterMF">영상포스터</form:label>
				<form:input type = "file" path = "media_posterMF" accept = "image/*" /><br/><br/>
				<form:label path = "media_fileMF">영상</form:label>
				<form:input type = "file" path = "media_fileMF" accept = "video/*" /><br/>
				<form:errors path = "media_fileMF" style = "color:red" /><br/>
				<form:button>업로드</form:button>
			</form:form>
		</c:otherwise>
	</c:choose>
</body>
</html>