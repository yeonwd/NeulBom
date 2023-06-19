package com.test.neulbom.admin.manage.repository;

import lombok.Data;

@Data
public class QreplyDTO {
	
	String title;
	String content;
	String fname;
	String admin_seq;
	String qna_seq;
	String read;
	
	String replier;

}
