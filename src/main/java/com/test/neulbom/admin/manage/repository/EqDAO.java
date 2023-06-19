package com.test.neulbom.admin.manage.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.test.neulbom.mylib.DBUtil3;

public class EqDAO {
	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private ResultSet rs;
	
	public EqDAO() {
		this.conn = DBUtil3.open(); 
	}

	// 비품 신청 내역 조회
	public List<RegEqDTO> regList(HashMap<String, String> map) {
		
		List<RegEqDTO> regList = new ArrayList<RegEqDTO>();

		try {
//			String sql = "select to_char(reg_date, 'yyyy-mm-dd') as reg_date, content, name, quantity, price, admin_seq, isAccept from tblRegEq order by reg_date asc";
			
	         String sql = String.format("select * from (select rownum as rnum, a.* from (select to_char(reg_date, 'yyyy-mm-dd') as reg_date, content, name, quantity, price, admin_seq, isAccept from tblRegEq order by reg_date asc) a) where rnum between %s and %s"
                     , map.get("begin")
                     , map.get("end")
                     );
			
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			
			
			while (rs.next()) {
				RegEqDTO regEqDto = new RegEqDTO();
				
				regEqDto.setReg_date(rs.getString("reg_date"));
				regEqDto.setContent(rs.getString("content"));
				regEqDto.setName(rs.getString("name"));
				regEqDto.setQuantity(rs.getInt("quantity"));
				regEqDto.setPrice(rs.getInt("price"));
				regEqDto.setAdmin_seq(rs.getString("admin_seq"));
				regEqDto.setIsAccept(rs.getString("isAccept"));
				
				regList.add(regEqDto);
			}
			
			return regList;
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		
		
		return regList;
	}


	// 새로 등록할 비품 seq 구하기
	public String getEqSeq() {
		
		try {
			
			String sql = "select max(eq_seq)+1 as eq_seq from tblEq";
			
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			
			if (rs.next()) {
				return rs.getString("eq_seq");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return null;
	}

	// 비품 등록하기
	public int registerEquip(EqDTO eqDto) {
		
		try {
			
			String sql = "insert into tblEq(eq_seq, name, quantity) values(?, ?, ?)";
			
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, eqDto.getEq_seq());
			pstat.setString(2, eqDto.getName());
			pstat.setInt(3, eqDto.getQuantity());
			
			return pstat.executeUpdate();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return 0;
	}

	// 비품 삭제하기
	public int delEquip(String eq_seq) {
		
//		System.out.println(eq_seq);
		try {
			
			String sql = "delete from tblEq where eq_seq = ?";
			
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, eq_seq);
			
			return pstat.executeUpdate();
			
		} catch(Exception e) {
			e.printStackTrace();
		}

		return 0;
	}

	
	// 등록된 비품 상세보기
	public EqDTO detailEquip(String eq_seq) {
		
		try {
			
			String sql = "select eq_seq, name, quantity from tblEq where eq_seq = ?";
			 
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, eq_seq);
			rs = pstat.executeQuery();
			
			if (rs.next()) {
				EqDTO eqDto = new EqDTO();
				
				eqDto.setEq_seq(rs.getString("eq_seq"));
				eqDto.setName(rs.getString("name"));
				eqDto.setQuantity(rs.getInt("quantity"));
				
				return eqDto;
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	
		return null;
	}

	// 비품 수정하기
	public int editEquip(EqDTO eqDto) {
		
		try {
			
			String sql = "update tblEq set quantity = ? where eq_seq = ?";
			
			pstat = conn.prepareStatement(sql);
			
			pstat.setInt(1, eqDto.getQuantity());
			pstat.setString(2, eqDto.getEq_seq());
			
			return pstat.executeUpdate();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return 0;
	}

	// 등록된 비품 목록 조회
	public List<EqDTO> getEqList(HashMap<String, String> map) {
		
		List<EqDTO> eqList = new ArrayList<EqDTO>();
		
		try {
			
			String where = "";
			
            if (map.get("search").equals("y")) {
                where = String.format("where name like '%%%s%%'"
                                     , map.get("word"));
             }

            String sql = String.format("select * from (select rownum as rnum, a.* from (select * from tblEq %s order by eq_seq asc) a) where rnum between %s and %s"
                    , where
            		, map.get("begin")
                    , map.get("end")
                    );
            
            stat = conn.createStatement();
            rs = stat.executeQuery(sql);
            
            
            while (rs.next()) {
            	EqDTO eqDto = new EqDTO();
            	
            	eqDto.setEq_seq(rs.getString("eq_seq"));
            	eqDto.setName(rs.getString("name"));
            	eqDto.setQuantity(Integer.parseInt(rs.getString("quantity")));
            	
            	eqList.add(eqDto);
            	
            }
            
            return eqList;
            
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		
		
		
		return eqList;
	}
	
	
	// 페이징
	public int getTotalCount(HashMap<String, String> map, int size) {
		
		try {
			
			String where ="";

			
            if (map.get("search").equals("y")) {
                where = String.format("where name like '%%%s%%'"
                                     , map.get("word"));
             }

            String sql = String.format("select count(*) as cnt from tblEq %s", where);
            
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
	
	// 페이징
	public int getRegEqTotalCount(HashMap<String, String> map, int size) {
		
		try {
			
//			String where ="";
//			
//			
//			if (map.get("search").equals("y")) {
//				where = String.format("where name like '%%%s%%'"
//						, map.get("word"));
//			}
			
			String sql = "select count(*) as cnt from tblRegEq";
			
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















