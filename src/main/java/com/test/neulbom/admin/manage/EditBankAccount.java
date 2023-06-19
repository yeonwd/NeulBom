package com.test.neulbom.admin.manage;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.neulbom.admin.repository.AdminDAO;
import com.test.neulbom.admin.repository.AdminDTO;

@WebServlet("/admin/manage/editBankAccount.do")
public class EditBankAccount extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// EditBankAccount.java
		
		String admin_seq = req.getParameter("admin_seq");
		
		AdminDAO dao = new AdminDAO();
		
		AdminDTO adminDto = new AdminDTO();
		
		adminDto = dao.detailBankAccount(admin_seq);
		
		req.setAttribute("admin_seq", admin_seq);
		req.setAttribute("adminDto", adminDto);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/admin/manage/editBankAccount.jsp");
		dispatcher.forward(req, resp);
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		
		AdminDAO dao = new AdminDAO();
		
		String admin_seq = req.getParameter("admin_seq");
		String name = req.getParameter("name");
		String bank = req.getParameter("bank");
		String bank_account = req.getParameter("bank_account");
		String tel = req.getParameter("tel");
		String email = req.getParameter("email");
		
		AdminDTO adminDto = new AdminDTO();
		
		adminDto.setAdmin_seq(admin_seq);
		adminDto.setName(name);
		adminDto.setBank(bank);
		adminDto.setBank_account(bank_account);
		adminDto.setTel(tel);
		adminDto.setEmail(email);

		
		int result = dao.editBankAccount(adminDto);
		
		if (result == 1) {
			resp.sendRedirect("/neulbom/admin/manage/staffSalary.do");
		} else {
			PrintWriter writer = resp.getWriter();
			writer.print("<script>alert('[Edit Bank Account] failed');history.back();</script>");
			writer.close();
		} 
		
		
	}

}


















