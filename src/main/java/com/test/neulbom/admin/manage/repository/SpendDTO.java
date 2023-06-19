package com.test.neulbom.admin.manage.repository;

import lombok.Data;

@Data
public class SpendDTO {
	private String spend_seq;
	private String title;
	private String category;
	private String money;
	private String sdate;
	private String regeq_seq;
	private String admin_seq;
	
	private int rownum;
	private String spendSum;
	private int monthly;
}
