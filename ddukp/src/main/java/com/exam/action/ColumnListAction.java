package com.exam.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.exam.model.BoardDAO;
import com.exam.model.BoardListTO;
import com.exam.model.ColumnBoardDAO;

public class ColumnListAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		int cpage=1;
		if (request.getParameter("cpage") != null && !request.getParameter("cpage").equals("")) {
			cpage = Integer.parseInt(request.getParameter("cpage"));
		}
		
		BoardListTO lLTO=new BoardListTO();
		lLTO.setCpage(cpage);
		String opt = request.getParameter("opt");	
		String searchText = request.getParameter("searchText");
		lLTO.setSearchKey(opt);
		lLTO.setSearchWord(searchText);
		
		System.out.println("액션 opt : " + opt);
		System.out.println("액션 searchText : " + searchText);
		
		ColumnBoardDAO dao=new ColumnBoardDAO();
		lLTO=dao.columnList(lLTO);


		request.setAttribute("lLTO", lLTO);
	}

}
