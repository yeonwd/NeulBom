package com.test.neulbom.admin.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.neulbom.admin.board.repository.BoardDAO;
import com.test.neulbom.admin.board.repository.CommentDTO;
import com.test.neulbom.admin.board.repository.FreeDTO;

@WebServlet("/admin/board/viewfree.do")
public class ViewFree extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String seq = req.getParameter("seq");

		BoardDAO dao = new BoardDAO();
		FreeDTO dto = dao.showFree(seq);

		String content = dto.getContent();

		// HTML 태그 이스케이프
		content = content.replace("<", "&lt;").replace(">", "&gt;");

		// 글 내용 개행 문자 처리
		content = content.replace("\r", "<br><br>");
		content = content.replace("\r\n", "<br>");
		
		dto.setContent(content);
	
		List<CommentDTO> clist = dao.getComment(seq);
		
		req.setAttribute("clist", clist);	
		req.setAttribute("dto", dto);

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/admin/board/viewFree.jsp");
		dispatcher.forward(req, resp);
	}

}