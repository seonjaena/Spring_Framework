<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<c:set var = "root" value = "${pageContext.request.contextPath }/" />
<script>
	alert("수정을 완료하였습니다");
	location.href = "${root}media/read?media_info_idx=${requestScope.modifyMediaBean.media_board_idx}&media_idx=${requestScope.modifyMediaBean.media_idx}&page=${requestScope.page}";
</script>