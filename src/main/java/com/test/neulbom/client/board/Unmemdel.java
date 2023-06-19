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

@WebServlet("/client/board/unmemdel.do")
public class Unmemdel extends HttpServlet {

	  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	        // Unmemdel.java
	        String con_seq = req.getParameter("con_seq");
	        BoardWjDAO dao = new BoardWjDAO();

	        int result = dao.delete(con_seq);

	    	if (result == 1) {
	    		resp.sendRedirect("/neulbom/client/board/resiconsult.do");
	    	} else {
	    		PrintWriter writer = resp.getWriter();
	    		writer.write("<script>alert('failed'); history.back();</script>");
	    		writer.close();
	    	}
	    }

}
