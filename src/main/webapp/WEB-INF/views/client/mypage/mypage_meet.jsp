<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page import="java.time.LocalDate" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>


 <%@ include file="/WEB-INF/views/inc/assetclient.jsp" %>
<link rel="stylesheet" href="/neulbom/asset/css/mypagelist.css">

<style>
    html,
    body {
        height: 100vh;
        margin: 0;
    }

    @font-face {
        font-family: 'IBMPlexSansKR-Regular';
        src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_20-07@1.0/IBMPlexSansKR-Regular.woff') format('woff');
        font-weight: normal;
        font-style: normal;
    }

    html {
        font-family: 'IBMPlexSansKR-Regular';
    }

    body {
        margin: 0;
    }
</style>



<body>
 
    <%@ include file="/WEB-INF/views/inc/headerclient.jsp" %>
    

	<div class="container">
		<div class="row">
			<div class="col-md-3">
				<div class="sidebox">
					<div class="profile">

						<img src="/neulbom/asset/images/user.png" id="profileimg">
						<h1 class="profilename">${sessionScope.name}님</h1>
						<h1 class="profilename_id">(${sessionScope.id})</h1>

					</div>
					<div class="menubox">
                  <div class="menu" id="create_account"
                     onclick="location.href='/neulbom/client/mypage/mypage_info.do';">
                     <span class="material-symbols-outlined">person</span> 내정보
                  </div>
                  <div class="menu" id="search_staff"
                     onclick="location.href='/neulbom/client/mypage/mypage_payment_details.do';">
                     <span class="material-symbols-outlined">
                        credit_score </span> 결제관리
                  </div>

                  <div class="menu" id="manage_account"
                     onclick="location.href='/neulbom/client/mypage/mypage_meet.do';">
                     <span class="material-symbols-outlined">
                        pending_actions </span> 면회관리
                  </div>

                  <div class="menu" id="staff_salary"
                     onclick="location.href='/neulbom/client/mypage/program.do';">
                     <span class="material-symbols-outlined btnprog"> groups_2
                     </span> 복지프로그램관리
                  </div>
                  <div class="menu" id="manage_equip"
                     onclick="location.href='/neulbom/client/mypage/mypage_inquiry.do';">
                     <span class="material-symbols-outlined">
                        support_agent </span> 내 문의
                  </div>

                  <hr>
                  <div class="menu" id="manage_qna_consult">
                     <span class="material-symbols-outlined"> help </span> 늘봄
                  </div>
               </div>
            </div>
         </div>
    
    


            
            <div class="col-md-9">
                <div class="box" style="height:100%;">
                    <div class="title">면회신청내역</div>
                    <form method="GET" action="/neulbom/client/mypage/mypage_meet.do">
                      <div class="selec">
                      	<input type="date" name="start_date" style="margin-right:10px;" required> 부터 <input type="date" name="end_date" style="margin-left:10px; margin-right: 10px;" required>
                    	<button type="submit">검색하기</button>
                    </div>
                    </form>
                    <c:if test="${map.start_date != null}">
                    <div id="summary">${map.start_date} ~ ${map.end_date} 면회신청내역이 ${totalCount}건이 있습니다.</div>
					</c:if>
					<c:if test="${map.start_date == null}">
                    <div id="summary">면회신청내역이 총 ${totalCount}건이 있습니다.</div>
                    </c:if>



                    <hr>
                    <div class="row justify-content-center">
                        <table class="table">
                            <thead>
                              <tr>
                                <th scope="col">번호</th>
                                <th scope="col">보호자명</th>
                                <th scope="col">입주자명</th>
                                <th scope="col">면회날짜</th>
                                <th scope="col">면회신청시간</th>
                                <th scope="col">승인여부</th>
                                <c:if test="${sessionScope.lv == 7}">
                                <th scope="col">면회 취소</th>
                                </c:if>
                              </tr>
                            </thead>
                            <tbody>
                              <form id="meetdel" method="POST" action="/neulbom/client/mypage/mypage_meetdel.do">
                              <c:set var="currentDate" value="<%= LocalDate.now() %>" />
                              <c:forEach items="${list}" var="dto">
							  <tr>
							    <th scope="row">${dto.rnum}</th>
							    <td>${dto.pname}</td>
							    <td>${dto.rname}</td>
							    <td><span class="meetDate">${dto.meet_date}</span></td>
							    <td>${dto.meet_time}</td>
							    <td>${dto.confirmation}<input type="hidden" name="meet_seq" value="${dto.meet_seq}"></td>
							      <c:if test="${sessionScope.lv ==7}">
							    <td style="padding-bottom: 0px; padding-top: 0.25rem;">
							      <c:if test="${dto.meet_date lt currentDate}">
							        <button type="button" id="cancelButton" class="btn btn-primary btn-sm" disabled>취소</button>
							      </c:if>
							      <c:if test="${dto.meet_date ge currentDate}">
							        <button type="button" id="cancelButton" class="btn btn-primary btn-sm" onclick="confirmCancel()">취소</button>
							      </c:if>
							    </td>
							      </c:if>
							  </tr>
							  </c:forEach>
                              </form>
                            </tbody>
                          </table>
                        
                          <!-- 페이징 -->
                          <div class="pagination justify-content-center" style="text-align : center; margin-bottom: 10px;">${pagination}</div>
                          
                          
                          <c:if test="${sessionScope.lv == 7}">
                          <div>
                          	<button type="button" style="float:right;" class="btn btn-primary" onclick="location.href='http://localhost:8090/neulbom/client/mypage/mypage_meetadd.do'">면회신청</button>
                          </div>
                          </c:if>

                          <br><br><br><br><br>
                          <!-- 캘린더 -->
                          <!-- <div class="calendar"></div> -->


                    </div>
                </div>
            </div>       
          </div>
        </div>
        
        <%@ include file="/WEB-INF/views/inc/footerclient.jsp" %>
        

</body>
<script>
  //check the console for date click event
//Fixed day highlight
//Added previous month and next month view

  

   
   
   function confirmCancel() {
    var result = confirm("정말 취소하시겠습니까?");
    if (result) {
    	document.getElementById("meetdel").submit();
    }
    else {
      
    }
  }
   
   
function CalendarControl() {
    const calendar = new Date();
    const calendarControl = {
      localDate: new Date(),
      prevMonthLastDate: null,
      calWeekDays: ["Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"],
      calMonthName: [
        "Jan",
        "Feb",
        "Mar",
        "Apr",
        "May",
        "Jun",
        "Jul",
        "Aug",
        "Sep",
        "Oct",
        "Nov",
        "Dec"
      ],
      daysInMonth: function (month, year) {
        return new Date(year, month, 0).getDate();
      },
      firstDay: function () {
        return new Date(calendar.getFullYear(), calendar.getMonth(), 1);
      },
      lastDay: function () {
        return new Date(calendar.getFullYear(), calendar.getMonth() + 1, 0);
      },
      firstDayNumber: function () {
        return calendarControl.firstDay().getDay() + 1;
      },
      lastDayNumber: function () {
        return calendarControl.lastDay().getDay() + 1;
      },
      getPreviousMonthLastDate: function () {
        let lastDate = new Date(
          calendar.getFullYear(),
          calendar.getMonth(),
          0
        ).getDate();
        return lastDate;
      },
      navigateToPreviousMonth: function () {
        calendar.setMonth(calendar.getMonth() - 1);
        calendarControl.attachEventsOnNextPrev();
      },
      navigateToNextMonth: function () {
        calendar.setMonth(calendar.getMonth() + 1);
        calendarControl.attachEventsOnNextPrev();
      },
      navigateToCurrentMonth: function () {
        let currentMonth = calendarControl.localDate.getMonth();
        let currentYear = calendarControl.localDate.getFullYear();
        calendar.setMonth(currentMonth);
        calendar.setYear(currentYear);
        calendarControl.attachEventsOnNextPrev();
      },
      displayYear: function () {
        let yearLabel = document.querySelector(".calendar .calendar-year-label");
        yearLabel.innerHTML = calendar.getFullYear();
      },
      displayMonth: function () {
        let monthLabel = document.querySelector(
          ".calendar .calendar-month-label"
        );
        monthLabel.innerHTML = calendarControl.calMonthName[calendar.getMonth()];
      },
      selectDate: function (e) {
        console.log(
          `${e.target.textContent} ${
            calendarControl.calMonthName[calendar.getMonth()]
          } ${calendar.getFullYear()}`
        );
      },
      plotSelectors: function () {
        document.querySelector(
          ".calendar"
        ).innerHTML += `<div class="calendar-inner"><div class="calendar-controls">
          <div class="calendar-prev"><a href="#"><svg xmlns="http://www.w3.org/2000/svg" width="128" height="128" viewBox="0 0 128 128"><path fill="#666" d="M88.2 3.8L35.8 56.23 28 64l7.8 7.78 52.4 52.4 9.78-7.76L45.58 64l52.4-52.4z"/></svg></a></div>
          <div class="calendar-year-month">
          <div class="calendar-month-label"></div>
          <div>-</div>
          <div class="calendar-year-label"></div>
          </div>
          <div class="calendar-next"><a href="#"><svg xmlns="http://www.w3.org/2000/svg" width="128" height="128" viewBox="0 0 128 128"><path fill="#666" d="M38.8 124.2l52.4-52.42L99 64l-7.77-7.78-52.4-52.4-9.8 7.77L81.44 64 29 116.42z"/></svg></a></div>
          </div>
          <div class="calendar-today-date">Today: 
            ${calendarControl.calWeekDays[calendarControl.localDate.getDay()]}, 
            ${calendarControl.localDate.getDate()}, 
            ${calendarControl.calMonthName[calendarControl.localDate.getMonth()]} 
            ${calendarControl.localDate.getFullYear()}
          </div>
          <div class="calendar-body"></div></div>`;
      },
      plotDayNames: function () {
        for (let i = 0; i < calendarControl.calWeekDays.length; i++) {
          document.querySelector(
            ".calendar .calendar-body"
          ).innerHTML += `<div>${calendarControl.calWeekDays[i]}</div>`;
        }
      },
      plotDates: function () {
        document.querySelector(".calendar .calendar-body").innerHTML = "";
        calendarControl.plotDayNames();
        calendarControl.displayMonth();
        calendarControl.displayYear();
        let count = 1;
        let prevDateCount = 0;
  
        calendarControl.prevMonthLastDate = calendarControl.getPreviousMonthLastDate();
        let prevMonthDatesArray = [];
        let calendarDays = calendarControl.daysInMonth(
          calendar.getMonth() + 1,
          calendar.getFullYear()
        );
        // dates of current month
        for (let i = 1; i < calendarDays; i++) {
          if (i < calendarControl.firstDayNumber()) {
            prevDateCount += 1;
            document.querySelector(
              ".calendar .calendar-body"
            ).innerHTML += `<div class="prev-dates"></div>`;
            prevMonthDatesArray.push(calendarControl.prevMonthLastDate--);
          } else {
            document.querySelector(
              ".calendar .calendar-body"
            ).innerHTML += `<div class="number-item" data-num=\${count}><a class="dateNumber" href="#">\${count++}</a></div>`;
          }
        }
        //remaining dates after month dates
        for (let j = 0; j < prevDateCount + 1; j++) {
          document.querySelector(
            ".calendar .calendar-body"
          ).innerHTML += `<div class="number-item" data-num=\${count}><a class="dateNumber" href="#">\${count++}</a></div>`;
        }
        calendarControl.highlightToday();
        calendarControl.plotPrevMonthDates(prevMonthDatesArray);
        calendarControl.plotNextMonthDates();
      },
      attachEvents: function () {
        let prevBtn = document.querySelector(".calendar .calendar-prev a");
        let nextBtn = document.querySelector(".calendar .calendar-next a");
        let todayDate = document.querySelector(".calendar .calendar-today-date");
        let dateNumber = document.querySelectorAll(".calendar .dateNumber");
        prevBtn.addEventListener(
          "click",
          calendarControl.navigateToPreviousMonth
        );
        nextBtn.addEventListener("click", calendarControl.navigateToNextMonth);
        todayDate.addEventListener(
          "click",
          calendarControl.navigateToCurrentMonth
        );
        for (var i = 0; i < dateNumber.length; i++) {
            dateNumber[i].addEventListener(
              "click",
              calendarControl.selectDate,
              false
            );
        }
      },
      highlightToday: function () {
        let currentMonth = calendarControl.localDate.getMonth() + 1;
        let changedMonth = calendar.getMonth() + 1;
        let currentYear = calendarControl.localDate.getFullYear();
        let changedYear = calendar.getFullYear();
        if (
          currentYear === changedYear &&
          currentMonth === changedMonth &&
          document.querySelectorAll(".number-item")
        ) {
          document
            .querySelectorAll(".number-item")
            [calendar.getDate() - 1].classList.add("calendar-today");
        }
      },
      plotPrevMonthDates: function(dates){
        dates.reverse();
        for(let i=0;i<dates.length;i++) {
            if(document.querySelectorAll(".prev-dates")) {
                document.querySelectorAll(".prev-dates")[i].textContent = dates[i];
            }
        }
      },
      plotNextMonthDates: function(){
       let childElemCount = document.querySelector('.calendar-body').childElementCount;
       //7 lines
       if(childElemCount > 42 ) {
           let diff = 49 - childElemCount;
           calendarControl.loopThroughNextDays(diff);
       }

       //6 lines
       if(childElemCount > 35 && childElemCount <= 42 ) {
        let diff = 42 - childElemCount;
        calendarControl.loopThroughNextDays(42 - childElemCount);
       }

      },
      loopThroughNextDays: function(count) {
        if(count > 0) {
            for(let i=1;i<=count;i++) {
                document.querySelector('.calendar-body').innerHTML += `<div class="next-dates">${i}</div>`;
            }
        }
      },
      attachEventsOnNextPrev: function () {
        calendarControl.plotDates();
        calendarControl.attachEvents();
      },
      init: function () {
        calendarControl.plotSelectors();
        calendarControl.plotDates();
        calendarControl.attachEvents();
      }
    };
    calendarControl.init();
  }
  
  const calendarControl = new CalendarControl(); 
  
</script>
</html>