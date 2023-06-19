package com.test.neulbom.client.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.neulbom.client.repository.Notice_DetailDAO;
import com.test.neulbom.client.repository.Notice_DetailDTO;

@WebServlet("/client/board/notice_detail.do")
public class Notice_Detail extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//notice_detail.java
		String notice_seq = req.getParameter("notice_seq");
		
		Notice_DetailDAO dao = new Notice_DetailDAO();

		
		    
		    
		    
		// 조회수 증가 메서드 호출
		String noticeSeq = req.getParameter("notice_seq");
	    dao.increaseReadCount(noticeSeq);

		Notice_DetailDTO dto = dao.get(notice_seq);
		
		 
		 
		 String content = dto.getContent();

			// HTML 태그 이스케이프
			content = content.replace("<", "&lt;").replace(">", "&gt;");

			// 글 내용 개행 문자 처리
			content = content.replace("\r", "<br><br>");
			content = content.replace("\r\n", "<br>");
			
			dto.setContent(content);
			req.setAttribute("dto", dto);
		
	
		
		req.setAttribute("dto", dto);
		
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/client/board/notice_detail.jsp");
		dispatcher.forward(req, resp);
	}


}





