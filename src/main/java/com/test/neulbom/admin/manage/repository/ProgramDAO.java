package com.test.neulbom.admin.manage.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.test.neulbom.mylib.DBUtil3;

public class ProgramDAO {
	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private ResultSet rs;
	
	public ProgramDAO() {
		this.conn = DBUtil3.open();
	}

	// 프로그램 신규 등록하기
	public int registerProgram(ProgramDTO progDto) {
		
		try {
			
			String sql = "insert into tblProgram(prog_seq, title, prog_date, content, place, people) values(?, ?, to_date(?, 'yyyy-mm-dd hh24:mi:ss'), ?, ?, ?)";
			
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, progDto.getProg_seq());
			pstat.setString(2, progDto.getTitle());
			pstat.setString(3, progDto.getProg_date());
			pstat.setString(4, progDto.getContent());
			pstat.setString(5, progDto.getPlace());
			pstat.setString(6, progDto.getPeople());
			
			return pstat.executeUpdate();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return 0;
	}

	// 프로그램 seq 구하기
	public String getProgSeq() {
		
		try {
			
			String sql = "select max(prog_seq)+1 as prog_seq from tblProgram";
			
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			
			if (rs.next()) {
				return rs.getString("prog_seq");
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		
		return null;
	}

	// 프로그램 삭제하기
	public int delProgram(String prog_seq) {

		try {
			
			String sql = "delete from tblProgram where prog_seq = ?";
			
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, prog_seq);
			
			return pstat.executeUpdate();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return 0;
	}

	
	// 프로그램 수정하기
	public int editProgram(ProgramDTO dto) {
		
		try {
			
			String sql ="update tblProgram set title=?, content=?, prog_date = to_date(?, 'yyyy-mm-dd hh24:mi:ss'), place=?, people=? where prog_seq=?";
			
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, dto.getTitle());
			pstat.setString(2, dto.getContent());
			pstat.setString(3, dto.getProg_date());
			pstat.setString(4, dto.getPlace());
			pstat.setString(5, dto.getPeople());
			pstat.setString(6, dto.getProg_seq());
		
			
			return pstat.executeUpdate();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return 0;
	}


	// 프로그램 상세보기
	public ProgramDTO getDetailProgram(String prog_seq) {
		
		try {
			
			String sql = "select title, content, to_char(prog_date, 'yyyy-mm-dd') as prog_date, place, people from tblProgram where prog_seq = ?";
			
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, prog_seq);
			
			rs = pstat.executeQuery();
			
			if (rs.next()) {
				ProgramDTO progDto = new ProgramDTO();
				
				progDto.setTitle(rs.getString("title"));
				progDto.setContent(rs.getString("content"));
				progDto.setProg_date(rs.getString("prog_date"));
				progDto.setPlace(rs.getString("place"));
				progDto.setPeople(rs.getString("people"));
				
				return progDto;
				
			}
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	// 프로그램 목록보기 & 검색
	public List<ProgramDTO> getProgList(HashMap<String, String> map) {
		
		List<ProgramDTO> progList = new ArrayList<ProgramDTO>();

		try {
		
			String where = "";
			
            if (map.get("search").equals("y")) {
                where = String.format("where %s like '%%%s%%'"
                                     , map.get("column")
                                     , map.get("word"));
             }
			
			
			String sql = String.format("select * from (select rownum as rnum, a.* from  (select prog_seq, title, to_char(prog_date, 'yyyy-mm-dd') as prog_date, content, place, people from tblProgram %s order by prog_seq desc, prog_date desc) a) where rnum between %s and %s"
									, where
									, map.get("begin")
									, map.get("end"));
			
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			
			
			while (rs.next()) {
				ProgramDTO progDto = new ProgramDTO();
				
				progDto.setProg_seq(rs.getString("prog_seq"));
				progDto.setTitle(rs.getString("title"));
				progDto.setProg_date(rs.getString("prog_date"));
				progDto.setContent(rs.getString("content"));
				progDto.setPlace(rs.getString("place"));
				progDto.setPeople(rs.getString("people"));
				
				progList.add(progDto);
			}
			
			return progList;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return progList;
	}


	//DAO > getTotalCount 메소드
	//자기 게시물에 맞게 수정
	public int getTotalCount(HashMap<String, String> map) {
		
		try {

			String where ="";
			
			if (map.get("search").equals("y")) {
				where = String.format("where %s like '%%%s%%'", map.get("column"), map.get("word") );
			}
			
			String sql = String.format("select count(*) as cnt from tblProgram %s", where); 

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














