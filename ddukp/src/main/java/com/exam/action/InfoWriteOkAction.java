package com.exam.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.exam.model.BoardDAO;
import com.exam.model.InfoBoardDAO;
import com.exam.model.InfoBoardTO;
import com.exam.model.latterBoardTO;

public class InfoWriteOkAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		InfoBoardTO iTO=new InfoBoardTO();

		iTO.setIsubject(request.getParameter("isubject"));
		System.out.println(request.getParameter("ieditor"));
		//lTO.setRcontent(request.getParameter("leditor").replaceAll("<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>", ""));
		iTO.setIcontent(request.getParameter("ieditor"));
		iTO.setIip(request.getRemoteAddr());
		HttpSession session = request.getSession();
		iTO.setAid((String)session.getAttribute("sid"));
		iTO.setIctgname(request.getParameter("ictgname"));
		
		InfoBoardDAO dao=new InfoBoardDAO();
		//int flag=lDAO.latterWrite(lTO);
		int inum=dao.infoWrite(iTO);
		request.setAttribute("inum", inum);
	}

}
