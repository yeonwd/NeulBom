package com.test.neulbom.client.repository;

import lombok.Data;

@Data
public class MeetDTO {
	private String meet_seq; 
	private String meet_date;
	private String meet_time;
	private String protect_seq;
	private String resi_seq;
	private String confirmation;
	
	private String pname;
	private String rname;
	private String rnum;
}
