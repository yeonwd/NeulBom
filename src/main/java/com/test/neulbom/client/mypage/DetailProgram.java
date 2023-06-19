package com.test.neulbom.client.mypage;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.test.neulbom.client.repository.MyProgramDTO;
import com.test.neulbom.client.repository.ProgramDAO;

@WebServlet("/client/mypage/detailprogram.do")
public class DetailProgram extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//DetailProgram.java
		
		//등록되어있는 복지프로그램의 상세내역 받아오기
		
		HttpSession session = req.getSession();
		
		String prog_seq = req.getParameter("prog_seq");
		String resi_seq = (String)session.getAttribute("resi_seq");
		
		
		MyProgramDTO dto = new MyProgramDTO();
		List<MyProgramDTO> pdto = new ArrayList<MyProgramDTO>();
		ProgramDAO dao = new ProgramDAO();
		
		dto = dao.detailProgram(prog_seq);	//내가 선택한 프로그램 번호
		String result = dao.myplist(resi_seq, prog_seq);		//내가 이미 신청한 프로그램 번호
		
//		pdto = dao.regiProgram(prog_seq, resi_seq);
		
		
		//마감 날짜 구하기 (현재날짜 -14일)
		Date date = new Date();
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -14);	//현재 날짜 -14
		Date limit = cal.getTime();

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");	//날짜 형식 변경
		String limitDate = dateFormat.format(limit);
		
		
		req.setAttribute("dto", dto);
		req.setAttribute("result", result);
		req.setAttribute("limitDate", limitDate);

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/client/mypage/detailprogram.jsp");
		dispatcher.forward(req, resp);

	}
	
	

}