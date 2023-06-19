package com.test.neulbom.client.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.test.my.DBUtil;

public class Notice_DetailDAO {
	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private ResultSet rs;

	public Notice_DetailDAO() {
		this.conn = DBUtil.open("180.68.11.121", "hr", "java1234");
	}


	public Notice_DetailDTO get(String notice_seq) {

		try {

			String sql = "select * from tblnotice where notice_seq = ?";

			pstat = conn.prepareStatement(sql);

			pstat.setString(1, notice_seq);

			rs = pstat.executeQuery();

			if (rs.next()) {

				Notice_DetailDTO dto = new Notice_DetailDTO();
				
				dto.setNotice_seq(rs.getString("notice_seq"));
				dto.setTitle(rs.getString("title"));
				dto.setContent(rs.getString("content"));
				dto.setNotice_date(rs.getString("notice_date"));
				dto.setRead(rs.getString("read"));

				return dto;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	public void increaseReadCount(String noticeSeq) {
	    try {
	        String sql = "UPDATE tblnotice SET read = read + 1 WHERE notice_seq = ?";
	        PreparedStatement pstmt = conn.prepareStatement(sql);
	        pstmt.setString(1, noticeSeq);
	        pstmt.executeUpdate();
	        
	        pstmt.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

	
	
	 public List<NoticeDTO> searchByTitle(String keyword) {
	        List<NoticeDTO> resultList = new ArrayList<>();

	        try {
	            String sql = "SELECT * FROM tblnotice WHERE title LIKE ?";
	            pstat = conn.prepareStatement(sql);
	            pstat.setString(1, "%" + keyword + "%");
	            rs = pstat.executeQuery();

	            while (rs.next()) {
	            	NoticeDTO dto = new NoticeDTO();
	                dto.setTitle(rs.getString("title"));
	                dto.setContent(rs.getString("notice_date"));
	                // 필요한 속성들을 설정
	                resultList.add(dto);
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            close();
	        }

	        return resultList;
	    }

	    private void close() {
	        try {
	            if (rs != null) rs.close();
	            if (pstat != null) pstat.close();
	            if (conn != null) conn.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
}