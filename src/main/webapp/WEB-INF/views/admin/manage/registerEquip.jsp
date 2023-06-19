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
	
	
	.registerEquip-form {
		width: 300px;
		margin-top: 5px;
		margin-bottom: 20px;
	}
	
</style>
</head>
<body>


   <div class="main">
    <%@ include file="/WEB-INF/views/inc/adSidemenu.jsp" %>
    <div class="content-box">
        <div id="inner-box">
            <div class="semititle">
                <div class="selected_menu" onclick="location.href='/neulbom/admin/manage/manageEquip.do'">
                    <span id="selected_menu_text" id="manageEquip">비품신청내역</span>
                </div><!-- selected_menu -->
                <div class="selected_menu" id="showEquip" onclick="location.href='/neulbom/admin/manage/showEquip.do'">
                    <span id="selected_menu_text">비품현황</span>
                </div><!-- selected_menu -->
                <div class="selected_menu" id="registerEquip" onclick="location.href='/neulbom/admin/manage/registerEquip.do'">
                    <span id="selected_menu_text">비품등록</span>
                </div><!-- selected_menu -->
            </div><!-- semi_title -->
            <div class="main-box">
            <form method="POST" action="/neulbom/admin/manage/registerEquip.do">
            <div>비품번호</div>
			<input type="text" class="form-control registerEquip-form" placeholder="${eq_seq}" disabled readonly>
            <div>비품명</div>
			<input type="text" name="name" class="form-control registerEquip-form" placeholder="등록할 비품 이름을 입력하세요." required maxlength="10">
            <div>수량</div>
			<input type="number" name="quantity" class="form-control registerEquip-form" min="1" max="50" value="1" required>
			<input class="btn btn-primary" type="submit" value="등록하기">
			
			
			<input type="hidden" name="eq_seq" value="${eq_seq}">
			</form>
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