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

    #map { 
        width: 1000px;
        height: 500px;
        margin-left: 100px;
        margin-right: 100px;
    }

    .mini-menu {
        margin-top: 50px;
        margin-left: 497px;
    }

    .highmenu {
        display: flex;
        justify-content: space-between;
        border-bottom: 1px solid #ccc;
        margin-bottom: 10px;    
    }

    .left-align {
    text-align: left;
    font-size: 2rem;
    }

    .right-align {
        text-align: right;
    }



</style>

<link rel="stylesheet" href="./asset/css/bootstrap.css">
<link rel="stylesheet" href="./asset/css/footer.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@48,400,0,0" />

<body>
    
    <%@ include file="/WEB-INF/views/inc/headerclient.jsp" %>
    
    <div class="introducetitle">알림게시판</div>


    <div class="mainmenu text-center">
        <span class="sidebar" >
            <div class="bg-light border-right">
                <div class="list-group list-group-flush">
                    <a href="#" class="list-group-item list-group-item-action active" style="background-color: #043886;">늘봄 소개</a>
                    <a href="http://localhost:8090/NeulBom/greetings.do" class="list-group-item list-group-item-action">인사말</a>
                    <a href="/neulbom/client/board/neulbomintroduce.do" class="list-group-item list-group-item-action">시설 소개</a>
                    <a href="/neulbom/client/board/directions.do" class="list-group-item list-group-item-action">오시는 길</a>
                </div>
            </div>
        </span>

        

        <div id="deepmenu">
            <nav class="navbar bg-light">
                <div class="container-fluid">
                    <h3 style="font-weight: 400;">오시는 길</h3>
                    <div style="display: flex;">
                        <span class="material-symbols-outlined" style="display: block;">
                            home
                        </span>
                        > 늘봄 소개 > 오시는 길
                    </div>
                </div>
            </nav>
            <hr>
            <div id="map"></div>
        </div>
        
    </div>

    
    <div class="mini-menu">
        <div><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-geo-alt" viewBox="0 0 16 16"><path d="M12.166 8.94c-.524 1.062-1.234 2.12-1.96 3.07A31.493 31.493 0 0 1 8 14.58a31.481 31.481 0 0 1-2.206-2.57c-.726-.95-1.436-2.008-1.96-3.07C3.304 7.867 3 6.862 3 6a5 5 0 0 1 10 0c0 .862-.305 1.867-.834 2.94zM8 16s6-5.686 6-10A6 6 0 0 0 2 6c0 4.314 6 10 6 10z"/><path d="M8 8a2 2 0 1 1 0-4 2 2 0 0 1 0 4zm0 1a3 3 0 1 0 0-6 3 3 0 0 0 0 6z"/></svg>주소 | (12345) 서울 특별시 강남구 테헤란로 어쩌구 한독빌딩 어쩌구</div>
        <div><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-telephone" viewBox="0 0 16 16"><path d="M3.654 1.328a.678.678 0 0 0-1.015-.063L1.605 2.3c-.483.484-.661 1.169-.45 1.77a17.568 17.568 0 0 0 4.168 6.608 17.569 17.569 0 0 0 6.608 4.168c.601.211 1.286.033 1.77-.45l1.034-1.034a.678.678 0 0 0-.063-1.015l-2.307-1.794a.678.678 0 0 0-.58-.122l-2.19.547a1.745 1.745 0 0 1-1.657-.459L5.482 8.062a1.745 1.745 0 0 1-.46-1.657l.548-2.19a.678.678 0 0 0-.122-.58L3.654 1.328zM1.884.511a1.745 1.745 0 0 1 2.612.163L6.29 2.98c.329.423.445.974.315 1.494l-.547 2.19a.678.678 0 0 0 .178.643l2.457 2.457a.678.678 0 0 0 .644.178l2.189-.547a1.745 1.745 0 0 1 1.494.315l2.306 1.794c.829.645.905 1.87.163 2.611l-1.034 1.034c-.74.74-1.846 1.065-2.877.702a18.634 18.634 0 0 1-7.01-4.42 18.634 18.634 0 0 1-4.42-7.009c-.362-1.03-.037-2.137.703-2.877L1.885.511z"/></svg>전화 | (02)123-456-7890</div>
    </div>

    
    <%@ include file="/WEB-INF/views/inc/footerclient.jsp" %>

</body>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=015fae8b95c2d0f2c4d727e44d11a138"></script>
<script>
    var container = document.getElementById('map');
		var options = {
			center: new kakao.maps.LatLng(37.4993, 127.0331),
			level: 3
		};

	var map = new kakao.maps.Map(container, options);

    var markerPosition  = new kakao.maps.LatLng(37.4993, 127.0331); 

    var marker = new kakao.maps.Marker({
        position: markerPosition
    });

    marker.setMap(map);
</script>
</html>










