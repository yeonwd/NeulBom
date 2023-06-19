package com.test.neulbom.client.community;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.test.neulbom.client.repository.ClientDAO;

@WebServlet("/client/community/delfree.do")
public class DelFree extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//DelFree.java
		
		HttpSession session = req.getSession();
		
		String free_seq = req.getParameter("free_seq");
		
		req.setAttribute("free_seq", free_seq);
		

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/client/community/delfree.jsp");
		dispatcher.forward(req, resp);

	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String free_seq = req.getParameter("free_seq");

		
		ClientDAO dao = new ClientDAO();
		
		int result = 0;
		
		result = dao.delFree(free_seq);	//글 삭제
		result = dao.defComment(free_seq);	//댓글 삭제
		
		
		if (result == 1) {
			//글 삭제가 완료되면 글목록으로 돌아가기
			resp.sendRedirect("/neulbom/client/community/free.do");
			
		} else {
			
			resp.sendRedirect("/neulbom/client/community/free.do");
		}
		
	}

}