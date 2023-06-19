package com.test.neulbom.client.repository;

import lombok.Data;

@Data
public class Mypage_InfoDTO {
	private String resi_seq;
	private String resi_id;
	private String resi_name;
	private String resi_ssn;
	private String resi_tel;
	private String resi_email;
	private String resi_address;
	private String resi_lv;
	
	private String protect_seq;
	private String pro_id;
	private String pro_name;
	private String pro_ssn;
	private String pro_tel;
	private String pro_email;
	private String pro_relation;
	private String pro_address;
	private String pro_lv;

}
