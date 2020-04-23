<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	int inum = (Integer)request.getAttribute("inum");
	 int flag= (Integer)request.getAttribute("flag");
	 String  cpage=(String)request.getAttribute("cpage");
	out.println("<script type='text/javascript'>");

	if (flag == 0) {
		out.println("alert('글수정에 성공');");
		out.println("location.href='infoView.do?cpage=" + cpage + "&inum=" + inum + "';");
	} else if (flag == 1) {
		out.println("alert('오류입니다.');");
		out.println("history.back();");
	} else if (flag == 2) {
		out.println("alert('글수정에 실패');");
		out.println("history.back();");
	}
	out.println("</script>");
%>