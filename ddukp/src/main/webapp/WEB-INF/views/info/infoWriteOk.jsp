<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<%
	int inum = (Integer)request.getAttribute("inum");

	out.println("<script type='text/javascript'>");
	if (inum == 0) {
		out.println("alert('글쓰기에 실패');");
		out.println("history.back();");
	} else  {
		
		out.println("alert('글쓰기에 성공');");
		out.println("location.href='infoView.do?inum="+inum+"';");
	}
	

	out.println("</script>");
%>