package com.test.neulbom.admin.manage;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.neulbom.admin.manage.repository.EqDAO;


@WebServlet("/admin/manage/delEquip.do")
public class DelEquip extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// DelEquip.java

		String eq_seq = req.getParameter("eq_seq");
		req.setAttribute("eq_seq", eq_seq);
		
		EqDAO dao = new EqDAO();
		
		
		int result = dao.delEquip(eq_seq);
		
		if (result == 1) {
			resp.sendRedirect("/neulbom/admin/manage/showEquip.do");
		} else {
			PrintWriter writer = resp.getWriter();
			writer.print("<script>alert('[Delete Equip] failed');history.back();</script>");
			writer.close();
		} 

	}

}















