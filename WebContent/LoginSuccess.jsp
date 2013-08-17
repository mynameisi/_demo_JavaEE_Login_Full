<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% session.setAttribute("CNT", session.getAttribute("CNT")==null?1:((Integer)session.getAttribute("CNT")+1)); %>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登陆成功</title>
<script>
	function Decrement() {
		timer = document.getElementById("CD");
		timer.innerHTML = timer.innerHTML - 1;
		if (timer.innerHTML != 0) {
			setTimeout('Decrement()', 1000);
			
		}
	}
</script>
</head>
<body onload="setTimeout('Decrement()', 500)">
	<h1>
		用户：<%=session.getAttribute("user")%>登陆成功
	</h1>
	<ul>
		<li>Session ID: <%=session.getId()%></li>
		<li>Session 创建时间: <%=new Date(session.getCreationTime())%></li>
		<li>Session 访问时间: <%=new Date(session.getLastAccessedTime())%></li>
		<li>Session 访问次数: <%=session.getAttribute("CNT") %>
		<li>Session 剩余时间: <em><strong id="CD"><%=session.getMaxInactiveInterval()%></strong></em>
		
		</li>
	</ul>
	<a href="Logout.jsp">登出</a>
</body>
</html>