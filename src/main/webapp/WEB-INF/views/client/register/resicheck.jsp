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
	flex-direction: column;
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
		<div class="resiId" style="display: block;"></div>
		<div class="resiName" style="display: block;"></div>
		<div class="resiTel" style="display: block;"></div>
		<span><input type="button" value="확인" id="confirmButton"
			onclick="notifyParentWindow()" class="btn btn-primary"> <input type="button"
			value="취소" id="cancelButton" onclick="window.close()" class="btn btn-secondary btn-light"></span>
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
	    const resiId = urlParams.get("resiId");
	    const resiName = "${resiName}";
	    const resiTel = "${resiTel}";

	    const resicheck1 = document.querySelector(".resiId");
	    const resicheck2 = document.querySelector(".resiName");
	    const resicheck3 = document.querySelector(".resiTel");
	    const confirmButton = document.getElementById("confirmButton");
	    
	    if (resiId && resiName && resiTel) {
	        resicheck1.textContent = "입주자 아이디 : " + resiId;
	        resicheck2.textContent = "입주자 이름 : " + resiName;
	        resicheck3.textContent = "입주자 전화번호 : " + resiTel;
	        confirmButton.hidden = false;
	    } else {
	        resicheck1.textContent = "일치하는 정보가 없습니다.";
	        confirmButton.hidden = true;
	    }
	});

	function notifyParentWindow() {
	    window.opener.postMessage("userIdReadOnly", "*");
	    window.close();
	}
	</script>
</body>
</html>