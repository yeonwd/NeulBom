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
	#showEquip {
		position: relative;
		left: 28px;
		background-color: #EE9696;
	}
	
	#showEquip > span {
		color: #A61123;
	}
	
	#registerEquip {
		position: relative;
		left: 56px;
		background-color: #EEEA96;
	}
	
	#registerEquip > span {
		color: #4F4F4F;
	}
	
	thead {
		text-align: center;
	}
	
	table{
		position:relative;
		top: 50px;
	}
	
	table td {
		text-align: center;
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
                <div class="selected_menu" id="manageEquip" onclick="location.href='/neulbom/admin/manage/manageEquip.do'">
                    <span id="selected_menu_text">비품신청내역</span>
                </div><!-- selected_menu -->
                <div class="selected_menu" id="showEquip" onclick="location.href='/neulbom/admin/manage/showEquip.do'">
                    <span id="selected_menu_text">비품현황</span>
                </div><!-- selected_menu -->
                <div class="selected_menu" id="registerEquip" onclick="location.href='/neulbom/admin/manage/registerEquip.do'">
                    <span id="selected_menu_text">비품등록</span>
                </div><!-- selected_menu -->
            </div><!-- semi_title -->

               
            
            <div class="main-box">
				<table class="table table-striped table-hover table-bordered">
				<colgroup>
					<col width=15%>
					<col width=20%>
					<col width=18%>
					<col width=7%>
					<col width=15%>
					<col width=15%>
					<col width=10%>
				</colgroup>
				<thead>
					<tr>
						<th>신청날짜</th>
						<th>내용</th>
						<th>물품명</th>
						<th>수량</th>
						<th>개당가격(원)</th>
						<th>관리자번호</th>
						<th>승인여부</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${regList}" var="regdto">
					<tr>
						<td>${regdto.reg_date }</td>
						<td>${regdto.content }</td>
						<td>${regdto.name }</td>
						<td>${regdto.quantity }</td>
						<td>${regdto.price }</td>
						<td>${regdto.admin_seq }</td>
						<td>${regdto.isAccept }</td>
					</tr>
					</c:forEach>
				</tbody>
				</table>
				
			<!-- 페이징 -->

            <div id="paging" class="pagination justify-content-center" style="text-align : center; margin-bottom: 10px;">${pagination}</div>

				
            </div><!-- main-box -->
        </div><!-- inner-box -->
    </div><!-- content-box -->
</div>

<!-- JavaScript Bundle with Popper -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
<script>

</script>
</body>
</html>