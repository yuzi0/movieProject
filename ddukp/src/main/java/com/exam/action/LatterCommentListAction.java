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
import com.exam.model.LatterCommentTO;
import com.exam.model.latterBoardTO;

public class LatterCommentListAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		LatterCommentTO lcTo=new LatterCommentTO();
		lcTo.setRnum(request.getParameter("rnum"));
	

		
		CommentDAO cDAO=new CommentDAO();
		ArrayList<LatterCommentTO> fcLists=new ArrayList<LatterCommentTO>();
		HttpSession session = request.getSession();
		String currentUser=(String)session.getAttribute("sid");
		int anum=cDAO.adminCheck(currentUser);
		System.out.println("자유 댓글  유저: "+currentUser);
		fcLists=cDAO.latterCommentList(lcTo);
		
		ArrayList<HashMap> hLists=new ArrayList<HashMap>();
		JSONArray jsonArray=new JSONArray();
		
		for(int i=0;i<fcLists.size();i++) {
			HashMap<String, String> map=new HashMap<String, String>();
			map.put("rcdate", fcLists.get(i).getRcdate());
			map.put("comment", fcLists.get(i).getRccontent());
			map.put("number",  String.valueOf(fcLists.get(i).getNumber()));
			map.put("rcnum",   fcLists.get(i).getRcnum());
			map.put("uid", fcLists.get(i).getUid());
			jsonArray.add(map);
		}
		HashMap<String, String> anumnumer=new HashMap<String, String>();
		anumnumer.put("anum", String.valueOf(anum));
		jsonArray.add(anumnumer);
		
		request.setAttribute("lattercommentlist", jsonArray.toString());
	
	}

}
