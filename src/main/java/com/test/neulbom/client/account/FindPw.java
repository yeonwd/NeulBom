package com.test.neulbom.client.account;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.neulbom.client.repository.AccountDTO;
import com.test.neulbom.client.repository.ClientDAO;

@WebServlet("/client/account/findpw.do")
public class FindPw extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//FindPw.java

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/client/account/findpw.jsp");
		dispatcher.forward(req, resp);

	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	    // 넘어온 파라미터의 인코딩 설정을 UTF-8로 설정
        req.setCharacterEncoding("utf-8");
     
        // HTML이 UTF-8 형식이라는 것을 브라우저에게 전달
        resp.setContentType("text/html; charset=utf-8");
     
        // 서블릿을 통해 생성되는 HTML 파일의 인코딩을 UTF-8로 설정
        resp.setCharacterEncoding("utf-8");
		
		//1. searchid.jsp에서 form으로 보낸 데이터를 받음
		String name = req.getParameter("mem_name");
		String id = req.getParameter("mem_id");
		
		//dto에 해당 정보를 받아서 DAO로 보내 DB로 찾음
		//해당 정보가 있으면 dto값이, 없으면 null값이 들어온다
		ClientDAO dao = new ClientDAO();
		AccountDTO dto = dao.find_pw(name, id);

		//회원정보 불일치 시
		//dto값이 null이거나 입력한 전화번호와 DB의 전화번호가 일치하지 않으면 message를 넘겨준다 → findid.jsp로 이동

		//회원정보 일치 시 message에 아이디를 넣어서 findid.jsp로 이동
		
		String message="";
		
		
		if (dto == null || !dto.getName().equals(name) || !dto.getId().equals(id)){
			/*req.setAttribute("message", "회원정보가 존재하지 않습니다.");
			url = "/client/account/searchid.jsp";
			forward(req,resp,url);*/
			
			message = "회원정보가 존재하지 않습니다.";
			req.setAttribute("message", message);
			RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/client/account/findpw.jsp");
			dispatcher.forward(req, resp);
//			req.getRequestDispatcher("/client/account/findid.jsp").forward(req, resp);
			
			return;	//로그인에 실패하면 밑으로는 실행 X
	
		} else if(dto != null && dto.getName().equals(name) && dto.getId().equals(id)) {
//		
		
			message = "입력하신 정보로 조회된 비밀번호는 [ " + dto.getPw() + " ] 입니다.";
			
			req.setAttribute("message", message);
			RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/client/account/findpw.jsp");
			dispatcher.forward(req, resp);
//			req.getRequestDispatcher("/client/account/findid.do").forward(req, resp);
		}
		

	}

}