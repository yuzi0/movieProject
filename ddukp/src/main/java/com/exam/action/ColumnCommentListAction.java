package com.exam.action;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;

import com.exam.model.BoardDAO;
import com.exam.model.ColumnBoardDAO;
import com.exam.model.ColumnBoardTO;
import com.exam.model.ColumnCommentTO;
import com.exam.model.CommentDAO;
import com.exam.model.FreeCommentTO;
import com.exam.model.latterBoardTO;

public class ColumnCommentListAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		ColumnCommentTO ccTo=new ColumnCommentTO();
		ccTo.setCnum(request.getParameter("cnum"));
		
		CommentDAO cDAO=new CommentDAO();
		ArrayList<ColumnCommentTO> fcLists=new ArrayList<ColumnCommentTO>();
		HttpSession session = request.getSession();
		String currentUser=(String)session.getAttribute("sid");
		int anum=cDAO.adminCheck(currentUser);
		System.out.println("자유 댓글  유저: "+currentUser);
		fcLists=cDAO.columnCommentList(ccTo);
		
		ArrayList<HashMap> hLists=new ArrayList<HashMap>();
		JSONArray jsonArray=new JSONArray();

		for(int i=0;i<fcLists.size();i++) {
			HashMap<String, String> map=new HashMap<String, String>();
			map.put("ccdate", fcLists.get(i).getCcdate());
			map.put("comment", fcLists.get(i).getCccontent());
			map.put("number",  String.valueOf(fcLists.get(i).getNumber()));
			map.put("ccnum",   fcLists.get(i).getCcnum());
			map.put("uid", fcLists.get(i).getUid());
			jsonArray.add(map);
		}
		HashMap<String, String> anumnumer=new HashMap<String, String>();
		anumnumer.put("anum", String.valueOf(anum));
		jsonArray.add(anumnumer);
		
		request.setAttribute("columncommentlist", jsonArray.toString());
	}

}
