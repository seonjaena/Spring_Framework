<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<c:set var = "root" value = "${pageContext.request.contextPath }/" />
<script>
	alert("게시물 수정 완료");
	location.href = "${root}board/read?board_info_idx=${requestScope.board_info_idx}&content_idx=${requestScope.content_idx}&page=${requestScope.page}";
</script>