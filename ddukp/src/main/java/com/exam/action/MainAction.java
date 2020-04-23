package com.exam.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.exam.model.BoardDAO;
import com.exam.model.BoardListTO;
import com.exam.model.ColumnBoardDAO;
import com.exam.model.MovieDAO;
import com.exam.model.MovieSortListTO;

public class MainAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub

		// 조회수 상위 영화 8개 출력
		MovieSortListTO msLTO = new MovieSortListTO();
		MovieDAO daoM = new MovieDAO();
		msLTO = daoM.mainMovie();
		request.setAttribute("msLTO", msLTO);

		// 조회수 상위 컬럼 3개 출력
		BoardListTO lLTO = new BoardListTO();
		ColumnBoardDAO daoC = new ColumnBoardDAO();
		lLTO = daoC.mainColumn();
		request.setAttribute("lLTO", lLTO);
	}

}
