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
#content {
	height: 400px;
	vertical-align: middle;
}

#title>td, #content>td {
	padding: 13px 25px;
}

#title-input textarea {
	width: 100%;
	resize: none;
	border: none;
	max-height: 100%;
	height: 25px;
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

.semititle {
	cursor: default;
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
						<span id="selected_menu_text">새 글 쓰기</span>
					</div>
					<!-- selected_menu -->

				</div>
				<!-- semi_title -->
				<div class="main-box">
					<form method="POST" action="/neulbom/admin/board/editfood.do" enctype="multipart/form-data">
					<input type="hidden" name="seq" value="${dto.food_seq}">
						<table class="table">
							<colgroup>
								<col width=25%>
								<col width=75%>
							</colgroup>
							<tr id="title">
								<td>제목</td>
								<td id="title-input"><textarea name="title" autofocus
										placeholder="제목을 입력하세요."></textarea></td>
							</tr>
							<tr id="content">
								<td>식단표 파일</td>
								 <td style="text-align: left;"><input type="file" name="fname" id="fname" /></td>
							</tr>
							<tr>
								<td colspan="4" id="button-td">
									<div id="button">
										<div class="add movable" id="add">
											<span id="add_txt">등록</span>
										</div>
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
	
	$('#title-input textarea').val('${dto.title}');
	
		$('#add').click(function() {
			$('form').submit();
		});
	</script>
</body>
</html>