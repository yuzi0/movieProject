package com.exam.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.exam.model.BoardDAO;
import com.exam.model.BoardListTO;

public class LatterListAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		int cpage=1;
		if (request.getParameter("cpage") != null && !request.getParameter("cpage").equals("")) {
			cpage = Integer.parseInt(request.getParameter("cpage"));
		}
		
		BoardListTO lLTO=new BoardListTO();
		System.out.println("cpge="+cpage);
		lLTO.setCpage(cpage);
		String opt = request.getParameter("opt");	
		String searchText = request.getParameter("searchText");
		lLTO.setSearchKey(opt);
		lLTO.setSearchWord(searchText);
		
		BoardDAO dao=new BoardDAO();
		lLTO=dao.latterList(lLTO);

		request.setAttribute("lLTO", lLTO);
	}

}
