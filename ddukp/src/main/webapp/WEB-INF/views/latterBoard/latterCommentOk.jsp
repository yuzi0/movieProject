<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="javax.naming.Context"%>
<%@ page import="javax.naming.InitialContext"%>
<%@ page import="javax.naming.NamingException"%>
<%@ page import="javax.sql.DataSource"%>
<%@ page import="java.sql.ResultSet"%>

<%@ page import="java.sql.Connection"%>
<%@ page import="java.sql.PreparedStatement"%>
<%@ page import="java.sql.SQLException"%>
<%@ page import="java.io.File"%>

<%



	int flag = (Integer)request.getAttribute("flag");
	int rnum=(Integer)request.getAttribute("rnum");
	out.println("<script type='text/javascript'>");
	if (flag == 0) {
		out.println("alert('댓글올리기 성공');");
		out.println("location.href='latterView.jsp?fnum=" + rnum+"';");
	} else if (flag == 1) {
		out.println("alert('비밀번호 오류');");
		out.println("history.back();");
	} else if (flag == 2) {
		out.println("alert('댓글올리기 실패');");
		out.println("history.back();");
	}
	out.println("</script>");
%>ml>
