<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	int fnum = (Integer)request.getAttribute("fnum");

	out.println("<script type='text/javascript'>");
	if (fnum == 0) {
		out.println("alert('글쓰기에 실패');");
		out.println("history.back();");
	} else  {
		
		out.println("alert('글쓰기에 성공');");
		out.println("location.href='freeView.do?fnum="+fnum+"';");
	}
	

	out.println("</script>");
%>