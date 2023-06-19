package com.test.neulbom.admin.manage.repository;

import lombok.Data;

@Data
public class PayDTO {

	String pay_seq;
	String resi_seq;
	String isPay;
	String pay_date;
	
	String displayed_seq;
	
	String id;
	String name;
	String kind;
	String price;
	String tel;
	
	public void setPrice(int price) {
	    this.price = String.format("%,d", price);
	}
	
}

