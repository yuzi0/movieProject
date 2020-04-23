package com.exam.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.exam.model.UserDAO;

public class CheckIdAction implements Action {
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		System.out.println("CheckIdAction 호출");
		
		String id = request.getParameter("id");
		int flag = 1;
		
		/* id 중복 확인 */
		UserDAO dao = new UserDAO();
		flag = dao.checkId(id);
		
		request.setAttribute("flag", flag);
		System.out.println("flag : " + flag);
	}

}
