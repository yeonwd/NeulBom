<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>늘봄</title>

<%@ include file="/WEB-INF/views/inc/assetclient.jsp" %>

<style>
.gallery_container {
   height: 80vh;
   width: 100vw;
   background-color:#f7f3f3;
   margin-bottom:50px;
   text-align:center;
}

.imagecontainer_wj {
    justify-content: center;
    display: flex;
    width: 100vw;
    height: 80vh !important;
    
}

.wjimg{
   height:250px;
   width:270px;
}

.wjimg-image{
   width:270px;
   height:250px;
   object-fit:cover;
}

</style>
</head>

<body>
   <%@ include file="/WEB-INF/views/inc/headerclient.jsp" %>
     <div class="imagecontainer_wj">
        <img src="/neulbom/asset/mainimage/frontimages.png" class="img-fluid" style="width:100vw; object-position: 0px -100px;">
        <div class="infotext_wj">
            <h1 style="color:white;">안녕하세요 늘봄입니다.</h1>

            <div class="shadow p-3 mb-5 bg-body rounded">
                <span class="notice_wj_line"></span>
                <span class="notice_wj">
                    공지사항
                </span>
                <ul class="eatnotice_wj" style="padding-top:30px">
 <li><a href="#"><b>[요양원]※공지사항※</b></a>
                    <p>2023-06-01</p>
                </li>
                <li><a href="#"><b>[실버타운]※공지사항※</b></a>
                    <p>2023.06.01</p>
                </li>
                <li><a href="#"><b>[요양원]※공지사항※</b></a>
                    <p>2023.05.01</p>
                </li>
                <li><a href="#"><b>[실버타운]※공지사항※</b></a>
                    <p>2023.05.01</p>
                </li>
                </ul>
            </div>
        </div>
    </div>
    
    <div class="gallery_container">
       <div style="padding-top:100px;">
          <h1 style="color:#64809d">늘봄 실버타운 & 요양원</h1>
       </div>
       <div style="margin-top:30px;">
       <h4>따뜻한 가슴으로 도움이 필요하신분께 더 많은 사랑과 나눔을 실천하는 늘봄실버타운 입니다.</h4>
       </div>
         
      <div style="width:1200px; margin:0 auto; height:380px; margin-top:30px; background-color:#daeeeb; border-radius: 10px; box-shadow: rgba(0, 0, 0, 0.24) 0px 3px 8px;"><h5 style="color:black; font-weight:300; padding-top:15px;">NeulBom Silver Town Gallery</h5>
               <div style="width:1150px; box-shadow: rgba(100, 100, 111, 0.2) 0px 7px 29px 0px; margin:0 auto; height:300px; border-radius: 10px; margin-top:10px; display:flex; background-color:white; justify-content: space-around;
    align-items: center;">
               <div class="wjimg">
                  <img class="wjimg-image" src="/neulbom/asset/gallery/1.jpg">
               </div>
               <div class="wjimg">
                  <img class="wjimg-image" src="/neulbom/asset/gallery/2.jpg">
               </div>
               <div class="wjimg">
                  <img class="wjimg-image" src="/neulbom/asset/gallery/3.jpg">
               </div>
               <div class="wjimg">
                  <img class="wjimg-image" src="/neulbom/asset/gallery/4.jpg">
               </div>
      </div>
</div>
</div>
    <div class="noticecontainer_wj">
        <div style="width: 600px; padding-left: 10%;">
            <div style= "box-shadow: 5px 5px 5px 5px #d3d3d3; padding:30px;">
            <span class="notice_wj_line"></span>
            <span class="notice_wj">
                공지사항
            </span>
            <ul class="eatnotice_wj" style="padding-top:30px">
                <li><a href="#"><b>[요양원]※공지사항※</b></a>
                    <p>2023-06-01</p>
                </li>
                <li><a href="#"><b>[실버타운]※공지사항※</b></a>
                    <p>2023.06.01</p>
                </li>
                <li><a href="#"><b>[요양원]※공지사항※</b></a>
                    <p>2023.05.01</p>
                </li>
                <li><a href="#"><b>[실버타운]※공지사항※</b></a>
                    <p>2023.05.01</p>
                </li>
                
            </ul>
            </div>
        </div>
        
        <div style="width: 400px; margin-right:200px;">
            <div style= "box-shadow: 5px 5px 5px 5px #d3d3d3; padding:30px; padding-top:50px;">
            <span class="notice_wj_line"></span>
            <span class="notice_wj">
                식단표
            </span>
            <ul class="eatnotice_wj" style="padding-top:30px">
                    <li><a href="#"><b>6월 식단표</b></a>
                        <p>2023.05.29</p>
                    </li>
                    <li><a href="#"><b>5월 식단표</b></a>
                        <p>2023.04.24</p>
                    </li>
                    <li><a href="#"><b>4월 식단표</b></a>
                        <p>2023.03.27</p>
                    </li>
                    <li><a href="#"><b>3월 식단표</b></a>
                        <p>2023.02.27</p>
                    </li>
            </ul>
            </div>
        </div>
    </div>
   <%@ include file="/WEB-INF/views/inc/footerclient.jsp" %>
<script>

</script>
</body>
</html>