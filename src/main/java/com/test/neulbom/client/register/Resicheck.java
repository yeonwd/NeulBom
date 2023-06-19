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

@WebServlet("/client/register/resicheck.do")
public class Resicheck extends HttpServlet {
	
	private Connection conn;
	private PreparedStatement pstat;
	private ResultSet rs;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	    String resiId = req.getParameter("resiId"); // userId GET 파라미터 값 받아오기
	    
	    
	    try {
	        conn = DBUtil3.open();
	        String sql = "SELECT r.name, r.tel, r.id\r\n"
	                + "FROM tblresident r\r\n"
	                + "JOIN tblprotect p ON r.resi_seq = p.resi_seq where r.id = ?";

	        pstat = conn.prepareStatement(sql);
	        pstat.setString(1, resiId);
	        rs = pstat.executeQuery();

	        if (rs.next()) {
	            String resiNameCheck = rs.getString("name");
	            String resiTelCheck = rs.getString("tel");
	            String resiIdCheck = rs.getString("id");

	            // 요청 객체에 값을 저장
	            req.setAttribute("resiName", resiNameCheck);
	            req.setAttribute("resiTel", resiTelCheck);
	            req.setAttribute("resiId", resiIdCheck);
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    } 

	    // JSP 페이지로 포워딩
	    RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/client/register/resicheck.jsp");
	    dispatcher.forward(req, resp);
	}
}

