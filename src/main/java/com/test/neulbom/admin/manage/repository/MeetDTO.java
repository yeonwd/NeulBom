package com.test.neulbom.admin.manage.repository;

import lombok.Data;

@Data
public class MeetDTO {
	
	String meet_seq;
	String meet_date;
	String meet_time;
	String protect_seq;
	String resi_seq;
	String confirmation;
	
	String pname;
	String rname;
	
	String displayed_seq;
	
	int isRevisable;

}
