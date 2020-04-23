package com.exam.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.exam.model.BoardDAO;
import com.exam.model.ColumnBoardDAO;
import com.exam.model.ColumnBoardTO;
import com.exam.model.CommentDAO;
import com.exam.model.FreeCommentTO;
import com.exam.model.LatterCommentTO;
import com.exam.model.latterBoardTO;

public class LatterCommentDeleteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		LatterCommentTO lcTo=new LatterCommentTO();
		HttpSession session = request.getSession();
		lcTo.setUid((String)session.getAttribute("sid"));
		lcTo.setRcnum(request.getParameter("rcnum"));

		CommentDAO dao=new CommentDAO();
		int flag=dao.latterCommentDelete(lcTo);
		
		request.setAttribute("flag", flag);
	}

}
