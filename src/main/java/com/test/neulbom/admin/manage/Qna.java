package com.test.neulbom.admin.manage;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.test.neulbom.admin.manage.repository.ManageDAO;
import com.test.neulbom.admin.manage.repository.QnaDTO;

@WebServlet("/admin/manage/qna.do")
public class Qna extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		HttpSession session = req.getSession();

		// jsp 에서 page 받아오기
		String page = req.getParameter("page");

		// 페이징
		int nowPage = 0; // 현재 페이지 번호
		int totalCount = 0; // 총 게시물 수
		int pageSize = 10; // 한페이지에서 출력할 게시물 수
		int totalPage = 0; // 총 페이지 수
		int begin = 0; // 시작 숫자
		int end = 0; // 끝 숫자
		int n = 0; //
		int loop = 0; //
		int blockSize = 10; // 한번에 보여질 페이지 개수

		// page값이 없으면 현재 페이지 = 1 나머지는 현재 페이지 = 페이지값
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

		HashMap<String, String> map = new HashMap<String, String>();

		map.put("begin", begin + "");
		map.put("end", end + "");

		ManageDAO dao = new ManageDAO();
		List<QnaDTO> list = dao.getQna(map);
		
		StringBuilder sb = new StringBuilder();

		int size = list.size();
		
		// 페이징 작업
		// 총 게시물
		totalCount = dao.getTotalCount(map, size, "tblQna");
		totalPage = (int) Math.ceil((double) totalCount / pageSize);
		
		loop = 1;	//루프 변수
		n = ((nowPage -1) / blockSize) * blockSize + 1; //페이지 번호
		
		//이전 10페이지
				// "<<" 아이콘 else일때 a태그 href 경로 수정
				if(n == 1) {
					sb.append(String.format("<nav aria-label=\"Page navigation example \"><ul class=\"pagination justify-content-center\"><li class=\"page-item\"><a class=\"page-link\" href=\"#\" aria-label=\"Previous\"><span aria-hidden=\"true\">&laquo;</span></a></li>"));			
				}else {
					sb.append(String.format("<nav aria-label=\"Page navigation example \"><ul class=\"pagination justify-content-center\"><li class=\"page-item\"><a class=\"page-link\" href=\"/neulbom/admin/manage/qna.do?page=%d\" aria-label=\"Previous\"><span aria-hidden=\"true\">&laquo;</span></a></li>", n-1));						
				}
				
				//안에 있는 숫자들 ex) << "1 2 3" >>
				//else일때 a태그 href 경로 수정
				while (!(loop > blockSize || n > totalPage)) {
					
					if (n == nowPage) {
						sb.append(String.format(" <li class=\"page-item\"><a class=\"page-link\" href=\"#\" style='color:tomato;'>%d</a></li> ", n));				
					} else {
						sb.append(String.format(" <li class=\"page-item\"><a class=\"page-link\" href=\"/neulbom/admin/manage/qna.do?page=%d\">%d</a></li> ", n, n));			
					}
					
					loop++;
					n++;
				}
				
				//다음 10페이지
				// ">>" 아이콘 else일때 a태그 href 경로 수정
				if(n > totalPage) {
					sb.append(String.format("<li class=\"page-item\"><a class=\"page-link\" href=\"#\" aria-label=\"Next\"><span aria-hidden=\"true\">&raquo;</span></a></li></ul></nav>"));
				}else {
					sb.append(String.format("<li class=\"page-item\"><a class=\"page-link\" href=\"/neulbom/admin/manage/qna.do?page=%d\" aria-label=\"Next\"><span aria-hidden=\"true\">&raquo;</span></a></li></ul></nav>", n));			
				}


		req.setAttribute("map", map);
		
		req.setAttribute("totalCount", totalCount);
		req.setAttribute("totalPage", totalPage);
		req.setAttribute("nowPage", nowPage);
		req.setAttribute("pagination", sb);

		req.setAttribute("list", list);

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/admin/manage/qna.jsp");
		dispatcher.forward(req, resp);

	}

}
