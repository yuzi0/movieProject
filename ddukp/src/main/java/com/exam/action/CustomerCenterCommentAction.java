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
import com.exam.model.latterBoardTO;

public class CustomerCenterCommentAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		CusCommentTO qcTO=new CusCommentTO();
		qcTO.setQccontent(request.getParameter("comment"));
		qcTO.setQcip(request.getRemoteAddr());
		qcTO.setQnum(request.getParameter("qnum"));
		HttpSession session = request.getSession();
		qcTO.setUid((String)session.getAttribute("sid"));
		
		CommentDAO dao=new CommentDAO();
		//int flag=lDAO.latterWrite(lTO);
		int flag=dao.customerCommentOk(qcTO);
		request.setAttribute("flag", flag);
		request.setAttribute("qnum", request.getParameter("qnum"));
	}

}
