package com.exam.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.exam.model.BoardDAO;
import com.exam.model.CusBoardDAO;
import com.exam.model.CusBoardTO;
import com.exam.model.latterBoardTO;

public class CustomerCenterModifyOkAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		CusBoardTO qTO=new CusBoardTO();

		qTO.setQsubject(request.getParameter("qsubject"));
		qTO.setQcontent(request.getParameter("qeditor"));
		qTO.setQip(request.getRemoteAddr());
		qTO.setQctgname(request.getParameter("qctgname"));
		qTO.setQnum(Integer.parseInt(request.getParameter("qnum")));
		
		CusBoardDAO dao=new CusBoardDAO();
		int flag=dao.customerCenterModifyOk(qTO);
		String cpage=request.getParameter("cpage");
		int qnum=Integer.parseInt(request.getParameter("qnum"));
		request.setAttribute("flag", flag);
		request.setAttribute("cpage", cpage);
		request.setAttribute("qnum", qnum);
	}

}
