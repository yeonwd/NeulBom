package com.test.neulbom.admin.manage.repository;

import lombok.Data;

@Data
public class ConsultDTO {
	
	String con_seq;
	String title;
	String content;
	String con_date;
	String isReply;
	String nomem_seq;
	
	String displayed_seq;
	
	String writer_name;
	String writer_tel;

}
