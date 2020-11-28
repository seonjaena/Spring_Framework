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
<style>
	#toc-content {
		display: none;
	}
	#toc-toggle {
		cursor: pointer;
		color: #2962ff;
	}
	#toc-toggle:hover {
		text-decoration: underline;
    }
</style>
<script>
	function openCloseToc() {
		if(document.getElementById('toc-content').style.display === 'block') {
			document.getElementById('toc-content').style.display = 'none';
			document.getElementById('toc-toggle').textContent = '보이기';
		}else {
			document.getElementById('toc-content').style.display = 'block';
			document.getElementById('toc-toggle').textContent = '숨기기';
		}
	}
</script>
</head>
<body>

	<c:import url = "/WEB-INF/views/include/top_menu.jsp" />
	
	<div class="container" style="margin-top:100px">
		<div class="row">
			<div class="col-sm-3"></div>
			<div class="col-sm-6">
				<div class="card shadow">
					<div class="card-body">
						<form:form action = "${root }board/modify_pro" method = "POST" autocomplete = "off" modelAttribute = "modifyContentBean" enctype = "multipart/form-data">
							<input type = "hidden" name = "page" value = "${requestScope.page }" readonly = "readonly" />
							<div class="form-group">
								<form:label path = "content_writer_name">작성자</form:label>
								<form:input path = "content_writer_name" class = "form-control" readonly = "true" />
							</div>
							<div class="form-group">
								<form:label path = "content_date">작성날짜</form:label>
								<form:input path = "content_date" class = "form-control" readonly = "true" />
							</div>
							<div class="form-group">
								<form:label path = "content_subject">제목</form:label>
								<form:input path = "content_subject" class = "form-control" />
								<form:errors path = "content_subject" style = "color:red" />
							</div>
							<div class="form-group">
								<form:label path = "content_text">내용</form:label>
								<form:textarea path = "content_text" class = "form-control" rows = "10" style = "resize:none" />
								<form:errors path = "content_text" style = "color:red" />
							</div>
							<div class="form-group">
								<c:if test = "${requestScope.file_list.size() != 0 }">
									<label for="board_file">첨부 이미지</label>
									<span id="toc-toggle" onclick="openCloseToc()">보이기</span>
									<div id = "toc-content">
										<c:forEach var = "obj" items = "${requestScope.file_list }">
											<img src="${root }upload/${obj }" width="100%" /><br/><br/>
										</c:forEach>
									</div>
								</c:if>
								<form:input multiple = "multiple" type = "file" path = "upload_file" class = "form-control" accept = "image/*" />
							</div>
							<div class="form-group">
								<div class="text-right">
									<button type="submit" class="btn btn-primary">수정완료</button>
									<a href="${root }board/read?board_info_idx=${sessionScope.board_info_idx }&content_idx=${sessionScope.content_idx }&page=${requestScope.page }" class="btn btn-info">취소</a>
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