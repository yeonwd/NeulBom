<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Toy Project</title>

<%@ include file="/WEB-INF/views/inc/assetclient.jsp" %>

<style>
.wjline {
	width:100px;
	display:inline-block;
}

.wjline2 {
	width:60%;
	margin:0 auto;
}

</style>
</head>

<body>
	<%@ include file="/WEB-INF/views/inc/headerclient.jsp" %>
	
	  <div id="consult" class="introducetitle" style="background-image: url('/neulbom/asset/mainimage/registwrite.jpg'); background-position: 50% 75%;">입주상담게시판</div>



<form action="/neulbom/client/community/residetailcheck.do" method="post">
 <input type="hidden" name="con_seq" value="${con_seq}">	 
    <div class="mainmenu text-center d-flex justify-content-center" style="width:600px; margin:0 auto;">
       <div class="card" style="width:500px;">
  <div class="card-body">
  <div>
  	<div class="mb-3">
  <label for="exampleFormControlInput1" class="form-label" >이름</label>
  <input type="text" required  name="pname" class="form-control wjline2" id="exampleFormControlInput1" placeholder="이름" maxlength=4>
</div>
  		<div class="mb-3">
  <label for="exampleFormControlInput1" class="form-label">전화번호</label>
  <br>
  <input type="text" name="ptel1" required maxlength=3 class="form-control wjline" placeholder="010" oninput="this.value = this.value.replace(/[^0-9]/g, '');">-
  <input type="text" name="ptel2" required maxlength=4 class="form-control wjline"  placeholder="XXXX" oninput="this.value = this.value.replace(/[^0-9]/g, '');">-
  <input type="text" name="ptel3" required maxlength=4 class="form-control wjline"  placeholder="XXXX" oninput="this.value = this.value.replace(/[^0-9]/g, '');">
  </div>
</div>
  	</div>
  	<span style="padding-bottom:10px;">
   <input type="submit" value="확인" class="btn btn-primary" id="register">
    <input type="button" value="돌아가기" class="btn" id="return" onclick="history.back()">
    </span>
  </div>
</div>
</form>
	 
	<%@ include file="/WEB-INF/views/inc/footerclient.jsp" %>
<script>

  

</script>
</body>
</html>