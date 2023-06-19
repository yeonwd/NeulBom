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
import com.test.neulbom.client.repository.FreeDTO;

@WebServlet("/client/community/editfree.do")
public class EditFree extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//EditFree.java
		
		String free_seq = req.getParameter("free_seq");
		System.out.println(free_seq);
		HttpSession session = req.getSession();
		
		ClientDAO dao = new ClientDAO();
		FreeDTO dto = dao.fcontent(free_seq);
		
		req.setAttribute("dto", dto);
		
		
		
		
		

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/client/community/editfree.jsp");
		dispatcher.forward(req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");

		String free_seq = req.getParameter("free_seq");	//수정할 글 번호
		String title = req.getParameter("title");		//수정할 제목
		String content = req.getParameter("content");	//수정할 내용
		String file = req.getParameter("file");			//수정할 첨부파일
	
		ClientDAO dao = new ClientDAO();
		FreeDTO dto = new FreeDTO();
		
		dto.setFree_seq(free_seq);
		dto.setTitle(title);
		dto.setContent(content);
		dto.setFile(file);
		
		int result = dao.editFree(dto);
		
		if (result == 1) {
			
			//req.getSession().setAttribute("message", "게시글이 수정되었습니다.");
			resp.sendRedirect("/neulbom/client/community/viewfree.do?free_seq=" + dto.getFree_seq());
			
		} else {
			
			PrintWriter writer = resp.getWriter();
			writer.print("<script>alert('failed');history.back();</script>");
			writer.close();
		
		}
	
	
	
	}
	
}