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
import com.exam.model.CommentDAO;
import com.exam.model.FreeCommentTO;
import com.exam.model.latterBoardTO;

public class FreeCommentListAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		FreeCommentTO fcTo=new FreeCommentTO();
		fcTo.setFnum(request.getParameter("fnum"));
		
		CommentDAO cDAO=new CommentDAO();
		ArrayList<FreeCommentTO> fcLists=new ArrayList<FreeCommentTO>();
		HttpSession session = request.getSession();
		String currentUser=(String)session.getAttribute("sid");
		int anum=cDAO.adminCheck(currentUser);
		System.out.println("자유 댓글  유저: "+currentUser);
		fcLists=cDAO.freeCommentList(fcTo);
		
		ArrayList<HashMap> hLists=new ArrayList<HashMap>();
		JSONArray jsonArray=new JSONArray();
		
		for(int i=0;i<fcLists.size();i++) {
			HashMap<String, String> map=new HashMap<String, String>();
			map.put("fcdate", fcLists.get(i).getFcdate());
			map.put("comment", fcLists.get(i).getFccontent());
			map.put("number",  String.valueOf(fcLists.get(i).getNumber()));
			map.put("fcnum",   fcLists.get(i).getFcnum());
			map.put("uid", fcLists.get(i).getUid());
			jsonArray.add(map);
		}
		HashMap<String, String> anumnumer=new HashMap<String, String>();
		anumnumer.put("anum", String.valueOf(anum));
		jsonArray.add(anumnumer);
		
		request.setAttribute("freecommentlist", jsonArray.toString());
	}

}
