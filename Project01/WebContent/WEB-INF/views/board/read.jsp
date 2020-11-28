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
						<div class="form-group">
							<label for="board_writer_name">작성자</label>
							<input type="text" id="board_writer_name" name="board_writer_name" class="form-control" value="${requestScope.readContentBean.content_writer_name }" disabled="disabled"/>
						</div>
						<div class="form-group">
							<label for="board_date">작성날짜</label>
							<input type="text" id="board_date" name="board_date" class="form-control" value="${requestScope.readContentBean.content_date }" disabled="disabled"/>
						</div>
						<div class="form-group">
							<label for="board_subject">제목</label>
							<input type="text" id="board_subject" name="board_subject" class="form-control" value="${requestScope.readContentBean.content_subject }" disabled="disabled"/>
						</div>
						<div class="form-group">
							<label for="board_content">내용</label>
							<textarea id="board_content" name="board_content" class="form-control" rows="10" style="resize:none" disabled="disabled">${requestScope.readContentBean.content_text }</textarea>
						</div>
						<div class="form-group">
							<c:if test = "${requestScope.file_list.size() != 0 }">
								<label for = "board_file">첨부 이미지</label>
								<span id="toc-toggle" onclick="openCloseToc()">보이기</span>
								<div id = "toc-content">
									<c:forEach var = "obj" items = "${requestScope.file_list }">
										<img src="${root }upload/${obj }" width="100%" /><br/><br/>
									</c:forEach>
								</div>
							</c:if>
						</div>
						<div class="form-group">
							<div class="text-right">
								<a href="${root }board/main?board_info_idx=${requestScope.board_info_idx }&page=${requestScope.page }" class="btn btn-primary">목록보기</a>
								<c:if test = "${requestScope.readContentBean.content_writer_idx == requestScope.loginUserBean.user_idx }">
									<a href="${root }board/modify?board_info_idx=${requestScope.board_info_idx }&content_idx=${requestScope.readContentBean.content_idx }&page=${requestScope.page }" class="btn btn-info">수정하기</a>
									<a href="${root }board/delete?board_info_idx=${requestScope.board_info_idx }&content_idx=${requestScope.readContentBean.content_idx }" class="btn btn-danger">삭제하기</a>
								</c:if>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="col-sm-3"></div>
		</div>
	</div>
	
	<c:import url = "/WEB-INF/views/include/bottom.jsp" />

</body>
</html>