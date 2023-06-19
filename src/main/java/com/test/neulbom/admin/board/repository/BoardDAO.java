package com.test.neulbom.admin.board.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.test.neulbom.mylib.DBUtil3;

public class BoardDAO {
	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private ResultSet rs;
	private ResultSet rs2;

	public BoardDAO() {
		this.conn = DBUtil3.open();
	}

	// TODO
	// 글쓰기: 각 게시판-관리자 관계 테이블 참조 데이터 생성
	private int createWriter(String writer, String seq, String table) {
		return 0;
	}

	// 글 삭제: 각 게시판-관리자 관계 테이블 참조 데이터 null화
	private int updateWriter(String seq, String table) {

		try {

			String sql = "";

			switch (table) {
			case "notice":
				sql = "UPDATE tblAlert SET notice_seq = null WHERE notice_seq = ?";
				break;
			case "life":
				sql = "UPDATE tblAlert SET life_seq = null WHERE life_seq = ?";
				break;
			}

			pstat = conn.prepareStatement(sql);

			pstat.setString(1, seq);

			int result = pstat.executeUpdate();

			return result;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return 0;
	}

	// 공지게시판 글 삭제
	public int deleteNotice(String seq) {

		try {

			updateWriter(seq, "notice");

			String sql = "DELETE FROM tblNotice where notice_seq = ?";

			pstat = conn.prepareStatement(sql);

			pstat.setString(1, seq);

			return pstat.executeUpdate();

		} catch (Exception e) {

			e.printStackTrace();

		}
		return 0;
	}

	// 공지게시판 전체 글 출력
	public List<NoticeDTO> getNotice(HashMap<String, String> map) {

		try {

			String sql = String.format(
					"select * from (select rownum as rnum, a.* from (select * from tblNotice order by notice_date desc) a) where rnum between %s and %s",
					map.get("begin"), map.get("end"));

			stat = conn.createStatement();
			rs = stat.executeQuery(sql);

			List<NoticeDTO> list = new ArrayList<NoticeDTO>();
			int i = Integer.parseInt(map.get("begin"));

			while (rs.next()) {

				NoticeDTO dto = new NoticeDTO();

				dto.setNotice_seq(rs.getString("notice_seq"));
				dto.setDisplayed_seq(i + "");
				dto.setTitle(rs.getString("title"));
				dto.setContent(rs.getString("content"));
				dto.setNotice_date(rs.getString("notice_date").substring(0, 10));
				dto.setRead(rs.getString("read"));

				list.add(dto);
				i++;
			}

			return list;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	// 식단표 게시판 전체 글 출력
	public List<FoodDTO> getFood(HashMap<String, String> map) {
		try {

			String sql = String.format(
					"select * from (select rownum as rnum, a.* from (select * from tblFood order by food_date desc) a) where rnum between %s and %s",
					map.get("begin"), map.get("end"));

			stat = conn.createStatement();
			rs = stat.executeQuery(sql);

			List<FoodDTO> list = new ArrayList<FoodDTO>();
			int i = Integer.parseInt(map.get("begin"));

			while (rs.next()) {

				FoodDTO dto = new FoodDTO();

				dto.setFood_seq(rs.getString("food_seq"));
				dto.setDisplayed_seq(i + "");
				dto.setTitle(rs.getString("title"));
				dto.setFood_date(rs.getString("food_date"));
				dto.setContent(rs.getString("content"));
				dto.setFood_date(rs.getString("food_date").substring(0, 10));
				dto.setRead(rs.getString("read"));

				list.add(dto);
				i++;
			}

			return list;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	// 공지게시판 글 조회
	public NoticeDTO showNotice(String seq) {

		try {

			String sql = "select * from tblnotice where notice_seq = ?";

			pstat = conn.prepareStatement(sql);
			pstat.setString(1, seq);

			rs = pstat.executeQuery();
			NoticeDTO dto = new NoticeDTO();

			while (rs.next()) {

				dto.setNotice_seq(rs.getString("notice_seq"));
				dto.setDisplayed_seq("-1");
				dto.setTitle(rs.getString("title"));
				dto.setContent(rs.getString("content"));
				dto.setNotice_date(rs.getString("notice_date").substring(0, 10));
				dto.setRead(rs.getString("read"));
			}

			return dto;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	// 공지게시판 새 글 쓰기
	public int addNotice(String title, String content) {

		try {

			String sql = "INSERT INTO tblNotice VALUES (notice_seq.nextVal, ?, ?, sysdate, 0)";

			pstat = conn.prepareStatement(sql);

			pstat.setString(1, title);
			pstat.setString(2, content);

			int result = pstat.executeUpdate();

			return result;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return 0;
	}

	// 공지게시판 수정: 기존 데이터 출력
	public NoticeDTO editNotice(String seq) {

		try {

			String sql = "SELECT * FROM tblNotice WHERE notice_seq = ?";

			pstat = conn.prepareStatement(sql);

			pstat.setString(1, seq);

			rs = pstat.executeQuery();

			NoticeDTO dto = new NoticeDTO();

			while (rs.next()) {

				dto.setNotice_seq(rs.getString("notice_seq"));
				dto.setTitle(rs.getString("title"));
				dto.setContent(rs.getString("content"));
				dto.setNotice_date(rs.getString("notice_date"));
				dto.setRead(rs.getString("read"));

			}

			return dto;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	// 공지게시판 수정: 수정 사항 DB 반영
	public int editNotice(String seq, String title, String content) {
		try {

			String sql = "UPDATE tblNotice SET title=?, content=? WHERE notice_seq = ?";

			pstat = conn.prepareStatement(sql);
			pstat.setString(1, title);
			pstat.setString(2, content);
			pstat.setString(3, seq);
			return pstat.executeUpdate();

		} catch (Exception e) {

			e.printStackTrace();

		}

		return 0;
	}

	// 생활게시판 전체 글 출력
	public List<LifeDTO> getLife(HashMap<String, String> map) {

		try {

			String sql = String.format(
					"select * from (select rownum as rnum, a.* from (select * from tblLife order by life_date desc) a) where rnum between %s and %s",
					map.get("begin"), map.get("end"));

			stat = conn.createStatement();
			rs = stat.executeQuery(sql);

			List<LifeDTO> list = new ArrayList<LifeDTO>();
			int i = Integer.parseInt(map.get("begin"));

			while (rs.next()) {

				LifeDTO dto = new LifeDTO();

				dto.setLife_seq(rs.getString("life_seq"));
				dto.setDisplayed_seq(i + "");
				dto.setTitle(rs.getString("title"));
				dto.setContent(rs.getString("content"));
				dto.setRead(rs.getString("read"));
				dto.setLife_date(rs.getString("life_date").substring(0, 10));

				list.add(dto);
				i++;
			}

			return list;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	// 생활게시판 글 조회
	public LifeDTO showLife(String seq) {

		try {

			String sql = "select * from tblLife where life_seq = ?";

			pstat = conn.prepareStatement(sql);
			pstat.setString(1, seq);

			rs = pstat.executeQuery();
			LifeDTO dto = new LifeDTO();

			while (rs.next()) {

				dto.setLife_seq(rs.getString("life_seq"));
				dto.setDisplayed_seq("-1");
				dto.setTitle(rs.getString("title"));
				dto.setContent(rs.getString("content"));
				dto.setLife_date(rs.getString("life_date").substring(0, 10));
				dto.setRead(rs.getString("read"));
			}

			return dto;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;

	}

	// 생활게시판 수정: 기존 데이터 출력
	public LifeDTO editLife(String seq) {

		try {

			String sql = "SELECT * FROM tblLife WHERE life_seq = ?";

			pstat = conn.prepareStatement(sql);

			pstat.setString(1, seq);

			rs = pstat.executeQuery();

			LifeDTO dto = new LifeDTO();

			while (rs.next()) {

				dto.setLife_seq(rs.getString("life_seq"));
				dto.setDisplayed_seq("-1");
				dto.setTitle(rs.getString("title"));
				dto.setContent(rs.getString("content"));
				dto.setRead(rs.getString("read"));
				dto.setLife_date(rs.getString("life_date"));
			}

			return dto;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	// 생활게시판 수정: 수정 사항 DB 반영
	public int editLife(String seq, String title, String content) {
		try {

			String sql = "UPDATE tblLife SET title=?, content=? WHERE life_seq = ?";

			pstat = conn.prepareStatement(sql);
			pstat.setString(1, title);
			pstat.setString(2, content);
			pstat.setString(3, seq);
			return pstat.executeUpdate();

		} catch (Exception e) {

			e.printStackTrace();

		}

		return 0;
	}

	// 생활게시판 새 글 쓰기
	public int addLife(String title, String content) {

		try {

			String sql = "INSERT INTO tblLife (life_seq, title, content, life_date, read) VALUES (life_seq.nextVal, ?, ?, sysdate, 0)";

			pstat = conn.prepareStatement(sql);

			pstat.setString(1, title);
			pstat.setString(2, content);

			int result = pstat.executeUpdate();

			return result;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return 0;

	}

	// 생활게시판 글 삭제
	public int deleteLife(String seq) {

		try {

			updateWriter(seq, "life");

			String sql = "DELETE FROM tblLife where life_seq = ?";

			pstat = conn.prepareStatement(sql);

			pstat.setString(1, seq);

			return pstat.executeUpdate();

		} catch (Exception e) {

			e.printStackTrace();

		}
		return 0;
	}

	// 자유게시판 전체 게시물 출력
	public List<FreeDTO> getFree(HashMap<String, String> map) {

		try {

			String sql = String.format(
					"select * from (select rownum as rnum, a.* from (select * from tblFree order by free_date desc) a) where rnum between %s and %s",
					map.get("begin"), map.get("end"));

			stat = conn.createStatement();
			rs = stat.executeQuery(sql);

			List<FreeDTO> list = new ArrayList<FreeDTO>();
			int i = Integer.parseInt(map.get("begin"));

			while (rs.next()) {

				FreeDTO dto = new FreeDTO();

				dto.setFree_seq(rs.getString("free_seq"));
				dto.setDisplayed_seq(i + "");
				dto.setTitle(rs.getString("title"));
				dto.setContent(rs.getString("content"));
				dto.setFree_date(rs.getString("free_date").substring(0, 10));
				dto.setFname(rs.getString("fname"));
				dto.setRead(rs.getString("read"));

				String pseq = rs.getString("protect_seq");
				String rseq = rs.getString("resi_seq");

				dto.setProtect_seq(pseq);
				dto.setResi_seq(rseq);

				List<String> temp = getWriter(dto.getFree_seq(), "free");
				dto.setWriter_type(temp.get(0));
				dto.setWriter_name(temp.get(1));

				list.add(dto);
				i++;

			}

			return list;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

// 자유게시판/댓글 작성자 이름 출력	
	private List<String> getWriter(String seq, String table) {
		String tbl = "";
		String column = "";

		switch (table) {
		case "free":
			tbl = "tblFree";
			column = "free_seq";
			break;
		case "comment":
			tbl = "tblComment";
			column = "comment_seq";
			break;
		}

		try {
			String sql = "SELECT p.name as pname, r.name as rname " + "FROM " + tbl + " f "
					+ "LEFT OUTER JOIN tblProtect p ON f.protect_seq = p.protect_seq "
					+ "LEFT OUTER JOIN tblResident r ON f.resi_seq = r.resi_seq " + "WHERE f." + column + "= ?";

			pstat = conn.prepareStatement(sql);
			pstat.setString(1, seq);

			rs2 = pstat.executeQuery();

			List<String> temp = new ArrayList<String>();

			if (rs2.next()) {
				if (rs2.getString("pname") != null) {
					temp.add("보호자");
					temp.add(rs2.getString("pname"));
				} else {
					temp.add("입주자");
					temp.add(rs2.getString("rname"));
				}
			}

			return temp;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public FoodDTO showFood(String seq) {

		try {

			String sql = "select * from tblFood where food_seq = ?";

			pstat = conn.prepareStatement(sql);
			pstat.setString(1, seq);

			rs = pstat.executeQuery();
			FoodDTO dto = new FoodDTO();

			while (rs.next()) {

				dto.setFood_seq(rs.getString("food_seq"));
				dto.setTitle(rs.getString("title"));
				dto.setFood_date(rs.getString("food_date"));
				dto.setContent(rs.getString("content"));
				dto.setFood_date(rs.getString("food_date").substring(0, 10));
				dto.setRead(rs.getString("read"));

			}

			return dto;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	// 자유게시판 글 조회
	public FreeDTO showFree(String seq) {

		try {

			String sql = "select * from tblFree where free_seq = ?";

			pstat = conn.prepareStatement(sql);
			pstat.setString(1, seq);

			rs = pstat.executeQuery();
			FreeDTO dto = new FreeDTO();

			while (rs.next()) {

				dto.setFree_seq(rs.getString("free_seq"));
				dto.setTitle(rs.getString("title"));
				dto.setContent(rs.getString("content"));
				dto.setFree_date(rs.getString("free_date").substring(0, 10));
				dto.setFname(rs.getString("fname"));
				dto.setRead(rs.getString("read"));

				String pseq = rs.getString("protect_seq");
				String rseq = rs.getString("resi_seq");

				dto.setProtect_seq(pseq);
				dto.setResi_seq(rseq);

				List<String> temp = getWriter(dto.getFree_seq(), "free");
				dto.setWriter_type(temp.get(0));
				dto.setWriter_name(temp.get(1));

			}

			return dto;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}


	// 자유게시판 글 삭제
	public int deleteFree(String seq) {

		try {

			deleteComment(seq);

			String sql = "DELETE FROM tblFree where free_seq = ?";

			pstat = conn.prepareStatement(sql);

			pstat.setString(1, seq);
			
			return pstat.executeUpdate();				

		} catch (Exception e) {

			e.printStackTrace();

		}

		return 0;
	}

	// 자유게시판 글 삭제 시 댓글 삭제
	private int deleteComment(String seq) {
		
		try {
			
			String sql = "delete from tblcomment where free_seq = ?";
			
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, seq);
			
			return pstat.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}


	// 식단 게시판 글 삭제
	public int deleteFood(String seq) {

		try {

			String sql = "DELETE FROM tblFood where food_seq = ?";

			pstat = conn.prepareStatement(sql);

			pstat.setString(1, seq);

			return pstat.executeUpdate();

		} catch (Exception e) {

			e.printStackTrace();

		}

		return 0;
	}

	// 자유게시판 댓글 조회
	public List<CommentDTO> getComment(String seq) {
		try {

			String sql = "SELECT * FROM tblComment WHERE free_seq = ?";

			pstat = conn.prepareStatement(sql);

			pstat.setString(1, seq);

			rs = pstat.executeQuery();

			List<CommentDTO> list = new ArrayList<CommentDTO>();

			while (rs.next()) {

				CommentDTO dto = new CommentDTO();

				dto.setComment_seq(rs.getString("comment_seq"));
				dto.setContent(rs.getString("content"));
				dto.setFree_seq(rs.getString("free_seq"));

				String pseq = rs.getString("protect_seq");
				String rseq = rs.getString("resi_seq");

				dto.setProtect_seq(pseq);
				dto.setResi_seq(rseq);

				List<String> temp = getWriter(dto.getComment_seq(), "comment");
				if (temp != null) {
					dto.setWriter_type(temp.get(0));
					dto.setWriter_name(temp.get(1));
				}

				list.add(dto);
			}

			return list;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public int getTotalCount(HashMap<String, String> map, int size, String table) {

		try {

			String sql = "select count(*) as cnt from " + table;

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

	public int addFood(String title, String fname) {
		
		try {
			
			String sql = "INSERT INTO tblFood VALUES (food_seq.nextVal, sysdate, ?, 0, ?)";
			
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, fname);
			pstat.setString(2, title);
			
			return pstat.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return 0;
	}

	public int editFood(String title, String fname, String seq) {
		try {
			
			String sql = "UPDATE tblFood SET title = ?, content = ? WHERE food_seq = ?";
			
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, title);
			pstat.setString(2, fname);	
			pstat.setString(3, seq);
			
			return pstat.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return 0;
	}

}
