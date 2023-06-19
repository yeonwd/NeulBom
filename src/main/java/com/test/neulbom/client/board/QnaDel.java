package com.test.neulbom.client.board;

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
import com.test.neulbom.client.repository.QnaDAO;
import com.test.neulbom.client.repository.QnaDTO;

@WebServlet("/client/board/qnadel.do")
public class QnaDel extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//QnaDel.java
		HttpSession session = req.getSession();
				
		String qna_seq = req.getParameter("qna_seq");
		
		req.setAttribute("qna_seq", qna_seq);

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/client/board/qnadel.jsp");
		dispatcher.forward(req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//QnaDel.java
		String qna_seq = req.getParameter("qna_seq");

		QnaDAO dao = new QnaDAO();
		
		
		int result = 0;
		
		result = dao.delQna(qna_seq);	//글 삭제
		
		if (result == 1) {
			//글 삭제가 완료되면 글목록으로 돌아가기
			resp.sendRedirect("/neulbom/client/board/qna.do");
			
		} else {
			
			PrintWriter writer = resp.getWriter();
			writer.print("<script>alert('failed');history.back();</script>");
			writer.close();
		}
	}
	
}
