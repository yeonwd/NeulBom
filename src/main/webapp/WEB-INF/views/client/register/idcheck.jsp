<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@
    taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<link rel="stylesheet" href="/neulbom/asset/css/admin.css">
<link rel="stylesheet" href="/neulbom/asset/css/bootstrap.css">

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아이디 중복확인</title>
<%@ include file="/WEB-INF/views/inc/asset.jsp"%>

<style>
.containerwj {
	display: flex;
	justify-content: center;
	align-items: center;
	height: 50vh;
	flex-wrap: wrap;
	max-width:80%;
	margin:0 auto;
}

.loginheader {
	display: flex;
	justify-content: center;
	align-items: center;
	height: 50vh;
	flex-wrap: wrap;
	max-width:80%;
	margin:0 auto;
}


</style>
</head>
<body>
	<div class="loginheader">
	<span style="display: flex; align-items: center;">
                        <img src="/neulbom/asset/logo/clover.png" style="width:40px; height:40px; margin-right:10px;">
                        <h1 style="display: inline; margin: 0;">늘봄</h1>
                        <hr>
    </span>
	</div>
	<div class="containerwj">
		<div class="idcheck"></div>
		<div style="width:300px; display:flex; justify-content:center;">
		<input type="button" value="확인" id="confirmButton"
			onclick="notifyParentWindow()" class="btn btn-primary"><input type="button"
			value="취소" id="cancelButton" onclick="window.close()" class="btn btn-secondary btn-light">
		</div>
	</div>

	<!-- JavaScript Bundle with Popper -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
		crossorigin="anonymous"></script>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
	<script>
	window.addEventListener("load", function() {
	    const urlParams = new URLSearchParams(window.location.search);
	    const userId = urlParams.get("userId");
	    const idcheck = document.querySelector(".idcheck");

	    if (userId === "${userIdChk}") {
	        idcheck.textContent = "아이디 : " + userId + "은(는) 사용 불가능한 아이디입니다.";
	        confirmButton.hidden = true;
	        // 부모 창의 userId 초기화
	        window.opener.document.getElementById("userId").value = "";
	    } else {
	        idcheck.textContent = "아이디 : " + userId + "은(는) 사용 가능한 아이디입니다.";
	        confirmButton.hidden = false;
	    }
	});

		function notifyParentWindow() {
			// 부모 창에 메시지 전달
			window.opener.postMessage("userIdReadOnly", "*");
			window.close();
		}
	</script>
</body>
</html>