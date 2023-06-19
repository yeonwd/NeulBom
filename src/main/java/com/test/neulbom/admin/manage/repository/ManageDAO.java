package com.test.neulbom.admin.manage.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.test.neulbom.mylib.DBUtil3;

public class ManageDAO {

	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private ResultSet rs;

	public ManageDAO() {
		this.conn = DBUtil3.open();
	}

	// 면회 신청 조회
	public List<MeetDTO> getMeet(HashMap<String, String> map) {

		try {

//			String sql = "SELECT m.*, p.name AS pname, r.name AS rname FROM tblMeet m INNER JOIN tblProtect p ON m.protect_seq = p.protect_seq INNER JOIN tblResident r ON m.resi_seq = r.resi_seq ORDER BY CASE WHEN m.CONFIRMATION IS NULL THEN 0 ELSE 1 END, CASE WHEN m.CONFIRMATION IS NULL THEN m.MEET_DATE END ASC, CASE WHEN m.CONFIRMATION IS NOT NULL AND m.MEET_DATE > TRUNC(SYSDATE) THEN m.MEET_DATE ELSE TO_DATE('9999-12-31', 'YYYY-MM-DD') END ASC, m.MEET_DATE DESC";

			String sql = String.format(
					"select * from (select rownum as rnum, a.* from (SELECT m.*, p.name AS pname, r.name AS rname FROM tblMeet m INNER JOIN tblProtect p ON m.protect_seq = p.protect_seq INNER JOIN tblResident r ON m.resi_seq = r.resi_seq ORDER BY CASE WHEN m.CONFIRMATION IS NULL THEN 0 ELSE 1 END, CASE WHEN m.CONFIRMATION IS NULL THEN m.MEET_DATE END ASC, CASE WHEN m.CONFIRMATION IS NOT NULL AND m.MEET_DATE > TRUNC(SYSDATE) THEN m.MEET_DATE ELSE TO_DATE('9999-12-31', 'YYYY-MM-DD') END ASC, m.MEET_DATE DESC) a) where rnum between %s and %s",
					map.get("begin"), map.get("end"));

			stat = conn.createStatement();
			rs = stat.executeQuery(sql);

			List<MeetDTO> list = new ArrayList<MeetDTO>();
			int i = Integer.parseInt(map.get("begin"));

			while (rs.next()) {

				MeetDTO dto = new MeetDTO();

				dto.setMeet_seq(rs.getString("meet_seq"));
				dto.setDisplayed_seq(i + "");

				String date = rs.getString("meet_date").substring(0, 10);
				dto.setMeet_date(date);

				String time = rs.getString("meet_time");

				String[] temp = time.split("~");
				String temp1 = temp[0];
				String temp2 = temp[1];

				if (temp1.equals("3"))
					temp1 = "15";
				if (temp1.equals("6"))
					temp1 = "18";

				if (temp2.equals("3"))
					temp2 = "15";
				if (temp2.equals("6"))
					temp2 = "18";

				dto.setMeet_time(String.format("%s:00~%s:00", temp1, temp2));

				dto.setProtect_seq(rs.getString("protect_seq"));
				dto.setResi_seq(rs.getString("resi_seq"));
				dto.setConfirmation(rs.getString("confirmation"));

				dto.setPname(rs.getString("pname"));
				dto.setRname(rs.getString("rname"));

				// 현재보다 미래 시점인지 날짜 비교

				String[] dateTemp = date.trim().split("-");
				int year = Integer.parseInt(dateTemp[0]);
				int month = Integer.parseInt(dateTemp[1]);
				int day = Integer.parseInt(dateTemp[2]);

				LocalDate mdate = LocalDate.of(year, month, day);
				LocalDate now = LocalDate.now();

				if (mdate.isAfter(now))
					dto.setIsRevisable(1);
				else
					dto.setIsRevisable(0);

				list.add(dto);
				i++;
			}

			return list;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	// 면회 승인
	public int confirmMeeting(String seq) {

		try {

			String sql = "UPDATE tblMeet SET confirmation = 'y' where meet_seq = ?";

			pstat = conn.prepareStatement(sql);
			pstat.setString(1, seq);

			return pstat.executeUpdate();

		} catch (Exception e) {

			e.printStackTrace();

		}

		return 0;
	}

	// 면회 반려
	public int rejectMeeting(String seq) {
		try {

			String sql = "UPDATE tblMeet SET confirmation = 'n' where meet_seq = ?";

			pstat = conn.prepareStatement(sql);
			pstat.setString(1, seq);

			return pstat.executeUpdate();

		} catch (Exception e) {

			e.printStackTrace();

		}

		return 0;
	}

	// 결제 기록 조회
	public List<PayDTO> getPayRecord(HashMap<String, String> smap, HashMap<String, String> map) {

		try {

			String where = "";

			if (smap.get("search").equals("y"))
				where = String.format("WHERE r.name LIKE '%%%s%%'", smap.get("name"));

			String sql = String.format(
					"select * from (select rownum as rnum, a.* from (SELECT p.pay_seq, p.resi_seq, p.ispay, p.pay_date, r.id, r.name, m.kind, p.price, r.tel From tblPay p INNER JOIN tblResident r ON p.resi_seq = r.resi_seq INNER JOIN tblMove m ON p.resi_seq = m.resi_seq %s ORDER BY CASE WHEN p.ispay = 'n' THEN 0 ELSE 1 END, p.pay_date DESC) a) where rnum between %s and %s",
					where, map.get("begin"), map.get("end"));

			stat = conn.createStatement();
			rs = stat.executeQuery(sql);

			List<PayDTO> list = new ArrayList<PayDTO>();

			int i = Integer.parseInt(map.get("begin"));

			while (rs.next()) {

				PayDTO dto = new PayDTO();

				dto.setPay_seq(rs.getString("pay_seq"));
				dto.setResi_seq(rs.getString("resi_seq"));
				dto.setIsPay(rs.getString("isPay"));
				dto.setPay_date(rs.getString("pay_date").substring(0, 10));

				dto.setDisplayed_seq(i + "");

				dto.setId(rs.getString("id"));
				dto.setName(rs.getString("name"));
				dto.setKind(rs.getString("kind"));

				int price = rs.getInt("price");
				dto.setPrice(price);

				dto.setTel(rs.getString("tel"));

				list.add(dto);
				i++;
			}

			return list;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;

	}

	// 일반 문의 전체 조회
	public List<QnaDTO> getQna(HashMap<String, String> map) {

		try {

			String sql = String.format(
					"select * from (select rownum as rnum, a.* from (SELECT q.*, r.name AS rname, p.name AS pname FROM tblQna q LEFT OUTER JOIN tblResident r ON r.resi_seq = q.resi_seq LEFT OUTER JOIN tblProtect p ON p.protect_seq = q.protect_seq ORDER BY CASE WHEN q.isReply = 'n' THEN q.qna_date END ASC, CASE WHEN q.isReply = 'y' THEN q.qna_date END DESC) a) where rnum between %s and %s",
					map.get("begin"), map.get("end"));

			stat = conn.createStatement();
			rs = stat.executeQuery(sql);

			List<QnaDTO> list = new ArrayList<QnaDTO>();
			int i = Integer.parseInt(map.get("begin"));

			while (rs.next()) {

				QnaDTO dto = new QnaDTO();

				dto.setQna_seq(rs.getString("qna_seq"));
				dto.setTitle(rs.getString("title"));
				dto.setContent(rs.getString("content"));
				dto.setQna_date(rs.getString("qna_date").substring(0, 10));
				dto.setIsReply(rs.getString("isReply"));
				dto.setFname(rs.getString("fname"));
				dto.setCategory(rs.getString("category"));
				dto.setRead(rs.getString("read"));

				dto.setDisplayed_seq(i + "");

				if (rs.getString("rname") != null) {
					dto.setType("입주자");
					dto.setRname(rs.getString("rname"));
				} else if (rs.getString("pname") != null) {
					dto.setType("보호자");
					dto.setPname(rs.getString("pname"));
				}

				list.add(dto);
				i++;
			}

			return list;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	// 일반 문의 상세 보기
	public QnaDTO getQna(String seq) {

		try {

			String sql = "SELECT q.*, r.name AS rname, p.name AS pname FROM tblQna q LEFT OUTER JOIN tblResident r ON r.resi_seq = q.resi_seq LEFT OUTER JOIN tblProtect p ON p.protect_seq = q.protect_seq WHERE q.qna_seq = ?";

			pstat = conn.prepareStatement(sql);
			pstat.setString(1, seq);
			rs = pstat.executeQuery();

			QnaDTO dto = new QnaDTO();

			while (rs.next()) {

				dto.setQna_seq(rs.getString("qna_seq"));
				dto.setTitle(rs.getString("title"));
				dto.setContent(rs.getString("content"));
				dto.setQna_date(rs.getString("qna_date").substring(0, 10));
				dto.setIsReply(rs.getString("isReply"));
				dto.setFname(rs.getString("fname"));
				dto.setCategory(rs.getString("category"));
				dto.setRead(rs.getString("read"));

				if (rs.getString("rname") != null) {
					dto.setType("입주자");
					dto.setRname(rs.getString("rname"));
				} else if (rs.getString("pname") != null) {
					dto.setType("보호자");
					dto.setPname(rs.getString("pname"));
				}
			}

			return dto;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// 문의 답글 조회
	public QreplyDTO getQReply(String seq) {

		try {

			String sql = "SELECT * FROM tblQreply q INNER JOIN tblAdmin a ON q.admin_seq = a.admin_seq where qna_seq = ?";

			pstat = conn.prepareStatement(sql);
			pstat.setString(1, seq);
			rs = pstat.executeQuery();

			QreplyDTO dto = new QreplyDTO();

			while (rs.next()) {

				dto.setTitle(rs.getString("title"));
				dto.setContent(rs.getString("content"));
				dto.setFname(rs.getString("fname"));
				dto.setAdmin_seq(rs.getString("admin_seq"));
				dto.setQna_seq(rs.getString("qna_seq"));
				dto.setRead(rs.getString("read"));

				dto.setReplier(rs.getString("name"));

			}

			return dto;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	// 입주 문의 전체 조회
	public List<ConsultDTO> getConsult(HashMap<String, String> map) {

		try {

			String sql = String.format(
					"select * from (select rownum as rnum, a.* from (SELECT c.*, n.name, n.tel FROM tblConsult c INNER JOIN tblNomem n ON c.nomem_seq = n.nomem_seq ORDER BY CASE WHEN c.isReply = 'n' THEN c.con_date END ASC, CASE WHEN c.isReply = 'y' THEN c.con_date END DESC) a) where rnum between %s and %s",
					map.get("begin"), map.get("end"));

			stat = conn.createStatement();
			rs = stat.executeQuery(sql);

			List<ConsultDTO> list = new ArrayList<ConsultDTO>();
			int i = Integer.parseInt(map.get("begin"));

			while (rs.next()) {

				ConsultDTO dto = new ConsultDTO();

				dto.setCon_seq(rs.getString("con_seq"));
				dto.setTitle(rs.getString("title"));
				dto.setContent(rs.getString("content"));
				dto.setCon_date(rs.getString("con_date").substring(0, 10));
				dto.setIsReply(rs.getString("isReply"));
				dto.setNomem_seq(rs.getString("nomem_seq"));
				dto.setWriter_name(rs.getString("name"));
				dto.setWriter_tel(rs.getString("tel"));

				dto.setDisplayed_seq(i + "");

				list.add(dto);
				i++;
			}

			return list;

		} catch (Exception e) {
			e.printStackTrace();

		}
		return null;
	}

	// 일반 문의 답글 달기
	public int replyToQna(String seq, String title, String content, String fname, String admin_seq) {

		try {

			String sql = "INSERT INTO tblqreply VALUES (qReplySeq.nextVal, ?, ?, ?, ?, ?, 0, 0, 0)";

			pstat = conn.prepareStatement(sql);

			pstat.setString(1, title);
			pstat.setString(2, content);
			pstat.setString(3, fname);
			pstat.setString(4, admin_seq);
			pstat.setString(5, seq);

			if (updateIsReply("tblQna", "qna_seq", seq) == 1) {
				return pstat.executeUpdate();
			} else
				return 0;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return 0;
	}

	// 문의 답변 여부 컬럼 업데이트
	private int updateIsReply(String tbl, String column, String seq) {
		try {

			String sql = String.format("UPDATE %s SET isReply = 'y' WHERE %s = %s", tbl, column, seq);

			stat = conn.createStatement();
			return stat.executeUpdate(sql);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return 0;
	}

	// 입주문의 글 출력
	public ConsultDTO getConsult(String seq) {

		try {

			String sql = "SELECT c.*, n.name, n.tel FROM tblConsult c INNER JOIN tblNomem n ON c.nomem_seq = n.nomem_seq WHERE c.con_seq = "
					+ seq;

			stat = conn.createStatement();
			rs = stat.executeQuery(sql);

			ConsultDTO dto = new ConsultDTO();

			while (rs.next()) {

				dto.setCon_seq(rs.getString("con_seq"));
				dto.setTitle(rs.getString("title"));
				dto.setContent(rs.getString("content"));
				dto.setCon_date(rs.getString("con_date").substring(0, 10));
				dto.setIsReply(rs.getString("isReply"));
				dto.setNomem_seq(rs.getString("nomem_seq"));
				dto.setWriter_name(rs.getString("name"));
				dto.setWriter_tel(rs.getString("tel"));

			}

			return dto;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	// 입주문의 답글 가져오기
	public CreplyDTO getCReply(String seq) {

		try {

			String sql = "SELECT * FROM tblCreply c INNER JOIN tblAdmin a ON c.admin_seq = a.admin_seq where con_seq = "
					+ seq;

			stat = conn.createStatement();
			rs = stat.executeQuery(sql);

			CreplyDTO dto = new CreplyDTO();

			while (rs.next()) {

				dto.setCreply_seq(rs.getString("creply_seq"));
				dto.setTitle(rs.getString("title"));
				dto.setContent(rs.getString("content"));

				dto.setCfile(rs.getString("cfile"));
				dto.setAdmin_seq(rs.getString("admin_seq"));
				dto.setCon_seq(rs.getString("con_seq"));

				dto.setReplier(rs.getString("name"));

			}

			return dto;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	// 입주문의 답글 달기
	public int replyToConsult(String seq, String title, String content, String fname, String admin_seq) {
		try {

			String sql = "INSERT INTO tblcreply VALUES (creply_seq.nextVal, ?, ?, ?, ?, ?, 0, 0)";

			pstat = conn.prepareStatement(sql);

			pstat.setString(1, title);
			pstat.setString(2, content);
			pstat.setString(3, fname);
			pstat.setString(4, admin_seq);
			pstat.setString(5, seq);
			
			if (updateIsReply("tblConsult", "con_seq", seq) == 1) {
				return pstat.executeUpdate();
			} else
				return 0;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return 0;
	}

	// 승인된 면회 목록(캘린더 출력용)
	public List<MeetDTO> confirmedMeet() {

		try {

			String sql = "SELECT m.*, p.name AS pname, r.name AS rname FROM tblMeet m INNER JOIN tblProtect p ON m.protect_seq = p.protect_seq INNER JOIN tblResident r ON m.resi_seq = r.resi_seq WHERE m.confirmation = 'y'";

			stat = conn.createStatement();
			rs = stat.executeQuery(sql);

			List<MeetDTO> list = new ArrayList<MeetDTO>();

			while (rs.next()) {

				MeetDTO dto = new MeetDTO();

				dto.setMeet_seq(rs.getString("meet_seq"));

				String date = rs.getString("meet_date").substring(0, 10);
				dto.setMeet_date(date);

				String time = rs.getString("meet_time");

				String[] temp = time.split("~");
				String temp1 = temp[0];

				if (temp1.equals("9"))
					temp1 = "09";
				else if (temp1.equals("3"))
					temp1 = "15";

				dto.setMeet_time(String.format("%s:00:00", temp1));

				dto.setPname(rs.getString("pname"));
				dto.setRname(rs.getString("rname"));

				String[] dateTemp = date.trim().split("-");
				int year = Integer.parseInt(dateTemp[0]);
				int month = Integer.parseInt(dateTemp[1]);
				int day = Integer.parseInt(dateTemp[2]);

				LocalDate mdate = LocalDate.of(year, month, day);
				LocalDate now = LocalDate.now();

				if (mdate.isAfter(now))
					dto.setIsRevisable(1);
				else
					dto.setIsRevisable(0);

				list.add(dto);
			}

			return list;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;

	}

	// TODO: 소엽 수정
	// - map > smap으로 수정
	public int getTotalCount(HashMap<String, String> smap, int size, String table) {

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

	public int getTotalCount2(HashMap<String, String> map) {

		try {

			String where = "";

			if (map.get("search").equals("y")) {
				where = String.format("where tblResident.name like '%%%s%%'", map.get("name"));
			}

			String sql = String.format(
					"select count(*) as cnt from tblPay inner join tblResident on tblpay.resi_seq = tblResident.resi_seq %s",
					where);

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
