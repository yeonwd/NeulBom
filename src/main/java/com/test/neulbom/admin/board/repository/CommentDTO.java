package com.test.neulbom.admin.board.repository;

import lombok.Data;

@Data
public class CommentDTO {
	
	String comment_seq;
	String content;
	String free_seq;
	String resi_seq;
	String protect_seq;
	
	String writer_type;
	String writer_name;
	
}
