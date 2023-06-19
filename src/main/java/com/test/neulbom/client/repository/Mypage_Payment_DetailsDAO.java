package com.test.neulbom.client.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.test.my.DBUtil;

public class Mypage_Payment_DetailsDAO {
	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private ResultSet rs;

	public Mypage_Payment_DetailsDAO() {
		this.conn = DBUtil.open("180.68.11.121", "hr", "java1234");
	}


	public List<Mypage_Payment_DetailsDTO> pro_list(String seq) {
		try {

			String sql = "SELECT a.pay_seq, a.resi_seq, a.ispay, a.pay_date, b.name AS resi_name, c.name AS pro_name, b.id "
			        + "FROM tblPay a "
			        + "INNER JOIN tblresident b ON a.resi_seq = b.resi_seq "
			        + "INNER JOIN tblprotect c ON b.resi_seq = c.resi_seq "
			        + "WHERE a.resi_seq = ? "
			        + "ORDER BY a.ispay ASC, a.pay_date desc";


			pstat = conn.prepareStatement(sql);

			pstat.setString(1, seq);

			rs = pstat.executeQuery();

			List<Mypage_Payment_DetailsDTO> list = new ArrayList<Mypage_Payment_DetailsDTO>();

			while (rs.next()) {

				Mypage_Payment_DetailsDTO dto = new Mypage_Payment_DetailsDTO();

				dto.setPay_seq(rs.getString("pay_seq"));
				dto.setResi_seq(rs.getString("resi_seq"));
				dto.setIspay(rs.getString("ispay"));
				dto.setPay_date(rs.getString("pay_date"));
				dto.setResi_name(rs.getString("resi_name"));
				dto.setPro_name(rs.getString("pro_name"));
				dto.setId(rs.getString("id"));

				list.add(dto);
			}

			return list;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}



	public List<Mypage_Payment_DetailsDTO> list_resi(String seq) {
		try {

			String sql = "SELECT a.pay_seq, a.resi_seq, a.ispay, a.pay_date, b.name AS resi_name, c.name AS pro_name, b.id "
			        + "FROM tblPay a "
			        + "INNER JOIN tblresident b ON a.resi_seq = b.resi_seq "
			        + "INNER JOIN tblprotect c ON b.resi_seq = c.resi_seq "
			        + "WHERE a.resi_seq = ? "
			        + "ORDER BY a.ispay ASC, a.pay_date desc";


			pstat = conn.prepareStatement(sql);

			pstat.setString(1, seq);

			rs = pstat.executeQuery();

			List<Mypage_Payment_DetailsDTO> list = new ArrayList<Mypage_Payment_DetailsDTO>();

			while (rs.next()) {

				Mypage_Payment_DetailsDTO dto = new Mypage_Payment_DetailsDTO();

				dto.setPay_seq(rs.getString("pay_seq"));
				dto.setResi_seq(rs.getString("resi_seq"));
				dto.setIspay(rs.getString("ispay"));
				dto.setPay_date(rs.getString("pay_date"));
				dto.setResi_name(rs.getString("resi_name"));
				dto.setPro_name(rs.getString("pro_name"));
				dto.setId(rs.getString("id"));

				list.add(dto);
			}

			return list;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
