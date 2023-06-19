<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@
    taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>늘봄 관리자</title>
<%@ include file="/WEB-INF/views/inc/asset.jsp"%>

<style>
#search-form {
	display: inline-block;
}

#back {
	position: relative;
	margin: auto auto;
	top: 60px;
}

#search_div {
	display: flex;
	align-items: center;
}

.search_input_wrapper {
	flex: 1;
	margin-right: 10px;
}

#search {
	text-align: center;
	border-radius: 5px;
	height: 37px;
	padding-top: 3px;
}

.back {
	text-align: center;
}

#button {
	float: right;
	position: relative;
	top: 48px;
}

#button>div {
	display: inline-block;
	margin-right: 10px;
}

#paging {
	position: relative;
	top: 50px;
}
</style>
</head>
<body>



	<div class="main">
		<%@ include file="/WEB-INF/views/inc/adSidemenu.jsp"%>
		<div class="content-box">
			<div id="inner-box">
				<div class="semititle">
					<div class="selected_menu">
						<span id="selected_menu_text">결제 관리</span>
					</div>
					<!-- selected_menu -->


					<form method="GET" action="/neulbom/admin/manage/pay.do"
						id="search-form">
						<div id="search_div">
							<div class="search_input_wrapper">
								<input type="text" name="name" class="search_input"
									placeholder="입주자 이름을 입력하세요." required autocomplete="off">
							</div>
							<div class="edit movable" id="search">
								<span id="edit_txt">검색</span>
							</div>
						</div>
					</form>


				</div>
				<!-- semi_title -->
				<div class="main-box">
					<c:if test="${smap.search eq 'y'}">
						<div style="text-align: center;">'${smap.name}'(으)로 검색한
							결과입니다.</div>
					</c:if>
					<table class="table table-striped table-bordered">
						<colgroup>
							<col width=5%>
							<col width=13%>
							<col width=13%>
							<col width=19%>
							<col width=15%>
							<col width=18%>
							<col width=23%>
						</colgroup>
						<thead>
							<tr>
								<th scope="col">번호</th>
								<th scope="col">이름</th>
								<th scope="col">입주 유형</th>
								<th scope="col">비용</th>
								<th scope="col">납부</th>
								<th scope="col">납부일</th>
								<th scope="col">연락처</th>
							</tr>
						</thead>

						<c:if test="${list.size()==0}">
							<tbody>
								<tr>
									<td colspan="7">일치하는 입주자가 없습니다.</td>
								</tr>
						</c:if>

						<c:forEach items="${list}" var="dto">
							<tr>
								<td>${dto.displayed_seq}</td>
								<td>${dto.name}</td>
								<td>${dto.kind}</td>
								<td>${dto.price}원</td>
								<c:if test="${dto.isPay eq 'y'}">
									<td>완료</td>
								</c:if>
								<c:if test="${dto.isPay eq 'n'}">
									<td>미완료</td>
								</c:if>
								<td>${dto.pay_date}</td>
								<td>${dto.tel}</td>
							</tr>
						</c:forEach>
						</tbody>
					</table>
					<c:if test="${list.size() != 0 }">
						<div id="paging" class="pagination justify-content-center"
							style="text-align: center; margin-bottom: 10px;">${pagination}</div>
					</c:if>
					<c:if test="${smap.search eq 'y'}">
						<div id="button">
							<div class="back movable"
								onclick="location.href='/neulbom/admin/manage/pay.do';">
								<span id="back_txt">돌아가기</span>
							</div>
						</div>
					</c:if>

				</div>
				<!-- main-box -->
			</div>
			<!-- inner-box -->
		</div>
		<!-- content-box -->
	</div>

	<!-- JavaScript Bundle with Popper -->
	<script src="/asset/js/bootstrap.js"></script>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
	<script>
		$('#search').click(function() {
			$('form').submit();
		});
	</script>
</body>
</html>