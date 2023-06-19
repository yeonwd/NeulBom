package com.test.neulbom.client.board;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.neulbom.client.repository.QnaDAO;
import com.test.neulbom.client.repository.QnaDTO;

@WebServlet("/client/board/qnaedit.do")
public class QnaEdit extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//QnaUpdate.java
		String qna_seq = req.getParameter("qna_seq"); // qna_seq 파라미터 가져오기

		QnaDAO dao = new QnaDAO();
        QnaDTO dto = new QnaDTO();
        
        dao.qnaView(qna_seq, dto);
        // qna_seq에 해당하는 내용 가져오기
        dao.increaseReadCount(qna_seq);
        
        String name = dao.getnameByProtect(qna_seq); // name 값 가져오기
        dto.setName(name); // name 값 설정
        
        if (name == null) {
        	name = dao.getnameByResi(qna_seq);
        	dto.setName(name);
        }
        
        
        req.setAttribute("dto", dto);
		

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/client/board/qnaedit.jsp");
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//QnaUpdate.java
		//EditOk.java
		//1. 데이터 가져오기(수정할 값)
		//2. DB작업 > update
		//3. 결과
		
		
		req.setCharacterEncoding("UTF-8");
		
		//1.
		String qna_seq = req.getParameter("qna_seq");
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		String category = req.getParameter("category");
		
		//2.
		QnaDAO dao = new QnaDAO();
		QnaDTO dto = new QnaDTO();
		dto.setQna_seq(qna_seq);
		dto.setTitle(title);
		dto.setContent(content);
		dto.setCategory(category);
		
		System.out.println(qna_seq);
		System.out.println(title);
		System.out.println(content);
		System.out.println(category);
		
		int result = dao.edit(dto);
		
		if (result == 1) {
			resp.sendRedirect("/neulbom/client/board/qnaview.do?qna_seq=" + qna_seq);
		} else {
			PrintWriter writer = resp.getWriter();
			writer.print("<script>alert('failed');history.back();</script>");
			writer.close();
		}
		

	}

}
