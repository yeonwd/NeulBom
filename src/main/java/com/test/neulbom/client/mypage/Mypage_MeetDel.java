package com.test.neulbom.client.mypage;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.neulbom.client.repository.MeetDAO;

@WebServlet("/client/mypage/mypage_meetdel.do")
public class Mypage_MeetDel extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//Mypage_MeetDel.java
		
		String meet_seq = req.getParameter("meet_seq");
		
		MeetDAO dao = new MeetDAO();
		
		
		int result = 0;
		
		result = dao.meetDel(meet_seq);
		
		
		if (result == 1) {
			resp.sendRedirect("/neulbom/client/mypage/mypage_meet.do");
		} else if (result == 0) {
			resp.sendRedirect("/neulbom/client/board/qna.do");
		}
		
		
		

	}

}
