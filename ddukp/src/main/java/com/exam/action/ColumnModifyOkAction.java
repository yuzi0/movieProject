package com.exam.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.exam.model.BoardDAO;
import com.exam.model.ColumnBoardDAO;
import com.exam.model.ColumnBoardTO;
import com.exam.model.latterBoardTO;

public class ColumnModifyOkAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		ColumnBoardTO cTO=new ColumnBoardTO();

		cTO.setCsubject(request.getParameter("csubject"));
		cTO.setCcontent(request.getParameter("ceditor"));
		cTO.setMsubject(request.getParameter("msubject"));
		cTO.setCip(request.getRemoteAddr());
		cTO.setCnum(Integer.parseInt(request.getParameter("cnum")));
		
		ColumnBoardDAO dao=new ColumnBoardDAO();
		int flag=dao.coulmnModifyOk(cTO);
		String cpage=request.getParameter("cpage");
		int cnum=Integer.parseInt(request.getParameter("cnum"));
		request.setAttribute("flag", flag);
		request.setAttribute("cpage", cpage);
		request.setAttribute("cnum", cnum);
	}

}
