package com.exam.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.exam.model.BoardDAO;
import com.exam.model.InfoBoardDAO;
import com.exam.model.InfoBoardTO;
import com.exam.model.latterBoardTO;

public class InfoDeleteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		InfoBoardTO iTO=new InfoBoardTO();
		iTO.setInum(Integer.parseInt(request.getParameter("inum")));
		InfoBoardDAO dao=new InfoBoardDAO();
		int flag=dao.infoDelete(iTO);
		
		request.setAttribute("flag", flag);
	}

}
