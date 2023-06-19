<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>늘봄 > 커뮤니티 > 자유게시판</title>

<%@ include file="/WEB-INF/views/inc/assetclient.jsp"%>

<style>
.introducetitle {
	background-image: url("/neulbom/asset/image/자유게시판 사진.jpg");
	background-size: cover;
}

tr:hover {
	cursor: pointer;
	font-weight: bold;
}

#addbtn {
	float: left;
	background-color: #043886;
	text-align: center;
	color: #FFF;
	border: none;
	width: 100px;
	height: 40px;
	border: none;
	font-size: 20px;
	font-weight: bold;
	border-radius: 5%;
	padding: 0;
}

a {
	border: none;
}
</style>
</head>

<body>
	<%@ include file="/WEB-INF/views/inc/headerclient.jsp"%>

	<div class="introducetitle">커뮤니티</div>


	<div class="mainmenu text-center">
		<span class="sidebar">
			<div class="bg-light border-right">
				<div class="list-group list-group-flush"">
                    <a href="#" class="list-group-item list-group-item-action active" style="background-color: #043886;">커뮤니티</a>
                    <a href="/neulbom/client/board/resiconsult.do" class="list-group-item list-group-item-action" >입주상담게시판</a>
                    <a href="/neulbom/client/board/qna.do" class="list-group-item list-group-item-action">문의게시판</a>
                           <a href="/neulbom/client/community/free.do" class="list-group-item list-group-item-action" style="background-color: #e9ecef;">자유게시판</a>
                </div>
			</div>
		</span>



		<div id="deepmenu">
			<nav class="navbar bg-light">
				<div class="container-fluid">
					<h3 style="font-weight: 400;">자유게시판</h3>
					<div style="display: flex;">
						<span class="material-symbols-outlined" style="display: block;">
							home </span> > 커뮤니티 > 자유게시판
					</div>
				</div>
			</nav>
			<hr>
			<div
				style="display: flex; justify-content: end; margin-bottom: 15px;">


				<c:if test="${map.search == 'y' }">
					<div id="searchresult"
						style="text-align: left; margin-right: 70px; margin-top: 5px;">
						'${map.word }'(으)로 검색한 결과 ${totalCount }건이 있습니다.</div>
				</c:if>

				<form class="d-flex" role="search" style="max-width: 50%;"
					action="/neulbom/client/community/free.do">

					<select name="column" class="form-select"
						aria-label="Default select example" style="margin-right: 10px;">
						<option selected value="title">제목</option>
						<option value="free_seq">번호</option>
						<option value="name">작성자</option>
						<option value="content">내용</option>
					</select> <input class="form-control_wj" type="search"
						placeholder="검색어를 입력하세요" aria-label="Search" name="word">
					<button class="btn btn-light" type="submit"
						style="width: 74px !important; height: 38px !important; white-space: nowrap;">검색</button>
				</form>

			</div>





			<hr>
			<table class="table table-hover list">
				<thead>
					<tr>
						<th>번호</th>
						<th style="text-align: center;">제목</th>
						<th>작성자</th>
						<th>날짜</th>
						<th>조회수</th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${list.size() == 0}">
						<tr>
							<td colspan="10">게시물이 없습니다.</td>
						</tr>
					</c:if>
					<c:forEach items="${list }" var="dto">
						<tr
							onclick="location.href='/neulbom/client/community/viewfree.do?free_seq=${dto.free_seq}';">
							<td><c:if test="${dto.depth == 0 }">
                        ${dto.displayed_seq }
                        </c:if> <c:if test="${dto.depth > 0 }">
									<td>답글</td>
								</c:if></td>
							<td style="text-align: justify">${dto.title}</td>
							<td>${dto.name }</td>
							<td>${dto.free_date }</td>
							<td>${dto.read }</td>
						</tr>
					</c:forEach>
					
					
				</tbody>
			</table>

			<c:if test="${not empty id }">
				<input type="button" id="addbtn" value="글쓰기"
					onclick="location.href='/neulbom/client/community/addfree.do?mode=new';">
			</c:if>
			<div class="pagination justify-content-center"
				style="text-align: center; margin: 80px;">${pagination}</div>

			<!-- 페이지 넘버 -->
			<%-- <br>
        <div id="pageForm">
        	<c:if test="${startPage != 1 }">
        		<a href='/neulbom/client/community/viewfree.do?page=${startPage - 1 }'>[ 이전 ]</a>    
            </c:if>
            
            <c:forEach var="pageNum" begin="${startPage }" end="${endPage }">
            	<c:if test="${pageNum == fpage }">
            		${pageNum }&nbsp;
            	</c:if>
            	<c:if test="${pageNum != fpage }">
            		<a href='/neulbom/client/community/viewfree.do?page=${pageNum }'>${pageNum }&nbsp;</a>
            	</c:if>
            </c:forEach>
            
            <c:if test="${endPage != maxPage }">
            	<a href='/neulbom/client/community/viewfree.do?page=${endPage + 1 }'>[ 다음 ]</a>
            </c:if>
        </div> --%>
		</div>
	</div>






	<div></div>


	</div>

	<%@ include file="/WEB-INF/views/inc/footerclient.jsp"%>
	<script>
		
	</script>
</body>
</html>