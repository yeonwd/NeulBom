package com.test.neulbom.client.community;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.test.neulbom.client.repository.ClientDAO;
import com.test.neulbom.client.repository.CommentDTO;

@WebServlet("/client/community/comment.do")
public class Comment extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		
		
		
		HttpSession session = req.getSession();	//댓글 쓴 이 아이디 받아오기
		
		
		String free_seq = req.getParameter("free_seq");
		String id = (String)session.getAttribute("id");
		String name = (String)session.getAttribute("name");
		String content = req.getParameter("content");
		String lv = (String)session.getAttribute("lv");
		String resi_seq = (String)session.getAttribute("resi_seq");
		String protect_seq = (String)session.getAttribute("protect_seq");
		
		CommentDTO cdto = new CommentDTO();	
		ClientDAO cdao = new ClientDAO();
		
		cdto.setFree_seq(free_seq);
		cdto.setId(id);
		cdto.setName(name);
		cdto.setContent(content);
		cdto.setLv(lv);
		cdto.setResi_seq(resi_seq);
		cdto.setProtect_seq(protect_seq);
		
		int result = cdao.addComment(cdto);
		
		if (result == 1) {
			resp.sendRedirect("/neulbom/client/community/viewfree.do?free_seq=" + free_seq);
			
		} else {
			
			PrintWriter writer = resp.getWriter();
			writer.print("<script>alert('failed');history.back();</script>");
			writer.close();
			
		}
		
	
	}

}
