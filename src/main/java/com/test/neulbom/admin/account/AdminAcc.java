package com.test.neulbom.admin.account;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.test.neulbom.admin.repository.AdminDAO;
import com.test.neulbom.admin.repository.AdminDTO;

@WebServlet("/admin/account/adminacc.do")
public class AdminAcc extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		//AddAccount.java

		RequestDispatcher dispatcher = req
				.getRequestDispatcher("/WEB-INF/views/admin/account/adminacc.jsp");
		dispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//RegisterOk.java
		//1. 데이터 가져오기
		//2. DB 작업 > insert
		//3. 결과
		
		try {
			
			MultipartRequest multi = new MultipartRequest(
						
										req,
										req.getRealPath("/asset/pic"),
										1024 * 1024 * 10,
										"UTF-8",
										new DefaultFileRenamePolicy()
					
									);
			
			System.out.println(req.getRealPath("/asset/pic"));
			
			String id = multi.getParameter("id");
			String pw = multi.getParameter("pw");
			String name = multi.getParameter("name");
			String ssn1 = multi.getParameter("ssn1");
			String ssn2 = multi.getParameter("ssn2");
			String ssn = ssn1 + "-" + ssn2;
			
			String tel1 = multi.getParameter("tel1");
			String tel2 = multi.getParameter("tel2");
			String tel3 = multi.getParameter("tel3");
			String tel = tel1 + "-" + tel2 + "-" + tel3;
			 
			String email1 = multi.getParameter("email1");
			String email2 = multi.getParameter("email2");
			String email = email1 + "@" + email2;
			
			String pic = multi.getFilesystemName("pic");
			String lv = multi.getParameter("lv");
			
			AdminDTO dto = new AdminDTO();
			
			dto.setId(id);
			dto.setPw(pw);
			dto.setName(name);
			dto.setSsn(ssn);
			dto.setTel(tel);
			dto.setEmail(email);
			dto.setPic(pic);
			dto.setLv(lv);		
			AdminDAO dao = new AdminDAO();
			
			int result = dao.register(dto);
			
			if (result == 1) {
				resp.sendRedirect("/neulbom/admin/index.do");
			} else {
				PrintWriter writer = resp.getWriter();
				writer.print("<script>alert('failed');history.back();</script>");
				writer.close();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}