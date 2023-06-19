package com.test.neulbom.admin.manage.repository;

import lombok.Data;

@Data
public class RegEqDTO {
	private String reqEq_seq;
	private String name;
	private String admin_seq;
	private String content;
	private int quantity;
	private String reg_date;
	private String isAccept;
	private int price;

}
