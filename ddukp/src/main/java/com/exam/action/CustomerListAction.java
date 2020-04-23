package com.exam.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.exam.model.BoardDAO;
import com.exam.model.BoardListTO;
import com.exam.model.ColumnBoardDAO;
import com.exam.model.CusBoardDAO;

public class CustomerListAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		int cpage=1;
		if (request.getParameter("cpage") != null && !request.getParameter("cpage").equals("")) {
			cpage = Integer.parseInt(request.getParameter("cpage"));
		}
		
		BoardListTO lLTO=new BoardListTO();
		lLTO.setCpage(cpage);
		
		
		CusBoardDAO dao=new CusBoardDAO();
		lLTO=dao.customerList(lLTO);
		
		request.setAttribute("lLTO", lLTO);
	}

}
