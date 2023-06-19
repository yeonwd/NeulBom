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
  #write {
        width: 100px;
        height: 40px;
        color: #ffffff;
        border: none;
        font-size: 20px;
        font-weight: bold;
    }

    #cancle {
        background-color: #A61123;
        color: #FFF;
        border: none;
        width: 100px;
        height: 40px;
        border: none;
        font-size: 20px;
        font-weight: bold;
    }

    #table {
        width: 100%;
        border-top: 1px solid #939393;
        border-bottom: 1px solid #939393;
    }
    

    .th {
        width: 150px;
        font-size: 23px;
        background-color: #EEE;
        padding: 20px 0px;
        border-bottom: 1px solid #DFDFDF;
    }

    .td {
        border-bottom: 1px solid #DFDFDF;
        border-bottom: 1px solid #DFDFDF;
    }

    #title {
        width: 97%;
        height: 50px;
        border: 1px solid #c7c8c9;
        color: black;
        padding: 10px;
    }

    #content {
        width: 97%;
        height: 500px;
        margin: 10px 10px;
        border: 1px solid #c7c8c9;
        padding: 10px;
        resize: none;
    }

    #pic {
        width: 97%;
        height: 50px;
        border: 1px solid #c7c8c9;
        padding: 10px;
        text-align: left;
    }



    .btn1 {
        text-align: center;
        /* margin: 0 auto; */
        margin: 40px auto;
    }
    
    #write, #cancle {
        display: inline-block;
        margin-left: 30px;
    }

    #cancle {
       background-color: #AAA;
    }

    .term_box {
        display: inline-block;
        width: 1200px;
        margin: 30px;
        border: 1px solid black;
    }
    
    #regi_header {
        height: 50px;
        text-align: left;
        padding-left: 10px;
        background-color: #F5F5F5;
    }

    #regi_checkbox {
        height: 20px;
        display: left;
        background-color: #F5F5F5;
    }

    .regi_term {
        height: 200px;
        text-align: left;
        overflow: auto;
    }

    .regi_term > span {
        font-weight: bold;
        padding-bottom: 20px;
    }
    
    #deepmenu {
       margin: 0 auto;
    
    }
    
    

</style>
</head>

<body>
   <%@ include file="/WEB-INF/views/inc/headerclient.jsp" %>
      <div id="consult" class="introducetitle" style="background-image: url('/neulbom/asset/mainimage/registwrite.jpg'); background-position: 50% 75%;">커뮤니티</div>



    <div class="mainmenu text-center">
         <span class="sidebar" >
            <div class="bg-light border-right">
                <div class="list-group list-group-flush"">
                    <a href="#" class="list-group-item list-group-item-action active" style="background-color: #043886;">커뮤니티</a>
                    <a href="/neulbom/client/board/resiconsult.do" class="list-group-item list-group-item-action" style="background-color: #e9ecef;">입주상담게시판</a>
                    <a href="#" class="list-group-item list-group-item-action">문의게시판</a>
                           <a href="#" class="list-group-item list-group-item-action">자유게시판</a>
                </div>
            </div>
        </span>

        

        <div id="deepmenu">
            <nav class="navbar bg-light">
                <div class="container-fluid">
                    <h3 style="font-weight: 400;">입주신청</h3>
                    <div style="display: flex;">
                        <span class="material-symbols-outlined">
assignment_add
</span>
                        > 입주상담게시판 > 등록하기
                    </div>
                </div>
            </nav>
            <hr>
           
               <form method="POST" action="/neulbom/client/board/resiconsultwrite.do" onsubmit="showAlert()">

                <table id="table">
                    <tr>
                        <th class="th">제목</th>
                        <td class="td"><input type="text" name="title" id="title" required class="full" placeholder="제목을 입력하세요." maxlength="60"></td>
                    </tr>
                    <tr>
                        <th class="th">내용</th>
                        <td class="td"><textarea name="content" id="content" required class="full"" placeholder="내용을 입력하세요." maxlength="900"></textarea>
                    </tr>
                    </table>
                    
                   <input type="hidden" name="nomem_seq" value="${param.nomem_seq}">
                    <div class="btn1">
                    <input type="submit" style="width:100px; height:40px;" class="btn btn-primary btn-sm" name="write" id="write" value="글쓰기"> 
                    <input type="button" class="btn btn-secondary btn-sm btn-light" name="cancle" id="cancle" value="취소하기" onclick="history.back();"> 
                    
                    
                    </div>
                    
            </form>
           
           
        </div>
        
    </div>

    
   <%@ include file="/WEB-INF/views/inc/footerclient.jsp" %>
<script>
function showAlert() {
    alert("글이 등록 되었습니다!");
  }
</script>
</body>
</html>