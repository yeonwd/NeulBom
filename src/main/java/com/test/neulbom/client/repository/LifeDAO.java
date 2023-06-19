package com.test.neulbom.client.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.test.my.DBUtil;
import com.test.neulbom.client.repository.LifeDTO;

public class LifeDAO {
	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private ResultSet rs;

	public LifeDAO() {
		this.conn = DBUtil.open("180.68.11.121", "hr", "java1234");
	}
	
	
	public List<LifeDTO> list(HashMap<String, String> map) {
		
		
		
		try {

			String sql = String.format("select * from (select rownum as rnum, a.* from tbllife a) where rnum between %s and %s order by life_date desc"
					, map.get("begin")
					, map.get("end"));

			stat = conn.createStatement();
			rs = stat.executeQuery(sql);

			List<LifeDTO> list = new ArrayList<LifeDTO>();

			while (rs.next()) {

				LifeDTO dto = new LifeDTO();
				
				dto.setRnum(rs.getString("rnum"));
				dto.setLife_seq(rs.getString("life_seq"));
				dto.setTitle(rs.getString("title")); dto.setContent(rs.getString("content"));
				dto.setRead(rs.getString("read"));
				dto.setLife_date(rs.getString("life_date"));
				
				list.add(dto);
			}

			return list;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/*
	 * public List<NoticeDTO> list(HashMap<String, String> map) {
	 * 
	 * try {
	 * 
	 * String sql = "select * from tblLife order by life_date desc";
	 * 
	 * stat = conn.createStatement(); rs = stat.executeQuery(sql);
	 * 
	 * List<LifeDTO> list = new ArrayList<LifeDTO>();
	 * 
	 * while (rs.next()) {
	 * 
	 * LifeDTO dto = new LifeDTO();
	 * 
	 * dto.setLife_seq(rs.getString("life_seq"));
	 * dto.setTitle(rs.getString("title")); dto.setContent(rs.getString("content"));
	 * dto.setRead(rs.getString("read"));
	 * dto.setLife_date(rs.getString("life_date"));
	 * 
	 * list.add(dto); }
	 * 
	 * return list;
	 * 
	 * } catch (Exception e) { e.printStackTrace(); }
	 * 
	 * return null; }
	 */

	public List<LifeDTO> search(String searchType, String keyword) {

		try {

			String sql = String.format("select * from (select rownum as rnum, a.* from tbllife a) where %s like '%%%s%%' order by life_date desc",
					searchType, keyword);

			stat = conn.createStatement();
			rs = stat.executeQuery(sql);

			List<LifeDTO> list = new ArrayList<LifeDTO>();

			while (rs.next()) {

				LifeDTO dto = new LifeDTO();

				dto.setRnum(rs.getString("rnum"));
				dto.setLife_seq(rs.getString("life_seq"));
				dto.setTitle(rs.getString("title"));
				dto.setContent(rs.getString("content"));
				dto.setRead(rs.getString("read"));
				dto.setLife_date(rs.getString("life_date"));

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

			String where = "";

			/*
			 * if(map.get("search").equals("y")) { where =
			 * String.format("where %s like '%%%s%%'" , map.get("column") ,
			 * map.get("word")); }
			 */
			String sql = String.format("select count(*) as cnt from tblLife %s", where);

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

			String where = String.format("where %s like '%%%s%%'", map.get("searchType"), map.get("keyword"));

			String sql = String.format("select count(*) as cnt from tblLife %s", where);

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
