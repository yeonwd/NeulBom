package com.test.neulbom.admin.manage.repository;

import lombok.Data;

@Data
public class QnaDTO {
	
	String qna_seq;
	String title;
	String content;
	String qna_date;
	String isReply;
	String fname;
	String category;
	String read;
	
	String displayed_seq;
	
	String type;
	String pname;
	String rname;

}
