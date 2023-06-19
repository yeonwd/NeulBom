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
a{
	text-decoration:none;
	color:#212529;
}
.introducetitle {
		background-size: cover;
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
                    <a href="/neulbom/client/board/qna.do" class="list-group-item list-group-item-action">문의게시판</a>
                           <a href="/neulbom/client/community/free.do" class="list-group-item list-group-item-action">자유게시판</a>
                </div>
            </div>
        </span>

        

        <div id="deepmenu">
            <nav class="navbar bg-light">
                <div class="container-fluid">
                    <h3 style="font-weight: 400;">입주상담게시판</h3>
                    <div style="display: flex;">
                        <span class="material-symbols-outlined">
help
</span>
                        > 입주상담게시판 > 입주 문의글
                    </div>
                </div>
            </nav>
            <hr>
     
            <div style="display: flex; justify-content: end; margin-bottom: 15px;">
<form class="d-flex" role="search" style="max-width: 50%;" action="/neulbom/client/board/resiconsult.do" method="GET">
    <select class="form-select" name="search_option" style="width:100px; margin-right:10px;" aria-label="Default select example" onchange="toggleDateInputs(this)">
        <option value="title">제목</option>
        <option value="author">작성자</option>
        <option value="date">날짜</option>
    </select>

    <input class="form-control_wj" style="width:200px; margin-right:10px; display: none;" type="date" name="start_date" placeholder="시작일" aria-label="Start Date">
    <span class="separate" style="display: none; margin-right: 10px;">~</span>
    <input class="form-control_wj" type="date" style="width:200px; margin-right:10px; display: none;" name="end_date" placeholder="종료일" aria-label="End Date">
    <input class="form-control_wj" style="width:200px; margin-right:10px;" type="text" name="search_keyword" placeholder="검색" aria-label="Search">
    <button class="btn btn-light" type="submit" style="width: 74px !important; height: 38px !important; white-space: nowrap;">검색</button>
</form>
            </div>

            <hr>
            <table class="table table-hover">
                <thead>
                    <tr>
                        <th>번호</th>
                        <th style="text-align: left;">제목</th>
                        <th>작성자</th>
                        <th>날짜</th>
                    </tr>
                </thead>
                <tbody>
<c:set var="currentPage" value="${param.page != null ? param.page : 1}" />
<c:set var="itemsPerPage" value="10" />
<c:set var="startIndex" value="${(currentPage - 1) * itemsPerPage}" />

<c:forEach var="dto" items="${list}" varStatus="loop">
  <input type="hidden" name="isreply" value="${dto.isreply}" />
  <tr>
    <td>
      <input type="hidden" name="con_seq" value="${dto.con_seq}" />
      ${(startIndex + loop.index) + 1}
    </td>
    <td style="text-align: left;">
      <c:choose>
        <c:when test="${dto.isreply == 'y'}">
           <span style="color: tomato;">[답변완료]</span>
        </c:when>
      </c:choose>
      <a href="/neulbom/client/community/residetailcheck.do?con_seq=${dto.con_seq}">${dto.con_title}</a>
    </td>
    <td>${dto.nomem_name}</td>
    <td>${dto.con_date}</td>
  </tr>
</c:forEach>
                </tbody>
            </table>
             <div style="display: flex; justify-content: flex-end;">
<button type="button" class="btn btn-primary btn-sm" style="width: 100px; height: 40px; margin-right: 10px; font-size: 20px;" onclick="location.href='/neulbom/client/board/unmem.do'">등록하기</button>

</div>
    </div>
        </div>
        
   
   

    <div>
    
  <div class="pagination justify-content-center" style="text-align : center; margin-bottom: 10px;">${pagination}</div>

    </div>
	 
	<%@ include file="/WEB-INF/views/inc/footerclient.jsp" %>
<script>
function toggleDateInputs(selectElement) {
    const startDateInput = document.getElementsByName('start_date')[0];
    const endDateInput = document.getElementsByName('end_date')[0];
    const searchKeywordInput = document.getElementsByName('search_keyword')[0];
    const separateElement = document.querySelector('.separate');

    if (selectElement.value === 'date') {
        startDateInput.style.display = 'inline-block';
        endDateInput.style.display = 'inline-block';
        separateElement.style.display = 'inline-block';
        searchKeywordInput.style.display = 'none';
    } else {
        startDateInput.style.display = 'none';
        endDateInput.style.display = 'none';
        separateElement.style.display = 'none';
        searchKeywordInput.style.display = 'inline-block';
    }
}

</script>
</body>
</html>