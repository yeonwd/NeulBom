package com.test.neulbom.admin.repository;

import lombok.Data;

@Data
public class AdminDTO {
	private String admin_seq;
	private String id;
	private String pw;
	private String name;
	private String ssn;
	private String tel;
	private String email;
	private String pic;
	private String lv;
	
	private String bank;
	private String bank_account;
	private String salary;
	
	
}
