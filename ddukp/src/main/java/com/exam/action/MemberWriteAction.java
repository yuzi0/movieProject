package com.exam.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.exam.model.BoardDAO;
import com.exam.model.BoardListTO;

public class MemberWriteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		int cpage=1;
		if (request.getParameter("cpage") != null && !request.getParameter("cpage").equals("")) {
			cpage = Integer.parseInt(request.getParameter("cpage"));
		}
		
		/* 검색을 위한 변수 설정 */
		String opt = request.getParameter("opt");
		String searchText = request.getParameter("searchText");
		System.out.println("opt : " + opt + ", text : " + searchText);
		
		BoardListTO mLTO=new BoardListTO();
		System.out.println("cpge="+cpage);
		mLTO.setCpage(cpage);

		HttpSession session = request.getSession();
		String sid = (String) session.getAttribute("sid");
		System.out.println("sid : " + sid);
		
		BoardDAO dao=new BoardDAO();
		mLTO=dao.memberWriteList(sid, mLTO, opt, searchText);
				
		request.setAttribute("mLTO", mLTO);
		request.setAttribute("searchKey", opt);
		request.setAttribute("searchWord", searchText);
	}

}
