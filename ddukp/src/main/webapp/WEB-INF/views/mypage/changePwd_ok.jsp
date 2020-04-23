<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<%
	int flag = (Integer) request.getAttribute("flag");

	out.println("<script type='text/javascript'>");
	if (flag == 0) {
		out.println("alert('비밀번호가 변경되었습니다.')");
		out.println("location.href='changePwd.do'");
	} else if (flag == 1) {
		out.println("alert('새 비밀번호 설정에 실패하였습니다.')");
		out.println("history.back()");
	} else if (flag == 2) {
		out.println("alert('현재 비밀번호가 다릅니다.')");
		out.println("history.back()");
	}
	out.println("</script>");
%>