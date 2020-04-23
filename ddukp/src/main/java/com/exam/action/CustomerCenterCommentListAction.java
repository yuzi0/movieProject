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
import com.exam.model.CusCommentTO;
import com.exam.model.FreeCommentTO;
import com.exam.model.LatterCommentTO;
import com.exam.model.latterBoardTO;

public class CustomerCenterCommentListAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		CusCommentTO qcTo=new CusCommentTO();
		qcTo.setQnum(request.getParameter("qnum"));
		
		CommentDAO cDAO=new CommentDAO();
		ArrayList<CusCommentTO> fcLists=new ArrayList<CusCommentTO>();
		HttpSession session = request.getSession();
		String currentUser=(String)session.getAttribute("sid");
		int anum=cDAO.adminCheck(currentUser);
		System.out.println("자유 댓글  유저: "+currentUser);
		
		fcLists=cDAO.customerCommentList(qcTo);
		
		ArrayList<HashMap> hLists=new ArrayList<HashMap>();
		JSONArray jsonArray=new JSONArray();
		
		for(int i=0;i<fcLists.size();i++) {
			HashMap<String, String> map=new HashMap<String, String>();
			map.put("qcdate", fcLists.get(i).getQcdate());
			map.put("comment", fcLists.get(i).getQccontent());
			map.put("number",  String.valueOf(fcLists.get(i).getNumber()));
			map.put("qcnum",   fcLists.get(i).getQcnum());
			map.put("uid", fcLists.get(i).getUid());
			map.put("aid", fcLists.get(i).getAid());
			jsonArray.add(map);
		}
		HashMap<String, String> anumnumer=new HashMap<String, String>();
		anumnumer.put("anum", String.valueOf(anum));
		jsonArray.add(anumnumer);
		
		request.setAttribute("customercommentlist", jsonArray.toString());
	
	}

}
