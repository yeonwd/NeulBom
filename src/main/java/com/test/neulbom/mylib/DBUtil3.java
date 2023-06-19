package com.test.neulbom.mylib;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBUtil3 {
	public static Connection open() {
		
		String url = "jdbc:oracle:thin:@180.68.11.121:1521:xe";
		String id = "hr";
		String pw = "java1234";
		
		
		try {
				
			Connection conn = null;
			Class.forName("oracle.jdbc.driver.OracleDriver");

			conn = DriverManager.getConnection(url, id, pw);	
			
			return conn;
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		return null;
		
	} //open
	
	
	// 다른 계정으로 접속할 수 있는 메소드
	public static Connection open(String server, String id, String pw) {
		
		String url = "jdbc:oracle:thin:@" + server + ":1521:xe";		
		
		try {
				
			Connection conn = null;
			Class.forName("oracle.jdbc.driver.OracleDriver");

			conn = DriverManager.getConnection(url, id, pw);	
			
			return conn;
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		return null;
		
	} //open
}
