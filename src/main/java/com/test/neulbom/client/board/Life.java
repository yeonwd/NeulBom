package com.test.neulbom.client.board;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.neulbom.client.repository.LifeDTO;
import com.test.neulbom.client.repository.NoticeDTO;
import com.test.neulbom.client.repository.LifeDAO;

@WebServlet("/client/board/life.do")
public class Life extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//notice.java
		 
		String searchType = req.getParameter("searchType");
		String keyword = req.getParameter("keyword");
		
		 LifeDAO dao = new LifeDAO();
		 

			int nowPage = 0; // 현재 보려는 페이지 번호
			int totalCount = 0; // 총 게시물 수
			int pageSize = 10; // 한 페이지 당 출력할 게시물 수
			int totalPage = 0; // 총 페이지 수
			int begin = 0; // 한 페이지 안에 보여줄 시작 rnum
			int end = 0; // 끝 rnum
			int n = 0;
			int loop = 0;
			int blockSize = 3; // 한 번에 보여질 페이지 개수(10개씩 끊어서 보여줄 것임)

			String page = req.getParameter("page"); // 값이 있을수도, 없을수도 있음

			if (page == null || page == "")
				nowPage = 1;
			else
				nowPage = Integer.parseInt(page);

			begin = ((nowPage - 1) * pageSize) + 1;
			end = begin + pageSize - 1;

			HashMap<String, String> map = new HashMap<String, String>();

			map.put("searchType", searchType);
			map.put("keyword", keyword);
			map.put("begin", begin + "");
			map.put("end", end + "");

			List<LifeDTO> list = null;

			if (searchType == null && keyword == null) {
				list = dao.list(map);
			} else {
				list = dao.search(searchType, keyword);
			}
			
			
			// 페이징

			StringBuilder sb = new StringBuilder();

			for (int i = 1; i <= totalPage; i++) {
				if (i == nowPage) {
					sb.append(String.format(" <a href=\"#!\" style='color: tomato;'>%d</a> ", i));
				} else {
					sb.append(String.format(" <a href=\"/neulbom/client/board/life.do?page=%d\">%d</a> ", i, i));
				}
			}
			
			if (searchType == null && keyword == null) {
				totalCount = dao.getTotalCount(map);
				totalPage = (int) Math.ceil((double) totalCount / pageSize);
			} else {
				totalCount = dao.getTotalCount2(map);
				totalPage = (int) Math.ceil((double) totalCount / pageSize);
			}
			

			
			loop = 1; // 루프 변수(10바퀴)
			n = ((nowPage - 1) / blockSize) * blockSize + 1; // 페이지 번호

			//이전 10페이지
					// "<<" 아이콘 else일때 a태그 href 경로 수정
			if(!(searchType == null && keyword == null)) {
		         if(n == 1) {
		            sb.append(String.format("<nav aria-label=\"Page navigation example \"><ul class=\"pagination justify-content-center\"><li class=\"page-item\"><a class=\"page-link\" href=\"#\" aria-label=\"Previous\"><span aria-hidden=\"true\">&laquo;</span></a></li>"));         
		         }else {
		            sb.append(String.format("<nav aria-label=\"Page navigation example \"><ul class=\"pagination justify-content-center\"><li class=\"page-item\"><a class=\"page-link\" href=\"/neulbom/client/board/life.do?searchType=%s&keyword=%s?page=%d\" aria-label=\"Previous\"><span aria-hidden=\"true\">&laquo;</span></a></li>", searchType, keyword, n-1));                  
		         }
		         
		         while (!(loop > blockSize || n > totalPage)) {
		            
		            if (n == nowPage) {
		               sb.append(String.format(" <li class=\"page-item\"><a class=\"page-link\" href=\"#\" style='color:tomato;'>%d</a></li> ", n));            
		            } else {
		               sb.append(String.format(" <li class=\"page-item\"><a class=\"page-link\" href=\"/neulbom/client/board/life.do?searchType=%s&keyword=%s&page=%d\">%d</a></li> ", searchType, keyword, n, n));         
		            }
		            
		            loop++;
		            n++;
		         }
		         
		         //다음 10페이지
		         if(n > totalPage) {
		            sb.append(String.format("<li class=\"page-item\"><a class=\"page-link\" href=\"#\" aria-label=\"Next\"><span aria-hidden=\"true\">&raquo;</span></a></li></ul></nav>"));
		         }else {
		            sb.append(String.format("<li class=\"page-item\"><a class=\"page-link\" href=\"/neulbom/client/board/life.do?searchType=%s&keyword=%s&page=%d\" aria-label=\"Next\"><span aria-hidden=\"true\">&raquo;</span></a></li></ul></nav>", searchType, keyword, n));         
		         }
		      } else {
		         
		         //이전 10페이지
		         if(n == 1) {
		            sb.append(String.format("<nav aria-label=\"Page navigation example \"><ul class=\"pagination justify-content-center\"><li class=\"page-item\"><a class=\"page-link\" href=\"#\" aria-label=\"Previous\"><span aria-hidden=\"true\">&laquo;</span></a></li>"));         
		         }else {
		            sb.append(String.format("<nav aria-label=\"Page navigation example \"><ul class=\"pagination justify-content-center\"><li class=\"page-item\"><a class=\"page-link\" href=\"/neulbom/client/board/life.do?page=%d\" aria-label=\"Previous\"><span aria-hidden=\"true\">&laquo;</span></a></li>", n-1));                  
		         }
		         
		         while (!(loop > blockSize || n > totalPage)) {
		            
		            if (n == nowPage) {
		               sb.append(String.format(" <li class=\"page-item\"><a class=\"page-link\" href=\"#\" style='color:tomato;'>%d</a></li> ", n));            
		            } else {
		               sb.append(String.format(" <li class=\"page-item\"><a class=\"page-link\" href=\"/neulbom/client/board/life.do?page=%d\">%d</a></li> ", n, n));         
		            }
		            
		            loop++;
		            n++;
		         }
		         
		         //다음 10페이지
		         if(n > totalPage) {
		            sb.append(String.format("<li class=\"page-item\"><a class=\"page-link\" href=\"#\" aria-label=\"Next\"><span aria-hidden=\"true\">&raquo;</span></a></li></ul></nav>"));
		         }else {
		            sb.append(String.format("<li class=\"page-item\"><a class=\"page-link\" href=\"/neulbom/client/board/life.do?page=%d\" aria-label=\"Next\"><span aria-hidden=\"true\">&raquo;</span></a></li></ul></nav>", n));         
		         }
		      }
					//넘겨야 되는 것들
					req.setAttribute("map", map);
					req.setAttribute("totalCount", totalCount);
					req.setAttribute("totalPage", totalPage);
					req.setAttribute("nowPage", nowPage);
					req.setAttribute("list", list);
					req.setAttribute("pagination", sb);

		 

		 
		 req.setAttribute("list", list);
		 req.setAttribute("searchType", searchType);
		 req.setAttribute("keyword", keyword);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/client/board/life.jsp");
		dispatcher.forward(req, resp);
		
		
	}

}
