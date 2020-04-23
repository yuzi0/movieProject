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

public class LatterCommentAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		LatterCommentTO lcTO=new LatterCommentTO();
		lcTO.setRccontent(request.getParameter("comment"));
		lcTO.setRcip(request.getRemoteAddr());
		lcTO.setRnum(request.getParameter("rnum"));
		HttpSession session = request.getSession();
		lcTO.setUid((String)session.getAttribute("sid"));
		
		CommentDAO dao=new CommentDAO();
		//int flag=lDAO.latterWrite(lTO);
		int flag=dao.latterCommentOk(lcTO);
		request.setAttribute("flag", flag);
		request.setAttribute("rnum", request.getParameter("rnum"));
	}

}
