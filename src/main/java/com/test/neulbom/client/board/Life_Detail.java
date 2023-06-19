package com.test.neulbom.client.board;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.neulbom.client.repository.Life_DetailDAO;
import com.test.neulbom.client.repository.Life_DetailDTO;

@WebServlet("/client/board/life_detail.do")
public class Life_Detail extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//Life_Detail.java
		String life_seq = req.getParameter("life_seq");
		Life_DetailDAO dao = new Life_DetailDAO();
		
		
		String lifeSeq = req.getParameter("life_seq");
	    dao.increaseReadCount(lifeSeq);
		
	    Life_DetailDTO dto = dao.get(life_seq);
		String content = dto.getContent();

		// HTML 태그 이스케이프
		content = content.replace("<", "&lt;").replace(">", "&gt;");

		// 글 내용 개행 문자 처리
		content = content.replace("\r", "<br><br>");
		content = content.replace("\r\n", "<br>");
		
		dto.setContent(content);
		req.setAttribute("dto", dto);
		
		
		req.setAttribute("dto", dto);
		
		
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/client/board/life_detail.jsp");
		dispatcher.forward(req, resp);
	}

}