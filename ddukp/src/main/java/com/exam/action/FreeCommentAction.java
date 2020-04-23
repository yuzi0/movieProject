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

public class FreeCommentAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		FreeCommentTO fcTO=new FreeCommentTO();
		fcTO.setFccontent(request.getParameter("comment"));
		fcTO.setFcip(request.getRemoteAddr());
	fcTO.setFnum(request.getParameter("fnum"));
		HttpSession session = request.getSession();
		fcTO.setUid((String)session.getAttribute("sid"));
		
		CommentDAO dao=new CommentDAO();
		//int flag=lDAO.latterWrite(lTO);
		int flag=dao.freeCommentOk(fcTO);
		request.setAttribute("flag", flag);
		request.setAttribute("fnum", request.getParameter("fnum"));
	}

}
