<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@
    taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" 
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>늘봄 관리자</title>
<%@ include file="/WEB-INF/views/inc/asset.jsp" %>

<style>
	#addemp, #addresi {
		display: flex;
	}
	#input-list {
		margin-top: 2rem;
	}
	#inner-list {
		position: relative;
		margin-top: 50px;
		margin-left: 5px;
	}
	.add-info {
		color: #999;
		margin-left: 10px;
		border-radius: 5px;
		border-style: none;
		height: 40px;
	}
	#lev-sel {
		margin-left: 10px;
		margin-top: 5px;
	}
	#photo-section {
		position: relative;
		text-align: center;
		margin-left: 20px;
		padding: 20px;
		background-color: white;
		height: 360px;
		top: 45px;
		
	}
	#preview {
		width: 250px;
		height: 250px;
	}
	#add-cate {
		font-size: 20px;
		font-weight: bold;
	}
	.main-box {
		background-color: rgba(221, 236, 227, 1);
		padding-top: 20px;
		padding-bottom: 20px;
		padding-left: 50px;
		padding-right: 50px;
		border-radius: 15px;
		height: 520px;
		width: 600px;
		
	}
	.detail{
		width: 600px;
		margin-left: 10px;
		border-radius: 5px;
		border-style: none;
		height: 120px;
	}
	#menubox {
		display: flex;
		justify-content: center;
	}

</style>
</head>
<body>
	<div class="main">
	    <%@ include file="/WEB-INF/views/inc/adSidemenu.jsp" %>
	    <div class="content-box">
	        <div id="inner-box">
	            <div class="semititle">
	                <div class="selected_menu">
	                    <span id="selected_menu_text">비밀번호 찾기</span>
	                </div><!-- selected_menu -->
	                
	            </div><!-- semi_title -->
	            <div id="menubox">
	            <div class="main-box">
	                <form method="POST" action="/neulbom/admin/account/findadpw.do">
						<div id="addresi">
	                		<div class="emp-box">
	                			<div id="inner-list">
									<h1>관리자 비밀번호</h1>
								</div>
								<div id="inner-list">
									<span id="add-cate">아이디:</span><input type="text" placeholder="이름" class="add-info" id="id" name="id">			
								</div>
								<div id="inner-list">
									<span id="add-cate">이름:</span><input type="text" placeholder="이름" class="add-info" id="name" name="name">			
								</div>
								<div id="inner-list">
									<span id="add-cate">주민번호:</span><input type="text" placeholder="주민번호"class="add-info" id="ssn" name="ssn">			
								</div>
								<div id="inner-list">
									<input type="submit" value="찾기" class="btn btn-primary" id="search" onclick="id_search()">
								</div>
							</div>
						</div>
	            	</form>
	       	 	</div>
	       	 	</div>
	    	</div>
		</div>
	</div>	

<!-- JavaScript Bundle with Popper -->
<script src="/neulbom/asset/js/bootstrap.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
<script>
	function id_search() {
		
		var f = document.searchadid;
		
		if (!f.id.value) {
			alert("아이디를 입력하세요");
			f.name.focus();
			return;
		}
		if (!f.name.value) {
			alert("이름을 입력하세요");
			f.name.focus();
			return;
		}
		
		if (!f.ssn.value) {
			alert("주민등록번호를 입력하세요");
			f.ssn.focus();
			return;
		}  
		
		if (f.ssn.value.length > 14 || f.ssn.value.length < 14) {
			alert("올바른 주민등록번호를 입력하세요")
			f.ssn.focus();
			return;
		}
		
		f.action = "<%=request.getContextPath()%>/admin/account/findadid.do ";
		f.submit();
		
		location.href="/views/admin/account/findadpw.do";
	}
</script>
</body>
</html>