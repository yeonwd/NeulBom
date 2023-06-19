package com.test.neulbom.client.account;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.neulbom.admin.repository.AdminDTO;
import com.test.neulbom.client.repository.ProtectDTO;
import com.test.neulbom.client.repository.ResiDTO;
import com.test.neulbom.client.repository.UserDAO;

@WebServlet("/client/account/login.do")
public class Login extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//Login.java

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/client/account/login.jsp");
		dispatcher.forward(req, resp);
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//Login.java
		
		//1. 데이터 가져오기
		//2. DB 작업 > 인증
		//3. 결과 > 인증 티켓 발부
		
		String resiId = req.getParameter("resiId");
		String resiPw = req.getParameter("resiPw");
		String protectId = req.getParameter("protectId");
		String protectPw = req.getParameter("protectPw");
		String adminId = req.getParameter("adminId");
		String adminPw = req.getParameter("adminPw");
		
		ResiDTO rdto = new ResiDTO();
		ProtectDTO pdto = new ProtectDTO();
		AdminDTO adto = new AdminDTO();
		
		
		rdto.setId(resiId);
		rdto.setPw(resiPw);
		pdto.setId(protectId);
		pdto.setPw(protectPw);
		adto.setId(adminId);
		adto.setPw(adminPw);
		
		System.out.println(resiId);
		System.out.println(protectId);
		System.out.println(adminId);
		
		
		UserDAO dao = new UserDAO();
		
		//dto : id,pw 전달
		//result : 로그인한 회원 정보 반환(아이디, 이름, 등급 등..) > 세션 추가
		if (protectId == null && adminId == null) {
			ResiDTO result = dao.login(rdto);
			
			if (result != null) {
				
				//로그인 성공
				req.getSession().setAttribute("id", resiId); //인증 티켓 발급
				req.getSession().setAttribute("lv", result.getLv()); //레벨
				req.getSession().setAttribute("name", result.getName()); //이름
				req.getSession().setAttribute("resi_seq", result.getResi_seq()); //번호
				
				resp.sendRedirect("/neulbom/client/index.do");
				req.getSession().setAttribute("resi_seq", result.getResi_seq()); //입주자 seq
				
				return;
				
			} else {
				
				//로그인 실패
				PrintWriter writer = resp.getWriter();
				writer.print("<script>alert('failed');history.back();</script>");
				writer.close();
				
			}
		} else if (resiId == null && adminId == null) {
			ProtectDTO result = dao.login(pdto);
						
			if (result != null) {
				
				//로그인 성공
				req.getSession().setAttribute("id", protectId); // 인증 티켓 발급
		        req.getSession().setAttribute("lv", result.getLv()); // 레벨
		        req.getSession().setAttribute("name", result.getName()); // 이름
		        req.getSession().setAttribute("protect_seq", result.getProtect_seq()); // 번호
		        req.getSession().setAttribute("resi_seq", result.getResi_seq()); // 연계 입주자 seq 추가
		        
		        resp.sendRedirect("/neulbom/client/index.do");
		        
				
				return;
				
			} else {
				
				//로그인 실패
				PrintWriter writer = resp.getWriter();
				writer.print("<script>alert('failed');history.back();</script>");
				writer.close();
				
			}
		} else if (resiId == null && protectId == null) {
			AdminDTO result = dao.login(adto);
			
			if (result != null) {
				
				//로그인 성공
				req.getSession().setAttribute("id", adminId); //인증 티켓 발급
				req.getSession().setAttribute("lv", result.getLv()); //레벨
				req.getSession().setAttribute("name", result.getName()); //이름
				req.getSession().setAttribute("admin_seq", result.getAdmin_seq()); //번호
				req.getSession().setAttribute("pic", result.getPic()); //프로필 사진 경로
				
				req.getSession().setAttribute("Admin_seq", result.getAdmin_seq()); //관리자 seq
				
				resp.sendRedirect("/neulbom/admin/index.do");
				System.out.println("사진" + result.getPic());
				return;
				
			} else {
				
				//로그인 실패
				PrintWriter writer = resp.getWriter();
				writer.print("<script>alert('failed');history.back();</script>");
				writer.close();
				
			}
		}
		
		

		resp.sendRedirect("/neulbom/client/board/qna.do");
		
//		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/client/account/login.jsp");
//		dispatcher.forward(req, resp);
	}

}
