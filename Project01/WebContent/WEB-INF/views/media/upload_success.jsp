<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<c:set var = "root" value = "${pageContext.request.contextPath }/" />
<script>
	alert("등록이 완료되었습니다");
	location.href = "${root}media/read?media_info_idx=${requestScope.writeMediaBean.media_board_idx}&media_idx=${requestScope.writeMediaBean.media_idx}&page=1";
</script>