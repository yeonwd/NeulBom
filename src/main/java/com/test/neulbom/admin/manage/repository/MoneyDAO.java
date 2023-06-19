package com.test.neulbom.admin.manage.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.test.neulbom.mylib.DBUtil3;

public class MoneyDAO {
	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private ResultSet rs;

	public MoneyDAO() {
		this.conn = DBUtil3.open();
	}

	public List<SpendDTO> getLatestSpend() {
		
		List<SpendDTO> latestSpendList = new ArrayList<SpendDTO>();
		
		try {
			
			String sql = "select rownum, a.* from (select to_char(sdate, 'yyyy-mm-dd') as sdate, title, category, to_char(money, 'FM9,999,999') || '원' as money from tblSpend order by sdate desc) a where rownum <=10";
			
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			
			while(rs.next()) {
				SpendDTO spendDto = new SpendDTO();
				
				spendDto.setRownum(rs.getInt("rownum"));
				spendDto.setSdate(rs.getString("sdate"));
				spendDto.setTitle(rs.getString("title"));
				spendDto.setCategory(rs.getString("category"));
				spendDto.setMoney(rs.getString("money"));
				
				latestSpendList.add(spendDto);
			}
			
			return latestSpendList;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return latestSpendList;
	}

	// 최근 지출 내역 총합 구하기
	public String getSpendSum() {
		
		try {
			
			String sql = "select to_char(sum(money), 'FM9,999,999,999') || '원' as spendSum from vwLatestSpend";
			
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			
			while (rs.next()) {
				
				return rs.getString("spendSum");
				
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return null;
	}

	public List<SpendDTO> getMonthlySpendList() {
		
		List<SpendDTO> monthlySpendList = new ArrayList<SpendDTO>();
		
		try {
			
			String sql = "select rownum, a.* from(select * from vwMonthly order by monthly desc) a where rownum <= 5";
			
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			
			while (rs.next()) {
				
				SpendDTO monthylSpendDto = new SpendDTO();
				
				monthylSpendDto.setSdate(rs.getString("sdate"));
				monthylSpendDto.setMonthly(rs.getInt("monthly"));
				
				monthlySpendList.add(monthylSpendDto);
				
			}
			
			return monthlySpendList;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return monthlySpendList;
	}

}

















