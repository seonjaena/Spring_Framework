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
				<td>작성일</td>
				<td>조회수</td>
			</tr>
		</thead>
		<tbody>
			<c:forEach var = "obj" items = "${requestScope.mediaList }" varStatus = "status">
				<tr>
					<td>${status.count }</td>
					<td><a href = "${root }media/read?media_info_idx=${requestScope.media_info_idx }&media_idx=${obj.media_idx }&page=${requestScope.page }">${obj.media_subject }</a></td>
					<td>${obj.media_date }</td>
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
						<a href="${root }media/main?page=${requestScope.pageBean.prevPage }" class="page-link">이전</a>
					</li>
				</c:otherwise>
			</c:choose>
			<c:forEach var = "idx" begin = "${requestScope.pageBean.min }" end = "${requestScope.pageBean.max }">
				<c:choose>
					<c:when test = "${requestScope.pageBean.currentPage == idx }">
						<li class="page-item active">
							<a href="${root }media/main?page=${idx }" class="page-link">${idx }</a>
						</li>
					</c:when>
					<c:otherwise>
						<li class="page-item">
							<a href="${root }media/main?page=${idx }" class="page-link">${idx }</a>
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
						<a href="${root }media/main?page=${requestScope.pageBean.nextPage }" class="page-link">다음</a>
					</li>
				</c:otherwise>
			</c:choose>
		</ul>
	</div>
	
	<a href = "${root }media/upload?media_info_idx=${requestScope.media_info_idx }" class = "btn btn-primary">글쓰기</a>

	<c:import url = "/WEB-INF/views/include/bottom.jsp" />

</body>
</html>