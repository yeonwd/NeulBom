package com.test.neulbom.client.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.test.my.DBUtil;

public class Life_DetailDAO {
	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private ResultSet rs;

	public Life_DetailDAO() {
		this.conn = DBUtil.open("180.68.11.121", "hr", "java1234");
	}

	
	public Life_DetailDTO get(String life_seq) {

		try {

			String sql = "select * from tbllife where life_seq = ?";

			pstat = conn.prepareStatement(sql);

			pstat.setString(1, life_seq);

			rs = pstat.executeQuery();

			if (rs.next()) {

				Life_DetailDTO dto = new Life_DetailDTO();
				
				dto.setLife_seq(rs.getString("life_seq"));
				dto.setTitle(rs.getString("title"));
				dto.setContent(rs.getString("content"));
				dto.setLife_date(rs.getString("life_date"));
				dto.setRead(rs.getString("read"));

				return dto;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	public void increaseReadCount(String lifeSeq) {
		try {
	        String sql = "UPDATE tbllife SET read = read + 1 WHERE life_seq = ?";
	        PreparedStatement pstmt = conn.prepareStatement(sql);
	        pstmt.setString(1, lifeSeq);
	        pstmt.executeUpdate();
	        
	        pstmt.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
		
	}
	

}
