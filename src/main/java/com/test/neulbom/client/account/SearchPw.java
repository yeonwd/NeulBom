package com.test.neulbom.client.account;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/client/account/searchpw.do")
public class SearchPw extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//SearchPw.java

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/client/account/searchpw.jsp");
		dispatcher.forward(req, resp);

	}

}