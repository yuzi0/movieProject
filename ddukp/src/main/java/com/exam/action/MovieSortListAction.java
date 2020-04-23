package com.exam.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.exam.model.MovieDAO;
import com.exam.model.MovieSortListTO;

public class MovieSortListAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("MovieSortListAction");
		// TODO Auto-generated method stub
		int cpage=1;
		if (request.getParameter("cpage") != null && !request.getParameter("cpage").equals("")) {
			cpage = Integer.parseInt(request.getParameter("cpage"));
		}
		System.out.println("카테고리 : "+request.getParameter("movieCategory"));
		
		MovieSortListTO msLTO=new MovieSortListTO();
		msLTO.setCpage(cpage);
		
		MovieDAO dao=new MovieDAO();
		msLTO=dao.movieSortList(msLTO,request.getParameter("movieCategory"));
		request.setAttribute("msLTO", msLTO);
		
		if(request.getParameter("movieCategory") == null || request.getParameter("movieCategory").equals("")) {
			request.setAttribute("movieCategory", "all");
		} else {
			request.setAttribute("movieCategory", request.getParameter("movieCategory"));
		}
	}

}
