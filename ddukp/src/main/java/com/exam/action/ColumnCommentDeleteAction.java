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

public class ColumnCommentDeleteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		ColumnCommentTO ccTo=new ColumnCommentTO();
		HttpSession session = request.getSession();
		ccTo.setUid((String)session.getAttribute("sid"));
		ccTo.setCcnum(request.getParameter("ccnum"));

		CommentDAO dao=new CommentDAO();
		int flag=dao.columnCommentDelete(ccTo);
		
		request.setAttribute("flag", flag);
	}

}
