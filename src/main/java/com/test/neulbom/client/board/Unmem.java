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
import com.test.neulbom.client.repository.UserWjDAO;
import com.test.neulbom.client.repository.UserWjDTO;

@WebServlet("/client/board/unmem.do")
public class Unmem extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//Resiconsult.java
		

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/client/community/unmem.jsp");
		dispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		
	    
	    String pname = req.getParameter("pname");

	    String ptel1 = req.getParameter("ptel1");
	    String ptel2 = req.getParameter("ptel2");
	    String ptel3 = req.getParameter("ptel3");
	    String ptel = ptel1 + "-" + ptel2 + "-" + ptel3;
	
	    NomemWjDAO dao = new NomemWjDAO();
    	
	    NomemWjDTO dto = new NomemWjDTO();
    	dto.setPname(pname);
    	dto.setPtel(ptel);

    	int result = dao.add(dto);
    	String nomem_seq = dao.getnoseq(dto);
    	
    	System.out.println(nomem_seq);
    	
    	if (result == 1) {
    		resp.sendRedirect("/neulbom/client/board/resiconsultwrite.do?nomem_seq=" + nomem_seq);
    	} else {
    		PrintWriter writer = resp.getWriter();
    		writer.write("<script>alert('failed'); history.back();</script>");
    		writer.close();
    	}
	

	}
}
