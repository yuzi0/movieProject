<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<%
	int flag = (Integer) request.getAttribute("flag");

	out.println("<script type='text/javascript'>");
	if (flag == 0) {
		out.println("alert('나의 정보가 수정되었습니다.')");
		out.println("location.href='memberInfo.do'");
	} else if (flag == 1) {
		out.println("alert('나의 정보 수정에 실패하였습니다.')");
		out.println("history.back()");
	} else if (flag == 2) {
		out.println("alert('비밀번호가 다릅니다.')");
		out.println("history.back()");
	}
	out.println("</script>");
%>