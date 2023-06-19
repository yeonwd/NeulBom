package com.test.neulbom.client.board;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/inc/viewtemplateclient.do")
public class Viewtemplateclient extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//Viewtemplateclient.java

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/inc/viewtemplateclient.jsp");
		dispatcher.forward(req, resp);
	}

}
