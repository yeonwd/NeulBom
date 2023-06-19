<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@
    taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>늘봄 관리자</title>
<%@ include file="/WEB-INF/views/inc/asset.jsp"%>

<style>

#content-tr {
	height: 400px;
	vertical-align: middle;
}

#content {
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

#content pre {
  white-space: pre-wrap;  /* Preserve line breaks */
  overflow: auto;         /* Add scrollbars when necessary */
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
					<table class="table">
						<colgroup>
							<col width=25%>
							<col width=25%>
							<col width=25%>
							<col width=25%>
						</colgroup>
						<tr>
							<td>제목</td>
							<td colspan="3"><b>${dto.title}</b></td>
						</tr>
						<tr>
							<td>등록일</td>
							<td>${dto.notice_date}</td>
							<td>조회수</td>
							<td>${dto.read}</td>
						</tr>
						<tr id="content-tr">
							<td>내용</td>
							<td id="content" colspan="3"><pre>${dto.content}</pre></td>
						</tr>
						<tr>
							<td colspan="4" id="button-td">
								<div id="button">
									<div class="edit movable"
										onclick="location.href='/neulbom/admin/board/editnotice.do?seq=${dto.notice_seq}'">
										<span id="edit_txt">수정</span>
									</div>
									<div class="delete movable"
										onclick="location.href='/neulbom/admin/board/deletenotice.do?seq=${dto.notice_seq}'">
										<span id="delete_txt">삭제</span>
									</div>
									<div class="back movable" onclick='history.back();'>
										<span id="back_txt">돌아가기</span>
									</div>
								</div>
							</td>
						</tr>
					</table>
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
		
	</script>
</body>
</html>