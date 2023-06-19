package com.test.neulbom.client.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.test.neulbom.mylib.DBUtil3;

public class NomemWjDAO {
	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private ResultSet rs;

	public NomemWjDAO() {
		this.conn = DBUtil3.open();
	}

	public int add(NomemWjDTO dto) {
	    try {
	    	String sql = "INSERT INTO tblnomem (nomem_seq, name, tel) "
	    	        + "VALUES (nsequence.nextVal, ?, ?)";

	    	pstat = conn.prepareStatement(sql);
	    	pstat.setString(1, dto.getPname()); // name에 해당하는 필드
	    	pstat.setString(2, dto.getPtel()); // tel에 해당하는 필드
	        return pstat.executeUpdate();
	        
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return 0;  // 회원 추가 실패 시 0 반환
	}

	public ConsultDTOWj show(String con_seq) {
		try {

			String sql = "select * from (select rownum as rnum, a.* from (select * from vwconsult order by con_seq desc) a) where con_seq = ?";

			pstat = conn.prepareStatement(sql);
			pstat.setString(1, con_seq);

			rs = pstat.executeQuery();

			if (rs.next()) {
				ConsultDTOWj dto = new ConsultDTOWj();
				dto.setCon_seq(rs.getString("con_seq"));
				dto.setTitle(rs.getString("con_title"));
				dto.setName(rs.getString("nomem_name"));
				dto.setCon_date(rs.getString("con_date"));
				dto.setContent(rs.getString("con_content"));
				dto.setRetitle(rs.getString("title"));
				dto.setIsreply(rs.getString("isreply"));
				dto.setRecontent(rs.getString("content"));
				dto.setRnum(rs.getString("rnum"));
				return dto;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
		
	}

	public String getnoseq(NomemWjDTO dto) {
	    try {
	        String sql = "SELECT MAX(nomem_seq) AS max_seq FROM tblnomem WHERE name = ? AND tel = ?";
	        pstat = conn.prepareStatement(sql);
	        pstat.setString(1, dto.getPname());
	        pstat.setString(2, dto.getPtel());
	        ResultSet rs = pstat.executeQuery();
	        if (rs.next()) {
	            return rs.getString("max_seq");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return null;
	}



	public int check(NomemWjDTO dto) {
		 try {
		    	String sql = "select * from vwconsult where nomem_name = ? and nomemtel = ?";
    	
		    	pstat = conn.prepareStatement(sql);
		    	pstat.setString(1, dto.getPname()); // name에 해당하는 필드
		    	pstat.setString(2, dto.getPtel()); // tel에 해당하는 필드
		        return pstat.executeUpdate();
		        
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		    return 0;  // 회원 추가 실패 시 0 반환
	}
	

	
}
