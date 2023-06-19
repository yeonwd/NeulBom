package com.test.neulbom.client.register;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.neulbom.admin.repository.AdminDTO;
import com.test.neulbom.mylib.DBUtil3;

@WebServlet("/client/register/idcheck.do")
public class Idcheck extends HttpServlet {
	
	private Connection conn;
	private PreparedStatement pstat;
	private ResultSet rs;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String userId = req.getParameter("userId"); // userId GET 파라미터 값 받아오기
		
		
		try {
			conn = DBUtil3.open();
			String sql = "SELECT * FROM tblprotect WHERE id = ?";
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, userId);
			rs = pstat.executeQuery();
			
			
			if (rs.next()) {
			    String userIdChk = rs.getString("id");
			    req.setAttribute("userIdChk", userIdChk);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/client/register/idcheck.jsp");
		dispatcher.forward(req, resp);
	}
}

