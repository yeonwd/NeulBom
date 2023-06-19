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
<style>

	
    html,body {
        margin: 0px;
        padding: 0px;
    }

    body {
        overflow-x: hidden;
    }

    

    .mainmenu {
        display: flex;
        width: auto;
        margin-left: 150px;
        margin-right: 150px;
    }

    .sidebar {
        float: left;
        margin-right: 50px;
        width: 200px; 
        height: 165px;
        top: 155px; 
        left:10px; 
        border:1px solid #A6AEB7;
    }
    
    .introducemenu tr {
        border-bottom: 1px solid black;
    }

    .introducemenu th {
        background-color: #ccc;
    }

    #deepmenu {
        float: right;
        width: 80%;
        clear: right;
    }

    .mini-menu {
        margin-top: 50px;
        margin-left: 590px;
    }

    .highmenu {
        display: flex;
        justify-content: space-between;
        border-bottom: 1px solid #ccc;
        margin-bottom: 10px;    
    }

    table.eattable {
        /* border: 1px solid black; */
        width: 800px;
        height: auto;
    }

    table tr.tablehead {
        border-bottom: 1px solid black;
        border-top: 1px solid black;
        text-align: center;
        background-color: #ccc;
    }

    table tr.tablecontent {
        border-bottom: 1px solid #ccc;
    }

    table tr td.content-title {
        text-align: left;
    }

    .left-align {
    text-align: left;
    font-size: 2rem;
    }

    .right-align {
        text-align: right;
    }

    
    h2 {
        color: #2D8ED6;
    }
    .introducetitle {
		background-size: cover;
	}

    


</style>


<link rel="stylesheet" href="./asset/css/bootstrap.css">
<link rel="stylesheet" href="./asset/css/footer.css">

<body>
    
    <%@ include file="/WEB-INF/views/inc/headerclient.jsp" %>
    
    <div class="introducetitle">늘봄 소개</div>


    <div class="mainmenu text-center">
        <span class="sidebar" >
            <div class="bg-light border-right">
                <div class="list-group list-group-flush">
                    <a href="#" class="list-group-item list-group-item-action active" style="background-color: #043886;">늘봄 소개</a>
                    <a href="/neulbom/client/board/greetings.do" class="list-group-item list-group-item-action">인사말</a>
                    <a href="/neulbom/client/board/neulbomintroduce.do" class="list-group-item list-group-item-action" style="background-color: #e9ecef;">시설 소개</a>
                    <a href="http://localhost:8090/neulbom/client/board/directions.do" class="list-group-item list-group-item-action">오시는 길</a>
                </div>
            </div>
        </span>

        

        <div id="deepmenu">
            <nav class="navbar bg-light">
                <div class="container-fluid">
                    <h3 style="font-weight: 400;">시설 소개</h3>
                    <div style="display: flex;">
                        <span class="material-symbols-outlined" style="display: block;">
                            home
                        </span>
                        > 늘봄 소개 > 시설 소개
                    </div>
                </div>
            </nav>
            <hr>
            <div>
            <h2>시설 소개</h2>
            <hr>
            시설명 : 늘봄 <br>
			시설장 : 이동재 <br>
			소재지 : 서울특별시 강남구 테헤란로 132<br>
			시설종류 : 실버타운, 요양원<br>
			건물면적 : 2,415㎡<br>
			대지면적 : 13,693㎡<br>
            </div>
            <hr style="margin-bottom:100px;">
            <h2>설립 이념</h2>
            <hr>
			같이 지낼 친구가 없는 시니어 여러분들을 위한 실버타운<br>
            치매와 중풍 등 중증 노인성 질환으로 인해 장기요양이 필요한 어르신의 보호와 복지의 제공<br>
			최선의 의료복지 서비스를 통한 어르신의 행복과 안락한 노후 생활 보장<br>
			<hr style="margin-bottom:100px;">
			<h2>실천 주제</h2>
			<hr>

			<h5>01 기본에 충실하기</h5>
			<table style="width:100%; margin-bottom:100px;">
				<tr>
					<th>소통</th>
					<th>배려</th>
					<th>경청</th>
				</tr>	
				<tr>
					<td>나의 미래가 어르신임을 잊지 않기<br>어르신의 입장에서 생각하기<br>출퇴근시 인사하며 스킨쉽하기</td>
					<td>귀로 듣기 / 눈으로 읽기<br>마음 읽어드리기<br>경청</td>
					<td>어르신의 프라이버시 지켜드리기<br>서비스 제공전 설명해 드리기<br>항상 미소로 대하기</td>
				</tr>
			</table>
			<hr>
			<h5>02 건강하게 성장하기</h5>
			<table style="width:100%; margin-bottom:100px;">
				<tr>
				<th>심신의 건강</th>
				</tr>	
				<tr>
					<td>투명하게 합리적인 시설운영하기<br>자아향상을 위해 노력하기<br>행복하고 신나는 일터 만들기</td>
				</tr>
			</table>
			<hr>
			<h5>03 좋은 소문 발하기</h5>
			<table style="width:100%; margin-bottom:100px;">
				<tr>
				<th>소문</th>
				</tr>	
				<tr>
					<td>시설 자랑하기 / 직원을 자랑하기<br>프로그램 자랑하기</td>
				</tr>
			</table>













        </div>
    </div>
        
    
    <%@ include file="/WEB-INF/views/inc/footerclient.jsp" %>

</body>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=015fae8b95c2d0f2c4d727e44d11a138"></script>
<script>
</script>
</html>










