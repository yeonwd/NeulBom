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
	.bankAccountEdit-form {
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
                <div class="selected_menu" onclick="location.href='/neulbom/admin/manage/staffSalary.do';">
                    <span id="selected_menu_text" id="editBankAccount">직원급여관리</span>
                </div><!-- selected_menu -->
            </div><!-- semi_title -->
            <div class="main-box">
            <form method="POST" action="/neulbom/admin/manage/editBankAccount.do">
            <div>사번</div>
			<input type="text" id="admin-seq" class="form-control bankAccountEdit-form" placeholder="${eq_seq}" disabled readonly>
            <div>이름</div>
			<input type="text" id="admin-name" class="form-control bankAccountEdit-form" readonly disabled>
            <div>입금은행</div>
			<input type="text" id="admin-bank" name="bank" class="form-control bankAccountEdit-form" placeholder="입금은행을 입력하세요." required maxlength="5">
            <div>계좌번호</div>
			<input type="text" id="admin-bank-account" name="bank_account" class="form-control bankAccountEdit-form" placeholder="입금 계좌번호를 입력하세요." required maxlength="15">
            <div>연락처</div>
			<input type="text" id="admin-tel" class="form-control bankAccountEdit-form"readonly disabled>
            <div>이메일</div>
			<input type="text" id="admin-email" class="form-control bankAccountEdit-form" readonly disabled>

				<input class="btn btn-secondary" type="button" value="돌아가기" onclick="history.back();">
				<input class="btn btn-primary" type="submit" id="edit" value="수정하기">

			<input type="hidden" name="admin_seq" value="${adminDto.admin_seq }">
			<input type="hidden" name="name" value="${adminDto.name }">
			<input type="hidden" name="tel" value="${adminDto.tel }">
			<input type="hidden" name="email" value="${adminDto.email }">
			</form>
            </div><!-- main-box -->
        </div><!-- inner-box -->
    </div><!-- content-box -->
</div>

<!-- JavaScript Bundle with Popper -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
<script>
	$('#admin-seq').val('${adminDto.admin_seq}');
	$('#admin-name').val('${adminDto.name}');
	$('#admin-bank').val('${adminDto.bank}');
	$('#admin-bank-account').val('${adminDto.bank_account}');	
	$('#admin-tel').val('${adminDto.tel}');
	$('#admin-email').val('${adminDto.email}');
	
	$('#edit').click(function() {
		$('form').submit();
	});

</script>
</body>
</html>













