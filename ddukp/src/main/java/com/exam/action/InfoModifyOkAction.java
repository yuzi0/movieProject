package com.exam.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.exam.model.BoardDAO;
import com.exam.model.FreeBoardDAO;
import com.exam.model.FreeBoardTO;
import com.exam.model.InfoBoardDAO;
import com.exam.model.InfoBoardTO;
import com.exam.model.latterBoardTO;

public class InfoModifyOkAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		InfoBoardTO iTO=new InfoBoardTO();

		iTO.setIsubject(request.getParameter("isubject"));
		iTO.setIcontent(request.getParameter("ieditor"));
		iTO.setIip(request.getRemoteAddr());
		iTO.setIctgname(request.getParameter("ictgname"));
		iTO.setInum(Integer.parseInt(request.getParameter("inum")));
		
		InfoBoardDAO dao=new InfoBoardDAO();
		int flag=dao.infoModifyOk(iTO);
		String cpage=request.getParameter("cpage");

		int inum=Integer.parseInt(request.getParameter("inum"));
		request.setAttribute("flag", flag);
		request.setAttribute("cpage", cpage);
		request.setAttribute("inum", inum);
	}

}
