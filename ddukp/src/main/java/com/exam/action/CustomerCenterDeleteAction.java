package com.exam.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.exam.model.BoardDAO;
import com.exam.model.CusBoardDAO;
import com.exam.model.CusBoardTO;
import com.exam.model.latterBoardTO;

public class CustomerCenterDeleteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		CusBoardTO qTO=new CusBoardTO();
		qTO.setQnum(Integer.parseInt(request.getParameter("qnum")));
		CusBoardDAO dao=new CusBoardDAO();
		int flag=dao.customerCenterDelete(qTO);
		
		request.setAttribute("flag", flag);
	}

}
