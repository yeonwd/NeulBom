package com.test.neulbom.client.board;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.test.neulbom.client.repository.QnaDAO;
import com.test.neulbom.client.repository.QnaDTO;

@WebServlet("/client/board/qnaadd.do")
public class QnaAdd extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//QnaAdd.java
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/client/board/qnaadd.jsp");
		dispatcher.forward(req, resp);
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//QnaAdd.java

		QnaDAO dao = new QnaDAO();
		
		
		try {
	         
	         MultipartRequest multi = new MultipartRequest(
	        		 
	                                 req,
	                                 req.getRealPath("/asset/qna"),
	                                 1024 * 1024 * 10,
	                                 "UTF-8",
	                                 new DefaultFileRenamePolicy()
	               
	                           );
	         
	         
	         //System.out.println(req.getRealPath("/asset/qna"));
	         
	         HttpSession session = req.getSession();
	         
	         String title = multi.getParameter("title");
	         String content = multi.getParameter("content");
	         String category = multi.getParameter("category");
	 		 String fname = multi.getFilesystemName("fname");
	 		 
	 		 
	 		 String resi_seq = (String) session.getAttribute("resi_seq");
	 		 String protect_seq = (String) session.getAttribute("protect_seq");
	         
	 		 QnaDTO dto = new QnaDTO();
	         
	         dto.setTitle(title);
	         dto.setContent(content);
	         dto.setCategory(category);
	         dto.setFname(fname);
	         dto.setId((String)session.getAttribute("id"));
	         dto.setResi_seq(resi_seq);
	         dto.setProtect_seq(protect_seq);
	         
	         System.out.println(title);
	         System.out.println(content);
	         System.out.println(category);
	         System.out.println(fname);
	         System.out.println((String)session.getAttribute("id"));
	         System.out.println(resi_seq);
	         System.out.println(protect_seq);
	         
	         int result = 0;
	 		
	 		 if (resi_seq != null && protect_seq == null) {
	 			result = dao.qnaResiAdd(title, content, fname, category, resi_seq);
	 		 } else if (protect_seq != null && resi_seq == null) {
	 			result = dao.qnaProtectAdd(title, content, fname, category, protect_seq);
	 		 }
	 		 
	 		 if(result == 1) {
	 			 resp.sendRedirect("/neulbom/client/board/qna.do");
	 		 } else {
	 			 PrintWriter writer = resp.getWriter();
	 			 writer.print("<script>alert('failed');history.back();</script>");
	 			 writer.close();
	 		 }

		} catch (Exception e) {
	         e.printStackTrace();
	      }
		
		
		
	}

}
