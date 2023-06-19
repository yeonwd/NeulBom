package com.test.neulbom.admin.account;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.neulbom.admin.repository.ResiDAO;

@WebServlet("/admin/account/delresi.do")
public class DelResi extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		//DelAdmin.java
		String resi_seq = req.getParameter("resi_seq");
		
		req.setAttribute("resi_seq", resi_seq);

		RequestDispatcher dispatcher = req
				.getRequestDispatcher("/WEB-INF/views/admin/account/delresi.jsp");
		dispatcher.forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String resi_seq = req.getParameter("resi_seq");
		
		ResiDAO dao = new ResiDAO();
		
		//삭제될 글에 댓글이 달렸는지 확인 > 있으면 삭제
		int result = 0;
		
		result = dao.del(resi_seq); //글 삭제
		
		if (result >= 1) {
			resp.sendRedirect("/neulbom/admin/account/manage.do");
		} else {
			PrintWriter writer = resp.getWriter();
			writer.print("<script>alert('failed');history.back();</script>");
			writer.close();
		}	
		
	}
}
