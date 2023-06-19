<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>늘봄 > 커뮤니티 > 자유게시판 > 글 조회</title>
   <%@ include file="/WEB-INF/views/inc/assetclient.jsp" %>
   
<style>
pre{
    white-space: pre-wrap; /* pre tag내에 word wrap */
}  

.introducetitle {
	background-image: url("/neulbom/asset/image/자유게시판 사진.jpg")

}
.content_wj {
	padding: 10px 40px;
	text-align: justify;
}

.btn, .btn-primary, .btn-sm {

	width:100px; 
	height:40px; 
	margin-right:10px; 
	font-size: 20px; 
	float:left;

}

.session {
	float: right;
	background-color: #AAA;
	border: 1px solid #AAA;
}

#addcomment {
	border: 1px solid #AAA;
	width: 100%;
	height: 30px;
	margin-bottom: 40px;
}

.full {
	float: left;
	width: 90%;
	height: 40px;
	margin: 10px;
	border: 1px solid #AAA;
}


#comname {
	display: inline-block;	
	float: left;
	font-weight: bold;
}

#comcontent {
	display: inline-block;
	width: 100%;
}

#comment {
	border: 1px solid #AAA;
	width: 100%;
	margin: 30px auto;
	padding: 100px;
}

.comment {
	background-color: #DFDFDF;
	font-weight: bold;
	border: none;
	height: 40px;
	width: 100px;
	border-radius: 10%;
}

#regicom {
	width: 150px;
	padding: auto 10px;
}

#eotrmf {
	width: 70px;
	font-weight: bold;
	padding: auto 0;
}

.del, .edit {
	width: 100px;
	height: 40px;
	font-size: 18px;
	border: 1px solid #AAA;
	background-color: #AAA;
	float: right;
}


.wjbutton {
	max-width:100%;
	display:flex;
	justify-content: end;
}

</style>
</head>
<body>
   <%@ include file="/WEB-INF/views/inc/headerclient.jsp" %>
    <div class="introducetitle">커뮤니티</div>


    <div class="mainmenu text-center">
        <span class="sidebar" >
            <div class="bg-light border-right">
                <div class="list-group list-group-flush">
                    <a href="#" class="list-group-item list-group-item-action active" style="background-color: #043886;">커뮤니티</a>
                    <a href="#" class="list-group-item list-group-item-action">입주상담 게시판</a>
                    <a href="#" class="list-group-item list-group-item-action">문의게시판</a>
                    <a href="/neulbom/client/community/free.do" class="list-group-item list-group-item-action">자유게시판</a>
                </div>
            </div>
        </span>

        

        <div id="deepmenu">
            <nav class="navbar bg-light">
                <div class="container-fluid">
                    <h3 style="font-weight: 400;">입주상담게시판</h3>
                    <div style="display: flex;">
                        <span class="material-symbols-outlined" style="display: block;">
                            home
                        </span>
                        > 커뮤니티 > 입주상담게시판
                    </div>
                </div>
            </nav>
            <hr>

            <table class="table table-bordered table-hover content-head_wj">
                <thead>
                    <tr>
                    	 <input type="hidden" name="con_seq" value="${dto.con_seq}" />
                      <th>${dto.rnum }</th>
                      <th style="text-align: left;">${dto.title }</th>
                      <th>${dto.name }</th>
                      <c:set var="date" value="${dto.con_date}" />
                      <th>${date.substring(0, 10)}</th>
                    </tr>
                </thead>
            </table>
            <div class="content_wj" style="font-size:25px; padding-left:50px;"><pre><c:out value="${dto.content}" /></pre></div>
            
           <c:choose>
    <c:when test="${dto.isreply eq 'y'}">
    <div style="background-color: #e8ebed;">
        <table class="table table-bordered table-hover content-head_wj">
            <thead>
                <tr>
                                    	 <input type="hidden" name="con_seq" value="${dto.con_seq}" />
                    <th>${dto.rnum}</th>
                    <th style="text-align: left;">${dto.retitle}</th>
                    <th>관리자</th>
                      <c:set var="date" value="${dto.con_date}" />
                      <th>${date.substring(0, 10)}</th>
                </tr>
            </thead>
        </table>
        <div class="content_wj" style="font-size: 25px; padding-left: 50px;">
            <pre><c:out value="${dto.recontent}" /></pre>
        </div>
     </div>
    </c:when>
    <c:otherwise>
        <!-- 아무 내용도 표시하지 않음 -->
    </c:otherwise>
</c:choose>
            <hr>
            <div class="wjbutton">
            <c:if test="${dto.isreply ne 'y'}">
   <button type="button" onclick="confirmEdit('${dto.con_seq}', '${dto.name}')" class="btn btn-primary btn-sm" style="width:100px; height:40px; margin-right:10px; font-size: 20px;">수정하기</button>

     <button type="button" class="btn btn-secondary btn-sm btn-light" style="width:100px; height:40px; font-size: 20px;" onclick="confirmDelete('${dto.con_seq}')">삭제하기</button>
</c:if>
       </div>
    </div>
 </div>


<c:set var="pname" value="${param.pname}" />

<c:if test="${dto.name ne pname}">
    <c:set var="message" value="일치하지 않는 정보입니다. 다시 시도하세요." />
    <script>
        alert("${message}");
        history.back();
    </script>
</c:if>


        <%@ include file="/WEB-INF/views/inc/footerclient.jsp" %>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
<script>
function confirmDelete(con_seq) {
    if (confirm("정말 삭제하시겠습니까?")) {
        // 확인 버튼을 눌렀을 때의 동작
        location.href = '/neulbom/client/board/unmemdel.do?con_seq=' + con_seq;
    } else {
        // 취소 버튼을 눌렀을 때의 동작
        // 필요한 경우 아무 동작도 수행하지 않거나 다른 동작을 추가할 수 있습니다.
    }
}

function confirmEdit(con_seq, pname) {
        location.href = '/neulbom/client/board/unmemedit.do?con_seq=' + con_seq + '&pname=' + encodeURIComponent(pname);
}
</script>
</body>
</html>




