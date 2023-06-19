package com.test.neulbom.admin.repository;

import java.util.ArrayList;
import java.util.List;

public class test {
	
	public static void main(String[] args) {
		
//		Queue<Integer> qlist = new LinkedList<Integer>();
//		Queue<Integer> flist = new LinkedList<Integer>();
		
		List<Integer> qlist = new ArrayList<Integer>();
		List<Integer> flist = new ArrayList<Integer>();
		
		for (int i=1; i<=140; i++) {
			int protect = (int)(Math.random()*139) + 1;
			qlist.add(protect);
		}
		
		for (int i=1; i<=200; i++) {
			int resident = (int)(Math.random()*199) + 1;
			flist.add(resident);
		}
		
		
		
		for (int i=1; i<=30; i++) {
			int rnd = (int)(Math.random() * 2) + 1;
		
			if (rnd == 1) {
				
				System.out.printf("update tblQna set protect_seq = %d where qna_seq = %d;", qlist.get(i), i);
				System.out.println();
				
			} else {
				System.out.printf("update tblQna set resi_seq = %d where qna_seq = %d;", flist.get(i), i);
				System.out.println();
			}

		}
		
		
		
		
		
		
		
	}

}
