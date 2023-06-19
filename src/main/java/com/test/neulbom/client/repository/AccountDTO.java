package com.test.neulbom.client.repository;

import lombok.Data;

@Data
public class AccountDTO {
	
	private String id;		//회원 아이디
	private String pw;		//회원 비밀번호
	private String name;	//회원 이름
	private String ssn;		//회원 주민등록번호

}
