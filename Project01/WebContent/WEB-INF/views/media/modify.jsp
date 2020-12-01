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
	
	<div class="container" style="margin-top:100px">
		<div class="row">
			<div class="col-sm-3"></div>
			<div class="col-sm-6">
				<div class="card shadow">
					<div class="card-body">
						<form:form action = "${root }media/modify_pro" method = "POST" autocomplete = "off" modelAttribute = "modifyMediaBean" enctype = "multipart/form-data">
							<div class="form-group">
								<form:label path = "media_writer_name">작성자</form:label>
								<form:input path = "media_writer_name" class = "form-control" readonly = "true" />
							</div>
							<div class="form-group">
								<form:label path = "media_date">작성날짜</form:label>
								<form:input path = "media_date" class = "form-control" readonly = "true" />
							</div>
							<div class="form-group">
								<form:label path = "media_subject">제목</form:label>
								<form:input path = "media_subject" class = "form-control" />
								<form:errors path = "media_subject" style = "color:red" />
							</div>
							<div class="form-group">
								<c:if test = "${requestScope.modifyMediaBean.media_poster != null }">
									<c:choose>
										<c:when test = "${requestScope.media_info_idx == 1 }">
											<label for = "media_file">음원 포스터</label>
										</c:when>
										<c:otherwise>
											<label for = "media_file">영상 포스터</label>
										</c:otherwise>
									</c:choose>
									<img src="${root }media/image/${requestScope.modifyMediaBean.media_poster }" width="100%" />
								</c:if>
								<form:input type = "file" path = "media_posterMF" accept = "image/*" />
							</div>
							<div class="form-group">
								<c:choose>
									<c:when test = "${requestScope.media_info_idx == 1 }">
										<label for="media_file">음원</label>
										<audio autoplay controls controlsList = "nodownload">
											<source src = "${root }media/media/${requestScope.modifyMediaBean.media_file }" type = "audio/mp3" />
										</audio>
										<form:input type = "file" path = "media_fileMF" accept = "audio/*" />
									</c:when>
									<c:otherwise>
										<label for = "media_file">영상</label>
										<video controlsList = "nodownload" src = "${root }media/media/${requestScope.modifyMediaBean.media_file }" controls width = "100%"></video>
										<form:input type = "file" path = "media_fileMF" accept = "video/*" />
									</c:otherwise>
								</c:choose>
							</div>
							<div class="form-group">
								<div class="text-right">
									<button type="submit" class="btn btn-primary">수정완료</button>
									<a href="${root }media/modify_pro?media_info_idx=${requestScope.media_info_idx }&media_idx=${requestScope.modifyMediaBean.media_idx }&page=${requestScope.page }" class="btn btn-info">취소</a>
								</div>
							</div>
						</form:form>
					</div>
				</div>
			</div>
			<div class="col-sm-3"></div>
		</div>
	</div>

	<c:import url = "/WEB-INF/views/include/bottom.jsp" />

</body>
</html>