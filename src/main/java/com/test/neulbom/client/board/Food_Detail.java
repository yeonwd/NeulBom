package com.test.neulbom.client.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.neulbom.client.repository.Food_DetailDAO;
import com.test.neulbom.client.repository.Food_DetailDTO;

@WebServlet("/client/board/food_detail.do")
public class Food_Detail extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//Food.java
		String food_seq = req.getParameter("food_seq");
		Food_DetailDAO dao = new Food_DetailDAO();


		String foodSeq = req.getParameter("food_seq");
	    dao.increaseReadCount(foodSeq);
	    
	    Food_DetailDTO dto = dao.get(food_seq);
		
	    
	    
	    req.setAttribute("dto", dto);
	    
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/client/board/food_detail.jsp");
		dispatcher.forward(req, resp);
	}

}
