package com.test.neulbom.client.mypage;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.test.neulbom.client.repository.ProgramDAO;

@WebServlet("/client/mypage/delprogram.do")
public class DelProgram extends HttpServlet {

   @Override
   protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

      //DelProgram.java
      
      String papp_seq = req.getParameter("papp_seq");
      String prog_seq = req.getParameter("prog_seq");
      
      ProgramDAO dao = new ProgramDAO();
      
      
      int result = 0;
      
      result = dao.delProgram(papp_seq);
      HttpSession session = req.getSession();
      String resi_seq = (String)session.getAttribute("resi_seq");
      
      if (result == 1) {
    	 dao.decreaseApplyCount(prog_seq);
         resp.sendRedirect("/neulbom/client/mypage/myprogram.do?resi_seq=" + resi_seq);
         
      } else if (result == 0) {
    	
    	  PrintWriter writer = resp.getWriter();
		writer.print("<script>alert('failed');history.back();</script>");
		writer.close();
      }
      
      

   }

}