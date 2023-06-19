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

   .edit {
      width: 120px;
   }

   .table tbody tr td:nth-child(7) {
      display: flex;
      justify-content: space-evenly;
   }
   
   #search-form {
	  display: inline-block;
   }
   
   #back {
   	position: relative;
   	margin: auto auto;
   	top: 60px;
   }

	#searchResult {


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
                   <div class="selected_menu">
                       <span id="selected_menu_text" onclick="location.href='/neulbom/admin/manage/staffSalary.do';">직원급여관리</span>
                   </div><!-- selected_menu -->
                   <form method="GET" action="/neulbom/admin/manage/staffSalary.do" id="search-form">
                   <select name="column" class="select_search_item">
                       <option value="name">이름</option>
                       <option value="bank">입금은행</option>
                   </select><!-- select_search_item -->
                   <input type="text" name="word" class="search_input" placeholder="조회할 직원 정보를 입력하세요." required maxlength="15">
                  <input class="btn btn-primary search_button" type="submit" value="검색하기">
                  </form>
               
               </div><!-- semi_title -->
               <div class="main-box">
		<c:if test="${map.search == 'y'}">
		<div id="searchResult">
			<span>'${map.word}'(으)로 검색한 결과 ${totalCount} 건이 있습니다.</span>
		</div>
		</c:if>
            <table class="table table-striped table-bordered table-hover">
            <colgroup>
               <col width=5%>
               <col width=10%>
               <col width=10%>
               <col width=20%>
               <col width=20%>
               <col width=20%>
               <col width=15%>
            </colgroup>
            <thead>
               <tr>
                  <th scope="col">사번</th>
                  <th scope="col">이름</th>
                  <th scope="col">입금은행</th>
                  <th scope="col">계좌번호</th>
                  <th scope="col">급여(원)</th>
                  <th scope="col">연락처</th>
                  <th scope="col">처리</th>
               </tr>
            </thead>
            <c:if test="${salaryList.size()==0 }">
            <tbody>
            <tr>
            	<td colspan="7">검색한 정보와 일치하는 직원이 없습니다.</td>
            </tr>
            </c:if>
            
            
            
            <c:forEach items="${salaryList}" var="salaryDto">
               <tr>
                  <td>${salaryDto.admin_seq }</td>
                  <td>${salaryDto.name}</td>
                  <td>${salaryDto.bank }</td>
                  <td>${salaryDto.bank_account }</td>
                  <td>${salaryDto.salary }</td>
                  <td>${salaryDto.tel }</td>
                  <td>
                     <div class="edit movable" onclick="location.href='/neulbom/admin/manage/editBankAccount.do?admin_seq=${salaryDto.admin_seq}';" >
                        <span id="edit_txt">계좌 정보 수정</span>
                     </div>
                     </td>
               </tr>
            </c:forEach>
            </tbody>
            </table>
            
            <!-- 페이징 -->
            <div id="paging" class="pagination justify-content-center" style="text-align : center; margin-bottom: 10px;">${pagination}</div>
            
            
			<c:if test="${map.search == 'y' }">
				<input class="btn btn-secondary" type="button" id="back" value="돌아가기" onclick="location.href='/neulbom/admin/manage/staffSalary.do';">
			</c:if>
            
               </div><!-- main-box -->
           </div><!-- inner-box -->
       </div><!-- content-box -->
   </div>

<!-- JavaScript Bundle with Popper -->
<script src="/asset/js/bootstrap.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
<script>

</script>
</body>
</html>