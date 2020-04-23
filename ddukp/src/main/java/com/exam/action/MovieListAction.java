package com.exam.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.exam.model.BoardDAO;

public class MovieListAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		BoardDAO dao = new BoardDAO();
		ArrayList<String> movieList = dao.movieList(request.getParameter("value"));
		System.out.println("v : "+request.getParameter("value"));
		ArrayList<String> same = new ArrayList<String>();
		JSONArray jsonArray = new JSONArray();
		JSONObject obj1 =null;
		
		for(String str:movieList) {
			obj1= new JSONObject();
			obj1.put("name", str);
			jsonArray.add(obj1);
		}

		/*String searchName=request.getParameter("value");
		
		
		
		for(String str:movieList) {
			if(str.toLowerCase().startsWith(searchName)) {
				same.add(str);
			}
		}
		for (String str : same) {
			obj1= new JSONObject();
			obj1.put("name", str);
			jsonArray.add(obj1);

		}*/
		
		request.setAttribute("movielist", jsonArray);
	}

}
