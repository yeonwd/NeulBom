package com.test.neulbom.client.repository;

import lombok.Data;

@Data
public class QnaDTO {
	private String qna_seq;
	private String title;
	private String content;
	private String qna_date;
	private String isreply;
	private String fname; 
	private String category;
	private String read;
	private String protect_seq; 
	private String resi_seq;
	
	private String name;
	private String id;
	private String rnum;
}
