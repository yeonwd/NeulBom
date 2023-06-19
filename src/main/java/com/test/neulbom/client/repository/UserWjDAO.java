package com.test.neulbom.client.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.test.neulbom.mylib.DBUtil3;

public class UserWjDAO {
	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private ResultSet rs;

	public UserWjDAO() {
		this.conn = DBUtil3.open();
	}

	public String check(String rid) {
	    try {
	        String sql = "SELECT resi_seq FROM tblresident WHERE id = ?";
	        pstat = conn.prepareStatement(sql);
	        pstat.setString(1, rid);
	        rs = pstat.executeQuery();

	        if (rs.next()) {
	            return rs.getString("resi_seq");
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    
	    return null; // 혹은 원하는 기본값을 반환할 수 있습니다.
	}

	public int add(UserWjDTO dto) {
	    try {
	    	String sql = "INSERT INTO tblprotect (protect_seq, id, pw, name, ssn, tel, email, resi_seq, relation, address, lv, rid) "
	    	        + "VALUES (rsequence.nextVal, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

	    	pstat = conn.prepareStatement(sql);
	    	pstat.setString(1, dto.getPid()); // id에 해당하는 필드
	    	pstat.setString(2, dto.getPpw()); // pw에 해당하는 필드
	    	pstat.setString(3, dto.getPname()); // name에 해당하는 필드
	    	pstat.setString(4, dto.getPssn()); // ssn에 해당하는 필드
	    	pstat.setString(5, dto.getPtel()); // tel에 해당하는 필드
	    	pstat.setString(6, dto.getPemail()); // email에 해당하는 필드
	    	pstat.setString(7, dto.getRseq()); // resi_seq에 해당하는 필드
	    	pstat.setString(8, dto.getRela()); // relation에 해당하는 필드
	    	pstat.setString(9, dto.getPadr()); // address에 해당하는 필드
	    	pstat.setString(10, "7"); // lv에 해당하는 필드
	    	pstat.setString(11, dto.getRid()); // rid에 해당하는 필드

	        
	        return pstat.executeUpdate();
	        
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return 0;  // 회원 추가 실패 시 0 반환
	}

	
}
