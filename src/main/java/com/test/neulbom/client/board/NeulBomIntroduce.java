package com.test.neulbom.client.board;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/client/board/neulbomintroduce.do")
public class NeulBomIntroduce extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//NeulBomIntroduce.java

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/neulbomintroduce.jsp");
		dispatcher.forward(req, resp);
	}

}
