package com.test.neulbom.client.repository;

import lombok.Data;

@Data
public class BoardWjDTO {
	private String con_seq;
	private String con_title;
	private String con_content;
	private String con_date;
	private String isreply;
	private String creply_seq;
	private String cre_title;
	private String cre_content;
	private String nomem_name;
	private String nomem_seq;
	
	
}
