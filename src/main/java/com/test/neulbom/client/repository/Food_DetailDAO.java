package com.test.neulbom.client.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.test.my.DBUtil;

public class Food_DetailDAO {
	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private ResultSet rs;

	public Food_DetailDAO() {
		this.conn = DBUtil.open("180.68.11.121", "hr", "java1234");
	}

	public Food_DetailDTO get(String food_seq) {

		try {

			String sql = "select * from tblfood where food_seq = ?";

			pstat = conn.prepareStatement(sql);

			pstat.setString(1, food_seq);

			rs = pstat.executeQuery();

			if (rs.next()) {

				Food_DetailDTO dto = new Food_DetailDTO();
				
				dto.setFood_seq(rs.getString("food_seq"));
				dto.setTitle(rs.getString("title"));
				dto.setContent(rs.getString("content"));
				dto.setFood_date(rs.getString("food_date"));
				dto.setRead(rs.getString("read"));

				return dto;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	public void increaseReadCount(String foodSeq) {
	    try {
	        String sql = "UPDATE tblfood SET read = read + 1 WHERE food_seq = ?";
	        PreparedStatement pstmt = conn.prepareStatement(sql);
	        pstmt.setString(1, foodSeq);
	        pstmt.executeUpdate();
	        
	        pstmt.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	
	
}
