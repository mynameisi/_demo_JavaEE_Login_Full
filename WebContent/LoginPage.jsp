<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<title>Login Page</title>
</head>
<body>
	<form name="actionForm" action="LoginServlet" method="POST">
		<fieldset>
			<legend>注册</legend>
			用户名:
			<input type="text" name="username" value="admin" />
			密码:
			<input type="password" name="password" value="admin" />
			<button type="submit">提交</button>

		</fieldset>
	</form>
</body>
</html>