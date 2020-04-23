package com.exam.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.exam.model.BoardDAO;
import com.exam.model.CusBoardDAO;
import com.exam.model.CusBoardTO;
import com.exam.model.latterBoardTO;

public class CustomerCenterViewAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		CusBoardTO qTO=new CusBoardTO();
		qTO.setQnum(Integer.parseInt(request.getParameter("qnum")));
		HttpSession session = request.getSession();
		String currentUser=(String)session.getAttribute("sid");
		CusBoardDAO dao=new CusBoardDAO();
		qTO=dao.customerCenterView(qTO,currentUser);
		
		request.setAttribute("qTO", qTO);
	}

}
