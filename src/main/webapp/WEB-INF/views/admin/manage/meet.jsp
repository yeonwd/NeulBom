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

.meet {
	background-color: rgba(227.37499594688416, 232.89999067783356, 255, 0.75);
}

#meet_txt {
	color: rgba(100.00000163912773, 119.00000050663948, 219.0000021457672, 1);
}

.meet_calendar {
	background-color: #EE9696;
	opacity: 0.75;
}

.meet_calendar>span {
	color: #A61123;
}

.back {
	background-color: rgba(105, 105, 105, 0.2);
	border-radius: 30px;
	width: 72px;
	height: 30px;
}

#back_txt {
	position: relative;
	top: 3px;
	color: #474747;
}

.table tbody tr td:nth-child(8) {
	display: flex;
	justify-content: space-evenly;
}

#buttons {
	float: right;
	display: flex;
	text-align: center;
	margin-bottom: 10px;
	margin-right: 5px;
}

.reject {
	background-color: rgba(235, 87, 87, 0.2);
	border-radius: 30px;
	width: 72px;
	height: 30px;
}

#reject_txt {
	position: relative;
	top: 3px;
	color: #EB5757;
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

					<div class="select_boards meet movable"
						onclick="location.href='/neulbom/admin/manage/meet.do';">
						<span id="meet_txt">면회 관리</span>
					</div>
					<div class="select_boards meet_calendar movable"
						onclick="location.href='/neulbom/admin/manage/calendar.do';">
						<span>달력 보기</span>
					</div>

				</div>
				<!-- semi_title -->

				<div class="main-box">

					<table class="table table-striped">
						<colgroup>
							<col width="10%">
							<col width="12%">
							<col width="10%">
							<col width="10%">
							<col width="10%">
							<col width="10%">
							<col width="10%">
							<col width="18%">
						</colgroup>
						<thead>
							<tr>
								<th scope="col">번호</th>
								<th scope="col">면회 날짜</th>
								<th scope="col">면회 시간</th>
								<th scope="col">입주자 번호</th>
								<th scope="col">입주자 이름</th>
								<th scope="col">보호자 번호</th>
								<th scope="col">보호자 이름</th>
								<th scope="col">처리</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${list}" var="dto">
								<tr>
									<td>${dto.displayed_seq}</td>
									<td>${dto.meet_date}</td>
									<td>${dto.meet_time}</td>
									<td>${dto.resi_seq}</td>
									<td>${dto.rname}</td>
									<td>${dto.protect_seq}</td>
									<td>${dto.pname}</td>
									<td>
										<!-- 승인 여부 없음 --> <c:if test="${empty dto.confirmation}">
											<div class="add movable" id="confirm"
												onclick="location.href='/neulbom/admin/manage/meetconfirmation.do?seq=${dto.meet_seq}'">
												<span id="add_txt">승인</span>
											</div>
											<div class="reject movable" id="reject"
												onclick="location.href='/neulbom/admin/manage/meetrejection.do?seq=${dto.meet_seq}'">
												<span id="reject_txt">반려</span>
											</div>
										</c:if> <!-- 승인 여부 있음 --> <c:if test="${not empty dto.confirmation}">
											<!-- 승인된 면회 -->
											<c:if test="${dto.confirmation eq 'y'}">
												<!-- 변경 가능한 승인(미래 시점) -->
												<c:if test="${dto.isRevisable eq 1}">
													<div class="add movable"
														onclick="location.href='/neulbom/admin/manage/meetrevision.do?seq=${dto.meet_seq}'">
														<span id="add_txt">승인</span>
													</div>
												</c:if>
												<!-- 변경 불가한 승인(과거 시점) -->
												<c:if test="${dto.isRevisable eq 0}">
													<div class="back">
														<span id="back_txt">승인</span>
													</div>
												</c:if>
											</c:if>
											<!-- 반려된 면회 -->
											<c:if test="${dto.confirmation eq 'n'}">
												<!-- 변경 가능한 반려(미래 시점) -->
												<c:if test="${dto.isRevisable eq 1}">
													<div class="reject movable"
														onclick="location.href='/neulbom/admin/manage/meetrevision.do?seq=${dto.meet_seq}'">
														<span id="reject_txt">반려</span>
													</div>
												</c:if>
												<!-- 변경 불가한 반려(과거 시점) -->
												<c:if test="${dto.isRevisable eq 0}">
													<div class="back">
														<span id="back_txt">반려</span>
													</div>
												</c:if>
											</c:if>
										</c:if>
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

				</div>
				<!-- main-box -->
			</div>
			<!-- inner-box -->
		</div>
		<!-- content-box -->
	</div>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
		crossorigin="anonymous"></script>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
	<script>
		
	</script>


</body>
</html>