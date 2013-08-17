<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<title>登出</title>
</head>
<body>

	<br />
	<%
		session.invalidate();
	%>
	你现在已经登出，
	<a href="LoginPage.jsp">返回登陆页面</a>
	<br />


</body>
</html>