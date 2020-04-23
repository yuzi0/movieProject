package com.exam.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.exam.model.BoardDAO;
import com.exam.model.ColumnBoardDAO;
import com.exam.model.ColumnBoardTO;
import com.exam.model.CommentDAO;
import com.exam.model.FreeCommentTO;
import com.exam.model.InfoCommentTO;
import com.exam.model.latterBoardTO;

public class InfoCommentDeleteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		InfoCommentTO icTo=new InfoCommentTO();
		HttpSession session = request.getSession();
		icTo.setUid((String)session.getAttribute("sid"));
		icTo.setIcnum(request.getParameter("icnum"));

		CommentDAO dao=new CommentDAO();
		int flag=dao.infoCommentDelete(icTo);
		
		request.setAttribute("flag", flag);
	}

}
