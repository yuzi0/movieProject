package com.exam.action;

import java.util.Arrays;

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

public class MemberWriteDeleteOkAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
		System.out.println("MemberWriteDeleteOkAction 호출");
		
		String[] reviewkArr = request.getParameterValues("review[]");
		String[] freeArr = request.getParameterValues("free[]");
		String[] columnArr = request.getParameterValues("column[]");
		String[] qnaArr = request.getParameterValues("qna[]");
		System.out.println(Arrays.toString(reviewkArr));
		System.out.println(Arrays.toString(freeArr));
		
		int flag = 2;
		if(reviewkArr != null) {
			for (String i : reviewkArr) {
				System.out.println("액션 i : " + i);
				latterBoardTO lTO = new latterBoardTO();
				lTO.setRnum(Integer.parseInt(i));
				BoardDAO dao = new BoardDAO();
				flag = dao.latterDelete(lTO);
				System.out.println("액션 flag : " + flag);
				System.out.println("성공");
			}	
		}
		if(freeArr != null) {
			for (String i : freeArr) {
				System.out.println("액션 i : " + i);
				FreeBoardTO fTO = new FreeBoardTO();
				fTO.setFnum(Integer.parseInt(i));
				FreeBoardDAO dao = new FreeBoardDAO();
				flag = dao.freerDelete(fTO);
				System.out.println("액션 flag : " + flag);
				System.out.println("성공");
			}	
		}
		if(columnArr != null) {
			for (String i : columnArr) {
				System.out.println("액션 i : " + i);
				ColumnBoardTO cTO = new ColumnBoardTO();
				cTO.setCnum(Integer.parseInt(i));
				ColumnBoardDAO dao = new ColumnBoardDAO();
				flag = dao.columnDelete(cTO);
				System.out.println("액션 flag : " + flag);
				System.out.println("성공");
			}	
		}
		if(qnaArr != null) {
			for (String i : qnaArr) {
				System.out.println("액션 i : " + i);
				CusBoardTO  qTO = new CusBoardTO();
				qTO.setQnum(Integer.parseInt(i));
				CusBoardDAO dao = new CusBoardDAO();
				flag = dao.customerCenterDelete(qTO);
				System.out.println("액션 flag : " + flag);
				System.out.println("성공");
			}	
		}
		
		request.setAttribute("flag", flag);
		System.out.println("액션1 flag : " + flag);
	}

}
