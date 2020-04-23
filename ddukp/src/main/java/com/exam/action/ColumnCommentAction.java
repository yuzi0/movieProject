package com.exam.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.exam.model.BoardDAO;
import com.exam.model.ColumnBoardDAO;
import com.exam.model.ColumnBoardTO;
import com.exam.model.ColumnCommentTO;
import com.exam.model.CommentDAO;
import com.exam.model.FreeCommentTO;
import com.exam.model.latterBoardTO;

public class ColumnCommentAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		ColumnCommentTO ccTO=new ColumnCommentTO();
		ccTO.setCccontent(request.getParameter("comment"));
		ccTO.setCcip(request.getRemoteAddr());
		ccTO.setCnum(request.getParameter("cnum"));
		HttpSession session = request.getSession();
		ccTO.setUid((String)session.getAttribute("sid"));
		
		CommentDAO dao=new CommentDAO();
		//int flag=lDAO.latterWrite(lTO);
		int flag=dao.columnCommentOk(ccTO);
		request.setAttribute("flag", flag);
		request.setAttribute("cnum", request.getParameter("cnum"));
	}

}
