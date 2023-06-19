package com.test.neulbom.client.account;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/client/account/logout.do")
public class Logout extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//Logout.java

		req.getSession().removeAttribute("id");
		req.getSession().removeAttribute("lv");
		req.getSession().removeAttribute("resi_seq");
		req.getSession().removeAttribute("protect_seq");
		req.getSession().removeAttribute("admin_seq");
		req.getSession().removeAttribute("name");
		
		
		resp.sendRedirect("/neulbom/client/index.do");
	}

}
