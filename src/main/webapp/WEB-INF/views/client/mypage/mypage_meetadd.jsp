<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<%@ include file="/WEB-INF/views/inc/assetclient.jsp"%>


<link rel="stylesheet" href="/neulbom/asset/css/mypage.css">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@48,400,0,0" />
<style>
html, body {
	height: 100vh;
	margin: 0;
}

@font-face {
	font-family: 'IBMPlexSansKR-Regular';
	src:
		url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_20-07@1.0/IBMPlexSansKR-Regular.woff')
		format('woff');
	font-weight: normal;
	font-style: normal;
}

html {
	font-family: 'IBMPlexSansKR-Regular';
}

body {
	margin: 0;
}
</style>
</head>
<body>
	<%@ include file="/WEB-INF/views/inc/headerclient.jsp"%>
	<div class="container">
		<div class="row">
			<div class="col-md-3">
				<div class="sidebox">
					<div class="profile">

						<c:set var="dto" value="${dto}" />
						<img src="/neulbom/asset/images/user.png" id="profileimg">
						<h1 class="profilename">${dto.name}님</h1>
						<h1 class="profilename_id">(${dto.id})</h1>

					</div>
					<div class="menubox">
                  <div class="menu" id="create_account"
                     onclick="location.href='/neulbom/client/mypage/mypage_info.do';">
                     <span class="material-symbols-outlined">person</span> 내정보
                  </div>
                  <div class="menu" id="search_staff"
                     onclick="location.href='/neulbom/client/mypage/mypage_payment_details.do';">
                     <span class="material-symbols-outlined">
                        credit_score </span> 결제관리
                  </div>

                  <div class="menu" id="manage_account"
                     onclick="location.href='/neulbom/client/mypage/mypage_meet.do';">
                     <span class="material-symbols-outlined">
                        pending_actions </span> 면회관리
                  </div>

                  <div class="menu" id="staff_salary"
                     onclick="location.href='/neulbom/client/mypage/program.do';">
                     <span class="material-symbols-outlined btnprog"> groups_2
                     </span> 복지프로그램관리
                  </div>
                  <div class="menu" id="manage_equip"
                     onclick="location.href='/neulbom/client/mypage/mypage_inquiry.do';">
                     <span class="material-symbols-outlined">
                        support_agent </span> 내 문의
                  </div>

                  <hr>
                  <div class="menu" id="manage_qna_consult">
                     <span class="material-symbols-outlined"> help </span> 늘봄
                  </div>
               </div>
            </div>
         </div>



			<div class="col-md-9">
				<div class="box" style="height:100%;">
					<div class="title">면회신청</div>
					<hr style="margin-bottom: 120px;">
					<div class="row justify-content-center">
						<form method="post" action="/neulbom/client/mypage/mypage_meetadd.do">
						<table class="table">
							<tr>
								<th scope="row">
									<div class="subtitle">면회 날짜:</div>
								</th>
								<td>
									<input type="date" id="visitDate" onchange="setSelectedDate()">
          							<input type="hidden" id="date" name="date">
								</td>
								
							</tr>
							<tr>
								<th scope="row">
									<div class="subtitle">면회시간:</div>
								</th>
								<td>
									<div class="content">
										<select name="meet_time" style="margin-right:50px;">
								            <option value="9~12">오전: 9시  - 12시</option>
								            <option value="12~3">오후: 12시 - 3시</option>
								            <option value="3~6">오후: 3시  - 6시</option>
								          </select>
									</div>
								</td>
							</tr>
							<tr>
								<th scope="row">
									<div class="subtitle">입주자 이름:</div>
								</th>
								<td><div class="content">${dto.resi_name}</div></td>
							</tr>
							<%-- <tr>
								<th scope="row">
									<div class="subtitle">방문 인원:</div>
								</th>
								<td><div class="content">${dto.email}</div></td>
							</tr> --%>
						</table>
						<hr style="margin-top: 120px; margin-bottom: 120px;">
							<div class="d-grid gap-2 col-6 mx-auto">
								<button class="btn btn-outline-primary" type="submit" onclick="location.href='/neulbom/client/mypage/mypage_meetadd.do';">면회 신청</button>
								<button class="btn btn-outline-primary" type="button" onclick="history.back()">취소</button>
							</div>
						</form>
						<br>

					</div>
				</div>
			</div>
		</div>
	</div>

<%@ include file="/WEB-INF/views/inc/footerclient.jsp"%>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
<script>
	function setSelectedDate() {
	    var selectedDate = document.getElementById("visitDate").value;
	    var formattedDate = new Date(selectedDate).toISOString().split('T')[0];
	    document.getElementById("date").value = formattedDate;
	  }
</script>
</body>
</html>
