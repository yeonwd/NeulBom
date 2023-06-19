package com.test.neulbom.admin.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.test.neulbom.mylib.DBUtil3;

public class ResiDAO {
	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private ResultSet rs;
	
	public ResiDAO() {
		this.conn = DBUtil3.open();
	}
	public int register(ResiDTO dto) {
		try {

			String sql = "insert into tblresident (resi_seq, id, pw, name, ssn, tel, email, detail, address, lv) values (resi_seq.nextval, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

			pstat = conn.prepareStatement(sql);

			pstat.setString(1, dto.getId());
			pstat.setString(2, dto.getPw());
			pstat.setString(3, dto.getName());
			pstat.setString(4, dto.getSsn());
			pstat.setString(5, dto.getTel());
			pstat.setString(6, dto.getEmail());
			pstat.setString(7, dto.getDetail());
			pstat.setString(8, dto.getAddress());
			pstat.setString(9, dto.getLv());
			

			return pstat.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return 0;
	}
	public ResiDTO find_id(String name, String ssn) {

		try {
			
			String sql = "select * from tblResident where name = ? and ssn = ?";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, name);
			pstat.setString(2, ssn);
			
			
			rs = pstat.executeQuery();
			
			if (rs.next()) {
				
				ResiDTO dto = new ResiDTO();
				
				dto.setId(rs.getString("id"));
				dto.setName(rs.getString("name"));
				dto.setSsn(rs.getString("ssn"));
				
				return dto;
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
		
	}
	public ResiDTO find_pw(String id, String name, String ssn) {

		try {
			
			String sql = "select * from tblResident where id = ? and name = ? and ssn = ?";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, id);
			pstat.setString(2, name);
			pstat.setString(3, ssn);
			
			
			rs = pstat.executeQuery();
			
			if (rs.next()) {
				
				ResiDTO dto = new ResiDTO();
				
				dto.setId(rs.getString("id"));
				dto.setName(rs.getString("name"));
				dto.setSsn(rs.getString("ssn"));
				dto.setPw(rs.getString("pw"));
				
				return dto;
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
		
	}
	public List<ResiDTO> rlist(HashMap<String, String> map) {
		
		List<ResiDTO> list = new ArrayList<ResiDTO>();
		
		try {
			
			String where = "";
			
			//10 %% 5 = 
			if (map.get("search").equals("y")) {
				where = String.format("where %s like '%%%s%%'"
										, map.get("column")
										, map.get("word"));
			}

			String sql = String.format("select * from (select a.*, rownum as rnum from vwResident a %s order by resi_seq) where rnum between %s and %s"
                    , where
                    , map.get("begin")
                    , map.get("end")
                    );

			stat = conn.createStatement();
			rs = stat.executeQuery(sql);

			

			while (rs.next()) {

				ResiDTO dto = new ResiDTO();

				dto.setResi_seq(rs.getString("resi_seq"));
				dto.setName(rs.getString("name"));
				dto.setId(rs.getString("id"));
				dto.setPw(rs.getString("pw"));

				list.add(dto);
			}

			return list;

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
		
		
	}
	public int del(String resi_seq) {
		try {

			String sql = "delete from tblresident where resi_seq = ?";

			pstat = conn.prepareStatement(sql);

			pstat.setString(1, resi_seq);

			return pstat.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return 0;
	}
	
	// 소엽 - 재무관리 中 실버타운 입주자 수 구하기
	public int getSilverCount() {
		
		try {
			
			String sql = "select count(*) as silverCount from tblResident where lv = 5 group by lv order by lv asc";
			
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			
			if (rs.next()) {
				
				return rs.getInt("silverCount");
				
			}

			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return 0;
	}
	
	
	// 소엽 - 재무관리 中 요양원 입주자 수 구하기
	public int getCenterCount() {
	
		try {
			
			String sql = "select count(*) as centerCount from tblResident where lv = 6 group by lv order by lv asc";
			
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			
			if (rs.next()) {
				
				return rs.getInt("centerCount");
				
			}

			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return 0;
	}
	public int getTotalCount(HashMap<String, String> map) {

		try {
			
			String where = "";
			
			if (map.get("search").equals("y")) {
	            where = String.format("where %s like '%%%s%%'", map.get("column"), map.get("word") );
	        }


			String sql = String.format("select count(*) as cnt from tblResident %s", where);

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


