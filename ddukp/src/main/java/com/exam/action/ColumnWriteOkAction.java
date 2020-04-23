package com.exam.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.exam.model.BoardDAO;
import com.exam.model.ColumnBoardDAO;
import com.exam.model.ColumnBoardTO;
import com.exam.model.latterBoardTO;

public class ColumnWriteOkAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		ColumnBoardTO cTO=new ColumnBoardTO();

		cTO.setCsubject(request.getParameter("csubject"));
		cTO.setCcontent(request.getParameter("ceditor"));
		cTO.setMsubject(request.getParameter("msubject"));
		cTO.setCip(request.getRemoteAddr());
		HttpSession session = request.getSession();
		cTO.setUid((String)session.getAttribute("sid"));
		
		ColumnBoardDAO dao=new ColumnBoardDAO();
		//int flag=lDAO.latterWrite(lTO);
		int cnum=dao.columnWrite(cTO);
		request.setAttribute("cnum", cnum);
	}

}
