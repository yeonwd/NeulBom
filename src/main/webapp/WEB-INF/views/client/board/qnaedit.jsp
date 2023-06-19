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
            <form method="POST" action="http://localhost:8090/neulbom/client/board/qnaedit.do">
            <table class="table table-bordered table-hover content-head_wj">
                <thead>
                    <tr>
                      <%-- <th>${dto.qna_seq}</th> --%>
                      <th style="text-align: left;"><input type="text" name="title" required value="${dto.title}" style="width:100%;"></th>
                      <c:if test="${dto.fname != null}">
                      <th>${dto.fname}</th>
                      </c:if>
                      <th>${dto.qna_date}</th>
                    </tr>
                    <tr>
                    	<th text-align="center" colspan=5>
                    		<select name="category" required style="width: 100px;">
					            <option value="실버타운" ${dto.category == '실버타운' ? 'selected' : ''}>실버타운</option>
					            <option value="요양원" ${dto.category == '요양원' ? 'selected' : ''}>요양원</option>
					            <option value="프로그램" ${dto.category == '프로그램' ? 'selected' : ''}>프로그램</option>
					            <option value="기타문의" ${dto.category == '기타문의' ? 'selected' : ''}>기타문의</option>
					        </select>
						</th>
                    </tr>
                </thead>
            </table>
            
            <c:if test="${not empty dto.fname}">
            <div style="display: inline-block; margin-bottom: 50px;">
				<img src="/neulbom/asset/qna/${dto.fname}" id="pic">
			</div>
			</c:if>
			
            <div class="content_wj">
              	<input type="text" name="content" id="content" required value="${dto.content}">
            </div>
     
     		<input type="hidden" name="qna_seq" id="qna_seq"value="${dto.qna_seq}">
     
            <hr>
            <button type="submit" class="btn btn-primary btn-sm" style="width:100px; height:40px; margin-right:10px; font-size: 20px; float:left;">수정하기</button>
            <button type="button" onclick="location.href='http://localhost:8090/neulbom/client/board/qna.do'" class="btn btn-primary btn-sm" style="width:100px; height:40px; margin-right:10px; font-size: 20px; float:left;">돌아가기</button>
			</form>
			
        </div>
        </div>
        
<%@ include file="/WEB-INF/views/inc/footerclient.jsp" %>
<script>

	

</script>
</body>
</html>






