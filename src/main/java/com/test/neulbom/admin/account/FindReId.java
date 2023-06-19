package com.test.neulbom.admin.account;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.neulbom.admin.repository.ResiDAO;
import com.test.neulbom.admin.repository.ResiDTO;

@WebServlet("/admin/account/findreid.do")
public class FindReId extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		//FindReId.java

		RequestDispatcher dispatcher = req
				.getRequestDispatcher("/WEB-INF/views/admin/account/findreid.jsp");
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	    // 넘어온 파라미터의 인코딩 설정을 UTF-8로 설정
        req.setCharacterEncoding("utf-8");
     
        // HTML이 UTF-8 형식이라는 것을 브라우저에게 전달
        resp.setContentType("text/html; charset=utf-8");
     
        // 서블릿을 통해 생성되는 HTML 파일의 인코딩을 UTF-8로 설정
        resp.setCharacterEncoding("utf-8");
		
		//1. searchadid.jsp에서 form으로 보낸 데이터를 받음
		String name = req.getParameter("name");
		String ssn = req.getParameter("ssn");
		
		//dto에 해당 정보를 받아서 DAO로 보내 DB로 찾음
		//해당 정보가 있으면 dto값이, 없으면 null값이 들어온다
		ResiDAO dao = new ResiDAO();
		ResiDTO dto = dao.find_id(name, ssn);
		System.out.println(name);
		

		//회원정보 불일치 시
		//dto값이 null이거나 입력한 전화번호와 DB의 전화번호가 일치하지 않으면 message를 넘겨준다 → findadid.jsp로 이동

		//회원정보 일치 시 message에 아이디를 넣어서 findadid.jsp로 이동
		
		String message="";
		
		
		if (dto == null || !dto.getName().equals(name) || !dto.getSsn().equals(ssn)){
			message = "회원정보가 존재하지 않습니다.";
			req.setAttribute("message", message);
			RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/admin/account/findreid.jsp");
			dispatcher.forward(req, resp);
			
			return;	//로그인에 실패하면 밑으로는 실행 X
	
		} else if(dto != null && dto.getName().equals(name) && dto.getSsn().equals(ssn)) {
		
			message = "입력하신 정보로 조회된 아이디는 [ " + dto.getId() + " ] 입니다.";
			
			req.setAttribute("message", message);
			RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/admin/account/findreid.jsp");
			dispatcher.forward(req, resp);
		}
		

	}

}
