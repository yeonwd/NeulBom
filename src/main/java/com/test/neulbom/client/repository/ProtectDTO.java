package com.test.neulbom.client.repository;

import lombok.Data;

@Data
public class ProtectDTO {
	private String protect_seq; 
	private String id;
	private String pw;
	private String name;
	private String ssn;
	private String tel;
	private String email;
	private String resi_seq;
	private String relation;
	private String address;
	private String lv;
	
	private String resi_name;
}

