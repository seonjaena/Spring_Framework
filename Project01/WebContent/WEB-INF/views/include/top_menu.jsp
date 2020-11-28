<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<c:set var = "root" value = "${pageContext.request.contextPath }/" />
<nav class = "navbar navbar-expand-sm bg-dark navbar-dark">
	<a class = "navbar-brand" href = "${root }main">선재</a>
	<button class = "navbar-toggler" type = "button" data-toggle = "collapse" data-target = "#navMenu">
		<span class = "navbar-toggler-icon"></span>        
	</button>
	<div class="collapse navbar-collapse" id = "navMenu">
		<ul class = "navbar-nav">
			<li class = "nav-item dropdown">
				<a class = "nav-link dropdown-toggle" href = "#" id = "navbardrop" data-toggle = "dropdown">게시판</a>
				<div class = "dropdown-menu">
					<c:forEach var = "obj" items = "${requestScope.topMenuList }">
						<a class = "dropdown-item" href = "${root }board/main?board_info_idx=${obj.board_info_idx }">${obj.board_info_name }</a>
					</c:forEach>
				</div>
			</li>
			<li class = "nav-item dropdown">
				<a class = "nav-link dropdown-toggle" href = "#" id = "navbardrop" data-toggle = "dropdown">사운드클라우드</a>
				<div class = "dropdown-menu">
					<c:forEach var = "obj" items = "${requestScope.mediaMenuList }">
						<a class = "dropdown-item" href = "${root }media/main?media_info_idx=${obj.media_info_idx }">${obj.media_info_name }</a>
					</c:forEach>
				</div>
			</li>
			<li class = "nav-item">
				<a class = "nav-link" href = "#">Link3</a>
			</li>
		</ul>
		<ul class="navbar-nav ml-auto">
			<li class = "nav-item">
				<form class = "form-inline" action = "#">
					<input class = "form-control" type = "text" placeholder = "검색어" />
					<button class = "btn btn-success" type = "submit">찾기</button>
				</form>
			</li>
			<c:choose>
				<c:when test = "${requestScope.loginUserBean.userLogin == true }">
					<li class = "nav-item">
						<a class = "nav-link" href = "${root }user/logout">로그아웃</a>
					</li>
					<li class = "nav-item">
						<a class = "nav-link" href = "${root }user/modify">정보수정</a>
					</li>
				</c:when>
				<c:otherwise>
					<li class = "nav-item">
						<a class = "nav-link" href = "${root }user/login">로그인</a>
					</li>
					<li class = "nav-item">
						<a class = "nav-link" href = "${root }user/join">회원가입</a>
					</li>
				</c:otherwise>
			</c:choose>
		</ul>
	</div>
</nav>