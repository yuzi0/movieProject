package com.exam.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.exam.model.BoardDAO;
import com.exam.model.FreeBoardDAO;
import com.exam.model.FreeBoardTO;
import com.exam.model.latterBoardTO;

public class FreeModifyAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
		FreeBoardTO fTO=new FreeBoardTO();
		fTO.setFnum(Integer.parseInt(request.getParameter("fnum")) );
		FreeBoardDAO dao=new FreeBoardDAO();
		fTO=dao.freeModify(fTO);
		
		request.setAttribute("fTO", fTO);
	}

}
