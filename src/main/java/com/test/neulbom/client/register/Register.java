package com.test.neulbom.client.register;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.neulbom.client.repository.UserWjDAO;
import com.test.neulbom.client.repository.UserWjDTO;

@WebServlet("/client/register/register.do")
public class Register extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//register.java
	    req.setCharacterEncoding("UTF-8"); // 한글 인코딩 설정

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/client/register/register.jsp");
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			
		req.setCharacterEncoding("UTF-8");
		
		    
		    String pname = req.getParameter("pname");
		    
		    String pid = req.getParameter("pid");
		    String ppw = req.getParameter("ppw");
		    
		    String pssn1 = req.getParameter("pssn1");
		    String pssn2 = req.getParameter("pssn2");
		    String pssn = pssn1 + "-" + pssn2;
		    
		    String ptel1 = req.getParameter("ptel1");
		    String ptel2 = req.getParameter("ptel2");
		    String ptel3 = req.getParameter("ptel3");
		    String ptel = ptel1 + "-" + ptel2 + "-" + ptel3;
		    
		    String padr = req.getParameter("padr");
		    String proad = req.getParameter("proad");
		    String padrdetail = req.getParameter("padrdetail");
		    String address = padr + " " + proad + " " + padrdetail;
		    
		    String pemail1 = req.getParameter("pemail1");
		    String pemail2 = req.getParameter("pemail2");
		    String pemail = pemail1 + "@" + pemail2;
		    
		    String rid = req.getParameter("rid");
		    
		    String rela = req.getParameter("rela");
		    
	    	UserWjDAO dao = new UserWjDAO();
	    	
	    	UserWjDTO dto = new UserWjDTO();
	    	
	    	dto.setPname(pname);
	    	dto.setPid(pid);
	    	dto.setPpw(ppw);
	    	dto.setPssn(pssn);
	    	dto.setPtel(ptel);
	    	dto.setPadr(address);
	    	dto.setPemail(pemail);
	    	dto.setRid(rid);
	    	dto.setRela(rela);
	    	
	    	String rseq = dao.check(rid);
	    	System.out.println(rseq);
	    	dto.setRseq(rseq);
	    	
	    	int result = dao.add(dto);
	    	
	    	if (result == 1) {
	    		resp.sendRedirect("/neulbom/client/index.do");
	    	} else {
	    		PrintWriter writer = resp.getWriter();
	    		writer.write("<script>alert('failed'); history.back();</script>");
	    		writer.close();
	    	}
		
	}

}
