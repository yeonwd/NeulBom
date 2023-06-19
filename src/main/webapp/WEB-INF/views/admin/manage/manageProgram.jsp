<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@
    taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" 
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>늘봄 관리자</title>
<%@ include file="/WEB-INF/views/inc/asset.jsp" %>

<style>
	#registerProgram {
		position: relative;
		left: 28px;
		background-color: #EE9696;
	}
	
	#registerProgram > span {
		color: #A61123;
	}
	

	.table tbody tr td:nth-child(1), 
	.table tbody tr td:nth-child(3), 
	.table tbody tr td:nth-child(4),
	.table tbody tr td:nth-child(5) {
	text-align: center;
}

	.table tbody tr td {
		text-overflow: ellipsis;
		overflow: hidden;
		white-space: nowrap;
	}
	
	.table tbody tr td:nth-child(7) {
		display: flex;
		justify-content: space-evenly;
	}
	
	.programPreview {
		cursor: pointer;
	}
	
	#search-form {
		float: right;
		display: block;
		position: relative;
		top: 40px;
		
		/* text-align: right; */
		/* position: relative; */
		/* right: 20px; */	
	}
	
	#back {
   		position: relative;
   		top: 60px;
   }
	
	#searchResult {
		display: inline-block;
		width: 400px;
		height: 37px;
		
		padding: 5px 0 5px 0;
	}
	
	#searchResult > span {
		font-weight: bold;
		font-size: 18px;
	}
	
	#paging {
		position: relative;
		top: 50px;
	}

</style>
</head>
<body>


   <div class="main">
    <%@ include file="/WEB-INF/views/inc/adSidemenu.jsp" %>
    <div class="content-box">
        <div id="inner-box">
            <div class="semititle">
                <div class="selected_menu" onclick="location.href='/neulbom/admin/manage/manageProgram.do';">
                    <span id="selected_menu_text">프로그램 조회</span>
                </div><!-- selected_menu -->
                <div class="selected_menu" id="registerProgram" onclick="location.href='/neulbom/admin/manage/registerProgram.do';">
                    <span id="selected_menu_text">프로그램 등록</span>
                </div><!-- selected_menu -->
                
                 <form method="GET" action="/neulbom/admin/manage/manageProgram.do" id="search-form">
                   <select name="column" class="select_search_item">
                       <option value="title">제목</option>
                       <option value="content">내용</option>
                       <option value="place">장소</option>
                   </select><!-- select_search_item -->
                   <input type="text" name="word" class="search_input" 
                   placeholder="프로그램 정보를 입력하세요." required maxlength="10">
                   <input class="btn btn-primary search_button" 
                   type="submit" value="검색하기">
                  </form>
                  
                  

             	</div><!-- semi_title -->
        <c:if test="${map.search == 'y'}">
		<div id="searchResult">
			<span>'${map.word}'(으)로 검색한 결과 ${totalCount} 건이 있습니다.</span>
		</div>
		</c:if>
            <div class="main-box">
            <table class="table table-hover table-bordered" style="table-layout: fixed">
            <colgroup>
            	<col width=5%>
				<col width=15%>
				<col width=20%>
				<col width=25%>
				<col width=10%>
				<col width=5%>
				<col width=20%>
			</colgroup>
            <thead>
            	<tr>	
            		<th>번호</th>
            		<th>날짜</th>
            		<th>제목</th>
            		<th>내용</th>
            		<th>장소</th>
            		<th>정원</th>
            		<th>처리</th>
            	</tr>
            </thead>
            
            <c:if test="${progList.size()==0 }">
            <tbody>
            <tr>
            	<td colspan="7">검색한 정보와 일치하는 프로그램이 없습니다.</td>
            </tr>
            </c:if>
            
            <tbody>
            	<c:forEach items="${progList}" var="progDto">
            	<tr>

            		<td class="programPreview">${progDto.prog_seq}</td>
            		<td class="programPreview">${progDto.prog_date }</td>
            		<td class="programPreview" onclick="location.href='/neulbom/admin/manage/detailProgram.do?prog_seq=${progDto.prog_seq}';">${progDto.title}</td>
            		<td class="programPreview" onclick="location.href='/neulbom/admin/manage/detailProgram.do?prog_seq=${progDto.prog_seq}';">${progDto.content }</td>
            		<td class="programPreview">${progDto.place }</td>
            		<td class="programPreview">${progDto.people }</td>
            		<td>
            			<div class="edit movable" onclick="location.href='/neulbom/admin/manage/editProgram.do?prog_seq=${progDto.prog_seq}';" >
            				<span id="edit_txt">수정</span>
            			</div>
            			<div class="delete movable" data-bs-toggle="modal" data-bs-target="#delProgramModal" onclick="setProgramSeq(${progDto.prog_seq})">
            				<span id="delete_txt">삭제</span>
            			</div>
            		</td>
            	</tr>
            	</c:forEach>
            </tbody>
            </table>
            
            <!-- 페이징 -->
            <c:if test="${progList.size() != 0 }">
			<div id="paging" class="pagination justify-content-center" style="text-align : center; margin-bottom: 10px;">${pagination}</div>
			</c:if>
					<!-- Modal -->
<div class="modal fade" id="delProgramModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered">
    <div class="modal-content">
      <div class="modal-header">
        <h1 class="modal-title fs-5" id="exampleModalLabel">경고!</h1>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        해당 프로그램을 정말 삭제하시겠습니까?
      </div>
      <div class="modal-footer">
      <form method="GET" action="/neulbom/admin/manage/delProgram.do">
        <input type="button" class="btn btn-secondary" data-bs-dismiss="modal" value="취소">
        <input type="submit" class="btn btn-primary" value="삭제" onclick="location.href='/neulbom/admin/manage/delProgram.do'">
        <input type="hidden" name="prog_seq">
        </form>
      </div>
    </div>
  </div>
</div>

		<c:if test="${map.search == 'y' }">
			<input class="btn btn-secondary" type="button" id="back" value="돌아가기" onclick="location.href='/neulbom/admin/manage/manageProgram.do';">
		</c:if>

            </div><!-- main-box -->
        </div><!-- inner-box -->
    </div><!-- content-box -->
</div>

<!-- JavaScript Bundle with Popper -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
<script>
	function setProgramSeq(prog_seq) {
		$('input[name=prog_seq]').val(prog_seq);
	}
</script>
</body>
</html>