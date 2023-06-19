package com.test.neulbom.client.repository;

import lombok.Data;

@Data
public class Mypage_InquiryDTO {
	private String qna_seq;
	private String title;
	private String content;
	private String qna_date;
	private String isreply;
	private String fname;
	private String category;
	private String protect_seq;
	private String pro_seq;
	private String resi_seq;
	private String pro_name;
	private String resi_name;
}
