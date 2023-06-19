package com.test.neulbom.admin.account;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.neulbom.admin.repository.AdminDAO;
import com.test.neulbom.admin.repository.AdminDTO;

@WebServlet("/admin/account/adminlist.do")
public class AdminList extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// Qna.java
		
		//HttpSession session = req.getSession();
		
		String page = req.getParameter("page");
		
		// 페이징
		int nowPage = 0; // 현재 페이지 번호
		int totalCount = 0; // 총 게시물 수
		int pageSize = 10; // 한페이지에서 출력할 게시물 수
		int totalPage = 0; // 총 페이지 수
		int begin = 0; //
		int end = 0; //
		int n = 0; //
		int loop = 0; //
		int blockSize = 10; // 한번에 보여질 페이지 개수
		
		if (page == null || page == "")
			nowPage = 1;
		else
			nowPage = Integer.parseInt(page);
		
		// nowPage > 현재 보려는 페이지 번호
		// board.do?page=1 > where rnum between 1 and 10
		// board.do?page=1 > where rnum between 11 and 20
		// board.do?page=1 > where rnum between 21 and 30
		begin = ((nowPage - 1) * pageSize) + 1;
		end = begin + pageSize - 1;
		
		// 2가지 용도로 호출
		// 1. 일반 목록 보기
		// 2. 검색 결과 보기
		
		// 1. 
		//검색
		String column = req.getParameter("column");
		String word = req.getParameter("word");
		String search = "n"; // 검색중(O,X)
		
		
		HashMap<String, String> map = new HashMap<String, String>();
		
		
		if ((column == null && word == null) ||
				(column.endsWith("") && word.equals(""))) { 
			search = "n"; 
		} else { 
			search ="y"; 
		}
		
		
		//검색
		map.put("column", column); 
		map.put("word", word);
		map.put("search", search);
		
		
		map.put("begin", begin + "");
		map.put("end", end + "");
		
		AdminDAO dao = new AdminDAO();
		
		
		
		List<AdminDTO> alist = dao.alist(map);
		
		for (AdminDTO adto : alist) {
			
			//날짜 출력(기준: 당일)
			//- 년월일
			//- 시분초
			//System.out.println(dto.getRegdate());
			//dto.setRegdate(dto.getRegdate().substring(0, 10));
			
			String subject = adto.getName();
			
			//태그 이스케이프
			subject = subject.replace("<", "&lt;")
							 .replace(">", "&gt;");
			
			//제목에서 검색 중.. 검색어를 강조
			if (search.equals("y") && column.equals("name")) {
				subject = subject.replace(word, "<span>" + word + "</span>");
			}
			
			adto.setName(subject);
			
		}
		
		
		
		StringBuilder sb = new StringBuilder();
		
		// 페이징 작업
		// 총 게시물
		totalCount = dao.getTotalCount(map);
		totalPage = (int) Math.ceil((double) totalCount / pageSize);
		
		
		loop = 1;   //루프 변수
		n = ((nowPage -1) / blockSize) * blockSize + 1; //페이지 번호
		
		//이전 10페이지
//		if(n == 1) {
//			sb.append(String.format("<nav aria-label=\"Page navigation example \"><ul class=\"pagination justify-content-center\"><li class=\"page-item\"><a class=\"page-link\" href=\"#\" aria-label=\"Previous\"><span aria-hidden=\"true\">&laquo;</span></a></li>"));         
//		}else {
//			sb.append(String.format("<nav aria-label=\"Page navigation example \"><ul class=\"pagination justify-content-center\"><li class=\"page-item\"><a class=\"page-link\" href=\"/neulbom/admin/account/adminlist.do?page=%d\" aria-label=\"Previous\"><span aria-hidden=\"true\">&laquo;</span></a></li>", n-1));                  
//		}
//		
//		while (!(loop > blockSize || n > totalPage)) {
//			
//			if (n == nowPage) {
//				sb.append(String.format(" <li class=\"page-item\"><a class=\"page-link\" href=\"#\" style='color:tomato;'>%d</a></li> ", n));            
//			} else {
//				sb.append(String.format(" <li class=\"page-item\"><a class=\"page-link\" href=\"/neulbom/admin/account/adminlist.do?page=%d\">%d</a></li> ", n, n));         
//			}
//			
//			loop++;
//			n++;
//		}
//		
//		//다음 10페이지
//		if(n > totalPage) {
//			sb.append(String.format("<li class=\"page-item\"><a class=\"page-link\" href=\"#\" aria-label=\"Next\"><span aria-hidden=\"true\">&raquo;</span></a></li></ul></nav>"));
//		}else {
//			sb.append(String.format("<li class=\"page-item\"><a class=\"page-link\" href=\"/neulbom/admin/account/adminlist.do?page=%d\" aria-label=\"Next\"><span aria-hidden=\"true\">&raquo;</span></a></li></ul></nav>", n));         
//		}
		
		
		if(search.equals("y")) {
	         if(n == 1) {
	            sb.append(String.format("<nav aria-label=\"Page navigation example \"><ul class=\"pagination justify-content-center\"><li class=\"page-item\"><a class=\"page-link\" href=\"#\" aria-label=\"Previous\"><span aria-hidden=\"true\">&laquo;</span></a></li>"));         
	         }else {
	            sb.append(String.format("<nav aria-label=\"Page navigation example \"><ul class=\"pagination justify-content-center\"><li class=\"page-item\"><a class=\"page-link\" href=\"/neulbom/admin/account/adminlist.do?column=%s&word=%s?page=%d\" aria-label=\"Previous\"><span aria-hidden=\"true\">&laquo;</span></a></li>", column, word, n-1));                  
	         }
	         
	         while (!(loop > blockSize || n > totalPage)) {
	            
	            if (n == nowPage) {
	               sb.append(String.format(" <li class=\"page-item\"><a class=\"page-link\" href=\"#\" style='color:tomato;'>%d</a></li> ", n));            
	            } else {
	               sb.append(String.format(" <li class=\"page-item\"><a class=\"page-link\" href=\"/neulbom/admin/account/adminlist.do?column=%s&word=%s&page=%d\">%d</a></li> ", column, word, n, n));         
	            }
	            
	            loop++;
	            n++;
	         }
	         
	         //다음 10페이지
	         if(n > totalPage) {
	            sb.append(String.format("<li class=\"page-item\"><a class=\"page-link\" href=\"#\" aria-label=\"Next\"><span aria-hidden=\"true\">&raquo;</span></a></li></ul></nav>"));
	         }else {
	            sb.append(String.format("<li class=\"page-item\"><a class=\"page-link\" href=\"/neulbom/admin/account/adminlist.do?column=%s&word=%s&page=%d\" aria-label=\"Next\"><span aria-hidden=\"true\">&raquo;</span></a></li></ul></nav>", column, word, n));         
	         }
	      } else {
	         
	         //이전 10페이지
	         if(n == 1) {
	            sb.append(String.format("<nav aria-label=\"Page navigation example \"><ul class=\"pagination justify-content-center\"><li class=\"page-item\"><a class=\"page-link\" href=\"#\" aria-label=\"Previous\"><span aria-hidden=\"true\">&laquo;</span></a></li>"));         
	         }else {
	            sb.append(String.format("<nav aria-label=\"Page navigation example \"><ul class=\"pagination justify-content-center\"><li class=\"page-item\"><a class=\"page-link\" href=\"/neulbom/admin/account/adminlist.do?page=%d\" aria-label=\"Previous\"><span aria-hidden=\"true\">&laquo;</span></a></li>", n-1));                  
	         }
	         
	         while (!(loop > blockSize || n > totalPage)) {
	            
	            if (n == nowPage) {
	               sb.append(String.format(" <li class=\"page-item\"><a class=\"page-link\" href=\"#\" style='color:tomato;'>%d</a></li> ", n));            
	            } else {
	               sb.append(String.format(" <li class=\"page-item\"><a class=\"page-link\" href=\"/neulbom/admin/account/adminlist.do?page=%d\">%d</a></li> ", n, n));         
	            }
	            
	            loop++;
	            n++;
	         }
	         
	         //다음 10페이지
	         if(n > totalPage) {
	            sb.append(String.format("<li class=\"page-item\"><a class=\"page-link\" href=\"#\" aria-label=\"Next\"><span aria-hidden=\"true\">&raquo;</span></a></li></ul></nav>"));
	         }else {
	            sb.append(String.format("<li class=\"page-item\"><a class=\"page-link\" href=\"/neulbom/admin/account/adminlist.do?page=%d\" aria-label=\"Next\"><span aria-hidden=\"true\">&raquo;</span></a></li></ul></nav>", n));         
	         }
	      }
		
		req.setAttribute("map", map);
		req.setAttribute("totalCount", totalCount);
		req.setAttribute("totalPage", totalPage);
		req.setAttribute("nowPage", nowPage);
		req.setAttribute("alist", alist);
		req.setAttribute("pagination", sb);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/admin/account/adminlist.jsp");
		dispatcher.forward(req, resp);
	}
	
	
}

/*WebServlet("/admin/account/adminlist.do")
	public class AdminList extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		test(req, resp);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		test(req, resp);
		
	}
	
	private void test(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//Board.java
		
		//1. DB 작업 > select
		//2. 결과 반환
		
		HttpSession session = req.getSession();
		
		//읽음 제어
		session.setAttribute("read", "n");
		
		
		//?page=5 > null
		//?page=5&column=&word= > ""
		//?page=5&column=subject&word=자바 > "subject"
		
		//req.getParameter("column")
		
		
		
		
		
		//2가지 용도로 호출
		//1. 일반 목록 보기 > board.do
		//2. 검색 결과 보기 > board.do?column=subject&word=test
		
		String column = req.getParameter("column");
		String word = req.getParameter("word");
		String tab = req.getParameter("tab");
		String search = "n"; //검색중(O,X)
		
		HashMap<String, String> map
			= new HashMap<String,String>();
				
		if ((column == null && word == null)
				 || (column.endsWith("") && word.equals(""))) {
			search = "n";
		} else {
			search = "y";
		}
		
		map.put("column", column);
		map.put("word", word);
		map.put("search", search);
		map.put("tab", tab);
		
		
		AdminDAO adao = new AdminDAO();
		ResiDAO rdao = new ResiDAO();
		
		List<AdminDTO> alist = adao.alist(map);
				
		//데이터 조작
		for (AdminDTO adto : alist) {
			
			//날짜 출력(기준: 당일)
			//- 년월일
			//- 시분초
			//System.out.println(dto.getRegdate());
			//dto.setRegdate(dto.getRegdate().substring(0, 10));
			
			String subject = adto.getName();
			
			//태그 이스케이프
			subject = subject.replace("<", "&lt;")
							 .replace(">", "&gt;");
			
			//제목에서 검색 중.. 검색어를 강조
			if (search.equals("y") && column.equals("name")) {
				subject = subject.replace(word, "<span>" + word + "</span>");
			}
			
			adto.setName(subject);
			
		}
		
		req.setAttribute("alist", alist);
		req.setAttribute("map", map);
		//JSP 페이지 알 수 있는 정보
		//1. req를 통해서 전달된 정보
		//2. session/cookie
		//3. application
	
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/admin/account/adminlist.jsp");
		dispatcher.forward(req, resp);
	}



} */