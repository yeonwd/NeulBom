package com.test.neulbom.client.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.test.neulbom.admin.repository.AdminDTO;
import com.test.neulbom.mylib.DBUtil3;

public class UserDAO {

	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private ResultSet rs;
	
	public UserDAO() {
		this.conn = DBUtil3.open();
	}

	public ResiDTO login(ResiDTO rdto) {

		try {

			String sql = "select * from tblResident where id = ? and pw = ?";	

			pstat = conn.prepareStatement(sql);

			pstat.setString(1, rdto.getId());
			pstat.setString(2, rdto.getPw());

			rs = pstat.executeQuery();

			if (rs.next()) {

				ResiDTO result = new ResiDTO();

				result.setId(rs.getString("id"));
				result.setLv(rs.getString("lv"));
				result.setName(rs.getString("name"));
				result.setResi_seq(rs.getString("resi_seq"));
				
				return result;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
		
	}
	
	public ProtectDTO login(ProtectDTO pdto) {
		
		try {
			
			String sql = "select * from tblProtect where id = ? and pw = ?";	
			
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, pdto.getId());
			pstat.setString(2, pdto.getPw());
			
			rs = pstat.executeQuery();
			
			if (rs.next()) {
				
				ProtectDTO result = new ProtectDTO();
				
				result.setId(rs.getString("id"));
				result.setLv(rs.getString("lv"));
				result.setName(rs.getString("name"));
				result.setProtect_seq(rs.getString("protect_seq"));
				result.setResi_seq(rs.getString("resi_seq"));	//추가
				
				return result;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
		
	}
	
	public AdminDTO login(AdminDTO adto) {
		
		try {
			
			String sql = "select * from tblAdmin where id = ? and pw = ?";	
			
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, adto.getId());
			pstat.setString(2, adto.getPw());
			
			rs = pstat.executeQuery();
			
			if (rs.next()) {
				
				AdminDTO result = new AdminDTO();
				
				result.setId(rs.getString("id"));
				result.setLv(rs.getString("lv"));
				result.setName(rs.getString("name"));
				result.setPic(rs.getString("pic"));
				result.setAdmin_seq(rs.getString("admin_seq"));
				
				return result;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
		
	}
	
}

