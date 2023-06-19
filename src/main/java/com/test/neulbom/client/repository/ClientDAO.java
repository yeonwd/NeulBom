package com.test.neulbom.client.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.test.neulbom.admin.manage.repository.ProgramDTO;
import com.test.neulbom.mylib.DBUtil3;

public class ClientDAO {

	private Connection conn;
	private PreparedStatement pstat;
	private Statement stat;
	private ResultSet rs;

	public ClientDAO() {
		this.conn = DBUtil3.open();
	}

	// 새 글 thread 값
	public int getMaxThread() {

		try {

			String sql = "select nvl(max(thread), 0) as tread from tblFree";

			stat = conn.createStatement();
			rs = stat.executeQuery(sql);

			if (rs.next()) {
				return rs.getInt("tread");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return 0;
	}

	// 답글 thread
	public void updateThread(HashMap<String, Integer> map) {

	}

	// 게시판에 쓴 글을 DB에 넣기
	public int add(FreeDTO dto) {
		try {

			String sql = "";
			int nextFreeSeq = getMaxFreeSeq();

			if (dto.getLv().equals("5") || dto.getLv().equals("6")) {

				sql = "insert into tblFree(free_seq, title, content, free_date, fname, read, thread, depth, protect_seq, resi_seq) values(?, ?, ?, default, ?, default, ?, ?, null, ?)";

				pstat = conn.prepareStatement(sql);

				pstat.setInt(1, nextFreeSeq);
				pstat.setString(2, dto.getTitle());
				pstat.setString(3, dto.getContent());
				// 스스로는 현재 접속자의 아이디를 알아낼 수 있는 방법 X > DTO에 글 쓴 사람 id를 담아서 넘겨야 함 > Add.java가 DAO에
				// 일을 의뢰할 때 session안에 들어있는 id값도 넘겨야 함
				pstat.setString(4, dto.getFile());

				pstat.setInt(5, dto.getThread());
				pstat.setInt(6, dto.getDepth());

				// 가져온 아이디가 insert할 tblFree 테이블의 protect_seq 혹은 resi_seq와 일치하는 회원의 id면 삽입
				pstat.setString(7, dto.getResi_seq());
//				pstat.setInt(getMaxThread(), getMaxThread());

			} else if (dto.getLv().equals("7")) {

				sql = "insert into tblFree(free_seq, title, content, free_date, fname, read, thread, depth, protect_seq, resi_seq) values(?, ?, ?, default, ?, default, ?, ?, ?, null)";

				pstat = conn.prepareStatement(sql);

				pstat.setInt(1, nextFreeSeq);
				pstat.setString(2, dto.getTitle());
				pstat.setString(3, dto.getContent());
				// 스스로는 현재 접속자의 아이디를 알아낼 수 있는 방법 X > DTO에 글 쓴 사람 id를 담아서 넘겨야 함 > Add.java가 DAO에
				// 일을 의뢰할 때 session안에 들어있는 id값도 넘겨야 함
				pstat.setString(4, dto.getFile());

				pstat.setInt(5, dto.getThread());
				pstat.setInt(6, dto.getDepth());

				// 가져온 아이디가 insert할 tblFree 테이블의 protect_seq 혹은 resi_seq와 일치하는 회원의 id면 삽입
				pstat.setString(7, dto.getProtect_seq());
//				pstat.setInt(getMaxThread(), getMaxThread());

			}

			return pstat.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return 0;
	}
	
	//게시판 글 넣을 때 free_seq를 1씩 증가시키기
	private int getMaxFreeSeq() {

		try {
			
			String sql = "select max(free_seq) + 1 from tblFree";
			
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

	//ID 찾기
	public AccountDTO find_id(String name, String ssn) {

		try {

			String sql = "select * from tblProtect where name = ? and ssn = ?";

			pstat = conn.prepareStatement(sql);
			pstat.setString(1, name);
			pstat.setString(2, ssn);

			rs = pstat.executeQuery();

			if (rs.next()) {

				AccountDTO dto = new AccountDTO();

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

	//비밀번호 찾기
	public AccountDTO find_pw(String name, String id) {

		try {

			String sql = "select * from tblProtect where name = ? and id = ?";

			pstat = conn.prepareStatement(sql);
			pstat.setString(1, name);
			pstat.setString(2, id);

			rs = pstat.executeQuery();

			if (rs.next()) {

				AccountDTO dto = new AccountDTO();

				dto.setPw(rs.getString("pw"));
				dto.setName(rs.getString("name"));
				dto.setId(rs.getString("id"));

				return dto;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	// 자유게시판 전체 글 목록
	public List<FreeDTO> list(HashMap<String, String> map) {

		List<FreeDTO> list = new ArrayList<FreeDTO>();

		try {
			
			String where = "";
			
			 if (map.get("search").equals("y")) {
				 	if (!map.get("column").equals("name")) {
				 		where = String.format("where %s like '%%%s%%'"
	                                 , map.get("column")
	                                 , map.get("word"));
				 	} else {
				 		where = String.format("where resi_seq = (select resi_seq from tblResident where name like '%%%s%%') or protect_seq = (select protect_seq from tblProtect where name like '%%%s%%')", map.get("word"), map.get("word"));
				 	}
	            }


				String sql = String.format("SELECT *\r\n"
						+ "FROM (\r\n"
						+ "    SELECT ROWNUM AS rnum, tblFree.*, \r\n"
						+ "        CASE\r\n"
						+ "            WHEN r.name IS NOT NULL THEN r.name\r\n"
						+ "            ELSE p.name\r\n"
						+ "        END AS name\r\n"
						+ "    FROM (\r\n"
						+ "        SELECT *\r\n"
						+ "        FROM tblFree %s\r\n"
						+ "        ORDER BY free_seq DESC\r\n"
						+ "    ) tblFree\r\n"
						+ "    LEFT JOIN tblResident r ON r.resi_seq = tblFree.resi_seq\r\n"
						+ "    LEFT JOIN tblProtect p ON p.protect_seq = tblFree.protect_seq\r\n"
						+ "    WHERE ROWNUM <= %s"
						+ ")\r\n"
						+ "WHERE rnum >= %s"
											, where
											, map.get("end")
											, map.get("begin"));

				stat = conn.createStatement();
				rs = stat.executeQuery(sql);
				
				int i = Integer.parseInt(map.get("begin"));


			

			while (rs.next()) {

				FreeDTO fdto = new FreeDTO();
				
				fdto.setDisplayed_seq(i + "");
				fdto.setFree_seq(rs.getString("free_seq"));
				fdto.setTitle(rs.getString("title"));
				fdto.setName(rs.getString("name"));
				fdto.setFree_date(rs.getString("free_date").substring(0, 10));
				fdto.setRead(rs.getString("read"));

				list.add(fdto);
				i++;
			}

			return list;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
	
	
	// 전체 글 목록 조회
	public List<FreeDTO> list() {

		try {

			String sql = "select tblFree.*, case when (select name from tblResident where resi_seq = tblFree.resi_seq) is not null then (select name from tblResident where resi_seq = tblFree.resi_seq) else (select name from tblProtect where protect_seq = tblFree.protect_seq) end as name from tblFree order by free_seq desc";

			stat = conn.createStatement();
			rs = stat.executeQuery(sql);

			List<FreeDTO> list = new ArrayList<FreeDTO>();

			while (rs.next()) {

				FreeDTO dto = new FreeDTO();

				dto.setFree_seq(rs.getString("free_seq"));
				dto.setTitle(rs.getString("title"));
				dto.setName(rs.getString("name"));
				dto.setFree_date(rs.getString("free_date").substring(0, 10));
				dto.setRead(rs.getString("read"));

				list.add(dto);

			}

			return list;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public FreeDTO fcontent(String free_seq) {

		try {
			String sql = "select tblFree.*, case when (select name from tblResident where resi_seq = tblFree.resi_seq) is not null then (select name from tblResident where resi_seq = tblFree.resi_seq) else (select name from tblProtect where protect_seq = tblFree.protect_seq) end as name, case when (select id from tblResident where resi_seq = tblFree.resi_seq) is not null then (select id from tblResident where resi_seq = tblFree.resi_seq) else (select id from tblProtect where protect_seq = tblFree.protect_seq) end as id from tblFree where free_seq = ?";

			pstat = conn.prepareStatement(sql);
			pstat.setString(1, free_seq);
			rs = pstat.executeQuery();
			
			

			if (rs.next()) {

				FreeDTO dto = new FreeDTO();
				
				dto.setId(rs.getString("id"));	//글쓴이의 id를 받아오기
				dto.setFree_seq(rs.getString("free_seq"));
				dto.setTitle(rs.getString("title"));
				dto.setName(rs.getString("name"));
				dto.setContent(rs.getString("content"));
				dto.setFree_date(rs.getString("free_date").substring(0, 10));
				dto.setRead(rs.getString("read"));
				dto.setFile(rs.getString("fname"));
				
				dto.setThread(rs.getInt("thread"));
				dto.setDepth(rs.getInt("depth"));

				
				
				return dto;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	//DB의 seq를 가져와서 +1씩 시켜주기
	public String addSeq() {

		try {
			
			String sql = "select max(free_seq) + 1 as free_seq from tblFree";
			
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			
			if(rs.next()) {
				return rs.getString("free_seq");
			
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
		
	}

	

	//댓글 목록 가져오기
	public List<CommentDTO> clist(String free_seq) {

		try {
			
			String sql = "select tblComment.*, case when (select id from tblResident where resi_seq = tblComment.resi_seq) is not null then (select id from tblResident where resi_seq = tblComment.resi_seq) else (select id from tblProtect where protect_seq = tblComment.protect_seq) end as id, case when (select name from tblResident where resi_seq = tblComment.resi_seq) is not null then (select name from tblResident where resi_seq = tblComment.resi_seq) else (select name from tblProtect where protect_seq = tblComment.protect_seq) end as name from tblComment where free_seq = ? order by comment_seq asc";

			pstat = conn.prepareStatement(sql);
			pstat.setString(1, free_seq);
			rs = pstat.executeQuery();
			
			List<CommentDTO> clist = new ArrayList<CommentDTO>();
			
			while (rs.next()) {
				
				CommentDTO cdto = new CommentDTO();
				
				cdto.setComment_seq(rs.getString("comment_seq"));
				cdto.setContent(rs.getString("content"));
				cdto.setName(rs.getString("name"));
				cdto.setId(rs.getString("id"));
				cdto.setResi_seq(rs.getString("resi_seq"));
				cdto.setProtect_seq(rs.getString("protect_seq"));
				cdto.setFree_seq(rs.getString("free_seq"));
			
				clist.add(cdto);
			}
			
			return clist;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	//자유게시판 > 댓글 작성
	public int addComment(CommentDTO cdto) {
		
		String sql = "";
		int nextCommentSeq = getNextCommnetSeq();	//max(comment_seq) + 1;
		
		try {
			
			if (cdto.getLv().equals("5") || cdto.getLv().equals("6")) {

				sql = "insert into tblComment(comment_seq, content, free_seq, resi_seq, protect_seq) values(?, ?, ?, ?, null)";

				
				pstat = conn.prepareStatement(sql);
				
				pstat.setInt(1, nextCommentSeq);
				pstat.setString(2, cdto.getContent());
				pstat.setString(3, cdto.getFree_seq());
				pstat.setString(4, cdto.getResi_seq());

				return pstat.executeUpdate();

			} else if (cdto.getLv().equals("7")) {

				sql = "insert into tblComment(comment_seq, content, free_seq, resi_seq, protect_seq) values(?, ?, ?, null, ?)";

				pstat = conn.prepareStatement(sql);

				pstat.setInt(1, nextCommentSeq);
				pstat.setString(2, cdto.getContent());
				pstat.setString(3, cdto.getFree_seq());
				pstat.setString(4, cdto.getProtect_seq());
				
				return pstat.executeUpdate();

			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return 0;
	}

	private int getNextCommnetSeq() {

		try {
			String sql = "select max(comment_seq) + 1 from tblComment";
			
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

	//자유게시판 글 삭제
	public int delFree(String free_seq) {

		try {
			
			String sql = "delete from tblFree where free_seq = ?";
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, free_seq);
			
			return pstat.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return 0;
		
	}

	//자유게시판 글 삭제 시 댓글도 함께 삭제
	public int defComment(String free_seq) {

		try {
			
			String sql = "delete from tblComment where free_seq = ?";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, free_seq);
			
			return pstat.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return 0;
	}

	
	//조회수 증가
	public void increaseReadCount(String free_seq) {
		
	            try {
	            	
	                String sql = "update tblfree set read = read + 1 where free_seq = ?";
	                
	                pstat = conn.prepareStatement(sql);
	                pstat.setString(1, free_seq);
	                pstat.executeUpdate();
	                
	                pstat.close();
	                
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	        }

	public int editFree(FreeDTO dto) {

		try {
			
			String sql = "update tblFree set title = ?, content = ?, fname = ? where free_seq = ?";

			pstat = conn.prepareStatement(sql);
			pstat.setString(1, dto.getTitle());
			pstat.setString(2, dto.getContent());
			pstat.setString(3, dto.getFile());
			pstat.setString(4, dto.getFree_seq());
			
			return pstat.executeUpdate();
			
		} catch (Exception e) {

			e.printStackTrace();
		
		}
		
		
		return 0;
	}

	public int getTotalCount(HashMap<String, String> map) {

			 try {
				 
				 String where ="";
		            
		            
		            if (map.get("search").equals("y")) {
		               where = String.format("where %s like '%%%s%%'", map.get("column"), map.get("word") );
		            }
				 
		            String sql = String.format("select count(*) as cnt from tblFree %s", where);

			  
			  pstat = conn.prepareStatement(sql);
			  rs = pstat.executeQuery();
			  
			  
			  if (rs.next()) {
			 
			  return rs.getInt("cnt"); }
			  
			  } catch (Exception e) { e.printStackTrace(); }
			  
		
		return 0;
	}

	

	//프로그램 글 목록 페이징 가져오기
	public List<MyProgramDTO> plist(HashMap<String, String> map) {


		try {
			
			String where = "";
			
			if (map.get("search").equals("y")) {
	            where = String.format(
	                  "and prog_date BETWEEN TO_DATE('%s', 'YYYY-MM-DD') AND TO_DATE('%s', 'YYYY-MM-DD')",
	                  map.get("start_date"), map.get("end_date"));
	         }

			 String sql = String.format(
					 
		                "SELECT * FROM (SELECT ROWNUM AS rnum, tblProgram.* FROM (SELECT * FROM tblProgram ORDER BY prog_seq DESC) tblProgram ORDER BY ROWNUM) tblProgram WHERE rnum <= %s AND rnum >= %s %s",
		                
		                map.get("end"),
		                map.get("begin"),
		                where);
			

			stat = conn.createStatement();
			rs = stat.executeQuery(sql);

			List<MyProgramDTO> list = new ArrayList<>();
			

			while (rs.next()) {
				String title = rs.getString("title");
				String content = rs.getString("content");
				
				if (title.length() > 8) {
					title = title.substring(0, 8) + "...";
				}
				if (content.length() > 18) {
					content = content.substring(0, 18) + "...";
				}

				MyProgramDTO pdto = new MyProgramDTO();
				
				pdto.setRnum(rs.getString("rnum"));
				pdto.setProg_seq(rs.getString("prog_seq"));
				pdto.setTitle(title);
				pdto.setContent(content);
				pdto.setApply(rs.getString("apply"));
				pdto.setPeople(rs.getString("people"));
				pdto.setPlace(rs.getString("place"));
				pdto.setProg_date(rs.getString("prog_date").substring(0, 10));
				
				
				list.add(pdto);
			}

			return list;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	

	//자유게시판 > 댓글 지우기
	public int delComment(String comment_seq) {

		try {
			
			String sql = "delete from tblComment where comment_seq = ?";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, comment_seq);
			
			return pstat.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return 0;
	}

	

	public int editComment(CommentDTO dto) {

		try {
		
			String sql = "update tblComment set content = ? where comment_seq = ?";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, dto.getContent());
			pstat.setString(2, dto.getComment_seq());
			
			return pstat.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return 0;
	}

	

	public String addpapp() {

		//프로그램 신청 테이블(tblPapp) seq 증가
		try {
			
			String sql = "select max(papp_seq) + 1 as papp_seq from tblPapp";
			
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			
			if(rs.next()) {
				return rs.getString("papp_seq");
			
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return null;
	}

	//복지프로그램 신청
	public int addprogram(MyProgramDTO pdto) {

		try {
			int nextPappSeq = getMaxPappSeq();
			
			String sql = "insert into tblPapp(papp_seq, resi_seq, prog_seq) values(?, ?, ?)";
			
			pstat = conn.prepareStatement(sql);
			pstat.setInt(1, getMaxPappSeq());
			pstat.setString(2, pdto.getResi_seq());
			pstat.setString(3, pdto.getProg_seq());
			
			return pstat.executeUpdate();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		return 0;
	}
	
	//게시판 글 넣을 때 free_seq를 1씩 증가시키기
		private int getMaxPappSeq() {

			try {
				
				String sql = "select max(free_seq) + 1 from tblFree";
				
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

		public int getProgramTotalCount(HashMap<String, String> map) {
			try {
				 
				 String where ="";
		            
		            
				 if (map.get("search").equals("y")) {
			            where = String.format(
			                  "where prog_date BETWEEN TO_DATE('%s', 'YYYY-MM-DD') and TO_DATE('%s', 'YYYY-MM-DD')",
			                  map.get("start_date"), map.get("end_date"));
			         }

					 String sql = String.format("select count(*) as cnt from tblProgram %s", where);

			  
			  pstat = conn.prepareStatement(sql);
			  rs = pstat.executeQuery();
			  
			  
			  if (rs.next()) {
			 
			  return rs.getInt("cnt"); }
			  
			  } catch (Exception e) { e.printStackTrace(); }
			  
		
			return 0;
		}

		


}
