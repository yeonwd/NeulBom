package com.test.neulbom.client.mypage;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.test.neulbom.client.repository.Mypage_PaymentDAO;
import com.test.neulbom.client.repository.Mypage_PaymentDTO;

@WebServlet("/client/mypage/mypage_payment.do")
public class Mypage_Payment extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// Mypage_Payment.java
		HttpSession session = req.getSession();

		// 테스트용
		

		String seq = (String) session.getAttribute("resi_seq");
		String pay_seq = req.getParameter("pay_seq");

		Mypage_PaymentDAO dao = new Mypage_PaymentDAO();

		Mypage_PaymentDTO dto = dao.list(seq, pay_seq);

		

		req.setAttribute("dto", dto);
		

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/client/mypage/mypage_payment.jsp");
		dispatcher.forward(req, resp);
	}

}
