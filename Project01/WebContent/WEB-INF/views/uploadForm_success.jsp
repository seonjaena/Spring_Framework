<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<c:set var = "root" value = "${pageContext.request.contextPath }/" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<table>
		<thead>
			<tr>
				<td>작성일</td>
				<td>제목</td>
				<td>포스터</td>
				<td></td>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>${requestScope.writeMediaBean.media_date }</td>
				<td>${requestScope.writeMediaBean.media_subject }</td>
				<c:if test = "${requestScope.writeMediaBean.media_poster != null }">
					<td><img src = "${root }media/image/${requestScope.writeMediaBean.media_poster }" /></td>
				</c:if>
				<td>
					<audio autoplay controls>
						<source src = "${root }media/media/${requestScope.writeMediaBean.media_file }" type = "audio/mp3" />
					</audio>
				</td>
			</tr>
		</tbody>
	</table>

</body>
</html>