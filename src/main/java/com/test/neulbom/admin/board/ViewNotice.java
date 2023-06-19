package com.test.neulbom.admin.board;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.neulbom.admin.board.repository.BoardDAO;
import com.test.neulbom.admin.board.repository.NoticeDTO;

@WebServlet("/admin/board/viewnotice.do")
public class ViewNotice extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String seq = req.getParameter("seq");

		BoardDAO dao = new BoardDAO();
		NoticeDTO dto = dao.showNotice(seq);

		String content = dto.getContent();

		// HTML 태그 이스케이프
		content = content.replace("<", "&lt;").replace(">", "&gt;");

		// 글 내용 개행 문자 처리
		content = content.replace("\r", "<br><br>");
		content = content.replace("\r\n", "<br>");
		
		dto.setContent(content);
		req.setAttribute("dto", dto);

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/admin/board/viewNotice.jsp");
		dispatcher.forward(req, resp);

	}

}
