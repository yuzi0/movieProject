<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<%
	int flag = (Integer) request.getAttribute("flag");

	out.println("<script type='text/javascript'>");
	if (flag == 0) {
		out.println("alert('메일로 임시 비밀번호가 전송되었습니다.')");
		out.println("location.href='login.do'");
	} else if (flag == 1) {
		out.println("alert('해당 메일과 아이디로 가입된 계정이 없습니다.')");
		out.println("history.back()");
	}
	out.println("</script>");
%>