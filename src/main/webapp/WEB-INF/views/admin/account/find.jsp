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
	.main-box {
		background-color: rgba(221, 236, 227, 1);
		padding-left: 50px;
		padding-right: 50px;
		border-radius: 15px;
		width: 900px;
		height: 520px;
		display: block;
		text-align: center;
		padding-top: 20px;
		
	}
	#inner-header {
		height: 250px;
	}
	#inner-content {
		display:flex;
		justify-content: space-around;
		padding: 20px;
	}
	#inner-menu {
		background-color: #bbb;
		width: 200px;
		height: 100px;
		border-radius: 10px;
		display: flex;
		align-items: center;
		justify-content: center;
	}
	#inner-menu:hover {
		cursor: pointer;
		background-color: gray;
	}
	#innertitle {
		font-size: 30px;
		font-weight: bold;
	}
	#inner-txt {
		color: white;
		font-size: 20px;
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
	                    <span id="selected_menu_text" style="font-size: 19px; padding-top:4px;">아이디/비밀번호 찾기</span>
	                </div><!-- selected_menu -->
	            </div><!-- semi_title -->
	            <div id="menubox">
	            
	            <div class="main-box">
					<div id="inner-header">
						<div id="innertitle">관리자</div>
						<div id="inner-content">
							<div id="inner-menu" onclick="location.href='/neulbom/admin/account/searchadid.do'">
								<div id="inner-txt">아이디 찾기</div>
							</div>
							<div id="inner-menu" onclick="location.href='/neulbom/admin/account/searchadpw.do'">
								<div id="inner-txt">비밀번호 찾기</div>
							</div>
						</div>
					</div>
					<div id="inner-header">
						<div id="innertitle">입주자</div>
						<div id="inner-content">
							<div id="inner-menu" onclick="location.href='/neulbom/admin/account/searchreid.do'">
								<div id="inner-txt">아이디 찾기</div>
							</div>
							<div id="inner-menu" onclick="location.href='/neulbom/admin/account/searchrepw.do'">
								<div id="inner-txt">비밀번호 찾기</div>
							</div>
						</div>
					</div>
	            </div><!-- main-box -->
	            </div>
	        </div><!-- inner-box -->
	    </div><!-- content-box -->
	</div>

<!-- JavaScript Bundle with Popper -->
<script src="/asset/js/bootstrap.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
<script>

</script>
</body>
</html>