package com.test.neulbom.client.community;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.neulbom.client.repository.ClientDAO;
import com.test.neulbom.client.repository.CommentDTO;

@WebServlet("/client/community/editcomment.do")
public class EditComment extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		
		//글 번호
		String free_seq = req.getParameter("free_seq");
		//수정할 댓글번호
		String comment_seq = req.getParameter("comment_seq");
		//수정할 댓글 내용
		String content = req.getParameter("content");
		
		ClientDAO dao = new ClientDAO();
		CommentDTO dto = new CommentDTO();
	
		dto.setComment_seq(comment_seq);
		dto.setContent(content);
		
		int result = dao.editComment(dto);
		
		if (result == 1) {
			
			resp.sendRedirect("/neulbom/client/community/viewfree.do?free_seq=" + free_seq);
		} else {
			
			PrintWriter writer = resp.getWriter();
			writer.print("<script>alert('failed');history.back();</script>");
			writer.close();
			
		}
		
	
	}

}