package com.test.neulbom.client.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.test.my.DBUtil;

public class Mypage_Payment_UpdateDAO {
	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private ResultSet rs;

	public Mypage_Payment_UpdateDAO() {
		this.conn = DBUtil.open("180.68.11.121", "hr", "java1234");
	}
	
	
	public int update(String seq, String pay_seq) {

		try {

			String sql = "UPDATE tblPay SET ispay = 'y' WHERE resi_seq = ? and pay_seq = ?";

			pstat = conn.prepareStatement(sql);

			pstat.setString(1, seq);
			pstat.setString(2, pay_seq);

			return pstat.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return 0;

	}
}
