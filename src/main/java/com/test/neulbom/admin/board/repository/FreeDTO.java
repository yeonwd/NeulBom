package com.test.neulbom.admin.board.repository;

import lombok.Data;

@Data
public class FreeDTO {
	
	String free_seq;
	String displayed_seq;
	String title;
	String content;
	String free_date;
	String fname;
	String read;
	String protect_seq;
	String resi_seq;
	
	String writer_type;
	String writer_name;
	
}
