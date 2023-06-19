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

import com.test.neulbom.client.repository.Mypage_InfoDAO;
import com.test.neulbom.client.repository.Mypage_InfoDTO;

@WebServlet("/client/mypage/mypage_info.do")
public class Mypage_Info extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//Mypage_Info.java
		HttpSession session = req.getSession();
		
		Mypage_InfoDTO dto = new Mypage_InfoDTO();
		Mypage_InfoDAO dao = new Mypage_InfoDAO();
		String lv = (String) session.getAttribute("lv");
		
		String seq = (String)session.getAttribute("protect_seq");
		
		if(seq == null) {
			
			seq = (String)session.getAttribute("resi_seq");
			dto = dao.resi_list(seq); 
			
		}
		
		
		dto = dao.pro_list(seq); 
		
		//System.out.println(dto.getPro_id());
		
		req.setAttribute("dto", dto);
		req.setAttribute("lv", lv);

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/client/mypage/mypage_info.jsp");
		dispatcher.forward(req, resp);
	}

}