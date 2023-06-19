package com.test.neulbom.client.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.test.my.DBUtil;

public class Mypage_PaymentDAO {
	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private ResultSet rs;

	public Mypage_PaymentDAO() {
		this.conn = DBUtil.open("180.68.11.121", "hr", "java1234");
	}

	public Mypage_PaymentDTO list(String seq, String pay_seq) {

		try {

			String sql = "SELECT a.pay_seq, a.resi_seq, a.ispay, a.pay_date, b.name AS resi_name, c.name AS pro_name, b.id as resi_id, c.id as pro_id, b.tel, b.email, c.relation "
					+ "FROM tblPay a " + "INNER JOIN tblresident b ON a.resi_seq = b.resi_seq "
					+ "INNER JOIN tblprotect c ON b.resi_seq = c.resi_seq " + "WHERE a.resi_seq = ? and a.pay_seq = ?";

			

			pstat = conn.prepareStatement(sql);

			pstat.setString(1, seq);
			pstat.setString(2, pay_seq);
			rs = pstat.executeQuery();

			Mypage_PaymentDTO dto = new Mypage_PaymentDTO();

			if (rs.next()) {

				dto.setPay_seq(rs.getString("pay_seq"));
				dto.setResi_seq(rs.getString("resi_seq"));
				dto.setIspay(rs.getString("ispay"));
				dto.setPay_date(rs.getString("pay_date"));
				dto.setResi_name(rs.getString("resi_name"));
				dto.setPro_name(rs.getString("pro_name"));
				dto.setPro_id(rs.getString("pro_id"));
				dto.setTel(rs.getString("tel"));
				dto.setEmail(rs.getString("email"));
				dto.setRelation(rs.getString("relation"));
				dto.setResi_id(rs.getString("resi_id"));

			}

			return dto;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	

}
