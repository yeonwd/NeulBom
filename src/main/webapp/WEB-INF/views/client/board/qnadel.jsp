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
                      <td id="id">정말로 삭제 하시겠습니까?</td>
                    </tr>
                </thead>
            </table>
			<form method="POST" action="/neulbom/client/board/qnadel.do">
				<button type="button" class="btn edit" onclick="history.back();">취소</button>
		        <button type="submit" class="btn del" style="margin-right: 10px;">삭제하기</button>
				<input type="hidden" name="qna_seq" value="${qna_seq}">
			</form>
            </div>
            </div>
	
     
    <hr>
            
            
        
<%@ include file="/WEB-INF/views/inc/footerclient.jsp" %>
<script>

	

</script>
</body>
</html>






