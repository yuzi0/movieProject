package com.exam.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.exam.model.BoardDAO;
import com.exam.model.ColumnBoardDAO;
import com.exam.model.ColumnBoardTO;
import com.exam.model.latterBoardTO;

public class ColumnModifyAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
	
		ColumnBoardTO cTO=new ColumnBoardTO();
		cTO.setCnum(Integer.parseInt(request.getParameter("cnum")) );
		ColumnBoardDAO dao=new ColumnBoardDAO();
		cTO=dao.columnModify(cTO);
		
		request.setAttribute("cTO", cTO);
	}

}
