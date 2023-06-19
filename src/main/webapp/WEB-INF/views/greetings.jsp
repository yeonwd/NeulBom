<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>늘봄 실버타운</title>
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

    .DJpic {
        float: right;
    }
    
    .DJhistory {
        clear: both;
        text-align: right;
        margin-top: 100px;
    }

    .introducecomment {
        float: left;
        width: 400px;
        height: 100px;
        margin-left: 10px;
        margin-right: 80px;
        text-align: left;
    }
    
    .greeting-area {
    	margin: 20px;
    }
	
	.greeting {
		font-size: 18px;
		padding: 10px;
	}
	
	.greeting > span {
		font-weight: bold;
	}
	
	.greeting-keyword {
		margin: 30px;
	}
	
	#greeting-keyword-one {
		font-weight: bold;
		font-size: 25px;
	}
	
	#greeting-keyword-two {
		font-weight: bold;
		font-size: 25px;
		color: #2D8ED6;
	}
	
	.ceo {
		text-align: right;
		position: relative;
		top: 20px;
		right: 100px;
	}
	
	#ceo-name {
		letter-spacing: 0.3rem;
		font-weight: bold;
	}
	
	#stamp {
		width: 60px;
		height: 60px;
		position: relative;
		right: 25px;
		opacity: 0.4;
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
                    <a href="/neulbom/client/board/greetings.do" class="list-group-item list-group-item-action" style="background-color: #e9ecef;">인사말</a>
                    <a href="/neulbom/client/board/neulbomintroduce.do" class="list-group-item list-group-item-action">시설 소개</a>
                    <a href="/neulbom/client/board/directions.do" class="list-group-item list-group-item-action">오시는 길</a>
                </div>
            </div>
        </span>

        

        <div id="deepmenu">
            <nav class="navbar bg-light">
                <div class="container-fluid">
                    <h3 style="font-weight: 400;">인사말</h3>
                    <div style="display: flex;">
                        <span class="material-symbols-outlined" style="display: block;">
                            home
                        </span>
                        > 늘봄 소개 > 인사말
                    </div>
                </div>
            </nav>
            <hr>
            <div class="greeting-area">
 <!--                    <div class="introducecomment">안녕하세요. 늘봄 실버타운 대표이사 이동재입니다.<br>늘봄 실버타운에 방문해주셔서 감사합니다!<br>우리 늘봄 실버타운은 2022.1.1에 어쩌구 저쩌구</div>
                    <img src="../최효종.jpg" class="DJpic">
                    <div class="DJhistory">대표이사 이동재<br><br>약력<br>쌍용 교육센터 수료 예정<br>정보처리기사 필기 취득<br>(실기 취득 예정)<br>서버 1조 마스터 출신</div>
 -->            
 		<p class="greeting-keyword">
 			<span id="greeting-keyword-one">건강하고 행복한 노후의 시작,</span><br>
 			<span id="greeting-keyword-two">늘봄 실버타운이 책임지겠습니다.</span>
 		</p>
 
 		<p class="greeting">
 		 안녕하세요. 늘봄 실버타운 대표이사 이동재입니다. <br> 
 		 늘봄 실버타운에 방문해주셔서 감사합니다. </p>
 	<p class="greeting">
 		늘봄 실버타운은 2022년 대표이사 이동재 외 6인이 공동으로 설립한<br>
 		역삼동 최초의 노인 전용 종합 주거시설입니다.<br>
 		늘봄은 어르신들의 더 나은 삶을 위해 실버타운과 요양원을 모두 운영하고 있습니다.<br>
 		편안하고 깔끔한 늘봄의 실버타운과, <br>숙련된 의료진 및 요양보호사가 상주하는 늘봄 요양원에서
 		효심을 다해 어르신들을 모시겠습니다.
 	</p> 
	<p class="greeting"> 		
 		또한, 요양 시설로 소중한 부모님을 모시는 보호자분들의 마음을 헤아려<br>
 		입주 후 늘봄에서의 생활을 살펴볼 수 있는 알림 서비스를 제공하고 있습니다.
 		<br>
 		어르신들의 모든 날, 모든 순간이 행복하고 건강한 삶이 될 수 있도록<br>
 		늘봄 실버타운의 모든 직원들은 언제나 최선을 다하겠습니다.
 		
 		<br><br>
 		<span>감사합니다.</span>	
 	</p>
 	
 	<p class="ceo">
 		<span style="font-weight: bold;">대표이사
 		</span>&nbsp;&nbsp;&nbsp;<span id="ceo-name">이동재</span>
 		<img id="stamp" src="../../asset/images/stamp.png">	
 	</p>
 	

 
 	</div>
        </div>
    </div>
        
    
    <%@ include file="/WEB-INF/views/inc/footerclient.jsp" %>

</body>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=015fae8b95c2d0f2c4d727e44d11a138"></script>
<script>
</script>
</html>










