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
	<%@ include file="/WEB-INF/views/inc/headerclient.jsp" %>
	 
	 	<div class="introducetitle">알림게시판</div>


    <div class="mainmenu text-center">
        <span class="sidebar" >
            <div class="bg-light border-right">
                <div class="list-group list-group-flush">
                    <a href="#" class="list-group-item list-group-item-action active" style="background-color: #043886;">커뮤니티</a>
                    <a href="#" class="list-group-item list-group-item-action">입주상담 게시판</a>
                    <a href="http://localhost:8090/neulbom/client/board/qna.do?" class="list-group-item list-group-item-action">문의 게시판</a>
                    <a href="#" class="list-group-item list-group-item-action">자유 게시판</a>
                </div>
            </div>
        </span>

        
		
        <div id="deepmenu">
            <nav class="navbar bg-light">
                <div class="container-fluid">
                    <h3 style="font-weight: 400;">문의 게시판</h3>
                    <div style="display: flex;">
                        <span class="material-symbols-outlined" style="display: block;">
                            home
                        </span>
                        > 커뮤니티 > 문의 게시판
                    </div>
                </div>
            </nav>
            <hr>
            <form action="/neulbom/client/board/qnaadd.do" method="post" enctype="multipart/form-data" style="text-align: right; margin-top: 50px; margin-bottom: 50px; margin-right: 300px;">
            <table class="table table-bordered table-hover content-head_wj">
                <thead>
                    <tr>
                      <th style="text-align: center; width: 300px;">문의 제목</th>
                      <td style="text-align: left;"><input type="text" name="title" placeholder="문의 제목" style="width: 600px;" required ></td>
                    </tr>
                    <tr> 
                      <th style="text-align: center; width: 300px;">카테고리</th>
                      <td style="text-align: left;">
                      		<select name="category" required style="width: 600px;">
					            <option value="실버타운">실버타운</option>
					            <option value="요양원">요양원</option>
					            <option value="프로그램">프로그램</option>
					            <option value="기타문의">기타문의</option>
					        </select>
                      </td>
                    </tr>
                    <tr> 
                      <th style="text-align: center; width: 300px;">첨부파일</th>
                      <td style="text-align: left;"><input type="file" name="fname" id="fname" /></td>
                    </tr>
                    <tr style="height: 500px;">
                    	<th style="text-align: center; vertical-align: middle; width: 300px; margin-top:auto; margin-bottom:auto;">문의 내용</th>
                    	<td style="text-align: left;"><textarea name="content" placeholder="문의 내용" required style="width:600px; height:500px;"></textarea></td>
                    </tr>
                </thead>
            </table>
            <input type="hidden" name="id" value="${sessionScope.id}">
            
            <hr>
            
            <a href="http://localhost:8090/neulbom/client/board/qna.do" class="btn btn-primary btn-sm" style="width:100px; height:40px; margin-right:0px; font-size: 20px; float:right;">돌아가기</a>
            <button type="submit" class="btn btn-primary btn-sm" style="width:100px; height:40px; margin-right:10px; font-size: 20px; float:right;">등록</button>
            </form>
     
        </div>
        </div>
        
<%@ include file="/WEB-INF/views/inc/footerclient.jsp" %>
<script>

	

</script>
</body>
</html>






