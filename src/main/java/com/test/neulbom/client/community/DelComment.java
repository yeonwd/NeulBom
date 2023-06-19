package com.test.neulbom.client.community;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.neulbom.client.repository.ClientDAO;

@WebServlet("/client/community/delcomment.do")
public class DelComment extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//DelComment.java
		//댓글 지우기
		
		String comment_seq = req.getParameter("comment_seq");	//댓글 번호
		String free_seq = req.getParameter("free_seq");			//글 번호
		
		ClientDAO dao = new ClientDAO();
		
		//지워야 할 댓글 번호 넘기기
		int result = dao.delComment(comment_seq);
		
		if (result == 1) {
			//글 삭제가 완료되면 기존 글로 돌아가기
			resp.sendRedirect("/neulbom/client/community/viewfree.do?free_seq=" + free_seq);
			
		} else {
			
			PrintWriter writer = resp.getWriter();
			writer.print("<script>alert('failed');history.back();</script>");
			writer.close();
			
		}
		

	}

}