package com.test.neulbom.client.community;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.test.neulbom.client.repository.ClientDAO;
import com.test.neulbom.client.repository.CommentDTO;
import com.test.neulbom.client.repository.FreeDTO;

@WebServlet("/client/community/viewfree.do")
public class ViewFree extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//ViewFree.java
		
		String free_seq = req.getParameter("free_seq");
		
		String pageNum = req.getParameter("pageNum");
		
		String column = req.getParameter("column");
		String word = req.getParameter("word");
		String search = req.getParameter("search");
		
		//로그인 계정 가져오기 
		HttpSession session = req.getSession();
		
		ClientDAO dao = new ClientDAO();
		
		
		
		
		//session의 read가 null이거나 n이면 아직 해당 계정이 그 글을 보지 못한 것
		if (session.getAttribute("read") == null || session.getAttribute("read").toString().equals("n")) {
			
			//read 증가 메소드
			dao.increaseReadCount(free_seq);
			//read를 y로 바꿔서 새로고침 시 무한 조회수 증가 방지
			session.setAttribute("read", "y");
			
		}
		
		
		
		FreeDTO dto = new FreeDTO();
		
		
		dto	= dao.fcontent(free_seq);
		String content = dto.getContent();
		
		//HTML 태그 이스케이프 > 꺽쇠 처리 > 엔터 처리 보다 먼저 처리해야함
		content = content.replace("<", "&lt;").replace(">", "&gt;");
		
		//글 내용 엔터 처리
		content = content.replace("\r\n", "<br>");
		
		//내용으로 검색 시 검색어 강조
		if (search != null && column != null && search.equals("y") && column.equals("content")) {
			content = content.replace(word, "<span style=\"background-color:gold;color:tomato;\">" + word + "</span>");
		}
		
		
		
//		dto.setId((String)session.getAttribute("id"));	//로그인 한 사람의 ID > 세션안에 있는거라 아무데서나 쓸 수 있음
		dto.setContent(content);
		req.setAttribute("dto", dto);
		
//		String protId = dao.getProtId(free_seq);
		
		//댓글 목록 가져오기
		List<CommentDTO> clist = dao.clist(free_seq);
		
		req.setAttribute("clist", clist);
		
		

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/client/community/viewfree.jsp");
		dispatcher.forward(req, resp);

	}
	
	
}