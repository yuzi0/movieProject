<%@page import="org.json.simple.JSONArray"%>
<%@page import="org.json.simple.JSONObject"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	int flag = (Integer)request.getAttribute( "flag" );
	System.out.println("딜리트오케이 플래그 : " + flag);

%>
{
	"flag" : <%=flag %>
}