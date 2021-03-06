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

public class InfoCommentAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		InfoCommentTO icTO=new InfoCommentTO();
		icTO.setIccontent(request.getParameter("comment"));
		icTO.setIcip(request.getRemoteAddr());
		icTO.setInum(request.getParameter("inum"));
		HttpSession session = request.getSession();
		icTO.setUid((String)session.getAttribute("sid"));
		
		CommentDAO dao=new CommentDAO();
		//int flag=lDAO.latterWrite(lTO);
		int flag=dao.infoCommentOk(icTO);
		request.setAttribute("flag", flag);
		request.setAttribute("inum", request.getParameter("inum"));
	}

}
