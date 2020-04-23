package com.exam.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.exam.model.BoardDAO;
import com.exam.model.ColumnBoardDAO;
import com.exam.model.ColumnBoardTO;
import com.exam.model.CusBoardDAO;
import com.exam.model.CusBoardTO;
import com.exam.model.FreeBoardDAO;
import com.exam.model.FreeBoardTO;
import com.exam.model.latterBoardTO;

public class MemberWriteViewAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String board = (String) request.getParameter("board");
		System.out.println("board : " + board);
		System.out.println("num : " + request.getParameter("num"));
		request.setAttribute("board", board);
		
		if(board.equals("review")) {
			System.out.println("후기!");
			latterBoardTO lTO = new latterBoardTO();
			lTO.setRnum(Integer.parseInt(request.getParameter("num")));
			BoardDAO dao=new BoardDAO();
			lTO = dao.latterView(lTO,"");
			
			request.setAttribute("lTO", lTO);
		} else if(board.equals("free")) {
			System.out.println("자유!");
			FreeBoardTO fTO = new FreeBoardTO();
			fTO.setFnum(Integer.parseInt(request.getParameter("num")));
			FreeBoardDAO dao=new FreeBoardDAO();
			fTO = dao.freeView(fTO,"");
			
			request.setAttribute("fTO", fTO);
		} else if(board.equals("column")) {
			System.out.println("컬럼!");
			ColumnBoardTO cTO = new ColumnBoardTO();
			cTO.setCnum(Integer.parseInt(request.getParameter("num")));
			ColumnBoardDAO dao=new ColumnBoardDAO();
			cTO = dao.columnView(cTO,"");
			
			request.setAttribute("cTO", cTO);
		} else if(board.equals("qna")) {
			System.out.println("고객센터!");
			CusBoardTO qTO = new CusBoardTO();
			qTO.setQnum(Integer.parseInt(request.getParameter("num")));
			CusBoardDAO dao=new CusBoardDAO();
			qTO = dao.customerCenterView(qTO,"");
			
			request.setAttribute("qTO", qTO);
		}
	}

}
