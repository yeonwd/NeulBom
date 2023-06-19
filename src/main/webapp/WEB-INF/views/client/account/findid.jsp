<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>	



<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아이디 찾기</title>
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

    #table {
        width: 80%;
        height: 200px;
        border-top: 2px solid #AAA;
        border-bottom: 2px solid #AAA;
        margin: 10px auto;
        text-align: center;
        
    }

       th {
        height: 50px;
        width: 30%;
        font-size: 18px;
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
    	height: 500px;
    	border: 2px solid #AAA;
    	margin: 50px auto;
    	
    }

    span {
        color: #42855B;
    }
    
    #return {
    	margin-left: 30px;
    	border: 1px solid #AAA;
    
    }
    
    #pw {
    	background-color: #AAA;
    	margin-left: 30px;
    	border: 1px solid #AAA;
    }
</style>
</head>
<body>
	<!-- findid.jsp 아이디를 찾음 -->
	
<%@ include file="/WEB-INF/views/inc/headerclient.jsp" %>
	
	<form method="POST" name="findid" action="">
	
	<div id="title">아이디 찾기</div>

    <div id="second">요청하신 아이디 찾기 결과입니다.</div>


    
    <table id="table">
        <tr>
            <th><div>${message }</div></th>
        </tr>
    </table>

    <div id="btn">
    	<!-- 왜 아무도 location 주소로 가지 않는거지? > root인 neulbom을 안했었음 -->
        <input type="button" value="로그인" class="btn" id="search" onclick="location.href='/neulbom/client/account/login.do';"> 
    	<input type="button" value="비밀번호 찾기" class="btn" id="pw" onclick="location.href='/neulbom/client/account/searchpw.do';">
        <input type="button" value="다시찾기" class="btn" id="return" onclick="location.href='/neulbom/client/account/searchid.do';">
    </div>
 </form>
 
<%@ include file="/WEB-INF/views/inc/footerclient.jsp" %>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
<script>

	/* 왜... 안되지.... */

	$("#pw").click.function({
		
		location.href="/client/account/searchpw.do";
		
	});
	
	
	$("#search").click.function({
		
		location.href="/client/account/login.do";
	});
	
	$("#return").click.function({
		location.href="/client/account/searchid.do";
	});
	
	
</script>

</body>
</html>