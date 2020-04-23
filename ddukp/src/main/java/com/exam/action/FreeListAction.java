package com.exam.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.exam.model.BoardListTO;
import com.exam.model.FreeBoardDAO;

public class FreeListAction implements Action {

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
		FreeBoardDAO dao=new FreeBoardDAO();
		lLTO=dao.freeList(lLTO);

		request.setAttribute("fLTO", lLTO);
	}

}
