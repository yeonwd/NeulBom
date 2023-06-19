<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@
    taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>늘봄 관리자</title>
<%@ include file="/WEB-INF/views/inc/asset.jsp"%>

<style>
#content-tr {
	height: 300px;
	vertical-align: middle;
}

#content, #comment {
	padding: 30px 15px 30px 15px;
}

#content>pre, #comment>pre {
	text-align: justify;
	padding: 30px 45px 30px 15px;
}

#button {
	float: right;
}

#button>div {
	display: inline-block;
	margin-right: 10px;
}

#button-td {
	padding: 12px 0px;
}

.selected_menu {
	cursor: default;
}

#attachment {
	text-align: center;
}

#attachment>img {
	max-width: 400px;
}

#content pre {
	white-space: pre-wrap; /* Preserve line breaks */
	overflow: auto; /* Add scrollbars when necessary */
}

td.comment {
	vertical-align: middle;
	text-align: center;
}

#comment pre {
	font-family: 'IBMPlexSansKR-Regular';
	margin-top: 4px;
	margin-bottom: 4px;
}

#comment-content {
	white-space: nowrap;
	overflow: hidden;
	text-overflow: ellipsis;
}

#comment-content pre {
	white-space: wrap;
	text-align: justify;
}

#comment {
	height: 200px;
	vertical-align: middle;
}

.main {
	height: 100vh !important;
}

#headline {
	background-color: rgba(39, 166, 174, 0.2);
}

#headline>span {
	color: #5888CE;
}

#rcontent {
	height: 400px;
	vertical-align: middle;
}

#rtitle>td, #rcontent>td {
	padding: 13px 25px;
}

#rtitle-input textarea {
	width: 100%;
	resize: none;
	border: none;
	max-height: 100%;
	height: 25px;
}

#rcontent-input textarea {
	width: 100%;
	height: 350px;
	min-height: 100%;
	max-height: 100%;
	resize: none;
	overflow: auto;
	border: none;
}

.scrollable {
	clear: both;
	width: auto;
	height: 650px;
	display: block;
	overflow: auto;
	overflow-x: hidden;
	padding: 10px;
}
</style>
</head>
<body>

	<div class="main">
		<%@ include file="/WEB-INF/views/inc/adSidemenu.jsp"%>
		<div class="content-box">
			<div id="inner-box">
				<div class="semititle">
					<div class="selected_menu">
						<span id="selected_menu_text">글 읽기</span>
					</div>
					<!-- selected_menu -->

				</div>
				<!-- semi_title -->
				<div class="main-box scrollable">
					<form method="POST" action="/neulbom/admin/manage/viewqna.do"
						enctype="multipart/form-data">
						<input type="hidden" name="seq" value="${dto.qna_seq}">
						<table class="table" style="table-layout: fixed;">
							<colgroup>
								<col width=25%>
								<col width=25%>
								<col width=25%>
								<col width=25%>
							</colgroup>
							<tr>
								<td>제목</td>
								<td><b>${dto.title}</b></td>
								<td>등록일</td>
								<td>${dto.qna_date}</td>
							</tr>
							<tr>
								<td>글쓴이</td>

								<td><c:if test="${not empty dto.pname}">
										${dto.pname}(${dto.type})
										</c:if> <c:if test="${not empty dto.rname}">
										${dto.rname}(${dto.type})
										</c:if></td>

								<td>조회수</td>
								<td>${dto.read}</td>
							</tr>
							<tr id="content-tr">
								<td>내용</td>
								<td id="content" colspan="3"><c:if
										test="${not empty dto.fname}">
										<div id="attachment">
											<img src="/neulbom/asset/image/${dto.fname}">
										</div>
									</c:if> <pre>${dto.content}</pre></td>
							</tr>

							<tr>
								<td colspan="4" id="headline"><span>답글</span></td>
							</tr>

							<c:if test="${dto.isReply eq 'y'}">
								<tr>
									<td>제목</td>
									<td>${qdto.title}</td>
									<td>작성자</td>
									<td>${qdto.replier}</td>
								</tr>
								<tr id="comment">
									<td>내용</td>
									<td colspan="3" id="comment-content"><c:if
											test="${not empty qdto.fname}">
											<div id="attachment">
												<img src="/neulbom/asset/qreply/${qdto.fname}">
											</div>
										</c:if> <pre>${qdto.content}</pre></td>
								</tr>
							</c:if>

							<c:if test="${dto.isReply eq 'n'}">

								<tr>
									<td>제목</td>
									<td colspan="3" id="rtitle-input"><textarea id="rtitle"
											name="rtitle" autofocus></textarea></td>
								</tr>
								<tr>
									<td>내용</td>
									<td colspan="3" id="rcontent-input"><textarea
											id="rcontent" name="rcontent"></textarea></td>
								</tr>
								<tr>
									<td>첨부파일</td>
									<td colspan="3" style="text-align: left;"><input
										type="file" name="fname" id="fname" /></td>
								</tr>

							</c:if>


							<tr>
								<td colspan="4" id="button-td">
									<div id="button">
										<c:if test="${dto.isReply eq 'n'}">
											<div class="delete movable" id="reply">
												<span id="delete_txt">답변하기</span>
											</div>
										</c:if>
										<div class="back movable" onclick='history.back();'>
											<span id="back_txt">돌아가기</span>
										</div>
									</div>
								</td>
							</tr>

						</table>
					</form>
				</div>

				<!-- main-box -->
			</div>
			<!-- inner-box -->
		</div>
		<!-- content-box -->

	</div>


	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
	<script>
		var rtitle = document.getElementById("rtitle");
		rtitle.placeholder = "제목을 입력하세요";

		var rcontent = document.getElementById("rcontent");
		rcontent.placeholder = "내용을 입력하세요";

		$('#reply').click(function() {
			$('form').submit();
		});
	</script>
</body>
</html>