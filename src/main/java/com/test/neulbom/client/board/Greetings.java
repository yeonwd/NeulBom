package com.test.neulbom.client.board;

import java.io.IOException; 

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/client/board/greetings.do")
public class Greetings extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//Greetings.java

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/greetings.jsp");
		dispatcher.forward(req, resp);
	}

}
