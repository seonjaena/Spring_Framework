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
	
	<div class="container" style="margin-top:100px">
		<div class="row">
			<div class="col-sm-3"></div>
			<div class="col-sm-6">
				<div class="card shadow">
					<div class="card-body">
						<div class="form-group">
							<label for="media_writer_name">작성자</label>
							<input type="text" id="media_writer_name" name="media_writer_name" class="form-control" value="${requestScope.readMediaBean.media_writer_name }" disabled="disabled"/>
						</div>
						<div class="form-group">
							<label for="media_date">작성날짜</label>
							<input type="text" id="media_date" name="media_date" class="form-control" value="${requestScope.readMediaBean.media_date }" disabled="disabled"/>
						</div>
						<div class="form-group">
							<label for="media_subject">제목</label>
							<input type="text" id="media_subject" name="media_subject" class="form-control" value="${requestScope.readMediaBean.media_subject }" disabled="disabled"/>
						</div>
						<div class="form-group">
							<c:if test = "${requestScope.readMediaBean.media_poster != null }">
								<c:choose>
									<c:when test = "${requestScope.media_info_idx == 1 }">
										<label for = "board_file">음원 포스터</label>
									</c:when>
									<c:otherwise>
										<label for = "board_file">영상 포스터</label>
									</c:otherwise>
								</c:choose>
								<img src="${root }media/image/${requestScope.readMediaBean.media_poster }" width="100%" />
							</c:if>
						</div>
						<div class="form-group">
							<c:choose>
								<c:when test = "${requestScope.media_info_idx == 1 }">
									<label for="media_file">음원</label>
									<audio autoplay controls controlsList = "nodownload">
										<source src = "${root }media/media/${requestScope.readMediaBean.media_file }" type = "audio/mp3" />
									</audio>
								</c:when>
								<c:otherwise>
									<label for = "media_file">영상</label>
									<video controlsList = "nodownload" src = "${root }media/media/${requestScope.readMediaBean.media_file }" controls width = "100%"></video>
								</c:otherwise>
							</c:choose>
						</div>
						<div class="form-group">
							<div class="text-right">
								<a href="${root }media/main?media_info_idx=${requestScope.media_info_idx }&page=${requestScope.page }" class="btn btn-primary">목록보기</a>
								<c:if test = "${requestScope.readMediaBean.media_writer_idx == requestScope.loginUserBean.user_idx }">
									<a href="${root }media/modify?media_info_idx=${requestScope.media_info_idx }&media_idx=${requestScope.media_idx }&page=${requestScope.page }" class="btn btn-info">수정하기</a>
									<a href="${root }media/delete?media_info_idx=${requestScope.media_info_idx }&media_idx=${requestScope.media_idx }" class="btn btn-danger">삭제하기</a>
								</c:if>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="col-sm-3"></div>
		</div>
	</div>
	<p>${requestScope.readMediaBean.media_writer_idx }</p>
	<c:import url = "/WEB-INF/views/include/bottom.jsp" />

</body>
</html>