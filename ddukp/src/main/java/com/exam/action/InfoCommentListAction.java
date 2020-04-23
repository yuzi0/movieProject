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
import com.exam.model.InfoCommentTO;
import com.exam.model.latterBoardTO;

public class InfoCommentListAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		InfoCommentTO icTo=new InfoCommentTO();
		icTo.setInum(request.getParameter("inum"));
		
		CommentDAO cDAO=new CommentDAO();
		ArrayList<InfoCommentTO> fcLists=new ArrayList<InfoCommentTO>();
		HttpSession session = request.getSession();
		String currentUser=(String)session.getAttribute("sid");
		int anum=cDAO.adminCheck(currentUser);
		System.out.println("자유 댓글  유저: "+currentUser);
		fcLists=cDAO.infoCommentList(icTo);
		
		ArrayList<HashMap> hLists=new ArrayList<HashMap>();
		JSONArray jsonArray=new JSONArray();
		
		for(int i=0;i<fcLists.size();i++) {
			HashMap<String, String> map=new HashMap<String, String>();
			map.put("icdate", fcLists.get(i).getIcdate());
			map.put("comment", fcLists.get(i).getIccontent());
			map.put("number",  String.valueOf(fcLists.get(i).getNumber()));
			map.put("icnum",   fcLists.get(i).getIcnum());
			map.put("uid", fcLists.get(i).getUid());
			jsonArray.add(map);
		}
		HashMap<String, String> anumnumer=new HashMap<String, String>();
		anumnumer.put("anum", String.valueOf(anum));
		jsonArray.add(anumnumer);
		
		request.setAttribute("infocommentlist", jsonArray.toString());
	}

}
