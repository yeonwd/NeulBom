<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>마이페이지 > 복지프로그램 신청</title>
</head>


<link rel="stylesheet" href="./css/mypage프로그램.css">
<link rel="stylesheet" href="./css/bootstrap.css">
<link rel="stylesheet" href="./css/footer.css">
<link rel="stylesheet" href="./javascript/mypage결제내역.js">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@48,400,0,0" />
<link rel="stylesheet" href="/neulbom/asset/css/mypagepro.css">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />

<%@ include file="/WEB-INF/views/inc/assetclient.jsp"%>
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

.tr:hover {
	cursor: pointer;
	background-color: #DFDFDF;
}

#summary {
	width: 100%;
	margin: 30px auto;
	font-weight: bold;
}

.search {

	margin: 0 10px;
}




</style>



<body>
	<%@ include file="/WEB-INF/views/inc/headerclient.jsp"%>

	<!-- 사이드바 -->
	<div class="container">
		<div class="row">
			<div class="col-md-3">
				<div class="sidebox">
					<div class="profile">
						<img src="/neulbom/asset/images/user.png" id="profileimg">
						<h1 class="profilename">${sessionScope.name}님</h1>
						<h1 class="profilename_id">(${sessionScope.id})</h1>
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



			<div class="col-md-9" >
				<div class="box" style="height: 100%; width: 100%;">
					<!-- 버튼 -->
					<div>
						<input type="button" class="btn btn-secondary" style="font-size: 20px; margin-right: 20px;" name="btnradio"
							id="btn1" checked onclick="location.href='/neulbom/client/mypage/program.do';" value="복지프로그램 신청">

						<input type="button" class="btn btn-primary"  style="font-size: 20px;" name="btnradio"
							id="btn2" selected onclick="location.href='/neulbom/client/mypage/myprogram.do?resi_seq=${resi_seq}';" value="내 신청내역">
					</div>

					<br> <br>
					<form method="GET" action="/neulbom/client/mypage/myprogram.do?resi_seq=${resi_seq }">
                      <div class="selec">
                         <input type="date" name="start_date" class="search"><div style="font-size: 20px;"> ~ </div><input type="date" class="search" name="end_date">
                       <button type="submit" class="btn " style="background-color: #AAA; color: white;">검색</button>
                    </div>
                    </form>
					<hr>

					<c:if test="${map.start_date != null}">
                    <div id="summary">${map.start_date} ~ ${map.end_date} 복지 프로그램 내역이 총 ${totalCount}건이 있습니다.</div>
               </c:if>
               <c:if test="${map.start_date == null}">
                    <div id="summary">복지 프로그램 신청 내역이 총 ${totalCount}건이 있습니다.</div>
                    </c:if>



					<div class="row justify-content-center">
						<form id="delprogram" method="POST" action="/neulbom/client/mypage/delprogram.do">
						<table class="table">
							<thead>
								<tr>
									<th scope="col">번호</th>
									<th scope="col">날짜</th>
									<th scope="col">프로그램명</th>
									<th scope="col">프로그램 내용</th>
									<th scope="col">장소</th>
									<th scope="col">정원</th>
									<th scope="col">신청</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${myplist}" var="dto">
									<tr class="tr">
										<td onclick="location.href='/neulbom/client/mypage/detailprogram.do?prog_seq=${dto.prog_seq}';">${dto.displayed_seq}</td>
										<td onclick="location.href='/neulbom/client/mypage/detailprogram.do?prog_seq=${dto.prog_seq}';">${dto.prog_date}</td>
										<td onclick="location.href='/neulbom/client/mypage/detailprogram.do?prog_seq=${dto.prog_seq}';" style="text-align: justify">${dto.title}</td>
										<td onclick="location.href='/neulbom/client/mypage/detailprogram.do?prog_seq=${dto.prog_seq}';" style="text-align: justify">${dto.content}</td>
										<td onclick="location.href='/neulbom/client/mypage/detailprogram.do?prog_seq=${dto.prog_seq}';">${dto.place}</td>
										<td onclick="location.href='/neulbom/client/mypage/detailprogram.do?prog_seq=${dto.prog_seq}';">${dto.people}</td>
										<c:if test="${dto.prog_date > limitDate }">
								        <td style="padding-bottom: 0px; padding-top: 0.25rem;"><button type="button" class="btn btn-primary btn-sm" style="width: 70px; font-size: 16px; font-weight: bold;" onclick="confirmCancel()">취소</button></td>
										</c:if>
										<c:if test="${dto.prog_date <= limitDate }">
										<td>취소 불가</td>
										</c:if>
									</tr>
								        <input type="hidden" name="prog_seq" value="${dto.prog_seq }">
								        <input type="hidden" name="resi_seq" value="${resi_seq }">
								        <input type="hidden" name="papp_seq" value="${dto.papp_seq }">
								</c:forEach>

							</tbody>
						</table>
						</form>
						
						

						<!-- 페이징 -->
						<c:if test="${myplist.size() != 0 }">
							<div id="paging" class="pagination justify-content-center"
								style="text-align: center; margin-bottom: 10px;">${pagination}</div>
						</c:if>

						<br> <br> <br> <br> <br>
						<!-- 캘린더 -->
						<div class="calendar"></div>


					</div>
				</div>
			</div>
		</div>
	</div>





	<%@ include file="/WEB-INF/views/inc/footerclient.jsp"%>
</body>
<script>
  //check the console for date click event
//Fixed day highlight
//Added previous month and next month view

	function delProgram(prog_seq) {
	  
	  var resi_seq = ${resi_seq};
		
		location.href='/neulbom/client/mypage/delprogram.do?prog_seq=' + prog_seq + '&resi_seq=' + resi_seq;
		
	}
  
	function confirmCancel() {
	    var result = confirm("정말 취소하시겠습니까?");
	    if (result) {
	       document.getElementById("delprogram").submit();
	    }
	    else {
	      
	    }
	  }



   

  
</script>
</html>