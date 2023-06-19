package com.test.neulbom.client.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.test.my.DBUtil;

public class Mypage_InfoDAO {
	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private ResultSet rs;

	public Mypage_InfoDAO() {
		this.conn = DBUtil.open("180.68.11.121", "hr", "java1234");
	}

	public Mypage_InfoDTO resi_list(String seq) {

		try {

			/*
			 * String sql =
			 * "select r.resi_seq as resi_seq, r.id as resi_id, r.name as resi_name, r.ssn as resi_ssn, "
			 * +
			 * "r.tel as resi_tel, r.email as resi_email, r.address as resi_address, r.lv as resi_lv, "
			 * +
			 * "p.protect_seq as pro_seq, p.id as pro_id, p.name as pro_name, p.ssn as pro_ssn, "
			 * +
			 * "p.tel as pro_tel, p.email as pro_email, p.relation as pro_relation, p.lv as pro_lv "
			 * + "from tblresident r" +
			 * "inner join tblprotect p on r.resi_seq = p.resi_seq " +
			 * "where r.resi_seq = ?";
			 */
			String sql = "select r.resi_seq, r.id as resi_id, r.name as resi_name, r.ssn as resi_ssn, "
		            + "r.tel as resi_tel, r.email as resi_email, r.address as resi_address, r.lv as resi_lv, "
		            + "p.protect_seq as pro_seq, p.id as pro_id, p.name as pro_name, p.ssn as pro_ssn, "
		            + "p.tel as pro_tel, p.email as pro_email, p.relation as pro_relation, p.lv as pro_lv "
		            + "from tblresident r "
		            + "left outer join tblprotect p on r.resi_seq = p.resi_seq "
		            + "where r.resi_seq = ?";

			pstat = conn.prepareStatement(sql);

			pstat.setString(1, seq);

			rs = pstat.executeQuery();

			Mypage_InfoDTO dto = new Mypage_InfoDTO();

			if (rs.next()) {

				
				dto.setResi_seq(rs.getString("resi_seq"));
				dto.setResi_id(rs.getString("resi_id"));
				dto.setResi_name(rs.getString("resi_name"));
				dto.setResi_ssn(rs.getString("resi_ssn"));
				dto.setResi_tel(rs.getString("resi_tel"));
				dto.setResi_email(rs.getString("resi_email"));
				dto.setResi_address(rs.getString("resi_address"));
				dto.setResi_lv(rs.getString("resi_lv"));

				dto.setProtect_seq(rs.getString("pro_seq"));
				dto.setPro_id(rs.getString("pro_id"));
				dto.setPro_name(rs.getString("pro_name"));
				dto.setPro_ssn(rs.getString("pro_ssn"));
				dto.setPro_tel(rs.getString("pro_tel"));
				dto.setPro_email(rs.getString("pro_email"));
				dto.setPro_relation(rs.getString("pro_relation"));
				dto.setPro_lv(rs.getString("pro_lv"));

				return dto;
				
			}


		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return null;
	}

	public Mypage_InfoDTO pro_list(String seq) {

		try {

			String sql = "select r.resi_seq, r.id as resi_id, r.name as resi_name, r.ssn as resi_ssn, "
		            + "r.tel as resi_tel, r.email as resi_email, r.address as resi_address, r.lv as resi_lv, "
		            + "p.protect_seq as pro_seq, p.id as pro_id, p.name as pro_name, p.ssn as pro_ssn, "
		            + "p.tel as pro_tel, p.email as pro_email, p.relation as pro_relation, p.lv as pro_lv "
		            + "from tblresident r "
		            + "left outer join tblprotect p on r.resi_seq = p.resi_seq "
		            + "where p.protect_seq = ?";

			pstat = conn.prepareStatement(sql);

			pstat.setString(1, seq);

			rs = pstat.executeQuery();

			Mypage_InfoDTO dto = new Mypage_InfoDTO();

			if (rs.next()) {

				
				dto.setResi_seq(rs.getString("resi_seq"));
				dto.setResi_id(rs.getString("resi_id"));
				dto.setResi_name(rs.getString("resi_name"));
				dto.setResi_ssn(rs.getString("resi_ssn"));
				dto.setResi_tel(rs.getString("resi_tel"));
				dto.setResi_email(rs.getString("resi_email"));
				dto.setResi_address(rs.getString("resi_address"));
				dto.setResi_lv(rs.getString("resi_lv"));

				dto.setProtect_seq(rs.getString("pro_seq"));
				dto.setPro_id(rs.getString("pro_id"));
				dto.setPro_name(rs.getString("pro_name"));
				dto.setPro_ssn(rs.getString("pro_ssn"));
				dto.setPro_tel(rs.getString("pro_tel"));
				dto.setPro_email(rs.getString("pro_email"));
				dto.setPro_relation(rs.getString("pro_relation"));
				dto.setPro_lv(rs.getString("pro_lv"));

				return dto;
			}


		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return null;
	}
	
	
	
	
}
