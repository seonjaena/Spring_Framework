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
	
	<div>
		<c:if test = "${requestScope.fail == true }">
			<div>
				<p style = "color:red">로그인 실패</p>
			</div>
		</c:if>
		<div>
			<form:form action = "${root }user/login_pro" method = "POST" autocomplete = "off" modelAttribute = "tempLoginUserBean">
				<form:label path = "user_id">아이디</form:label>
				<form:input path = "user_id" /><br/>
				<form:errors path = "user_id" style = "color:red" /><br/>
				<form:label path = "user_pw">비밀번호</form:label>
				<form:password path = "user_pw" /><br/>
				<form:errors path = "user_pw" style = "color:red" /><br/>
				<form:button>로그인</form:button>
			</form:form>
		</div>
	</div>
	
	<div>
		<div style = "text-align:center">
			<div id = "naver_id_login">
				<a href="${url}">
					<img width="223" src = "https://developers.naver.com/doc/review_201802/CK_bEFnWMeEBjXpQ5o8N_20180202_7aot50.png" />
				</a>
			</div>
		</div>
	</div>
	
	<c:import url = "/WEB-INF/views/include/bottom.jsp" />
</body>
</html>