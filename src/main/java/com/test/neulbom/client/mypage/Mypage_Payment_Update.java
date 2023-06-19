package com.test.neulbom.client.mypage;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.test.neulbom.client.repository.Mypage_Payment_UpdateDAO;

@WebServlet("/client/mypage/mypage_payment_update.do")
public class Mypage_Payment_Update extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//Mypage_Payment_Update.java
		
		// Mypage_Payment.java
		HttpSession session = req.getSession();

		// 테스트용
		

		String seq = (String) session.getAttribute("resi_seq");
		String pay_seq = req.getParameter("pay_seq");
		
		
				
		Mypage_Payment_UpdateDAO dao = new Mypage_Payment_UpdateDAO();
		
		int result = dao.update(seq, pay_seq);
		req.setAttribute("result", result);
		
		
		//RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/client/mypage/mypage_payment_update.jsp");
		//dispatcher.forward(req, resp);
		
		resp.sendRedirect("/neulbom/client/mypage/mypage_payment_details.do");
		
	}

}
