package com.test.neulbom.admin.board;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.neulbom.admin.board.repository.BoardDAO;
import com.test.neulbom.admin.board.repository.LifeDTO;

@WebServlet("/admin/board/editlife.do")
public class EditLife extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String seq = req.getParameter("seq");

		BoardDAO dao = new BoardDAO();
		LifeDTO dto = dao.editLife(seq);

		String content = dto.getContent().replace("\\", "\\\\").replace("'", "\\'").replace("\"", "\\\"")
				.replace("\r", "\\r").replace("\n", "\\n");

		dto.setContent(content);
		req.setAttribute("dto", dto);

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/admin/board/editLife.jsp");
		dispatcher.forward(req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");

		BoardDAO dao = new BoardDAO();

		String seq = req.getParameter("seq");
		String title = req.getParameter("title");
		String content = req.getParameter("content");

		int result = dao.editLife(seq, title, content);

		if (result >= 1) {
			resp.sendRedirect("/neulbom/admin/board/life.do");
		} else {
			PrintWriter writer = resp.getWriter();
			writer.print("<script>alert('failed'); history.back();</script>");
			writer.close();
		}

	}

}
