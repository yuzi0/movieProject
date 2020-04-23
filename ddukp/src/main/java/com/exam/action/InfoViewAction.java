package com.exam.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.exam.model.BoardDAO;
import com.exam.model.InfoBoardDAO;
import com.exam.model.InfoBoardTO;
import com.exam.model.latterBoardTO;

public class InfoViewAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		InfoBoardTO iTO=new InfoBoardTO();
		System.out.println("viewnum : "  +request.getParameter("inum"));
		iTO.setInum(Integer.parseInt(request.getParameter("inum")));
		HttpSession session = request.getSession();
		String currentUser=(String)session.getAttribute("sid");
		InfoBoardDAO dao=new InfoBoardDAO();
		iTO=dao.infoView(iTO,currentUser);
		
		request.setAttribute("iTO", iTO);
	}

}
