package com.test.neulbom.client.mypage;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.test.neulbom.admin.manage.repository.ProgramDTO;
import com.test.neulbom.client.repository.ClientDAO;
import com.test.neulbom.client.repository.CommentDTO;
import com.test.neulbom.client.repository.ProgramDAO;

@WebServlet("/client/mypage/regiprogram.do")
public class RegiProgram extends HttpServlet {
	

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");

		HttpSession session = req.getSession(); // 아이디 받아오기
		
		String prog_seq = req.getParameter("prog_seq");
		String content = req.getParameter("content");
		String resi_seq = (String) session.getAttribute("resi_seq");
		String protect_seq = (String) session.getAttribute("protect_seq");
		String id = (String) session.getAttribute("id");
		String name = (String) session.getAttribute("name");

		ProgramDTO dto = new ProgramDTO();
		ProgramDAO dao = new ProgramDAO();

		dto.setProg_seq(prog_seq);
		dto.setId(id);
		dto.setName(name);
		dto.setContent(content);
		dto.setResi_seq(resi_seq);
		dto.setProtect_seq(protect_seq);

		int result = dao.regiProgram(dto);

		if (result == 1) {
			dao.increaseApplyCount(prog_seq);
			resp.sendRedirect("/neulbom/client/mypage/program.do");

		} else {

			PrintWriter writer = resp.getWriter();
			writer.print("<script>alert('failed');history.back();</script>");
			writer.close();

		}

	}

}