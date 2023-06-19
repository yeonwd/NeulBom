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
#message, #buttons {
	display: flex;
	justify-content: center;
	align-items: center;
	text-align: center;
	align-items: center;
}

#message {
	font-size: 20px;
	margin-bottom: 16px;
}

#buttons>div {
	margin-right: 10px;
	margin-left: 10px;
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
						<span id="selected_menu_text">면회 승인</span>
					</div>
					<!-- selected_menu -->
				</div>
				<!-- semi_title -->
				<div class="main-box">
					<div>
						<div id="message">면회를 승인하시겠습니까?</div>

						<form method="POST" action="/neulbom/admin/manage/meetconfirmation.do">
							<div id="buttons">
								<div id="confirm" class="add movable">
									<span id="add_txt">승인하기</span>
								</div>
								<input type="hidden" name="seq" value="${seq}">
								<div class="back movable" onclick='history.back();'>
									<span id="back_txt">돌아가기</span>
								</div>
							</div>
						</form>
					</div>
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
	
		$('#confirm').click(function() {
			$('form').submit();
		});
		
	</script>
</body>
</html>