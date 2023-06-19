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
import com.test.neulbom.admin.manage.repository.ProgramDTO;

@WebServlet("/admin/manage/editProgram.do")
public class EditProgram extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// EditProgram.java
		
		String prog_seq = req.getParameter("prog_seq");
		
		ProgramDAO dao = new ProgramDAO();
		
		ProgramDTO progDto = new ProgramDTO();
		
		progDto = dao.getDetailProgram(prog_seq);

		req.setAttribute("progDto", progDto);
		req.setAttribute("prog_seq", prog_seq);
		

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/admin/manage/editProgram.jsp");
		dispatcher.forward(req, resp);
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		
		ProgramDAO dao = new ProgramDAO();
		
		String prog_seq = req.getParameter("prog_seq");
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		String prog_date = req.getParameter("prog_date");
		String place = req.getParameter("place");
		String people = req.getParameter("people");
		
		ProgramDTO dto = new ProgramDTO();
		
		dto.setProg_seq(prog_seq);
		dto.setTitle(title);
		dto.setContent(content);
		dto.setProg_date(prog_date);
		dto.setPlace(place);
		dto.setPeople(people);
		
		
		int result = dao.editProgram(dto);
		
		if (result == 1) {
			resp.sendRedirect("/neulbom/admin/manage/manageProgram.do");
		} else {
			PrintWriter writer = resp.getWriter();
			writer.print("<script>alert('[Edit Program] failed');history.back();</script>");
			writer.close();
		} 
		
		
		
	}

}


















