package com.test.neulbom.client.repository;

import lombok.Data;

@Data
public class FreeDTO {
	
	private String free_seq;	//자유게시판 글 번호
	private String title;		//자유게시판 글 제목
	private String content;		//자유게시판 글 내용
	private String file;		//자유게시판 글 첨부파일
	
	private String id;			//자유게시판 글 작성자 ID
	private String protect_seq;	//자유게시판 글 작성자 seq(보호자)
	private String resi_seq;	//자유게시판 글 작성자 seq(입주자)
	
	private String name;		//자유게시판 글 작성자 이름
	private String lv;			//자유게시판 글 작성자 레벨
	
	private String read;		//자유게시판 글 조회수
	private String free_date;	//자유게시판 작성날짜
	
	private double isnew; 		//최신글	
	
	private String ccnt; 		//현재글에 달린 댓글 개수

	private int thread;			//쓰레드
	private int depth;			
	
	private String displayed_seq;

}
