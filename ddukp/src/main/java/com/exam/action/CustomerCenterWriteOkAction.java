package com.exam.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.exam.model.BoardDAO;
import com.exam.model.CusBoardDAO;
import com.exam.model.CusBoardTO;
import com.exam.model.latterBoardTO;

public class CustomerCenterWriteOkAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		CusBoardTO qTO=new CusBoardTO();

		qTO.setQsubject(request.getParameter("qsubject"));
		qTO.setQcontent(request.getParameter("qeditor"));
		qTO.setQip(request.getRemoteAddr());
		HttpSession session = request.getSession();
		qTO.setUid((String)session.getAttribute("sid"));
		qTO.setQctgname(request.getParameter("qctgname"));
		
		CusBoardDAO dao=new CusBoardDAO();
		//int flag=lDAO.latterWrite(lTO);
		int qnum=dao.customerCenterWrite(qTO);
		request.setAttribute("qnum", qnum);
	}

}
