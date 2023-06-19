package com.test.neulbom.admin.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.test.neulbom.mylib.DBUtil3;

public class AdminDAO {
   private Connection conn;
   private Statement stat;
   private PreparedStatement pstat;
   private ResultSet rs;
   
   public AdminDAO() {
      this.conn = DBUtil3.open();
   }
   public AdminDTO login(AdminDTO dto) {
      try {

         String sql = "select * from tblAdmin where id = ? and pw = ?";

         pstat = conn.prepareStatement(sql);

         pstat.setString(1, dto.getId());
         pstat.setString(2, dto.getPw());         

         rs = pstat.executeQuery();

         if (rs.next()) {

            AdminDTO result = new AdminDTO();

            result.setId(rs.getString("id"));
            result.setLv(rs.getString("lv"));

            return result;
         }

      } catch (Exception e) {
         e.printStackTrace();
      }
      
      return null;
   }
   public int register(AdminDTO dto) {
      try {

         String sql = "insert into tblAdmin (admin_seq, id, pw, name, ssn, tel, email, pic, lv) values (admin_seq.nextval, ?, ?, ?, ?, ?, ?, ?, ?)";

         pstat = conn.prepareStatement(sql);

         pstat.setString(1, dto.getId());
         pstat.setString(2, dto.getPw());
         pstat.setString(3, dto.getName());
         pstat.setString(4, dto.getSsn());
         pstat.setString(5, dto.getTel());
         pstat.setString(6, dto.getEmail());
         pstat.setString(7, dto.getPic());
         pstat.setString(8, dto.getLv());

         return pstat.executeUpdate();

      } catch (Exception e) {
         e.printStackTrace();
      }
      
      return 0;
   }
   
   public AdminDTO find_id(String name, String ssn) {

      try {
         
         String sql = "select * from tblAdmin where name = ? and ssn = ?";
         
         pstat = conn.prepareStatement(sql);
         pstat.setString(1, name);
         pstat.setString(2, ssn);
         
         
         rs = pstat.executeQuery();
         
         if (rs.next()) {
            
            AdminDTO dto = new AdminDTO();
            
            dto.setId(rs.getString("id"));
            dto.setName(rs.getString("name"));
            dto.setSsn(rs.getString("ssn"));
            
            return dto;
         }
         
         
      } catch (Exception e) {
         e.printStackTrace();
      }
      
      return null;
   }
   public AdminDTO find_pw(String id, String name, String ssn) {

      try {
         
         String sql = "select * from tblAdmin where id = ? and name = ? and ssn = ?";
         
         pstat = conn.prepareStatement(sql);
         pstat.setString(1, id);
         pstat.setString(2, name);
         pstat.setString(3, ssn);
         
         
         rs = pstat.executeQuery();
         
         if (rs.next()) {
            
            AdminDTO dto = new AdminDTO();
            
            dto.setId(rs.getString("id"));
            dto.setName(rs.getString("name"));
            dto.setSsn(rs.getString("ssn"));
            dto.setPw(rs.getString("pw"));
            
            return dto;
         }
         
         
      } catch (Exception e) {
         e.printStackTrace();
      }
      
      return null;
   }
   
      // 직원 급여 목록 조회
      public List<AdminDTO> getSalaryList(HashMap<String, String> map) {
            
    	  List<AdminDTO> adminSalaryList = new ArrayList<AdminDTO>();

    	  try {
               
               String where = "";
               
               if (map.get("search").equals("y")) {
                  where = String.format("where %s like '%%%s%%'"
                                       , map.get("column")
                                       , map.get("word"));
               }
               
//               String sql = String.format("select admin_seq, name, bank, bank_account, to_char(salary, 'FM9,999,999') || '원' as salary, tel, email from tblAdmin %s "
//                        , where);
               
		       String sql = String.format("select * from (select rownum as rnum, a.* from (select admin_seq, name, bank, bank_account, to_char(salary, 'FM9,999,999') || '원' as salary, tel, email from tblAdmin %s) a) where rnum between %s and %s"
		    		   	 , where
                         , map.get("begin")
                         , map.get("end")
                         );
               
               //String sql = "select admin_seq, name, bank, bank_account, to_char(salary, 'FM9,999,999') || '원' as salary, tel, email from tblAdmin";
               
               stat = conn.createStatement();
               rs = stat.executeQuery(sql);
               
               
               while (rs.next()) {
                  AdminDTO dto = new AdminDTO();
                  
                  dto.setAdmin_seq(rs.getString("admin_seq"));
                  dto.setName(rs.getString("name"));
                  dto.setBank(rs.getString("bank"));
                  dto.setBank_account(rs.getString("bank_account"));
                  dto.setSalary(rs.getString("salary"));
                  dto.setTel(rs.getString("tel"));
                  dto.setEmail(rs.getString("email"));
                  
                  adminSalaryList.add(dto);
                  
               }
               
               return adminSalaryList;
               
               
            } catch (Exception e) {
               e.printStackTrace();
            }
            
            
            return adminSalaryList;
         }
      
   public List<AdminDTO> alist(HashMap<String, String> map) {
      
      List<AdminDTO> list = new ArrayList<AdminDTO>();
      
      try {
         
         String where = "";
         
         //10 %% 5 = 
         if (map.get("search").equals("y")) {
            where = String.format("where %s like '%%%s%%'"
                              , map.get("column")
                              , map.get("word"));
         }

         String sql = String.format("select * from (select a.*, rownum as rnum from vwAdmin a %s order by admin_seq) where rnum between %s and %s"
                              , where
                              , map.get("begin")
                              , map.get("end")
                              );

         stat = conn.createStatement();
         rs = stat.executeQuery(sql);

         

         while (rs.next()) {

            AdminDTO dto = new AdminDTO();

            dto.setAdmin_seq(rs.getString("admin_seq"));
            dto.setName(rs.getString("name"));
            dto.setId(rs.getString("id"));
            dto.setPw(rs.getString("pw"));
            dto.setLv(rs.getString("lv"));

            list.add(dto);
         }

         return list;

      } catch (Exception e) {
         e.printStackTrace();
      }
      
      return list;
      
   }
   
   
	public int getTotalCount(HashMap<String, String> map) {
		
		try {
			
			String where = "";
			
			if (map.get("search").equals("y")) {
	            where = String.format("where %s like '%%%s%%'", map.get("column"), map.get("word") );
	        }


			String sql = String.format("select count(*) as cnt from tblAdmin %s", where);

			pstat = conn.prepareStatement(sql);

			rs = pstat.executeQuery();

			if (rs.next()) {

				return rs.getInt("cnt");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return 0;
	}
	
   
   public int del(String admin_seq) {
      try {

         String sql = "delete from tbladmin where admin_seq = ?";

         pstat = conn.prepareStatement(sql);

         pstat.setString(1, admin_seq);

         return pstat.executeUpdate();

      } catch (Exception e) {
         e.printStackTrace();
      }
      return 0;
   }
    
    
    
    // 직원 급여, 계좌번호 상세보기
    public AdminDTO detailBankAccount(String admin_seq) {
       
       try {
       
          String sql = "select admin_seq, name, bank, bank_account, tel, email from tblAdmin where admin_seq = ?";
          
          pstat = conn.prepareStatement(sql);
          
          pstat.setString(1, admin_seq);
          
          rs = pstat.executeQuery();
          
          if (rs.next()) {
             
             AdminDTO adminDto = new AdminDTO();
             
             adminDto.setAdmin_seq(rs.getString("admin_seq"));
             adminDto.setName(rs.getString("name"));
             adminDto.setBank(rs.getString("bank"));
             adminDto.setBank_account(rs.getString("bank_account"));
             adminDto.setTel(rs.getString("tel"));
             adminDto.setEmail(rs.getString("email"));
             
             return adminDto;
             
          }
          
       } catch (Exception e) {
          e.printStackTrace();
       }
       return null;
    }
    
    
    // 직원 급여 中 입금은행, 계좌번호 수정
    public int editBankAccount(AdminDTO adminDto) {
       
       try {
          
          String sql = "update tblAdmin set bank = ?, bank_account = ? where admin_seq = ?";
          
          pstat = conn.prepareStatement(sql);
          
          pstat.setString(1, adminDto.getBank());
          pstat.setString(2, adminDto.getBank_account());
          pstat.setString(3, adminDto.getAdmin_seq());
          
          return pstat.executeUpdate();
          
       } catch(Exception e) {
          e.printStackTrace();
       }
       
       return 0;
    }
    
    public AdminDTO get(String admin_seq) {
		
		try {

			String sql = "select *  from tblAdmin where admin_seq = ?";

			pstat = conn.prepareStatement(sql);

			pstat.setString(1, admin_seq);

			rs = pstat.executeQuery();

			if (rs.next()) {

				AdminDTO dto = new AdminDTO();

				dto.setAdmin_seq(rs.getString("admin_seq"));
				dto.setName(rs.getString("name"));
				dto.setSsn(rs.getString("ssn"));
				dto.setId(rs.getString("id"));
				dto.setTel(rs.getString("tel"));
				dto.setEmail(rs.getString("email"));
				dto.setLv(rs.getString("lv"));
				dto.setPic(rs.getString("pic"));

				return dto;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

         
}

