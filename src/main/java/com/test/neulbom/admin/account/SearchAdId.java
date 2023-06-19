package com.test.neulbom.admin.account;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/admin/account/searchadid.do")
public class SearchAdId extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		//SearchAdId.java

		RequestDispatcher dispatcher = req
				.getRequestDispatcher("/WEB-INF/views/admin/account/searchadid.jsp");
		dispatcher.forward(req, resp);
	}

}
