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

	#pic {
		width: 300px;
		height: 300px;
		display: block;
		}
		
	.del, .edit {
		width: 100px;
		height: 40px;
		font-size: 18px;
		border: 1px solid #AAA;
		background-color: #AAA;
		float: right;
	}

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
            <table class="table table-bordered table-hover content-head_wj">
                <thead>
                    <tr>
                      <%-- <th>${dto.qna_seq}</th> --%>
                      <th style="text-align: left;">제목: ${dto.title}</th>
                      <th>작성자: ${dto.name}</th>
                      <th>작성날짜: ${dto.qna_date}</th>
                      <th>조회수: ${dto.read}</th>
                    </tr>
                    <tr>
                    	<th text-align="center" colspan=5>${dto.category}</th>
                    </tr>
                </thead>
            </table>
            
            <c:if test="${not empty dto.fname}">
            <div style="display: inline-block; margin-bottom: 50px;">
				<img src="/neulbom/asset/qna/${dto.fname}" id="pic">
			</div>
			</c:if>
            <div class="content_wj" style="text-align:center;">
              	${dto.content}
            </div>
     
            <hr>
            
            <c:if test="${dto.isreply == 'y'}">
            	<table class="table table-bordered table-hover content-head_wj">
                <thead>
                    <tr>
                      <%-- <th>${dto.qna_seq}</th> --%>
                      <th style="text-align: center; width:90%;">${qdto.title}</th>
                      <th style="text-align: center; width:10%;">${dto.category}</th>
                    </tr>
                </thead>
            </table>
            <div class="content_wj" style="text-align:center;">
              	${qdto.content}
            </div>
            </c:if>
            <button type="button" onclick="location.href='http://localhost:8090/neulbom/client/board/qna.do'" class="btn btn-primary btn-sm" style="width:100px; height:40px; margin-right:10px; font-size: 20px; float:left;">돌아가기</button>
            
            <c:if test="${dto.isreply == 'n' && sessionScope.id == dto.id}">
            <button type="button" class="btn edit" onclick="location.href='http://localhost:8090/neulbom/client/board/qnaedit.do?qna_seq=${dto.qna_seq}'">수정하기</button>
            <button type="button" class="btn del" onclick="delfree()" style="margin-right: 10px;">삭제하기</button>
            </c:if>
            
            
        </div>
        </div>
        
<%@ include file="/WEB-INF/views/inc/footerclient.jsp" %>
<script>

	function delfree() {
		
		if (confirm('작성하신 글을 삭제하시겠습니까?')) {
			location.href='/neulbom/client/board/qnadel.do?qna_seq=' + ${dto.qna_seq};
		}
		
	}

</script>
</body>
</html>






