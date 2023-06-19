package com.test.neulbom.client.mypage;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.test.neulbom.client.repository.MyProgramDTO;
import com.test.neulbom.client.repository.ProgramDAO;
import com.test.neulbom.client.repository.ProtectDTO;
import com.test.neulbom.client.repository.ResiDTO;

@WebServlet("/client/mypage/myprogram.do")
public class MyProgram extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// MyProgram.java

		HttpSession session = req.getSession();
		
		req.setCharacterEncoding("utf-8");


		// 페이징
		int nowPage = 0; // 현재 보려는 페이지
		int totalCount = 0; // 총 게시물 수
		int pageSize = 10; // 한 페이지 당 보여줄 게시물 수
		int totalPage = 0; // 총 페이지 수
		int begin = 0; // 시작 글
		int end = 0; // 끝 글
		int n = 0;
		int loop = 0; // 루프
		int blockSize = 10; // 한 번에 보여줄 페이지 수

		// 페이지 넘길 때
		// free.do?page=1 > 기본값
		// free.do?page=2 > 페이지 넘길 시
		String page = req.getParameter("page");

		if (page == null || page == "") {
			nowPage = 1; // 페이지가 지정되지 않은 경우 첫 번째 페이지를 보여줌
		} else {
			nowPage = Integer.parseInt(page); // 그렇지 않은 경우 page를 정수로 변환해서 저장
		}

		// nowPage > 현재 보려는 페이지 번호
		// free.do?page=1 > where rnum between 1 and 10
		// free.do?page=2 > where rnum between 11 and 20
		// between begin and end
		begin = ((nowPage - 1) * pageSize) + 1;
		end = begin + pageSize - 1;

		// 2가지 용도로 호출
		// 1. 일반 목록 보기
		// 2. 검색 결과 보기

		// 1.
		// 검색
		String start_date = req.getParameter("start_date");
		String end_date = req.getParameter("end_date");
		String search = "n"; // 검색중(O,X)

		HashMap<String, String> map = new HashMap<String, String>();

		if ((start_date == null && end_date == null) ||
	              (start_date.endsWith("") && end_date.equals(""))) { 
	         search = "n"; 
	      } else { 
	         search ="y"; 
	      }

		// 검색
		map.put("start_date", start_date); 
        map.put("end_date", end_date);
        map.put("search", search);


		map.put("begin", begin + "");
		map.put("end", end + "");

		// 프로그램 글 가져오기
		MyProgramDTO dto = new MyProgramDTO();
		ProgramDAO dao = new ProgramDAO();


		StringBuilder sb = new StringBuilder();
				
				
		
		//계정 가져오기
		ProtectDTO prot = new ProtectDTO();
		ResiDTO resi = new ResiDTO();
		
		String protect_seq = (String)session.getAttribute("protect_seq");
		String resi_seq = (String)session.getAttribute("resi_seq");
		
		List<MyProgramDTO> myplist = null;
		
		if(protect_seq != null) {
			prot = dao.getProtect(prot, protect_seq);
			myplist = dao.myplist(map, prot.getResi_seq());
		} else {
			resi = dao.getResident(resi, resi_seq);
			myplist = dao.myplist(map, resi.getResi_seq());
		}
		

		// 페이징 작업
		// 총 게시물
		totalCount = dao.getProgramTotalCount(map, resi_seq);
		totalPage = (int) Math.ceil((double) totalCount / pageSize);

		loop = 1; // 루프 변수
		n = ((nowPage - 1) / blockSize) * blockSize + 1; // 페이지 번호

		// 이전 10페이지 // "<<" 아이콘 else일때 a태그 href 경로 수정
		if(search.equals("y")) {
            if(n == 1) {
               sb.append(String.format("<nav aria-label=\"Page navigation example \"><ul class=\"pagination justify-content-center\"><li class=\"page-item\"><a class=\"page-link\" href=\"#\" aria-label=\"Previous\"><span aria-hidden=\"true\">&laquo;</span></a></li>"));         
            }else {
               sb.append(String.format("<nav aria-label=\"Page navigation example \"><ul class=\"pagination justify-content-center\"><li class=\"page-item\"><a class=\"page-link\" href=\"/neulbom/client/mypage/myprogram.do?resi_seq=%s&start_date=%s&end_date=%s&page=%d\" aria-label=\"Previous\"><span aria-hidden=\"true\">&laquo;</span></a></li>", resi_seq, start_date, end_date, n-1));                  
            }
            
            while (!(loop > blockSize || n > totalPage)) {
               
               if (n == nowPage) {
                  sb.append(String.format(" <li class=\"page-item\"><a class=\"page-link\" href=\"#\" style='color:tomato;'>%d</a></li> ", n));            
               } else {
                  sb.append(String.format(" <li class=\"page-item\"><a class=\"page-link\" href=\"/neulbom/client/mypage/myprogram.do?resi_seq=%s&start_date=%s&end_date=%s&page=%d\">%d</a></li> ", resi_seq, start_date, end_date, n, n));         
               }
               
               loop++;
               n++;
            }
            
            //다음 10페이지
            if(n > totalPage) {
               sb.append(String.format("<li class=\"page-item\"><a class=\"page-link\" href=\"#\" aria-label=\"Next\"><span aria-hidden=\"true\">&raquo;</span></a></li></ul></nav>"));
            }else {
               sb.append(String.format("<li class=\"page-item\"><a class=\"page-link\" href=\"/neulbom/client/mypage/myprogram.do?resi_seq=%s&start_date=%s&end_date=%s&page=%d\" aria-label=\"Next\"><span aria-hidden=\"true\">&raquo;</span></a></li></ul></nav>", resi_seq, start_date, end_date, n));         
            }
         } else {
            
            //이전 10페이지
            if(n == 1) {
               sb.append(String.format("<nav aria-label=\"Page navigation example \"><ul class=\"pagination justify-content-center\"><li class=\"page-item\"><a class=\"page-link\" href=\"#\" aria-label=\"Previous\"><span aria-hidden=\"true\">&laquo;</span></a></li>"));         
            }else {
               sb.append(String.format("<nav aria-label=\"Page navigation example \"><ul class=\"pagination justify-content-center\"><li class=\"page-item\"><a class=\"page-link\" href=\"/neulbom/client/mypage/myprogram.do?resi_seq=%s&page=%d\" aria-label=\"Previous\"><span aria-hidden=\"true\">&laquo;</span></a></li>", resi_seq, n-1));                  
            }
            
            while (!(loop > blockSize || n > totalPage)) {
               
               if (n == nowPage) {
                  sb.append(String.format(" <li class=\"page-item\"><a class=\"page-link\" href=\"#\" style='color:tomato;'>%d</a></li> ", n));            
               } else {
                  sb.append(String.format(" <li class=\"page-item\"><a class=\"page-link\" href=\"/neulbom/client/mypage/myprogram.do?resi_seq=%s&page=%d\">%d</a></li> ", resi_seq, n, n));         
               }
               
               loop++;
               n++;
            }
            
            //다음 10페이지
            if(n > totalPage) {
               sb.append(String.format("<li class=\"page-item\"><a class=\"page-link\" href=\"#\" aria-label=\"Next\"><span aria-hidden=\"true\">&raquo;</span></a></li></ul></nav>"));
            }else {
               sb.append(String.format("<li class=\"page-item\"><a class=\"page-link\" href=\"/neulbom/client/mypage/myprogram.do?resi_seq=%s&page=%d\" aria-label=\"Next\"><span aria-hidden=\"true\">&raquo;</span></a></li></ul></nav>", resi_seq, n));         
            }
         }
		
		//프로그램 취소 마감 날짜 구하기 (현재날짜 -14일)
		Date date = new Date();
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -3);	//현재 날짜 -3
		Date limit = cal.getTime();

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");	//날짜 형식 변경
		String limitDate = dateFormat.format(limit);
		
		

		// 넘겨야 되는 것들
		req.setAttribute("map", map);
		req.setAttribute("totalCount", totalCount);
		req.setAttribute("totalPage", totalPage);
		req.setAttribute("nowPage", nowPage);
		req.setAttribute("myplist", myplist);
		req.setAttribute("pagination", sb);
		req.setAttribute("prot", prot);
		req.setAttribute("resi", resi);
		req.setAttribute("limitDate", limitDate);

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/client/mypage/myprogram.jsp");
		dispatcher.forward(req, resp);

	}

}