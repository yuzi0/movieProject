package com.exam.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.exam.model.BoardDAO;
import com.exam.model.FreeBoardDAO;
import com.exam.model.FreeBoardTO;

public class FreeWriteOkAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		FreeBoardTO fTO=new FreeBoardTO();
		
		fTO.setFsubject(request.getParameter("fsubject"));
		fTO.setFcontent(request.getParameter("feditor"));
		fTO.setFsubject(request.getParameter("fsubject"));
		fTO.setFip(request.getRemoteAddr());
		HttpSession session = request.getSession();
		fTO.setUid((String)session.getAttribute("sid"));
		fTO.setFctgname(request.getParameter("fctgname"));
		
		FreeBoardDAO dao=new FreeBoardDAO();
		int fnum=dao.freeWriteOk(fTO);
		
		request.setAttribute("fnum", fnum);
		
	}

}
