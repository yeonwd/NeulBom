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
.qna {
	background-color: rgba(227.37499594688416, 232.89999067783356, 255, 0.75);
}

.qna>span {
	color: rgba(100.00000163912773, 119.00000050663948, 219.0000021457672, 1);
}

.c_enquiry {
	background-color: #EE9696;
	opacity: 0.75;
}

.c_enquiry>span {
	color: #A61123;
}

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

.delete {
	width: 78px;
}

.back {
	background-color: rgba(105, 105, 105, 0.2);
	border-radius: 30px;
	width: 78px;
	height: 30px;
}

#back_txt {
	position: relative;
	top: 3px;
	color: #474747;
}

.table tbody tr td:nth-child(7) {
	display: flex;
	justify-content: space-evenly;
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

					<div class="select_boards qna movable"
						onclick="location.href='/neulbom/admin/manage/qna.do';">
						<span>일반 문의</span>
					</div>
					<div class="select_boards c_enquiry movable"
						onclick="location.href='/neulbom/admin/manage/consult.do';">
						<span>입주 문의</span>
					</div>

				</div>
				<!-- semi_title -->

				<div class="main-box">

					<table class="table table-striped">
						<colgroup>
							<col width="8%">
							<col width="11%">
							<col width="21%">
							<col width="15%">
							<col width="15%">
							<col width="8%">
							<col width="12%">
						</colgroup>
						<thead>
							<tr>
								<th scope="col">번호</th>
								<th scope="col">카테고리</th>
								<th scope="col">제목</th>
								<th scope="col">작성자</th>
								<th scope="col">등록일</th>
								<th scope="col">조회수</th>
								<th scope="col">처리</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${list}" var="dto">
								<tr>

									<td>${dto.displayed_seq}</td>
									<td>${dto.category}</td>
									<td class="movable"
										onclick="location.href='/neulbom/admin/manage/viewqna.do?seq=${dto.qna_seq}'">${dto.title}</td>

									<td><c:if test="${not empty dto.pname}">
										${dto.pname}(${dto.type})
										</c:if> <c:if test="${not empty dto.rname}">
										${dto.rname}(${dto.type})
										</c:if></td>


									<td>${dto.qna_date}</td>
									<td>${dto.read}</td>
									<td id="buttons">
										<!-- 미처리된 문의 --> <c:if test="${dto.isReply eq 'n'}">
											<div class="delete movable"
												onclick="location.href='/neulbom/admin/manage/viewqna.do?seq=${dto.qna_seq}'">
												<span id="delete_txt">답변하기</span>
											</div>
										</c:if> <!-- 처리된 문의 --> <c:if test="${dto.isReply eq 'y'}">
											<div class="back">
												<span id="back_txt">완료</span>
											</div>
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