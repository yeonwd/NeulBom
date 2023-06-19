<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비밀번호 찾기</title>
<%@ include file="/WEB-INF/views/inc/assetclient.jsp" %>
<style>
 
    #title {
        background-color: #043886;
        height: 60px;
        margin-bottom: 20px;
        font-size: 25px;
        font-weight: bold;
        color: white;
        padding: 10px 10px 5px 20px;
    }

    #second {
        width: 80%;
        height: 55px;
        background-color: #F1F1f1;
        text-align: center;
        font-weight: bold;
        font-size: 18px;
        padding-top: 15px;
        margin: 30px auto;
    }

    table {
        width: 80%;
        height: 200px;
        border-top: 2px solid #AAA;
        border-bottom: 2px solid #AAA;
        margin: 10px auto;
        text-align: center;
    }

    th {
        background-color: #f1f1f1;
        height: 100px;
        width: 30%;
        font-size: 18px;
        border-bottom: 1px solid #DDD;
    }
    
    td {
    	border-bottom: 1px solid #DDD;
    	margin-bottom: 10px;
    }

    .input {
        width: 450px;
        height: 40px;
        font-size: 18px;
        margin: 5px 5px;
        padding-left: 10px;
    }

    #btn {
        text-align: center;
        margin-top: 30px;
    }
    
    td > input {
    
    	margin: 30px 0;
    
    }

    .btn {
        width: 150px;
        height: 50px;
        font-size: 18px;
        font-weight: bold;
    }

    #search {
        background-color: #043886;
        color: white;
    }
    
    form {
    	width: 1000px;
    	height: 600px;
    	border: 2px solid #AAA;
    	margin: 50px auto;
    	
    }
    
     #cancle {
    	margin-left: 30px;
    	border: 1px solid #AAA;
    
    }
</style>
</head>
<body>
	<!-- searchpw.jsp 비밀번호 찾기 -->
	
<%@ include file="/WEB-INF/views/inc/headerclient.jsp" %>
	
	<form method="POST" name="searchpw" action="/views/client/account/findid.do">
	
   <div id="title">비밀번호 찾기</div>

    <div id="second">사용자 정보를 입력 후 확인 버튼을 눌러주세요.</div>


    
    <table>
        <tr>
            <th>아이디</th>
            <td><input type="text" name="mem_id" class="input" id="mem_id" required placeholder="아이디를 입력하세요."></td>
        </tr>
        <tr>
            <th>이름</th>
            <td><input type="text" name="mem_name" class="input" id="mem_name" required placeholder="이름을 입력하세요."></td>
        </tr>
        
    	<tr>
    		<div id="message"></div>
    	</tr>
    </table>
    <div style="margin-top: 60px; text-align: center">
        	<input type="submit" value="확인" class="btn" id="search" onClick="pw_search()"> 
        	<input type="submit" value="취소" class="btn" id="cancle" onClick="history.back()">
	</div>
 </form>
 
 
<%@ include file="/WEB-INF/views/inc/footerclient.jsp" %>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
<script>
function pw_search() {
	
	var f = document.searchpw;
	
	
	if (!f.mem_id.value) {
		alert("아이디를 입력하세요");
		f.mem_name.focus();
		return;
	}
	
	if (!f.mem_name.value) {
		alert("이름을 입력하세요");
		f.mem_ssn.focus();
		return;
	}  
	
	
	f.action = "<%=request.getContextPath()%>/client/account/findpw.do ";
	f.submit();
	
	location.href="/client/account/findpw.do";
}
</script>

</body>
</html>