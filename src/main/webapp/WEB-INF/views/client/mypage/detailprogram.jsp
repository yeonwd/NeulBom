<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
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

.tr {
	width: 150px;
	
}

.table {
	margin: 30px 0;

}

.regi {

	width: 85px;
	height: 38px;
	font-size: 16px;
}


</style>



<body>
	<%@ include file="/WEB-INF/views/inc/headerclient.jsp" %>
	<div class="container">
		<div class="row">
			<div class="col-md-3">
				<div class="sidebox">
					<div class="profile">
						<img src="/neulbom/asset/images/user.png" id="profileimg">
						<h1 class="profilename">${sessionScope.name}님</h1>
						<h1 class="profilename_id">(${sessionScope.id})</h1>
						<!-- <div class="profilelevel">계정 관리자</div> -->
					</div>
					   <div class="menubox">
                  <div class="menu" id="create_account">
                     <span class="material-symbols-outlined">person</span> 내정보
                  </div>
<!--                   <div class="menu" id="manage_money">
                     <span class="material-symbols-outlined"> credit_card </span> 결제신청
                  </div> -->
                  <div class="menu" id="search_staff">
                     <span class="material-symbols-outlined"> credit_score </span>
                     결제관리
                  </div>


                  <div class="menu" id="manage_account">
                     <span class="material-symbols-outlined"> pending_actions </span>
                     면회관리
                  </div>


                  <div class="menu" id="staff_salary" onclick="location.href='/neulbom/client/mypage/program.do';">
                     <span class="material-symbols-outlined"> groups_2 </span> 복지프로그램관리
                  </div>
                  <div class="menu" id="manage_equip">
                     <span class="material-symbols-outlined"> support_agent </span> 내 문의
                  </div>

                  <hr>
                  <div class="menu" id="manage_qna_consult">
                     <span class="material-symbols-outlined"> help </span> 늘봄
                  </div>

               </div>
            </div>
         </div>


			<div class="col-md-9">
				<div class="box" style="height: 100%">
					

			<form id="regiprogram" method="POST" action="/neulbom/client/mypage/regiprogram.do">
					<div class="row justify-content-center" style="font-size: 18px;">
						<table class="table">
								<tr>
									<th class="tr" scope="col">프로그램명</th>
									<th>${dto.title }
									</th>
	 							</tr>
								<tr>
									<th class="tr"  scope="col">내용</th>
									<td>${dto.content }</td>
								</tr>
	 							<tr>
									<th class="tr"  scope="col">날짜</th>
									<td>${dto.prog_date }</td>
								</tr>
								<tr>
									<th class="tr"  scope="col">장소</th>
									<td>${dto.place }</td>
								</tr>
								<tr>	
									<th class="tr"  scope="col">인원수</th>
									<td>${dto.apply} / ${dto.people }</td>
								</tr>
								
						</table>
						
					<hr>
					
						<div style="text-align: center; font-weight: bold; font-size: 23px;">
						<c:if test="${dto.apply >= dto.people || dto.prog_date <= limitDate}">
										<td style="color: red">이번 프로그램은 마감되었습니다.</td>
						</c:if>
						
						<c:if test="${dto.apply < dto.people && result == null && dto.prog_date > limitDate}">
										<td style="padding-bottom: 0px; padding-top: 0.25rem;">
										<button type="button" class="btn btn-primary btn-sm regi" 
										onclick="confirmRegiProgram()">신청하기</button></td>
						</c:if>
						
						<c:if test ="${dto.apply < dto.people && result != null && dto.prog_date > limitDate}">
						<td style="color: #00007C">이미 신청하신 프로그램입니다.</td>
						</c:if> 
						
						<input style="float: left" type="button" class="btn btn-secondary" name="btnreturn" onclick="history.back()" value="돌아가기">
						</div>

						<input type="hidden" name="prog_seq" value="${dto.prog_seq }">
						<input type="hidden" name="resi_seq" value="${resi_seq }">
						

						
						
					</div>
			</form>
				</div>
			</div>
			
			
		</div>
	</div>




	<%@ include file="/WEB-INF/views/inc/footerclient.jsp" %>
</body>
<script>
  //check the console for date click event
//Fixed day highlight
//Added previous month and next month view

	function alertPdto() {
    var pdto = "${pdto.prog_seq}";
    alert(pdto);
  }
   
  
   
   function confirmRegiProgram() {
    var result = confirm("신청하시겠습니까?");
    if (result) {
       document.getElementById("regiprogram").submit();
    }
    else {
      
    }
  }

  
</script>
</html>