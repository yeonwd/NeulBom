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
	#intro_icon {
		width: 50px;
	}
	#intro_prog {
		margin: 20px;
	}
	#iconcircle {
		width: 100px;
		height: 100px;
		border-radius: 50%;
		border: 1px solid #D3DCE7;
		display: flex;
		justify-content: center;
		align-items: center;
		margin-bottom: 5px;
	}
	#document-box {
		width:380px;
		border: 1px solid #D3DCE7;
		margin-top: 10px;
		border-radius: 10px;
	}
	
	li {
		text-align: left;
	}
	
	#document-header {
		width:120px;
		position: relative;
		text-align: left;
		left: 30px;
		font-size: 20px;
		font-weight: bold;
		padding: 3px;
		border-bottom: 1px solid #C7C8C9;
		
	}
	#heading-title {
		font-size: 40px;
		font-weight: bold;
		margin: 20px;
		border-bottom: 1px dashed #C7C8C9;
		padding-bottom: 15px;
	}
	#doucment-inner {
		margin-bottom: 5px;
		maring-top: 5px;	
	}
	#content-inner-box {
		margin: 60px;
	}
	.sidebar {
		height: 0px;
	}
	.introducetitle {
		background-size: cover;
	}
</style>
</head>

<body>
	<%@ include file="/WEB-INF/views/inc/headerclient.jsp"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
	<div class="introducetitle">입주 안내</div>


	<div class="mainmenu text-center">
		<span class="sidebar">
			<div class="bg-light border-right">
				<div class="list-group list-group-flush">
					<a href="#" class="list-group-item list-group-item-action active"
						style="background-color: #043886;">입주 안내</a> 
				</div>
			</div>
		</span>

			

		<div id="deepmenu">
			<nav class="navbar bg-light">
				<div class="container-fluid">
					<h3 style="font-weight: 400;">입주 안내</h3>
					<div style="display: flex;">
						<span class="material-symbols-outlined" style="display: block;">
							home </span> > 이용 안내 > 입주 안내 
					</div>
				</div>
			</nav>
			<hr>
			<div style="text-align: center;">
				<div id="content-inner-box">
					<div id="heading-title">
						입주안내
					</div>
					<h1 style="margin: 30px;">" <span style="color: #2D8ED6">안심</span>으로 <span style="color: #2D8ED6">행복</span>으로 "</h1>
					<div>
						<span>
						<h3 style="font-weight: bold;">입주 대상자</h3> 
							<p>시설 요양 등급 1~5등급 판정을 받은 수급권자 및 일반 어르신</p> 
						</span>
					</div>
				</div>
				<div id="content-inner-box">
					<div id="heading-title">절차</div>
					<div style="display: flex; justify-content: center;">
						<hr>
						<div id="intro_prog">
							<div id="iconcircle">
								<img src="/neulbom/asset/image/call.png" id="intro_icon">
							</div>
							<div>
								1. 전화문의
							</div>
						</div>
						<div id="intro_prog">
							<div id="iconcircle">
								<img src="/neulbom/asset/image/consulting.png" id="intro_icon">
							</div>
							<div>
								2. 전화상담
							</div>
						</div>
						<div id="intro_prog">
							<div id="iconcircle">
								<img src="/neulbom/asset/image/check.png" id="intro_icon">
							</div>
							<div>
								3. 입주결정
							</div>
						</div>
						<div id="intro_prog">
							<div id="iconcircle">
								<img src="/neulbom/asset/image/paper.png" id="intro_icon">
							</div>
							<div>
								4. 서류준비
							</div>
						</div>
						<div id="intro_prog">
							<div id="iconcircle">
								<img src="/neulbom/asset/image/handshake.png" id="intro_icon">
							</div>
							<div>
								5. 입주계약
							</div>
						</div>
						<div id="intro_prog">
							<div id="iconcircle">
								<img src="/neulbom/asset/image/building.png" id="intro_icon">
							</div>
							<div>
								6. 입주
							</div>
						</div>
					</div>
				</div>
				<div id="content-inner-box">
					<div id="heading-title">구비서류</div>
					<div style="display: flex; justify-content: space-around;">
						<div id="document-box">
							<div id="doucment-inner">
								<div id="document-header">
									입주서류	
								</div>
							</div>
							<div>
								<div>
									<div><ul><li>노인장기요양인증서</li><li>표준이용계획서</li><li>건강진단서</li><li>처방전(복용약)</li><li>의사소견서</li><li>주민등록등본</li><li>가족관계증명서</li><li>도장 및 신분증(어르신, 보호자)</li><li>의료급여수급권자 증명서(해당자에 한함)</li></ul></div>
								</div>
							</div>
						</div>
						<div id="document-box">
							<div id="doucment-inner">
								<div id="document-header">
									입주준비물	
								</div>
							</div>
							<div>
								<div>
									<div><ul><li>겉옷, 속옷, 양말, 개인 소지품(로션, 면도기 등), 칫솔, 양치컵, 실내화, 물병, 로션</li></ul></div>
								</div>
							</div>
							<div id="doucment-inner">
								<div id="document-header">
									입주비용		
								</div>
							</div>
							<div>
								<div>
									<div><ul><li>월이용료</li></ul></div>
								</div>
							</div>
						</div>
					</div>
					
				</div>
			</div>
			<hr>
		</div>

	</div>




	<%@ include file="/WEB-INF/views/inc/footerclient.jsp"%>
	<script>
		
	</script>
</body>
</html>