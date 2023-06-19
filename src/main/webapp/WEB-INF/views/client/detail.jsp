<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
   <%@ include file="/WEB-INF/views/inc/assetclient.jsp" %>
   
<style>

</style>
</head>
<body>
   <!--  -->
   <%@ include file="/WEB-INF/views/inc/headerclient.jsp" %>
    <div class="introducetitle">공지사항</div>


    <div class="mainmenu text-center">
        <span class="sidebar" >
            <div class="bg-light border-right">
                <div class="list-group list-group-flush">
                    <a href="#" class="list-group-item list-group-item-action active" style="background-color: #043886;">알림게시판</a>
                    <a href="#" class="list-group-item list-group-item-action">메뉴 2</a>
                    <a href="#" class="list-group-item list-group-item-action">메뉴 3</a>
                    <a href="#" class="list-group-item list-group-item-action">메뉴 4</a>
                </div>
            </div>
        </span>

        

        <div id="deepmenu">
            <nav class="navbar bg-light">
                <div class="container-fluid">
                    <h3 style="font-weight: 400;">공지사항</h3>
                    <div style="display: flex;">
                        <span class="material-symbols-outlined" style="display: block;">
                            home
                        </span>
                        > 알림게시판 > 공지사항
                    </div>
                </div>
            </nav>
            <hr>
            <table class="table table-bordered table-hover content-head_wj">
                <thead>
                    <tr>
                      <th>1</th>
                      <th style="text-align: left;">[실버타운]공지사항</th>
                      <th>관리자4</th>
                      <th>2023.06.03</th>
                      <th>46</th>
                    </tr>
                </thead>
            </table>
            <div class="content_wj">
              안녕하세요, 실버타운 이웃 여러분!

계속해서 공지사항을 안내드립니다. 아래 내용을 확인하시고 필요한 조치를 취해주시기 바랍니다.

실버타운 여행 단체 참여 안내

실버타운에서는 함께 여행을 즐기는 단체를 구성하고 있습니다.
다양한 여행지를 함께 방문하며 새로운 경험과 추억을 만들 수 있습니다.
여행 단체에 참여하고 싶으신 분들은 실버타운 사무실로 문의하여 참여 신청해주세요.
실버타운 문화강좌 수강 신청 안내

실버타운에서는 다양한 문화강좌를 제공합니다.
그림, 미술, 서예, 음악 등 다양한 분야의 수업을 들을 수 있으며, 자신의 취미를 발전시킬 수 있습니다.
문화강좌 수강 신청과 상세 내용은 실버타운 사무실로 문의하여 확인해주세요.
실버타운 심리상담 서비스 안내

실버타운에서는 심리상담 서비스를 제공합니다.
스트레스 해소, 노후 우울감 등 다양한 심리적 어려움을 함께 해결해 나갈 수 있습니다.
심리상담을 받고 싶으신 분들은 실버타운 사무실로 문의하여 상담 예약을 해주세요.
실버타운 컴퓨터 교육 프로그램 안내

실버타운에서는 컴퓨터 교육 프로그램을 운영합니다.
컴퓨터 기초부터 인터넷 사용, 이메일, SNS 등 다양한 컴퓨터 기술을 배울 수 있습니다.
컴퓨터 교육 프로그램에 관심이 있으신 분들은 실버타운 사무실로 문의하여 참여 방법을 확인해주세요.
위의 공지사항은 실버타운의 원활한 운영과 이웃들의 편의를 위한 내용입니다. 실버타운은 이웃들의 행복한 노후를 
위해 최선을 다할 것입니다.

감사합니다.

실버타운 관리팀 드림
            </div>
            <hr>
            <button type="button" class="btn btn-primary btn-sm" style="width:100px; height:40px; margin-right:10px; font-size: 20px; float:left;">돌아가기</button>
        </div>
        
        
    </div>
        
<%@ include file="/WEB-INF/views/inc/footerclient.jsp" %>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
<script>

</script>
</body>
</html>





