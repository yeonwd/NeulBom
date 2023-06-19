package com.test.neulbom.admin.manage;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.neulbom.admin.manage.repository.ManageDAO;

@WebServlet("/admin/manage/meetrevision.do")
public class MeetRevision extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String seq = req.getParameter("seq");
		req.setAttribute("seq", seq);

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/admin/manage/meetRevision.jsp");
		dispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		ManageDAO dao = new ManageDAO();
		int result = 0;

		String seq = req.getParameter("seq");
		String type = req.getParameter("type");

		if (type.equals("confirm"))
			result = dao.confirmMeeting(seq);
		else
			result = dao.rejectMeeting(seq);

		if (result >= 1) {
			resp.sendRedirect("/neulbom/admin/manage/meet.do");
		} else {
			PrintWriter writer = resp.getWriter();
			writer.print("<script>alert('failed'); history.back();</script>");
			writer.close();
		}

	}

}
