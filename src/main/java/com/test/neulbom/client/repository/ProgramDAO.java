package com.test.neulbom.client.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.test.neulbom.admin.manage.repository.ProgramDTO;
import com.test.neulbom.mylib.DBUtil3;

public class ProgramDAO {

	private Connection conn;
	private PreparedStatement pstat;
	private Statement stat;
	private ResultSet rs;

	public ProgramDAO() {
		this.conn = DBUtil3.open();
	}

	public int regiProgram(ProgramDTO dto) {
		int nextPappSeq = getPappSeq();

		try {

			String sql = "insert into tblPapp(papp_seq, resi_seq, prog_seq) values (?, ?, ?)";

			pstat = conn.prepareStatement(sql);
			pstat.setInt(1, nextPappSeq);
			pstat.setString(2, dto.getResi_seq());
			pstat.setString(3, dto.getProg_seq());

			return pstat.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return 0;
	}

	private int getPappSeq() {

		try {

			String sql = "select max(papp_seq) + 1 from tblPapp";

			stat = conn.createStatement();
			rs = stat.executeQuery(sql);

			if (rs.next()) {

				return rs.getInt(1);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return 0;
	}

	// 검색 페이징(보류)
//	public List<ProgramDTO> view(HashMap<String, String> map) {
//		try {
//			String searchOption = map.get("search_option");
//			String searchKeyword = map.get("search_keyword");
//			String startDate = map.get("start_date");
//			String endDate = map.get("end_date");
//
//			StringBuilder sqlBuilder = new StringBuilder();
//			sqlBuilder.append(
//					"SELECT rnum, con_seq, con_title, nomem_name, TO_CHAR(con_date, 'YYYY-MM-DD') AS con_date ");
//			sqlBuilder.append("FROM ( ");
//			sqlBuilder.append("    SELECT ROWNUM AS rnum, a.* ");
//			sqlBuilder.append("    FROM ( ");
//			sqlBuilder.append("        SELECT * ");
//			sqlBuilder.append("        FROM vwconsult ");
//
//			if (searchOption != null && searchOption.equals("date") && startDate != null && endDate != null) {
//				sqlBuilder.append(
//						"WHERE con_date >= TO_DATE(?, 'YYYY-MM-DD') AND con_date < TO_DATE(?, 'YYYY-MM-DD') + 1 ");
//			} else if (searchOption != null && searchOption.equals("title") && searchKeyword != null) {
//				sqlBuilder.append("WHERE UPPER(con_title) LIKE '%' || UPPER(?) || '%' ");
//			} else if (searchOption != null && searchOption.equals("name") && searchKeyword != null) {
//				sqlBuilder.append("WHERE UPPER(nomem_name) LIKE '%' || UPPER(?) || '%' ");
//			}
//
//			sqlBuilder.append(") a) WHERE rnum BETWEEN ? AND ? ORDER BY con_seq DESC");
//
//			pstat = conn.prepareStatement(sqlBuilder.toString());
//
//			int parameterIndex = 1;
//
//			if (searchOption != null && searchOption.equals("date") && startDate != null && endDate != null) {
//				pstat.setString(parameterIndex++, startDate);
//				pstat.setString(parameterIndex++, endDate);
//			} else if ((searchOption != null && searchOption.equals("title") && searchKeyword != null)
//					|| (searchOption != null && searchOption.equals("name") && searchKeyword != null)) {
//				pstat.setString(parameterIndex++, searchKeyword);
//			}
//
//			pstat.setInt(parameterIndex++, Integer.parseInt(map.get("begin")));
//			pstat.setInt(parameterIndex, Integer.parseInt(map.get("end")));
//
//			rs = pstat.executeQuery();
//
//			List<MyProgramDTO> list = new ArrayList<>();
//
//			while (rs.next()) {
//				String title = rs.getString("title");
//				String content = rs.getString("content");
//				
//				if (title.length() > 8) {
//					title = title.substring(0, 8) + "...";
//				}
//				if (content.length() > 18) {
//					content = content.substring(0, 18) + "...";
//				}
//
//				MyProgramDTO pdto = new MyProgramDTO();
//				
//				pdto.setResi_seq("resi_seq");
//				pdto.setProg_seq(rs.getString("prog_seq"));
//				pdto.setTitle(title);
//				pdto.setContent(content);
//				pdto.setApply(rs.getString("apply"));
//				pdto.setPeople(rs.getString("people"));
//				pdto.setPlace(rs.getString("place"));
//				pdto.setProg_date(rs.getString("prog_date").substring(0, 10));
//				
//				
//				list.add(pdto);
//			}
//
//			return list;
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//
//		return null;
//	}

	public int getTotalCount(Map<String, String> map) {
		String searchOption = map.get("search_option");
		String startDate = map.get("start_date");
		String endDate = map.get("end_date");

		StringBuilder sqlBuilder = new StringBuilder();
		sqlBuilder.append("SELECT COUNT(*) FROM vwconsult");

		if (searchOption != null && searchOption.equals("date") && startDate != null && endDate != null) {
			sqlBuilder
					.append(" WHERE con_date >= TO_DATE(?, 'YYYY-MM-DD') AND con_date < TO_DATE(?, 'YYYY-MM-DD') + 1");
		} else if (searchOption != null && searchOption.equals("title") && map.get("search_keyword") != null) {
			sqlBuilder.append(" WHERE UPPER(con_title) LIKE '%' || UPPER(?) || '%'");
		} else if (searchOption != null && searchOption.equals("name") && map.get("search_keyword") != null) {
			sqlBuilder.append(" WHERE UPPER(nomem_name) LIKE '%' || UPPER(?) || '%'");
		}

		try {
			pstat = conn.prepareStatement(sqlBuilder.toString());

			int parameterIndex = 1;

			if (searchOption != null && searchOption.equals("date") && startDate != null && endDate != null) {
				pstat.setString(parameterIndex++, startDate);
				pstat.setString(parameterIndex++, endDate);
			} else if ((searchOption != null && searchOption.equals("title") && map.get("search_keyword") != null)
					|| (searchOption != null && searchOption.equals("name") && map.get("search_keyword") != null)) {
				pstat.setString(parameterIndex++, map.get("search_keyword"));
			}

			rs = pstat.executeQuery();

			if (rs.next()) {
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return 0;
	}

	public int getTotalCount(HashMap<String, String> map) {

		try {

			String where = "";

			if (map.get("search").equals("y")) {
				where = String.format("where %s like '%%%s%%'", map.get("column"), map.get("word"));
			}

			String sql = String.format("select count(*) as cnt from tblFree %s", where);

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

	//프로그램 취소
	public int delProgram(String papp_seq) {

		try {
			
			String sql = "delete from tblPapp where papp_seq = ?";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, papp_seq);
			
			return pstat.executeUpdate();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return 0;
	}

	public int getProgramTotalCount(HashMap<String, String> map, String resi_seq) {
		try {
			 
			 String where ="";
	            
	            
			 if (map.get("search").equals("y")) {
		            where = String.format(
		                  "and prog_date BETWEEN TO_DATE('%s', 'YYYY-MM-DD') and TO_DATE('%s', 'YYYY-MM-DD')",
		                  map.get("start_date"), map.get("end_date"));
		         }

				 String sql = String.format("select count(*) as cnt from vwRegiProgram where resi_seq = %s %s", resi_seq, where);

		  
		  pstat = conn.prepareStatement(sql);
		  rs = pstat.executeQuery();
		  
		  
		  if (rs.next()) {
		 
		  return rs.getInt("cnt"); }
		  
		  } catch (Exception e) { e.printStackTrace(); }
		  
	
		return 0;
	}

	//보호자 정보
			public ProtectDTO getProtect(ProtectDTO dto, String protect_seq) {

				try {

					String sql = "select * from tblProtect where protect_seq = ?";

					pstat = conn.prepareStatement(sql);

					pstat.setString(1, protect_seq);

					rs = pstat.executeQuery();

					if (rs.next()) {

						ProtectDTO result = new ProtectDTO();

						result.setId(rs.getString("id"));
						result.setPw(rs.getString("pw"));
						result.setName(rs.getString("name"));
						result.setSsn(rs.getString("ssn"));
						result.setTel(rs.getString("tel"));
						result.setEmail(rs.getString("email"));
						result.setResi_seq(rs.getString("resi_seq"));
						result.setRelation(rs.getString("relation"));
						result.setAddress(rs.getString("address"));
						result.setLv(rs.getString("lv"));

						return result;

					}

				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;

			}

			//
			public ResiDTO getResident(ResiDTO resi, String resi_seq) {

				try {

					String sql = "select * from tblResident where resi_seq = ?";

					pstat = conn.prepareStatement(sql);

					pstat.setString(1, resi_seq);

					rs = pstat.executeQuery();

					if (rs.next()) {

						ResiDTO result = new ResiDTO();

						result.setId(rs.getString("id"));
						result.setPw(rs.getString("pw"));
						result.setName(rs.getString("name"));
						result.setSsn(rs.getString("ssn"));
						result.setTel(rs.getString("tel"));
						result.setEmail(rs.getString("email"));
						result.setResi_seq(rs.getString("resi_seq"));
						result.setAddress(rs.getString("address"));
						result.setLv(rs.getString("lv"));

						return result;

					}

				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;

			}
			
			//내가 신청한 복지프로그램 내역 가져오기
			public List<MyProgramDTO> myplist(HashMap<String, String> map, String resi_seq) {

				try {
					
					
					String where = "";
					
					if (map.get("search").equals("y")) {
			            where = String.format(
			                  "and prog_date BETWEEN TO_DATE('%s', 'YYYY-MM-DD') AND TO_DATE('%s', 'YYYY-MM-DD')",
			                  map.get("start_date"), map.get("end_date"));
					}
					
					

					String sql = String.format(
						    	"SELECT * FROM (SELECT ROWNUM AS rnum, vwRegiProgram.* FROM (SELECT * FROM vwRegiProgram where resi_seq=%s ORDER BY prog_seq DESC ) vwRegiProgram ORDER BY ROWNUM) vwRegiProgram WHERE rnum <= %s AND rnum >= %s %s"
								, resi_seq
								, map.get("end")
								, map.get("begin")
								, where);

					stat = conn.createStatement();
					rs = stat.executeQuery(sql);
					
					List<MyProgramDTO> list = new ArrayList<>();
					int i = Integer.parseInt(map.get("begin"));
					

					while (rs.next()) {
						String title = rs.getString("title");
						String content = rs.getString("content");
						
						if (title.length() > 11) {
							title = title.substring(0, 11) + "...";
						}
						if (content.length() > 21) {
							content = content.substring(0, 21) + "...";
						}

						MyProgramDTO pdto = new MyProgramDTO();
						
						pdto.setPapp_seq(rs.getString("papp_seq"));
						pdto.setProg_seq(rs.getString("prog_seq"));
						pdto.setTitle(title);
						pdto.setContent(content);
						pdto.setApply(rs.getString("apply"));
						pdto.setPeople(rs.getString("people"));
						pdto.setPlace(rs.getString("place"));
						pdto.setProg_date(rs.getString("prog_date").substring(0, 10));
						pdto.setResi_seq(rs.getString("resi_seq"));
						pdto.setDisplayed_seq(i + "");
						
						
						list.add(pdto);
						i++;
					}

					return list;

				} catch (Exception e) {
					e.printStackTrace();
				}

				return null;
				

			}
			
			//복지프로그램 상세 내역
			public MyProgramDTO detailProgram(String prog_seq) {

				try {
					
					String sql = "select * from tblProgram where prog_seq = ?";
					
					pstat = conn.prepareStatement(sql);
					pstat.setString(1, prog_seq);
					rs = pstat.executeQuery();	
					
					if (rs.next()) {
						
						MyProgramDTO dto = new MyProgramDTO();
						
						dto.setProg_seq(rs.getString("prog_seq"));
						dto.setTitle(rs.getString("title"));
						dto.setApply(rs.getString("apply"));
						dto.setContent(rs.getString("content"));
						dto.setProg_date(rs.getString("prog_date").substring(0, 10));
						dto.setPeople(rs.getString("people"));
						dto.setPlace(rs.getString("place"));
						
						return dto;
						
					}
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				
				return null;
			}
			
			//내가 신청한 복지프로그램 내역 
			public MyProgramDTO regiProgram(String prog_seq, String resi_seq) {

				try {
					
					String sql = "select * from tblPapp where prog_seq = ? and resi_seq = ?";
					
					pstat = conn.prepareStatement(sql);
					pstat.setString(1, prog_seq);
					pstat.setString(2, resi_seq);
					rs = pstat.executeQuery();
					
					if (rs.next()) {
						
						MyProgramDTO pdto = new MyProgramDTO();
						
						pdto.setPapp_seq(rs.getString("papp_seq"));
						pdto.setResi_seq(rs.getString("resi_seq"));
						pdto.setProg_seq(rs.getString("prog_seq"));
						
						return pdto;
					}
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				
				
				return null;
			}

			public String myplist(String resi_seq, String prog_seq) {
			    
				try {
			        String sql = "SELECT * FROM vwRegiProgram WHERE resi_seq = ? and prog_seq = ?";
			        
			        pstat = conn.prepareStatement(sql);
			        pstat.setString(1, resi_seq);
			        pstat.setString(2, prog_seq);
			        
			        rs = pstat.executeQuery();
			        
			        
			        if (rs.next()) {
			        	
			        	return rs.getString("prog_seq");
			        }

			        
			    } catch (Exception e) {
			        e.printStackTrace();
			    }

			    return null;
			}

			//신청 수 증가
			public void increaseApplyCount(String prog_seq) {
				
			            try {
			            	
			                String sql = "update tblprogram set apply = apply + 1 where prog_seq = ?";
			                
			                pstat = conn.prepareStatement(sql);
			                pstat.setString(1, prog_seq);
			                pstat.executeUpdate();
			                
			                pstat.close();
			                
			            } catch (Exception e) {
			                e.printStackTrace();
			            }
			        }

			//신청 수 감소
			public void decreaseApplyCount(String prog_seq) {
				try {
	            	
	                String sql = "update tblprogram set apply = apply - 1 where prog_seq = ?";
	                
	                pstat = conn.prepareStatement(sql);
	                pstat.setString(1, prog_seq);
	                pstat.executeUpdate();
	                
	                pstat.close();
	                
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
				
			}

}