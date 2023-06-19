package com.test.neulbom.client.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.test.neulbom.client.repository.ClientDAO;
import com.test.neulbom.client.repository.CommentDTO;
import com.test.neulbom.client.repository.ConsultDTOWj;
import com.test.neulbom.client.repository.Food_DetailDTO;
import com.test.neulbom.client.repository.FreeDTO;
import com.test.neulbom.client.repository.NomemWjDAO;
import com.test.neulbom.client.repository.NomemWjDTO;

@WebServlet("/client/board/residetailwj.do")
public class Residetailwj extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//Residetailwj.java
String con_seq = req.getParameter("con_seq");
		
	  NomemWjDAO dao = new NomemWjDAO();
	  ConsultDTOWj dto = new ConsultDTOWj();
	  dto = dao.show(con_seq);
	  
	  req.setAttribute("dto", dto);
	  
	  
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/client/board/residetailwj.jsp");
		dispatcher.forward(req, resp);

	}

}
