package com.test.neulbom.client.repository;

import lombok.Data;

@Data
public class CommentDTO {
	
	private String comment_seq;
	private String id;
	private String content;
	private String free_seq;
	private String resi_seq;
	private String protect_seq;
	private String lv;
	
	private String name;

}
