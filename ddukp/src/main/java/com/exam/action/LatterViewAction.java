package com.exam.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.exam.model.BoardDAO;
import com.exam.model.latterBoardTO;

public class LatterViewAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		latterBoardTO lTO=new latterBoardTO();
		lTO.setRnum(Integer.parseInt(request.getParameter("rnum")));
		HttpSession session = request.getSession();
		String currentUser=(String)session.getAttribute("sid");
		BoardDAO dao=new BoardDAO();
		lTO=dao.latterView(lTO,currentUser);
		
		request.setAttribute("lTO", lTO);
	}

}
