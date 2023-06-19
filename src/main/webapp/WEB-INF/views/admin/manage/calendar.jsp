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
<link
	href='https://cdn.jsdelivr.net/npm/fullcalendar@5.8.0/main.min.css'
	rel='stylesheet' />
<script
	src='https://cdn.jsdelivr.net/npm/fullcalendar@5.8.0/main.min.js'></script>
<script
	src='https://cdn.jsdelivr.net/npm/fullcalendar@5.8.0/locales-all.min.js'></script>
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

#cal-container {
	display: flex;
	justify-content: center;
	align-items: center;
}

#calendar {
	position: relative;
	top: 30px;
	width: 800px !important;
	height: 600px;
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
					<div id="cal-container">
						<div id="calendar"></div>
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
		document.addEventListener(
						'DOMContentLoaded',
						function() {
							var calendarEl = document
									.getElementById('calendar');

							$.ajax({
										url : '/neulbom/admin/manage/caldata.do',
										type : 'get',
										dataType : 'json',
										success : function(data) {

											data.forEach(function(event) {
												event.start = event.start
														.replace(/'/g, '');
											});

											var calendar = new FullCalendar.Calendar(
													calendarEl,
													{
														locale : 'ko',
														events : data,
														initialView : 'dayGridMonth',
														eventDidMount : function(
																info) {
															info.el.style.backgroundColor = 'rgba(39, 166, 174, 0.2)';
															info.el.style.color = '#5888CE';
														},
													});

											calendar.render();
										},
										error : function(a, b, c) {
											console.log(a, b, c);
										}
									});
						});
	</script>


</body>
</html>