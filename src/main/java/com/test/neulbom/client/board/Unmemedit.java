package com.test.neulbom.client.board;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.neulbom.client.repository.BoardWjDAO;
import com.test.neulbom.client.repository.BoardWjDTO;

@WebServlet("/client/board/unmemedit.do")
public class Unmemedit extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


		//Unmemedit.java
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/client/board/unmemeditform.jsp");
		dispatcher.forward(req, resp);
}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
req.setCharacterEncoding("UTF-8");
		
	    
	    String title = req.getParameter("title");
	    String content = req.getParameter("content");
	    
	    //redirect로 보낼 주소
	    String con_seq = req.getParameter("con_seq");
	    String pname = req.getParameter("pname");
	   
	    BoardWjDAO dao = new BoardWjDAO();  	
    	BoardWjDTO dto = new BoardWjDTO();
    	
    	dto.setCon_title(title);
    	dto.setCon_content(content);
    	dto.setCon_seq(con_seq);

    	int result = dao.edit(dto);
    	
    	if (result == 1) {
    		String redirectURL = "/neulbom/client/board/residetailwj.do?con_seq=" + con_seq + "&pname=" + URLEncoder.encode(pname, "UTF-8");
    	    resp.sendRedirect(redirectURL);
    	} else {
    		PrintWriter writer = resp.getWriter();
    		writer.write("<script>alert('failed'); history.back();</script>");
    		writer.close();
    	}
	}
}
