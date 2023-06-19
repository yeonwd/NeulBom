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
		padding: 30px;
	}
	#input-list {
		margin-top: 2rem;
	}
	#inner-list {
		position: relative;
		margin-top: 50px;
		margin-left: 5px;
		display: inline-block;
	}
	.input-form {
		display:none;
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
	#add-cate {
		font-size: 20px;
		font-weight: bold;
	}
	.main-box {
		background-color: rgba(221, 236, 227, 1);
		padding-left: 50px;
		padding-right: 50px;
		border-radius: 15px;
		width: 900px;
		height: 520px;
		display: flex;
		justify-content: space-around;
		align-content: center;
		
	}
	.emp-box {
		display: none;
	}
	.detail{
		width: 600px;
		margin-left: 10px;
		border-radius: 5px;
		border-style: none;
		height: 120px;
	}
	
	#selicon1, #selicon2 {
		width:200px;
	}
	
	#iconbox {
		position: relative;
		margin: 50px;
		top: 50px;
	}
	
	#icontext {
		text-align: center;
		font-size: 25px;
		margin-top: 30px;
		font-weight: bold;
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
	                    <span id="selected_menu_text">계정 부여</span>
	                </div><!-- selected_menu -->
	            </div><!-- semi_title -->
	            <div id="menubox">
		            <div class="main-box">
			            <div id="iconbox">
							<img src="/neulbom/asset/image/admin.png" id="selicon1" onclick="location.href='/neulbom/admin/account/adminacc.do';">
							<div id="icontext">관리자</div>         
			            </div>
			            <div id="iconbox">
							<img src="/neulbom/asset/image/resident.png" id="selicon2" onclick="location.href='/neulbom/admin/account/resiacc.do';">
							<div id="icontext">입주자</div>       
			            </div>
		       	 	</div>
	            </div>
	    	</div>
		</div>
	</div>	

<!-- JavaScript Bundle with Popper -->
<script src="/neulbom/asset/js/bootstrap.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
<script>
	$("#selicon1").hover(
			function () { // mouseover
				$(this).attr("src","/neulbom/asset/image/adminh.png");
			},
			function () { // mouseout
				$(this).attr("src","/neulbom/asset/image/admin.png");
			}
	);
	
	$("#selicon2").hover(
			function () { // mouseover
				$(this).attr("src","/neulbom/asset/image/residenth.png");
			},
			function () { // mouseout
				$(this).attr("src","/neulbom/asset/image/resident.png");
			}
	);
</script>
</body>
</html>