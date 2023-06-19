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
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/flatpickr/dist/flatpickr.min.css">
<link rel="stylesheet" type="text/css" href="https://npmcdn.com/flatpickr/dist/themes/material_blue.css">
<script src="https://cdn.jsdelivr.net/npm/flatpickr"></script>
<script src="https://npmcdn.com/flatpickr/dist/l10n/ko.js"></script>


<style>
	#addemp, #addresi {
		display: flex;
		padding: 30px;
	}
	#input-list {
		margin-top: 2rem;
	}
	#inner-list {
		position: relative;
		margin-top: 30px;
		margin-left: 5px;
		display: inline-block;
	}
	.input-form {
		display:none;
	}
	.add-info {
		color: #999;
		margin-left: 10px;
		border-radius: 5px;
		border-style: none;
		height: 40px;
	}
	#lev-sel {
		margin-left: 10px;
		margin-top: 5px;
	}
	#photo-section {
		position: relative;
		text-align: center;
		margin-left: 20px;
		padding: 20px;
		background-color: white;
		height: 360px;
		top: 45px;
		
	}
	#preview {
		width: 250px;
		height: 250px;
	}
	#add-cate {
		font-size: 20px;
		font-weight: bold;
	}
	.main-box {
		background-color: rgba(221, 236, 227, 1);
		padding-left: 50px;
		padding-right: 50px;
		border-radius: 15px;
		width: 900px;
		height: 980px;
		display: flex;
		justify-content: space-around;
		align-content: center;
		
	}
	.emp-box {
		display: none;
	}
	.detail{
		width: 600px;
		margin-left: 10px;
		border-radius: 5px;
		border-style: none;
		height: 120px;
	}
	.tel {
		color: #999;
		margin-left: 10px;
		border-radius: 5px;
		border-style: none;
		height: 40px;
		width: 80px;
	}
	.email {
		color: #999;
		margin-left: 10px;
		border-radius: 5px;
		border-style: none;
		height: 40px;
		width: 150px;
	}
	.address {
		margin-top: 20px;
	}
	#menubox {
		display: flex;
		justify-content: center;
	}
	

</style>
</head>
<body>
	<div class="main">
	    <%@ include file="/WEB-INF/views/inc/adSidemenu.jsp" %>
	    <div class="content-box">
	        <div id="inner-box">
	            <div class="semititle">
	                <div class="selected_menu">
	                    <span id="selected_menu_text">계정 부여</span>
	                </div><!-- selected_menu -->
	                
	            </div><!-- semi_title -->
	            <div id="menubox">
	            
	            <div class="main-box">
	                <form method="POST" action="/neulbom/admin/account/resiacc.do"
			enctype="multipart/form-data">
	                <div id="addemp">
	                	<div>
							<div id="inner-list">
								<span id="add-cate">입주자 이름:</span><input type="text" name="name" onkeyup="checkReg(event)" maxlength="8" required id="name" class="add-info"/>			
							</div>
							<div>
							<div id="inner-list">
								<span id="add-cate">주민번호:</span><input id="birth" pattern="\d{6}" placeholder="" required th:field="*{birth}" type="text" name="ssn1" oninput="convertDateFormat(event)" style="width:100px !important;" class="add-info">
    <span id="ssnSeparator">-</span>
    <input type="password" name="ssn2" class="add-info" style="width:130px;" required maxlength="7" onkeypress="return isNumeric(event)">
							</div>
							</div>
							<div id="inner-list">
								<span id="add-cate">아이디:</span><input type="text" id="id" required name="id" maxlength="12" class="add-info">	
							</div>
							<div>
							<div id="inner-list">
								<span id="add-cate">비밀번호:</span><input type="password" name="pw" class="add-info" id="passwordField" required maxlength="20" onkeyup="checkPasswordValidity()"><br><span id="passwordValidityText"></span><br>비밀번호: 영문, 숫자, 특수문자 최소 1개 포함 6자~20자 이내<br>		
							</div>
							</div>
							<div id="inner-list">
								<span id="add-cate">전화번호:</span><input type="text" id="telFirst" class="tel" value="010" name="tel1" oninput="this.value = this.value.replace(/[^0-9]/g, '');" required maxlength="3">
  - <input type="text" name="tel2" id="telSecond" class="tel" oninput="this.value = this.value.replace(/[^0-9]/g, '');" required maxlength="4">
  - <input type="text" name="tel3" id="telThird" class="tel" oninput="this.value = this.value.replace(/[^0-9]/g, '');" required maxlength="4">		
							</div>
							<div id="inner-list">
								<span id="add-cate">이메일:</span><input type="text" name="email1" required class="email"> <div style="display:inline">@</div> <input type="text" name="email2" required class="email">			
							</div>
							<div id="inner-list">
								<div class="address">
								<span id="add-cate">주소:</span><input type="text" name="padr" id="sample4_postcode" required readonly class="add-info"><input type="button" class="btn btn-primary" style="margin-left: 10px;" value="우편번호 검색"  onclick="sample4_execDaumPostcode()" ><br>
								</div>
								<div class="address">
                   <span id="add-cate">도로명 주소: </span><input type="text" id="sample4_roadAddress" name="proad" class="add-info" readonly> <br>
								</div>
								<div class="address">
                    <span id="add-cate">상세 주소: </span><input type="text" name="padrdetail" class="add-info" required></div> <br>			
							</div>
							<div id="inner-list">
								<span id="add-cate">입주유형:</span><select id="lev-sel" id="lv" name="lv">
									<option value="5">실버타운</option>
									<option value="6">요양원</option>
								</select>
							</div>
							<div id="inner-list">
								<span id="add-cate">세부사항:</span><input type="text" class="detail" id="detail" name="detail">
							</div>
							<div id="inner-list">
								<button class="btn btn-primary" type="submit" style="margin-left: 20px;">저장</button>
							</div>	
	                	</div>
	                </div>
	            	</form>
	       	 	</div> 
	       	 	</div>
	    	</div>
		</div>
	</div>	

<!-- JavaScript Bundle with Popper -->
<script src="/neulbom/asset/js/bootstrap.js"></script>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=015fae8b95c2d0f2c4d727e44d11a138"></script>
<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
<script>
	
function sample4_execDaumPostcode() {
    new daum.Postcode({
        oncomplete: function(data) {
            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

            // 도로명 주소의 노출 규칙에 따라 주소를 표시한다.
            // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
            var roadAddr = data.roadAddress; // 도로명 주소 변수
            var extraRoadAddr = ''; // 참고 항목 변수

            // 법정동명이 있을 경우 추가한다. (법정리는 제외)
            // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
            if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                extraRoadAddr += data.bname;
            }
            // 건물명이 있고, 공동주택일 경우 추가한다.
            if(data.buildingName !== '' && data.apartment === 'Y'){
               extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
            }

            // 우편번호와 주소 정보를 해당 필드에 넣는다.
            document.getElementById('sample4_postcode').value = data.zonecode;
            document.getElementById("sample4_roadAddress").value = roadAddr;
        }
    }).open();
}
	
	function sample4_execDaumPostcode() {
        new daum.Postcode({
            oncomplete: function(data) {
                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                // 도로명 주소의 노출 규칙에 따라 주소를 표시한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var roadAddr = data.roadAddress; // 도로명 주소 변수
                var extraRoadAddr = ''; // 참고 항목 변수

                // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                    extraRoadAddr += data.bname;
                }
                // 건물명이 있고, 공동주택일 경우 추가한다.
                if(data.buildingName !== '' && data.apartment === 'Y'){
                   extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                }

                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                document.getElementById('sample4_postcode').value = data.zonecode;
                document.getElementById("sample4_roadAddress").value = roadAddr;
            }
        }).open();
    }
    
    /*달력 한국어 변환 */
    var fp = flatpickr(document.getElementById("birth"), {
    	'monthSelectorType' : 'dropdown',
    	"locale": "ko" 
    });
    
    /* 생년월일 출력 */
    function convertDateFormat(event) {
        const input = event.target;
        const originalValue = input.value;

        // 입력된 값을 하이픈(-)을 제거하여 숫자만 남기고, 가장 앞 두자리 에서부터 4자리만 선택합니다.
        const year = originalValue.replace(/\D/g, '').slice(2, 4);

        // 2자리씩 그룹으로 묶어 YY-MM-DD 형식으로 변환합니다.
        const formattedValue = year + originalValue.slice(5, 7) + originalValue.slice(8, 10);

        // 변환된 값을 입력 필드에 설정합니다.
        input.value = formattedValue;
    }
    
   /* 주민번호 뒷자리 마스킹 처리 */
function isNumeric(event) {
  const keyCode = event.which ? event.which : event.keyCode;
  const validCharacters = /^[0-9]*$/; // 숫자만을 매칭하는 정규식
  const key = String.fromCharCode(keyCode);
  if (!validCharacters.test(key)) {
    // 입력된 키가 숫자가 아닌 경우 입력을 방지
    event.preventDefault();
    return false;
  }

  return true;
}




    /* 주민번호로 숫자만 받아옴 */
  function isNumberKey(event) {
  		const charCode = event.which ? event.which : event.keyCode;
  		if (charCode < 48 || charCode > 57) {
    		event.preventDefault();
    		return false;
  		}
  	return true;
	}
   
  
    
    /* 전화번호로 숫자만 받아옴 */
   function isNumKey(event) {
    if (event.key < "0" || event.key > "9") {
        event.preventDefault();
        return false;
    }
    return true;
}
    
    
    /* 이름에 한글만 입력하는 함수 */
  function checkReg(event) {
  //const regExp = /[^0-9a-zA-Z]/g; // 숫자와 영문자만 허용
  const regExp = /[^ㄱ-ㅎ|가-힣]/g; // 한글만 허용
  const del = event.target;
  if (regExp.test(del.value)) {
	  alert('한글만 입력해주세요.');
      del.value = del.value.replace(regExp, '');
  }
}; 

/* 아이디 유효성 체크 */
function validateId() {
    const userIdInput = document.getElementById('userId');
    const userId = userIdInput.value;

    const regex = /^(?=.*[a-zA-Z])(?=.*\d)[a-zA-Z\d]{4,12}$/;
    // 정규식: 영문, 숫자 최소 1개 포함 4~12자 이내

      if (regex.test(userId)) {
    	  const childWindow = window.open("idcheck.do?userId=" + encodeURIComponent(userId), '_blank', "width=500,height=300,left=1000,top=200"); // 새 창을 여는 URL을 지정

          // 자식 창이 로드되었을 때 실행되는 이벤트 리스너
          childWindow.addEventListener("load", function() {
              childWindow.document.getElementById("confirmButton").addEventListener("click", function() {
                  userIdInput.readOnly = true; // userId 입력란을 읽기 전용으로 변경
                  childWindow.close(); // 자식 창 닫기
              });
          });
    } else {
        alert("아이디는 영문, 숫자 최소 1개 포함 4~12자 이내로 입력해주세요.");
        userIdInput.value = "";
    }
}
function checkPasswordValidity() {
	  const passwordField = document.getElementById("passwordField");
	  const password = passwordField.value;
	  
	  const regex = /^(?=.*[a-zA-Z])(?=.*\d)(?=.*[@$!%*#?&])[a-zA-Z\d@$!%*#?&]{6,20}$/;
	  
	  if (regex.test(password)) {
	    document.getElementById("passwordValidityText").textContent = "비밀번호 형식이 유효합니다.";
	    document.getElementById("passwordValidityText").style.color = "blue";
	  } else {
	    document.getElementById("passwordValidityText").textContent = "비밀번호는 영문, 숫자, 특수문자를 최소 1개 이상 포함하고 6자~20자 이내여야 합니다.";
	    document.getElementById("passwordValidityText").style.color = "tomato";
	  }
	}
</script>
</body>
</html>