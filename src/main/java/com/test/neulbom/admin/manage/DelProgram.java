package com.test.neulbom.admin.manage;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.neulbom.admin.manage.repository.ProgramDAO;

@WebServlet("/admin/manage/delProgram.do")
public class DelProgram extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// DelProgram.java
		
		String prog_seq = req.getParameter("prog_seq");
		req.setAttribute("prog_seq", prog_seq);
		
		ProgramDAO dao = new ProgramDAO();
		
		int result = dao.delProgram(prog_seq);
		
		if (result == 1) {
			resp.sendRedirect("/neulbom/admin/manage/manageProgram.do");
		} else {
			PrintWriter writer = resp.getWriter();
			writer.print("<script>alert('[Delete Program] failed');history.back();</script>");
			writer.close();
		} 
	}

}
