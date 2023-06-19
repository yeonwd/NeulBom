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

@WebServlet("/admin/manage/registerProgram.do")
public class RegisterProgram extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// RegisterProgram.java

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/admin/manage/registerProgram.jsp");
		dispatcher.forward(req, resp);
	}

	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		
		ProgramDAO dao = new ProgramDAO();
		
		// 1. 새로 등록할 시퀀스 구하기
		String prog_seq = dao.getProgSeq();

		// 2. 입력한 데이터 받아오기
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		String prog_date = req.getParameter("prog_date");
		String place = req.getParameter("place");
		String people = req.getParameter("people");
		
		// 3. insert 하기 위해 dto에 포장
		ProgramDTO progDto = new ProgramDTO();
		progDto.setProg_seq(prog_seq);
		progDto.setTitle(title);
		progDto.setContent(content);
		progDto.setProg_date(prog_date);
		progDto.setPlace(place);
		progDto.setPeople(people);
		
		int result = dao.registerProgram(progDto);
		
		if (result==1) {
			// insert 성공
			resp.sendRedirect("/neulbom/admin/manage/manageProgram.do");
		} else {
			PrintWriter writer = resp.getWriter();
			writer.print("<script>alert('[Resiter Program] failed');history.back();</script>");
			writer.close();
		}
		
	}
}





































