<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<%
	int flag = (Integer) request.getAttribute("flag");

	out.println("<script type='text/javascript'>");
	if (flag == 0) {
		session.invalidate();
		out.println("alert('회원탈퇴 성공했습니다.')");
		out.println("location.href='main.do'");
	} else if (flag == 1) {
		out.println("alert('비밀번호가 틀립니다.')");
		out.println("history.back()");
	}
	out.println("</script>");
%>