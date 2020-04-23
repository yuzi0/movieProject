package com.exam.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.exam.model.BoardDAO;
import com.exam.model.ColumnBoardDAO;
import com.exam.model.ColumnBoardTO;
import com.exam.model.CommentDAO;
import com.exam.model.FreeCommentTO;
import com.exam.model.latterBoardTO;

public class FreeCommentModifyAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		FreeCommentTO fcTo=new FreeCommentTO();
		HttpSession session = request.getSession();
		fcTo.setUid((String)session.getAttribute("sid"));
		fcTo.setFcnum(request.getParameter("fcnum"));

		CommentDAO dao=new CommentDAO();
		int flag=dao.freeCommentModify(fcTo);
		
		request.setAttribute("flag", flag);
	}

}
