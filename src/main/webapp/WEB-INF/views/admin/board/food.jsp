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
.select_boards {
	width: 183px;
	height: 47px;
	position: relative;
	border-radius: 30px;
	text-align: center;
	justify-content: center;
	display: inline-block;
	padding-top: 7px;
	margin-right: 28px;
}

.select_boards>span {
	width: 144px;
	height: 31px;
	font-size: 24px;
	letter-spacing: 0;
}

.notice {
	background-color: rgba(227.37499594688416, 232.89999067783356, 255, 0.75);
}

#notice_txt {
	color: rgba(100.00000163912773, 119.00000050663948, 219.0000021457672, 1);
}

.food {
	background-color: #EE9696;
	opacity: 0.75;
}

#food_txt {
	color: #A61123;
}

.life {
	background-color: #EEEA96;
	opacity: 0.75;
}

#life_txt {
	color: #9D7C25;
}

.free {
	background-color: #ACACA2;
	opacity: 0.75;
}

#free_txt {
	color: #4F4F4F;
}

.table thead tr th {
	text-align: center;
}

.table tbody tr td:nth-child(1), .table tbody tr td:nth-child(3), .table tbody tr td:nth-child(4),
	.table tbody tr td:nth-child(5) {
	text-align: center;
}

.table tbody tr td:nth-child(5) {
	display: flex;
	justify-content: space-evenly;
}

.edit {
	background-color: rgba(39, 166, 174, 0.2);
	border-radius: 30px;
	width: 72px;
	height: 30px;
}

#edit_txt {
	color: #5888CE;
}

.delete {
	background-color: rgba(199, 143, 0, 0.2);
	border-radius: 30px;
	width: 72px;
	height: 30px;
}

#delete_txt {
	color: #EB7257;
}

.add {
	position: relative;
	top: 30px;
	text-align: center;
	display: inline-block;
}

#add_div {
	float: right;
}

#paging {
	position: relative;
	top: 50px;
}

#title, .edit, .delete {
	cursor: pointer;
}
</style>
</head>
<body>

	<div class="main">
		<%@ include file="/WEB-INF/views/inc/adSidemenu.jsp"%>
		<div class="content-box">
			<div id="inner-box">
				<div class="semititle">

					<div class="select_boards notice movable"
						onclick="location.href='/neulbom/admin/board/notice.do';">
						<span id="notice_txt">공지사항</span>
					</div>
					<div class="select_boards food movable"
						onclick="location.href='/neulbom/admin/board/food.do';">
						<span id="food_txt">식단표</span>
					</div>

					<div class="select_boards life movable"
						onclick="location.href='/neulbom/admin/board/life.do';">
						<span id="life_txt">생활게시판</span>
					</div>

					<div class="select_boards free movable"
						onclick="location.href='/neulbom/admin/board/free.do';">
						<span id="free_txt">자유게시판</span>
					</div>

				</div>
				<!-- semi_title -->
				<div class="main-box">
					<div>
						<table class="table table-striped">
							<colgroup>
								<col width=10%>
								<col width=35%>
								<col width=15%>
								<col width=15%>
								<col width=35%>
							</colgroup>
							<thead>
								<tr>
									<th scope="col">번호</th>
									<th scope="col">제목</th>
									<th scope="col">등록일</th>
									<th scope="col">조회수</th>
									<th scope="col">처리</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${list}" var="dto">

									<tr>
										<td>${dto.displayed_seq}</td>
										<td id="title"
											onclick="location.href='/neulbom/admin/board/viewfood.do?seq=${dto.food_seq}'">
											${dto.title}</td>
										<td>${dto.food_date}</td>
										<td>${dto.read}</td>
										<td>
											<div class="edit"
											onclick="location.href='/neulbom/admin/board/editfood.do?seq=${dto.food_seq}'">
												<span id="edit_txt">수정</span>
											</div>
											<div class="delete"
												onclick="location.href='/neulbom/admin/board/deletefood.do?seq=${dto.food_seq}'">
												<span id="delete_txt">삭제</span>
											</div>
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
						<!-- 페이징 -->
						<c:if test="${list.size() != 0 }">
							<div id="paging" class="pagination justify-content-center"
								style="text-align: center; margin-bottom: 10px;">${pagination}</div>
						</c:if>
						<div id="add_div">
							<div class="add movable"
							onclick="location.href='/neulbom/admin/board/addfood.do'">
								<span id="add_txt">등록</span>
							</div>
						</div>
					</div>
				</div>
				<!-- main-box -->
			</div>
			<!-- inner-box -->
		</div>
		<!-- content-box -->
	</div>

	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
	<script>
		
	</script>
</body>
</html>