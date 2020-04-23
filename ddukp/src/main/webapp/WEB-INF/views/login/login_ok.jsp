<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<%
	int flag = (Integer) request.getAttribute("flag");

	out.println("<script type='text/javascript'>");
	if (flag == 0) {
		out.println("alert('로그인 되셨습니다.')");
		out.println("location.href='main.do'");
	} else if (flag == 1) {
		out.println("alert('로그인 실패하셨습니다.')");
		out.println("history.back()");
	}else {
		out.println("alert('아이디와 비밀번호를 확인해주세요.')");
		out.println("history.back()");
	}
	out.println("</script>");
%>