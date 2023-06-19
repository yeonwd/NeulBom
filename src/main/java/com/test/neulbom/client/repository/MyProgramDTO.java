package com.test.neulbom.client.repository;

import lombok.Data;

@Data
public class MyProgramDTO {
	
	private String prog_seq;
	private String title;
	private String prog_date;
	private String content;
	private String place;
	private String apply;	//신청자 수
	private String people;	//정원
	private String papp_seq;
	
	private String resi_seq;	//신청한 입주자
	private String id;
	private String lv;
	private String rnum;
	
	private String displayed_seq;
}
