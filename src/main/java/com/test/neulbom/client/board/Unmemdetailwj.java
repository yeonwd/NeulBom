package com.test.neulbom.client.board;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.neulbom.client.repository.NomemWjDAO;
import com.test.neulbom.client.repository.NomemWjDTO;

@WebServlet("/client/board/unmemdetail.do")
public class Unmemdetailwj extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//Resiconsult.java
		

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/client/community/unmemdetail.jsp");
		dispatcher.forward(req, resp);
	}

	
	

	
}
