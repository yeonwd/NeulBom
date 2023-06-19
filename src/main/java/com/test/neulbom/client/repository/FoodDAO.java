package com.test.neulbom.client.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.test.my.DBUtil;

public class FoodDAO {
	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private ResultSet rs;

	public FoodDAO() {
		this.conn = DBUtil.open("180.68.11.121", "hr", "java1234");
	}

	public List<FoodDTO> list(HashMap<String, String> map) {

		try {

			String sql = String.format("select * from (select rownum as rnum, a.* from tblfood a) where rnum between %s and %s"
					, map.get("begin")
					, map.get("end"));

			stat = conn.createStatement();
			rs = stat.executeQuery(sql);

			List<FoodDTO> list = new ArrayList<FoodDTO>();

			while (rs.next()) {

				FoodDTO dto = new FoodDTO();
				
				dto.setRnum(rs.getString("rnum"));
				dto.setFood_seq(rs.getString("food_seq"));
				dto.setFood_date(rs.getString("food_date"));
				dto.setContent(rs.getString("content"));
				dto.setRead(rs.getString("read"));
				dto.setTitle(rs.getString("title"));

				list.add(dto);
			}

			return list;

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	public List<FoodDTO> search(String searchType, String keyword) {
		try {

			String sql = String.format("SELECT * FROM (select rownum as rnum, a.* from tblfood a) where %s like '%%%s%%' ORDER BY food_date DESC", searchType, keyword);

			stat = conn.createStatement();
			rs = stat.executeQuery(sql);

			List<FoodDTO> list = new ArrayList<FoodDTO>();

			while (rs.next()) {

				FoodDTO dto = new FoodDTO();
				
				dto.setRnum(rs.getString("rnum"));
				dto.setFood_seq(rs.getString("food_seq"));
				dto.setFood_date(rs.getString("food_date"));
				dto.setContent(rs.getString("content"));
				dto.setRead(rs.getString("read"));
				dto.setTitle(rs.getString("title"));

				list.add(dto);
			}

			return list;

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	public int getTotalCount(HashMap<String, String> map) {

		try {

			String sql = "select count(*) as cnt from tblfood";

			pstat = conn.prepareStatement(sql);

			rs = pstat.executeQuery();

			if (rs.next()) {

				return rs.getInt("cnt");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		

		
		return 0;
	}

	public int getTotalCount2(HashMap<String, String> map) {
		try {
			
			String where = String.format("where %s like '%%%s%%'" , map.get("searchType") ,
					 map.get("keyword"));
			
			String sql = "select count(*) as cnt from tblfood";

			pstat = conn.prepareStatement(sql);

			rs = pstat.executeQuery();

			if (rs.next()) {

				return rs.getInt("cnt");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		

		
		return 0;
	}
	

	
	
}
