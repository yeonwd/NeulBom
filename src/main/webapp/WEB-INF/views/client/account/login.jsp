<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>

<%@ include file="/WEB-INF/views/inc/assetclient.jsp" %>
<link rel="stylesheet" href="/neulbom/asset/css/login2.css">

<style>


@font-face {
    font-family: 'IBMPlexSansKR-Regular';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_20-07@1.0/IBMPlexSansKR-Regular.woff') format('woff');
    font-weight: normal;
    font-style: normal;
}

html {
    font-family: 'IBMPlexSansKR-Regular';
}

body {
    margin: 0;
}
html,
    body {
        margin: 0px;
        padding: 0px;
    }

    body {
        overflow-x: hidden;
    }
    

</style>

<body>
      <%@ include file="/WEB-INF/views/inc/headerclient.jsp" %>
    
    <!-- 로그인 -->
     <!-- Pills navs -->
     <div class="login_box">
      <div class="title_login">안녕하세요.<br> 당신만 늘 보는 늘봄입니다</div>

   <div class="btn_umm">
<ul class="nav nav-pills nav-justified mb-3" id="ex1" role="tablist">
 
   <div class="btn-group" role="group" aria-label="Basic radio toggle button group">
    <input type="radio" class="btn-check" name="btnradio" id="btnradio1" autocomplete="off" checked>
    <label class="btn btn-outline-primary" for="btnradio1">입주자</label>
  
    <input type="radio" class="btn-check" name="btnradio" id="btnradio2" autocomplete="off">
  <label class="btn btn-outline-primary" for="btnradio2">보호자</label>
  
    <input type="radio" class="btn-check" name="btnradio" id="btnradio3" autocomplete="off">
    <label class="btn btn-outline-primary" for="btnradio3">관리자</label>
  </div>
  </ul>
</div>
  <!-- Pills navs -->
  
  <!-- Pills content -->

  <div class="tab-content">
    <div class="tab-pane fade show active" id="pills-login" role="tabpanel" aria-labelledby="tab-login">
      <form form method="POST" action="/neulbom/client/account/login.do">
  
        <!-- Email input -->
        <div class="form-outline mb-4">
          <input type="text" id="id" name="resiId" class="form-control" place-holder="아이디" value="41ciyi72f001"/>
        </div>
  
        <!-- Password input -->
        <div class="form-outline mb-4">
          <input type="password" id="pw" name="resiPw" class="form-control" place-holder="비밀번호" value="rzeu7i29"/>
          <!-- <label class="form-label" for="loginPassword">비밀번호</label> -->
        </div>
  
        <!-- Submit button -->
        <!-- <button type="submit" class="btn btn-primary btn-block mb-4">Sign in</button> -->
        <div class="login_button"><div class="d-grid gap-2 col-6 mx-auto">
          <button class="btn btn-primary" type="submit">로그인</button>
          
        </div></div>


        <!-- 2 column grid layout -->
        <div class="row mb-4">
          <div class="col-md-6 d-flex justify-content-center">
            <!-- Checkbox -->
            <div class="form-check mb-3 mb-md-0">
              <input type="button" class="btn" onclick="location.href='/neulbom/client/account/searchid.do';" value="아이디 찾기">
            </div>
          </div>
  
          <div class="col-md-6 d-flex justify-content-center">
            <!-- Simple link -->
            <input type="button" class="btn" onclick="location.href='/neulbom/client/account/searchpw.do';" value="비밀번호 찾기">
          </div>
        </div>
  
  
        <!-- Register buttons -->
        <div class="text-center">
          <p> <input type="button" class="btn" onclick="location.href='/neulbom/client/account/searchid.do';" value="회원가입"></p>
        </div>
      </form>
    </div>
  </div>
  <!-- Pills content -->
  </div>

    
  <%@ include file="/WEB-INF/views/inc/footerclient.jsp" %>

</body>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
<script>

	/* var btnradio1 = document.getElementById('btnradio1');
	var btnradio2 = document.getElementById('btnradio2');
	var btnradio3 = document.getElementById('btnradio3');
	
  	btnradio1.onclick = function() {
	    document.getElementById('id').name = 'resiId';
	    document.getElementById('pw').name = 'resiPw';
	};
	  
	btnradio2.onclick = function() {
	    document.getElementById('id').name = 'ProtectId';
	    document.getElementById('pw').name = 'ProtectPw';
	};
	  
	btnradio3.onclick = function() {
	    document.getElementById('id').name = 'AdminId';
	    document.getElementById('pw').name = 'AdminPw';
	}; */
	
	
	
	
	
	$('#btnradio1').on('click',function(){
		$("#id").attr('name', 'resiId');
		$("#pw").attr('name', 'resiPw');
	});
	
	$('#btnradio2').on('click',function(){
		$("#id").attr('name', 'protectId');
		$("#pw").attr('name', 'protectPw');
	});
	
	$('#btnradio3').on('click',function(){
		$("#id").attr('name', 'adminId');
		$("#pw").attr('name', 'adminPw');
	});
	

</script>
</html>










