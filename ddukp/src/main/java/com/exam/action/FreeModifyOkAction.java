package com.exam.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.exam.model.BoardDAO;
import com.exam.model.FreeBoardDAO;
import com.exam.model.FreeBoardTO;
import com.exam.model.latterBoardTO;

public class FreeModifyOkAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		FreeBoardTO fTO=new FreeBoardTO();

		fTO.setFsubject(request.getParameter("fsubject"));
		fTO.setFcontent(request.getParameter("feditor"));
		fTO.setFip(request.getRemoteAddr());
		fTO.setFctgname(request.getParameter("fctgname"));
		fTO.setFnum(Integer.parseInt(request.getParameter("fnum")));
		
		FreeBoardDAO dao=new FreeBoardDAO();
		int flag=dao.freeModifyOk(fTO);
		String cpage=request.getParameter("cpage");

		int fnum=Integer.parseInt(request.getParameter("fnum"));
		request.setAttribute("flag", flag);
		request.setAttribute("cpage", cpage);
		request.setAttribute("fnum", fnum);
	}

}
