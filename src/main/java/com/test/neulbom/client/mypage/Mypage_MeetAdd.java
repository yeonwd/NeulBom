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

import com.test.neulbom.client.repository.MeetDAO;
import com.test.neulbom.client.repository.ProtectDTO;
import com.test.neulbom.client.repository.UserDAO;

@WebServlet("/client/mypage/mypage_meetadd.do")
public class Mypage_MeetAdd extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// Mypage_MeetAdd.java
		HttpSession session = req.getSession();

		ProtectDTO dto = new ProtectDTO();

		MeetDAO dao = new MeetDAO();

		String protect_seq = (String) session.getAttribute("protect_seq");

		dto = dao.getProtect(dto, protect_seq);

		String resi_name = dao.getResiName(dto.getResi_seq());

		dto.setResi_name(resi_name);

		req.setAttribute("dto", dto);

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/client/mypage/mypage_meetadd.jsp");
		dispatcher.forward(req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// Mypage_MeetAdd.java
		HttpSession session = req.getSession();

		String meet_date = req.getParameter("date");
		String meet_time = req.getParameter("meet_time");

		ProtectDTO dto = new ProtectDTO();

		MeetDAO dao = new MeetDAO();

		String protect_seq = (String) session.getAttribute("protect_seq");

		dto = dao.getProtect(dto, protect_seq);


		int result = 0;

		result = dao.meetAdd(meet_date, meet_time, protect_seq, dto.getResi_seq());

		if (result == 1) {
			resp.sendRedirect("/neulbom/client/mypage/mypage_meet.do");
		} else {
			PrintWriter writer = resp.getWriter();
			writer.print("<script>alert('failed');history.back();</script>");
			writer.close();
		}

	}

}
