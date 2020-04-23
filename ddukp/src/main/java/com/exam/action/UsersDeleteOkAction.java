package com.exam.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.exam.model.UserDAO;
import com.exam.model.UserTO;

public class UsersDeleteOkAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
		System.out.println("UsersDeleteOkAction 호출");
		
		String[] arr = request.getParameterValues("chbox[]");
		System.out.println(Arrays.toString(arr));
		
		
		UserTO to = new UserTO();
		UserDAO dao = new UserDAO();
		
		int flag = 2;
		int uNum = 0;

		for (String i : arr) {
			String id = to.getUid();
			System.out.println("액션 i : " + i);
			flag = dao.usersDeleteOk(i);
			System.out.println("액션 flag : " + flag);
			System.out.println("성공");
		}
		request.setAttribute("flag", flag);
		System.out.println("액션1 flag : " + flag);
	}

}
