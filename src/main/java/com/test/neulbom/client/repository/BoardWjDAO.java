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

import com.test.neulbom.mylib.DBUtil3;

public class BoardWjDAO {
	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private ResultSet rs;

	public BoardWjDAO() {
		this.conn = DBUtil3.open();
	}
	public List<BoardWjDTO> view(HashMap<String, String> map) {
	    try {
	        String searchOption = map.get("search_option");
	        String searchKeyword = map.get("search_keyword");
	        String startDate = map.get("start_date");
	        String endDate = map.get("end_date");

	        StringBuilder sqlBuilder = new StringBuilder();
	        sqlBuilder.append("SELECT rnum, con_seq, con_title, nomem_name, TO_CHAR(con_date, 'YYYY-MM-DD') AS con_date, isreply ");
	        sqlBuilder.append("FROM ( ");
	        sqlBuilder.append("    SELECT ROWNUM AS rnum, a.* ");
	        sqlBuilder.append("    FROM ( ");
	        sqlBuilder.append("        SELECT * ");
	        sqlBuilder.append("        FROM vwconsult ");

	        if (searchOption != null && searchOption.equals("date") && startDate != null && endDate != null) {
	            sqlBuilder.append("WHERE con_date >= TO_DATE(?, 'YYYY-MM-DD') AND con_date < TO_DATE(?, 'YYYY-MM-DD') + 1 ");
	        } else if (searchOption != null && searchOption.equals("title") && searchKeyword != null) {
	            sqlBuilder.append("WHERE UPPER(con_title) LIKE '%' || UPPER(?) || '%' ");
	        } else if (searchOption != null && searchOption.equals("author") && searchKeyword != null) {
	            sqlBuilder.append("WHERE UPPER(nomem_name) LIKE '%' || UPPER(?) || '%' ");
	        }

	        sqlBuilder.append(") a) WHERE rnum BETWEEN ? AND ? ORDER BY con_seq DESC");

	        pstat = conn.prepareStatement(sqlBuilder.toString());

	        int parameterIndex = 1;

	        if (searchOption != null && searchOption.equals("date") && startDate != null && endDate != null) {
	            pstat.setString(parameterIndex++, startDate);
	            pstat.setString(parameterIndex++, endDate);
	        } else if ((searchOption != null && searchOption.equals("title") && searchKeyword != null) ||
	                (searchOption != null && searchOption.equals("author") && searchKeyword != null)) {
	            pstat.setString(parameterIndex++, searchKeyword);
	        }

	        pstat.setInt(parameterIndex++, Integer.parseInt(map.get("begin")));
	        pstat.setInt(parameterIndex, Integer.parseInt(map.get("end")));

	        rs = pstat.executeQuery();

	        List<BoardWjDTO> list = new ArrayList<>();

	        while (rs.next()) {
	            BoardWjDTO dto = new BoardWjDTO();
	            dto.setCon_seq(rs.getString("con_seq"));
	            dto.setCon_title(rs.getString("con_title"));
	            dto.setNomem_name(rs.getString("nomem_name"));
	            dto.setCon_date(rs.getString("con_date"));      
	            dto.setIsreply(rs.getString("isreply"));
	            list.add(dto);
	        }

	        return list;
	        
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return null;
	}

	public int getTotalCount(Map<String, String> map) {
	    String searchOption = map.get("search_option");
	    String startDate = map.get("start_date");
	    String endDate = map.get("end_date");

	    StringBuilder sqlBuilder = new StringBuilder();
	    sqlBuilder.append("SELECT COUNT(*) FROM vwconsult");

	    if (searchOption != null && searchOption.equals("date") && startDate != null && endDate != null) {
	        sqlBuilder.append(" WHERE con_date >= TO_DATE(?, 'YYYY-MM-DD') AND con_date < TO_DATE(?, 'YYYY-MM-DD') + 1");
	    } else if (searchOption != null && searchOption.equals("title") && map.get("search_keyword") != null) {
	        sqlBuilder.append(" WHERE UPPER(con_title) LIKE '%' || UPPER(?) || '%'");
	    } else if (searchOption != null && searchOption.equals("author") && map.get("search_keyword") != null) {
	        sqlBuilder.append(" WHERE UPPER(nomem_name) LIKE '%' || UPPER(?) || '%'");
	    }

	    try {
	        pstat = conn.prepareStatement(sqlBuilder.toString());

	        int parameterIndex = 1;

	        if (searchOption != null && searchOption.equals("date") && startDate != null && endDate != null) {
	            pstat.setString(parameterIndex++, startDate);
	            pstat.setString(parameterIndex++, endDate);
	        } else if ((searchOption != null && searchOption.equals("title") && map.get("search_keyword") != null) ||
	                (searchOption != null && searchOption.equals("author") && map.get("search_keyword") != null)) {
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




	public int add(BoardWjDTO dto) {
	    try {
	    	String sql = "INSERT INTO tblconsult (con_seq, title, content, con_date, isreply, nomem_seq, thread, depth) "
	    	        + "VALUES (conseq.nextVal, ?, ?, sysdate, 'n', ?, '0', '0')";

	    	pstat = conn.prepareStatement(sql);
	    	pstat.setString(1, dto.getCon_title());
	    	pstat.setString(2, dto.getCon_content());
	    	pstat.setString(3, dto.getNomem_seq());

	    	return pstat.executeUpdate();
	        
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return 0;
	}
	public int delete(String con_seq) {
		 try {
		    	String sql = "delete from tblconsult where con_seq = ?";

		    	pstat = conn.prepareStatement(sql);
		    	pstat.setString(1, con_seq);

		    	return pstat.executeUpdate();
		        
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		    return 0;
		}
	public int edit(BoardWjDTO dto) {
		 try {
		    	String sql = "UPDATE tblconsult SET title = ?, content = ? WHERE con_seq = ?";

		    	pstat = conn.prepareStatement(sql);
		    	pstat.setString(1, dto.getCon_title());
		    	pstat.setString(2, dto.getCon_content());
		    	pstat.setString(3, dto.getCon_seq());

		    	return pstat.executeUpdate();
		        
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		    return 0;
	}
	

	
	
}
