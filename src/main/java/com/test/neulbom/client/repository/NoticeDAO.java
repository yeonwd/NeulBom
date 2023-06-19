package com.test.neulbom.client.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.test.my.DBUtil;

import oracle.jdbc.proxy.annotation.Post;

public class NoticeDAO {
	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private ResultSet rs;

	public NoticeDAO() {
		this.conn = DBUtil.open("180.68.11.121", "hr", "java1234");
	}

	
	
	
	
	public List<NoticeDTO> list(HashMap<String, String> map) {
		
		
		
		try {

			String sql = String.format("select * from (select rownum as rnum, a.* from tblnotice a) where rnum between %s and %s"
					, map.get("begin")
					, map.get("end"));

			stat = conn.createStatement();
			rs = stat.executeQuery(sql);

			List<NoticeDTO> list = new ArrayList<NoticeDTO>();

			while (rs.next()) {

				NoticeDTO dto = new NoticeDTO();
				
				dto.setRnum(rs.getString("rnum"));
				dto.setNotice_seq(rs.getString("notice_seq"));
				dto.setTitle(rs.getString("title"));
				dto.setContent(rs.getString("content"));
				dto.setNotice_date(rs.getString("notice_date"));
				dto.setRead(rs.getString("read"));
				
				list.add(dto);
			}

			return list;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	



	public List<NoticeDTO> search(String searchType, String keyword) {
		try {

			String sql = String.format("SELECT * FROM (select rownum as rnum, a.* from tblnotice a) where %s like '%%%s%%' ORDER BY notice_date DESC"
																	, searchType, keyword
																	);
			
//			System.out.println(sql);

			stat = conn.createStatement();
			
			rs = stat.executeQuery(sql);

			List<NoticeDTO> list = new ArrayList<NoticeDTO>();

			while (rs.next()) {

				NoticeDTO dto = new NoticeDTO();
				
				dto.setRnum(rs.getString("rnum"));
				dto.setNotice_seq(rs.getString("notice_seq"));
				dto.setTitle(rs.getString("title"));
				dto.setContent(rs.getString("content"));
				dto.setNotice_date(rs.getString("notice_date"));
				dto.setRead(rs.getString("read"));
				
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
			String sql = String.format("select count(*) as cnt from tblnotice %s", where);
			
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
	
		 
				String sql = String.format("select count(*) as cnt from tblnotice %s", where);
				
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

