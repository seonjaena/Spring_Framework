<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
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
	<table border = "1">
		<thead>
			<tr>
				<td>글번호</td>
				<td>제목</td>
				<td>작성자</td>
				<td>작성일</td>
				<td>조회수</td>
			</tr>
		</thead>
		<tbody>
			<c:forEach var = "obj" items = "${requestScope.contentList }" varStatus = "status">
				<tr>
					<td>${status.count }</td>
					<td><a href = "${root }board/read?board_info_idx=${requestScope.board_info_idx }&content_idx=${obj.content_idx }&page=${requestScope.pageBean.currentPage }">${obj.content_subject }</a></td>
					<td>${obj.content_writer_name }</td>
					<td>${obj.content_date }</td>
					<td>${obj.views }</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	
	<div class="d-none d-md-block">
		<ul class="pagination justify-content-center">
			<c:choose>
				<c:when test = "${requestScope.pageBean.currentPage == 1 }">
					<li class="page-item disabled">
						<a href="#" class="page-link">이전</a>
					</li>
				</c:when>
				<c:otherwise>
					<li class="page-item">
						<a href="${root }board/main?board_info_idx=${requestScope.board_info_idx }&page=${requestScope.pageBean.prevPage }" class="page-link">이전</a>
					</li>
				</c:otherwise>
			</c:choose>
			<c:forEach var = "idx" begin = "${requestScope.pageBean.min }" end = "${requestScope.pageBean.max }">
				<c:choose>
					<c:when test = "${requestScope.pageBean.currentPage == idx }">
						<li class="page-item active">
							<a href="${root }board/main?board_info_idx=${requestScope.board_info_idx }&page=${idx }" class="page-link">${idx }</a>
						</li>
					</c:when>
					<c:otherwise>
						<li class="page-item">
							<a href="${root }board/main?board_info_idx=${requestScope.board_info_idx }&page=${idx }" class="page-link">${idx }</a>
						</li>
					</c:otherwise>
				</c:choose>
			</c:forEach>
			<c:choose>
				<c:when test = "${requestScope.pageBean.currentPage == requestScope.pageBean.pageCnt }">
					<li class="page-item disabled">
						<a href="#" class="page-link">다음</a>
					</li>
				</c:when>
				<c:otherwise>
					<li class="page-item">
						<a href="${root }board/main?board_info_idx=${requestScope.board_info_idx }&page=${requestScope.pageBean.nextPage }" class="page-link">다음</a>
					</li>
				</c:otherwise>
			</c:choose>
		</ul>
	</div>
	
	<c:if test = "${requestScope.loginUserBean.userSocial == false }">
		<a href = "${root }board/write?board_info_idx=${requestScope.board_info_idx }" class = "btn btn-primary">글쓰기</a>
	</c:if>
	<c:if test = "${requestScope.loginUserBean.userSocial == true }">
		<p style = "color:red">소셜 로그인 유저는 사용 불가능합니다.</p>
		<button type = "button" class = "btn btn-danger" disabled = "disabled">글쓰기</button>
	</c:if>

	<c:import url = "/WEB-INF/views/include/bottom.jsp" />

</body>
</html>