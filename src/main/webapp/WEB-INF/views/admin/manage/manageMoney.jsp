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

	@font-face {
    font-family: 'LINESeedKR-Bd';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_11-01@1.0/LINESeedKR-Bd.woff2') format('woff2');
    font-weight: 700;
    font-style: normal;
}

	.chart-area {
		width: 1000px;
		height: 320px;
		
	}
	
	#myChartPie{
		margin-bottom: 10px;	
	}
	
	#pie-chart {
		display: inline-block;
		float: left;
		margin-right: 20px;
		width: 250px; 
		height: 300px;
		
		background-color: #F8F8F8;
		border-radius: 10px;
	}
	
	.pie-chart-data-default {
		color: #666;
	}
	
	.pie-chart-data {
		font-weight: bold;
		font-size: 12px;
		text-align: center;
	}
	
	#line-chart {
		width: 700px; 
		height: 300px;
		float: left;
		padding-left: 10px;
		padding-right: 10px;
		background-color: #F8F8F8;
		border-radius: 10px;
	}
	
	.table-area {
		width: 975px;
		height: 370px;
		background-color: #F8F8F8;
		border-radius: 10px;
		padding: 15px 0 15px 0; 
	}
	
	#table-title{
		text-align: center;
	}

	#table-title > span{
		font-family: 'LINESeedKR-Bd';
		font-size: 22px;
		color: #666;
		font-weight: bold;
	}
	
	.scrollable {
		clear: both;
		width: 950px;
		height: 320px;
		display: block;
		overflow: auto;
		overflow-x: hidden;
		padding: 10px;

	}

	#latestSpendList-table {
		width: 850px;
		text-align: center;
		font-size: 14px;
		white-space: nowrap;
		margin: auto auto;
		margin-bottom: 20px;
	}

	.latestSpendList-total {
		font-weight: bold;
	}
	
	
	
	
</style>
</head>
<body>


   <div class="main">
    <%@ include file="/WEB-INF/views/inc/adSidemenu.jsp" %>
    <div class="content-box">
        <div id="inner-box">
            <div class="semititle">
                <div class="selected_menu" onclick="location.href='/neulbom/admin/manage/manageMoney.do';">
                    <span id="selected_menu_text">재무관리</span>
                </div><!-- selected_menu -->
            </div><!-- semi_title -->
            
            
            <div class="main-box">
            <div class="chart-area">
			<div id="pie-chart">
				<canvas id="myChartPie"></canvas>
				<div class="pie-chart-data">
					<span class="pie-chart-data-default">요양원 입주자:</span>&nbsp;&nbsp;&nbsp;&nbsp;${centerCount} <span class="pie-chart-data-default">명</span>
				</div>
				<div class="pie-chart-data">
					<span class="pie-chart-data-default">실버타운 입주자:</span>&nbsp;&nbsp;&nbsp;&nbsp;${silverCount} <span class="pie-chart-data-default">명</span>
				</div>
			</div>
			<div id="line-chart">
				<!--차트가 그려질 부분-->
				<canvas id="myChart"></canvas>
			</div>
			</div>
			
			<div class="table-area">
	        <div id="table-title">
	        	<span>최근 지출 내역</span>
	        </div>
	        
	        <div class="scrollable">
	        
	        
            <table id="latestSpendList-table" class="table table-hover">
           	<colgroup>
					<col width=10%>
					<col width=20%>
					<col width=25%>
					<col width=25%>
					<col width=20%>
			</colgroup>
			<thead>
				<tr>
					<th>번호</th>
					<th>날짜</th>
					<th>지출항목</th>
					<th>지출금액</th>
					<th>분류</th>
				</tr>
			<tbody>
				<c:forEach items="${latestSpendList}" var="spendDto">
				<tr>
					<td>${spendDto.rownum}</td>
					<td>${spendDto.sdate}</td>
					<td>${spendDto.title}</td>
					<td>${spendDto.money}</td>
					<td>${spendDto.category}</td>
				</tr>
				</c:forEach>
			</tbody>
			<tfoot>
				<tr>
					<td colspan="3" class="latestSpendList-total">합계</td>
					<td class="latestSpendList-total">${spendSum}</td>
					<td></td>
				</tr>
			</tfoot>
			</table>
			</div>
			</div>
		
			
			
					
            </div><!-- main-box -->
        </div><!-- inner-box -->
    </div><!-- content-box -->
</div>

<!-- JavaScript Bundle with Popper -->
<script src="https://cdn.jsdelivr.net/npm/chart.js@2.8.0"></script>
<script src="https://cdn.jsdelivr.net/npm/chart.js@4.3.0/dist/chart.umd.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
<script>
	Chart.defaults.scales.linear.min = 150000000;
	Chart.defaults.scales.linear.max = 170000000;
	Chart.defaults.scales.linear.stepSize = 5;

	var context = document
	.getElementById('myChartPie')
	.getContext('2d');
	var myChartPie = new Chart(context, {
	type: 'pie', // 차트의 형태
	data: { // 차트에 들어갈 데이터
	    labels: [
	        //x 축
	        '실버타운', '요양원'
	    ],
	    datasets: [
	        { //데이터
	            label: '입주자 현황', //차트 제목
	            fill: false, // line 형태일 때, 선 안쪽을 채우는지 안채우는지
	            data: [
	              	${silverCount}, ${centerCount}
	            ],
	            backgroundColor: [
	                //색상
	/*                       'rgba(255, 99, 132, 0.2)',
	                'rgba(54, 162, 235, 0.2)',
	                'rgba(255, 206, 86, 0.2)',
	                'rgba(75, 192, 192, 0.2)', */
	                'rgba(153, 102, 255, 0.2)',
	                'rgba(255, 159, 64, 0.2)'
	            ],
	            borderColor: [
	                //경계선 색상
	/*                       'rgba(255, 99, 132, 1)',
	                'rgba(54, 162, 235, 1)',
	                'rgba(255, 206, 86, 1)',
	                'rgba(75, 192, 192, 1)', */
	                'rgba(153, 102, 255, 1)',
	                'rgba(255, 159, 64, 1)'
	            ],
	            borderWidth: 1 //경계선 굵기
	        }
	    ]
	},
	options: {
		    plugins: {
		    	
		    	legend: {
		    		position: 'bottom'
		    	},
		    	
		        title: {
		          display: true,
		          text: '입주자 현황',
		          fullSize: true,
		          
		          font: {
		        	  size: 20
		          }
		        }
		      }  
		  
	}
	});
	
	

	var context = document
      .getElementById('myChart')
      .getContext('2d');
  	
	var myChart = new Chart(context, {
      type: 'line', // 차트의 형태
      data: { // 차트에 들어갈 데이터
          labels: [
              //x 축
              '2023.01', '2023.02', '2023.03', '2023.04', '2023.05'
          ],
          datasets: [
              { //데이터
                  label: '지출액', //차트 제목
                  fill: false, // line 형태일 때, 선 안쪽을 채우는지 안채우는지
                  data: [
                	  160016425, 163007189, 160017454, 160611850,160621990
                  ],
                  backgroundColor: 'rgba(255, 99, 132, 0.2)',
                  borderColor: 'rgba(255, 159, 64, 0.2)',
                  borderWidth: 3 //경계선 굵기
              }
          ]
      },
      options: {
          maintainAspectRatio: false,
          plugins: {
        	  
        	  legend: {
		    		position: 'bottom'
		    	},
        	  
              title: {
                  display: true,
                  text: '월별 지출 현황',
                  
                  font: {
		        	  size: 20
		          },
              },

          },
		
      }

  });
	  

	  
</script>
</body>
</html>