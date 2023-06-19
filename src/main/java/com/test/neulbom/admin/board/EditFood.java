package com.test.neulbom.admin.board;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.test.neulbom.admin.board.repository.BoardDAO;
import com.test.neulbom.admin.board.repository.FoodDTO;

@WebServlet("/admin/board/editfood.do")
public class EditFood extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String seq = req.getParameter("seq");

		BoardDAO dao = new BoardDAO();
		FoodDTO dto = dao.showFood(seq);
		
		req.setAttribute("dto", dto);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/admin/board/editFood.jsp");
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		BoardDAO dao = new BoardDAO();
		
		try {
	         
	         MultipartRequest multi = new MultipartRequest(
	        		 
	                                 req,
	                                 req.getRealPath("/asset/food"),
	                                 1024 * 1024 * 10,
	                                 "UTF-8",
	                                 new DefaultFileRenamePolicy()
	               
	                           );
	         
//	         System.out.println(req.getRealPath("/asset/food"));
	         
	         HttpSession session = req.getSession();
	         
	         String seq = multi.getParameter("seq");
	         String title = multi.getParameter("title");
	 		 String fname = multi.getFilesystemName("fname");
	         
	         int result = 0;

	         result = dao.editFood(title, fname, seq);
	 		 
	 		 if(result == 1) {
	 			 resp.sendRedirect("/neulbom/admin/board/food.do");
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
