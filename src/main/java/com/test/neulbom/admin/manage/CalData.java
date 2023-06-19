package com.test.neulbom.admin.manage;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.test.neulbom.admin.manage.repository.ManageDAO;
import com.test.neulbom.admin.manage.repository.MeetDTO;

@WebServlet("/admin/manage/caldata.do")
public class CalData extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		ManageDAO dao = new ManageDAO();

		List<MeetDTO> list = dao.confirmedMeet();
//	        List<Map<String, Object>> events = new ArrayList<>();

		JSONArray jarray = new JSONArray();
		for (MeetDTO dto : list) {
			JSONObject obj = new JSONObject();
			obj.put("title", dto.getRname() + "(보호자: " + dto.getPname() + ")");
			obj.put("start", "\'" + dto.getMeet_date() + "T" + dto.getMeet_time() + "\'");

			jarray.add(obj);
		}

		//
//	        for (MeetDTO dto : mlist) {
//	            Map<String, Object> event = new HashMap<>();
//	            event.put("title", dto.getRname() + "(보호자: " + dto.getPname() + ")");
//	            event.put("start", "\'" + dto.getMeet_date() + "T" + dto.getMeet_time() + "\'");
//	            list.put(event);
//	        }
		//
//	        req.setAttribute("events", events);
		
		resp.setCharacterEncoding("UTF-8");
		
		resp.setContentType("application/json");
		
		
		PrintWriter writer = resp.getWriter();
		writer.print(jarray);
		writer.close();
	}

}
