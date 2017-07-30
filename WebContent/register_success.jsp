<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>注册成功</title>
</head>
<body>
<span style="float: right"> <a style="text-decoration: none" align="center"
		href="login.jsp">返回登录</a></span>
	<%
		String register_message = null;
		if (request.getAttribute("register_message") != null) {
			register_message = (String) request.getAttribute("register_message");
		}
	%>
	&nbsp&nbsp&nbsp&nbsp&nbsp
	<h1 align="center">注册成功！</h1>
	<h2 align="center"><%=register_message%></h2>
</body>
</html>