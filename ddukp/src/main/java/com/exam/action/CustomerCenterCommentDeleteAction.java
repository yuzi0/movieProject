package com.exam.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.exam.model.BoardDAO;
import com.exam.model.ColumnBoardDAO;
import com.exam.model.ColumnBoardTO;
import com.exam.model.CommentDAO;
import com.exam.model.CusCommentTO;
import com.exam.model.FreeCommentTO;
import com.exam.model.LatterCommentTO;
import com.exam.model.latterBoardTO;

public class CustomerCenterCommentDeleteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		CusCommentTO qcTo=new CusCommentTO();
		HttpSession session = request.getSession();
		qcTo.setUid((String)session.getAttribute("sid"));
		qcTo.setQcnum(request.getParameter("qcnum"));

		CommentDAO dao=new CommentDAO();
		int flag=dao.customerCommentDelete(qcTo);
		
		request.setAttribute("flag", flag);
	}

}
