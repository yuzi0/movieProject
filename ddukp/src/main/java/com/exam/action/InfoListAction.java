package com.exam.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.exam.model.BoardListTO;
import com.exam.model.InfoBoardDAO;

public class InfoListAction implements Action {

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
		
		
		InfoBoardDAO dao=new InfoBoardDAO();
		lLTO=dao.latterList(lLTO);
		String opt = request.getParameter("opt");	
		String searchText = request.getParameter("searchText");
		lLTO.setSearchKey(opt);
		lLTO.setSearchWord(searchText);
		request.setAttribute("lLTO", lLTO);
	
	}

}
