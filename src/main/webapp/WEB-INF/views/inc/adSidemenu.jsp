<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<div class="sidebox">
        <div class="profile">
        	<c:if test="${sessionScope.pic != null}">
				<img class="profile-img" src="/neulbom/asset/pic/${sessionScope.pic}">
			</c:if>
			<c:if test="${sessionScope.pic == null}">	
				<img class="profile-img" src="/neulbom/asset/images/user.png">
			</c:if>
            <h1 class="profilename">${sessionScope.name}</h1>
            <c:if test="${sessionScope.lv == '1' }">
	            <div class="profilelevel">계정관리자</div>
            </c:if>
            <c:if test="${sessionScope.lv == '2' }">
	            <div class="profilelevel">사무직원</div>
            </c:if>
            <c:if test="${sessionScope.lv == '3' || sessionScope.lv == '4' }">
	            <div class="profilelevel">실무직원</div>
            </c:if>
        </div>
                <div class="menubox">
                    <a href="#" class="menu1" id="create_account">계정 부여</a>
                    <a href="#" class="menu1" id="manage_account">계정 관리</a>
                    <a href="#" class="menu1" id="find_account">아이디/비밀번호 찾기</a>
                    <a href="#" class="menu2" id="staff_salary">직원 급여 관리</a>
                    <a href="#" class="menu2" id="manage_equip">비품 관리</a>
                    <a href="#" class="menu2" id="manage_money">재무 관리</a>
                    <a href="#" class="menu2" id="manage_pay">결제 관리</a>
                    <a href="#" class="menu2" id="manage_meet">면회 관리</a>
                    <a href="#" class="menu2" id="manage_alert">게시판 관리</a>
                    <a href="/neulbom/admin/manage/manageProgram.do" class="menu" id="manage_program">복지 프로그램 관리</a>
                    <a href="/neulbom/admin/manage/qna.do" class="menu" id="manage_alert">문의 및 입주상담 관리</a>
                    <a href="/neulbom/admin/account/adminlist.do" class="menu" id="search_staff">직원 정보 조회</a>
                </div>
                <div class="logout" onclick="location.href='/neulbom/client/account/logout.do';">
                        로그아웃
                </div>
    </div>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
    <script>
	    $('.menu1').click(function() { 
		    var lv = '<%=(String)session.getAttribute("lv")%>';
		    var click_id = $(this).attr('id');
		     if(lv == "1"){ 
		    	if(click_id=="create_account") {
			    	$(location).attr('href', '/neulbom/admin/account/selcate.do');
			    } else if (click_id=="manage_account") {
			    	$(location).attr('href', '/neulbom/admin/account/manage.do');	
			    } else if (click_id=="find_account") {
			    	$(location).attr('href', '/neulbom/admin/account/find.do');	
			    }
		     }
		     else {
				alert("권한이 없습니다.");
		    	
		     }
		}); 
	    
	    $('.menu2').click(function() { 
		    var lv = '<%=(String)session.getAttribute("lv")%>';
		    var click_id = $(this).attr('id');
		     if(lv == "2"){ 
		    	if(click_id=="staff_salary") {
			    	$(location).attr('href', '/neulbom/admin/manage/staffSalary.do');
			    } else if (click_id=="manage_equip") {
			    	$(location).attr('href', '/neulbom/admin/manage/manageEquip.do');	
			    } else if (click_id=="manage_money") {
			    	$(location).attr('href', '/neulbom/admin/manage/manageMoney.do');	
			    } else if (click_id=="manage_pay") {
			    	$(location).attr('href', '/neulbom/admin/manage/pay.do');	
			    } else if (click_id=="manage_meet") {
			    	$(location).attr('href', '/neulbom/admin/manage/meet.do');	
			    } else if (click_id=="manage_alert") {
			    	$(location).attr('href', '/neulbom/admin/board/notice.do');	
			    }
		     }
		     else {
				alert("권한이 없습니다.");
		    	
		     }
		}); 
   
    </script>