package com.test.neulbom.client.repository;

import java.sql.Connection; 

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.test.neulbom.client.repository.QreplyDTO;
import com.test.neulbom.mylib.DBUtil3;

public class QnaDAO {

	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private ResultSet rs;
	
	public QnaDAO() {
		this.conn = DBUtil3.open();
	}
	
	//페이징 안했을 때 다불러오기
	public List<QnaDTO> list() {
		try {
			
			String sql = "select * from tblQna";
			
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			
			List<QnaDTO> list = new ArrayList<QnaDTO>();
			
			while (rs.next()) {
				
				QnaDTO dto = new QnaDTO();
				
				dto.setQna_seq(rs.getString("qna_seq"));
		        dto.setTitle(rs.getString("title"));
		        dto.setContent(rs.getString("content"));
		        dto.setQna_date(rs.getString("qna_date"));
		        dto.setIsreply(rs.getString("isreply"));
		        dto.setCategory(rs.getString("category"));
		        dto.setRead(rs.getString("read"));
				
				list.add(dto);
			}
			
			return list;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//페이징 불러오기
	public List<QnaDTO> list(HashMap<String, String> map) {

	    List<QnaDTO> list = new ArrayList<QnaDTO>();

	    try {

	        String where = "";

	        if (map.get("search").equals("y")) {
	            where = String.format("where %s like '%%%s%%'",
	                                  map.get("column"),
	                                  map.get("word"));
	        }

	        String sql = String.format("select * from (select rownum as rnum, a.* from (select q.*, r.name as rname, r.id as rid, p.name as pname, p.id as pid from tblqna q left outer join tblResident r on q.resi_seq = r.resi_seq left outer join tblProtect p on q.protect_seq = p.protect_seq order by qna_seq desc) a %s) where rnum between %s and %s",
	                                   where,
	                                   map.get("begin"),
	                                   map.get("end"));


	        stat = conn.createStatement();
	        rs = stat.executeQuery(sql);

	        while (rs.next()) {
	            QnaDTO dto = new QnaDTO();

	            dto.setRnum(rs.getString("rnum"));
	            dto.setQna_seq(rs.getString("qna_seq"));
	            dto.setTitle(rs.getString("title"));
	            dto.setContent(rs.getString("content"));
	            dto.setQna_date(rs.getString("qna_date"));
	            dto.setIsreply(rs.getString("isreply"));
	            dto.setFname(rs.getString("fname"));
	            dto.setCategory(rs.getString("category"));
	            dto.setRead(rs.getString("read"));
	            dto.setProtect_seq(rs.getString("protect_seq"));
	            dto.setResi_seq(rs.getString("resi_seq"));
	            if (rs.getString("pname") == null) {
	                dto.setName(rs.getString("rname"));
	                dto.setId(rs.getString("rid"));
	            } else {
	                dto.setName(rs.getString("pname"));
	                dto.setId(rs.getString("pid"));
	            }

	            list.add(dto);
	        }
	        return list;

	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return list;
	}
	
	//어떻게 하지?
	public String getnameByResi(String qna_seq) {

		try {

			String sql = "select tblresident.name from tblqna inner join tblresident on tblqna.resi_seq = tblresident.resi_seq where tblqna.qna_seq = ?";

			pstat = conn.prepareStatement(sql);

			pstat.setString(1, qna_seq);

			rs = pstat.executeQuery();

			if (rs.next()) {

				return rs.getString("name");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	public String getnameByProtect(String qna_seq) {

		try {

			String sql = "select tblProtect.name from tblqna inner join tblprotect on tblqna.protect_seq = tblprotect.protect_seq where tblqna.qna_seq = ?";

			pstat = conn.prepareStatement(sql);

			pstat.setString(1, qna_seq);

			rs = pstat.executeQuery();

			if (rs.next()) {

				return rs.getString("name");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public String getIdByProtect(String qna_seq) {
		
		try {
			
			String sql = "select tblProtect.id from tblqna inner join tblprotect on tblqna.protect_seq = tblprotect.protect_seq where tblqna.qna_seq = ?";
			
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, qna_seq);
			
			rs = pstat.executeQuery();
			
			if (rs.next()) {
				
				return rs.getString("id");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	//어떻게 하지?
	public String getIdByResi(String qna_seq) {
		
		try {
			
			String sql = "select tblresident.id from tblqna inner join tblresident on tblqna.resi_seq = tblresident.resi_seq where tblqna.qna_seq = ?";
			
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, qna_seq);
			
			rs = pstat.executeQuery();
			
			if (rs.next()) {
				
				return rs.getString("id");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}



		public void qnaView(String qnaSeq, QnaDTO dto) {

	        try {
	        	
	            String sql = "select * from (select q.*, r.name as rname, r.id as rid, p.name as pname, p.id as pid from tblqna q left outer join tblResident r on q.resi_seq = r.resi_seq left outer join tblProtect p on q.protect_seq = p.protect_seq order by qna_seq desc) where qna_seq = ?";
	            pstat = conn.prepareStatement(sql);
	            
	            pstat.setString(1, qnaSeq);
	            rs = pstat.executeQuery();

	            if (rs.next()) {
	                dto.setQna_seq(rs.getString("qna_seq"));
	                dto.setTitle(rs.getString("title"));
	                dto.setContent(rs.getString("content"));
	                dto.setQna_date(rs.getString("qna_date"));
	                dto.setIsreply(rs.getString("isreply"));
	                dto.setFname(rs.getString("fname"));
	                dto.setCategory(rs.getString("category"));
	                dto.setRead(rs.getString("read"));
	                if (rs.getString("pname") == null) {
	                	dto.setName(rs.getString("rname"));
	                	dto.setId(rs.getString("rid"));
	                } else {
	                	dto.setName(rs.getString("pname"));
	                	dto.setId(rs.getString("pid"));
	                }
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }


		public int qnaResiAdd(String title, String content, String fname, String category, String resi_seq) {

			
			
			try {

				String sql = "insert into tblQna values (qna_seq.nextVal, ?, ?, sysdate, 'n', ?, ?, 0, null, ?)";
				
				pstat = conn.prepareStatement(sql);

				pstat.setString(1, title);
				pstat.setString(2, content);
				pstat.setString(3, fname);
				pstat.setString(4, category);
				pstat.setInt(5, Integer.parseInt(resi_seq));

				return pstat.executeUpdate();

			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return 0;
			
		}
		
		public int qnaProtectAdd(String title, String content, String fname, String category, String protect_seq) {

			try {

				String sql = "insert into tblQna values (qna_seq.nextVal, ?, ?, sysdate, 'n', ?, ?, 0, ?, null)";

				pstat = conn.prepareStatement(sql);

				pstat.setString(1, title);
				pstat.setString(2, content);
				pstat.setString(3, fname);
				pstat.setString(4, category);
				pstat.setInt(5, Integer.parseInt(protect_seq));
				
				return pstat.executeUpdate();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			return 0;
			
		}


		public String getResiSeq(String id) {

			try {

				String sql = "select name from tblResident where id = ?";

				pstat = conn.prepareStatement(sql);

				pstat.setString(1, id);

				rs = pstat.executeQuery();

				if (rs.next()) {

					return rs.getString("name");
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return null;
		}


		public String getProtectSeq(String id) {
			
			try {

				String sql = "select name from tblProtect where id = ?";

				pstat = conn.prepareStatement(sql);

				pstat.setString(1, id);

				rs = pstat.executeQuery();

				if (rs.next()) {

					return rs.getString("name");
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return null;
			
		}

		public int getTotalCount(HashMap<String, String> map) {
				
			try {

				String where ="";
				
				
				if (map.get("search").equals("y")) {
					where = String.format("where %s like '%%%s%%'", map.get("column"), map.get("word") );
				}
				

				String sql = String.format("select count(*) as cnt from tblQna %s", where);

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

		//조회수 증가
		public void increaseReadCount(String qna_seq) {
            try {
                String sql = "UPDATE tblqna SET read = read + 1 WHERE qna_seq = ?";
                pstat = conn.prepareStatement(sql);
                pstat.setString(1, qna_seq);
                pstat.executeUpdate();
                
                pstat.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
		
		//Edit 서블릿이 DTO를 건내주면, 수정
		public int edit(QnaDTO dto) {
			
			try {

				String sql = "update tblQna set title = ?, content = ?, category = ? where qna_seq = ?";

				pstat = conn.prepareStatement(sql);

				pstat.setString(1, dto.getTitle());
				pstat.setString(2, dto.getContent());
				pstat.setString(3, dto.getCategory());
				pstat.setString(4, dto.getQna_seq());

				return pstat.executeUpdate();

			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return 0;
		}

		public int delQna(String qna_seq) {

			try {
				
				String sql = "delete from tblQna where qna_seq = ?";
				
				pstat = conn.prepareStatement(sql);
				pstat.setString(1, qna_seq);
				
				return pstat.executeUpdate();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return 0;
			
		}

	public QreplyDTO getQReply(String qna_seq) {
			
			try {

				String sql = "SELECT * FROM tblQreply where qna_seq = ?";

				pstat = conn.prepareStatement(sql);
				pstat.setString(1, qna_seq);
				rs = pstat.executeQuery();

				QreplyDTO dto = new QreplyDTO();

				while (rs.next()) {

					dto.setTitle(rs.getString("title"));
					dto.setContent(rs.getString("content"));
					dto.setFname(rs.getString("fname"));
					dto.setAdmin_seq(rs.getString("admin_seq"));
					dto.setQna_seq(rs.getString("qna_seq"));
					dto.setRead(rs.getString("read"));		
						
				}
				
				return dto;

			} catch (Exception e) {
				e.printStackTrace();
			}

			return null;
		}

	
	
	

}
