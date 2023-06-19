package com.test.neulbom.client;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/client/introduce.do")
public class Introduce extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		//Introduce.java

		RequestDispatcher dispatcher = req
				.getRequestDispatcher("/WEB-INF/views/client/introduce.jsp");
		dispatcher.forward(req, resp);
	}

} 
