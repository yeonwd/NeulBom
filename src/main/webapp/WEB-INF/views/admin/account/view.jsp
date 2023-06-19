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
		margin-left: 5px;
		margin-top: 30px;
	}
	.input-form {
		display:none;
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
		top: 35px;
		background-color: rgba(221, 236, 227, 1);
		
	}
	#preview {
		width: 250px;
		height: 250px;
	}
	#add-cate {
		font-size: 20px;
		font-weight: bold;
		margin-left: 10px;
		height: 40px;
		width: 200px;
	}
	.main-box {
		background-color: rgba(221, 236, 227, 1);
		padding-top: 20px;
		padding-bottom: 20px;
		padding-left: 50px;
		padding-right: 50px;
		border-radius: 15px;
		height: 520px;
		
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
	#left-box {
		display: inline-block;
		width: 452px;
	}
	#profile {
		margin-top: 20px;
		font-size: 20px;
		font-weight: bold;
		
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
	                    <span id="selected_menu_text">상세 보기</span>
	                </div><!-- selected_menu -->
	                
	            </div><!-- semi_title -->
	            <div class="main-box">
	                <div id="addemp">
						<div id="left-box">
							<div id="inner-list">
								<span id="add-cate">직원 이름: ${dto.name}</span>		
							</div>
							<c:if test="${sessionScope.lv == '1'}">
								<div id="inner-list">
									<span id="add-cate">주민번호: ${dto.ssn}</span>			
								</div>
							</c:if>
							<div id="inner-list">
								<span id="add-cate">아이디: ${dto.id}</span>			
							</div>
							<div id="inner-list">
								<span id="add-cate">전화번호: ${dto.tel}</span>			
							</div>
							<div id="inner-list">
								<span id="add-cate">이메일: ${dto.email}</span>			
							</div>
							<div id="inner-list">
								<span id="add-cate">구분: 
								<c:if test="${dto.lv == '1'}">
									관리자
								</c:if>
								<c:if test="${dto.lv == '2'}">
									사무직
								</c:if>
								<c:if test="${dto.lv == '3' || dto.lv == '4'}">
									실무직
								</c:if>
								</span>
							</div>
	                	</div>
						<div id="photo-section">
							<c:if test="${dto.pic != null}">
								<img id="preview" src="/neulbom/asset/pic/${dto.pic}">
							</c:if>
							<c:if test="${dto.pic == null}">
								<img id="preview" src="/neulbom/asset/images/user.png">
							</c:if>
							<div id="profile">프로필 사진</div>
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
</script>
</body>
</html>