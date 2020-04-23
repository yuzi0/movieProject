package com.exam.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.exam.model.FreeBoardDAO;
import com.exam.model.FreeBoardTO;

public class FreeViewAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		FreeBoardTO fTO=new FreeBoardTO();
		fTO.setFnum(Integer.parseInt(request.getParameter("fnum")));
		HttpSession session = request.getSession();
		String currentUser=(String)session.getAttribute("sid");
		FreeBoardDAO dao=new FreeBoardDAO();
		fTO=dao.freeView(fTO,currentUser);
		
		request.setAttribute("fTO", fTO);
	}

}
