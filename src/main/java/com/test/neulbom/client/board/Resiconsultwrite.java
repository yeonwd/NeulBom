package com.test.neulbom.client.board;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.neulbom.client.repository.BoardWjDAO;
import com.test.neulbom.client.repository.BoardWjDTO;
import com.test.neulbom.client.repository.UserWjDAO;
import com.test.neulbom.client.repository.UserWjDTO;

@WebServlet("/client/board/resiconsultwrite.do")
public class Resiconsultwrite extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//Resiconsultwrite.java
		

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/client/community/resiconsultwrite.jsp");
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		
	    
	    String title = req.getParameter("title");
	    String content = req.getParameter("content");
	    String nomem_seq = req.getParameter("nomem_seq");
	   
	    BoardWjDAO dao = new BoardWjDAO();  	
    	BoardWjDTO dto = new BoardWjDTO();
    	
    	dto.setCon_title(title);
    	dto.setCon_content(content);
    	dto.setNomem_seq(nomem_seq);

    	int result = dao.add(dto);
    	
    	if (result == 1) {
    		resp.sendRedirect("/neulbom/client/board/resiconsult.do");
    	} else {
    		PrintWriter writer = resp.getWriter();
    		writer.write("<script>alert('failed'); history.back();</script>");
    		writer.close();
    	}
	}
}
