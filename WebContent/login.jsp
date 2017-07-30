<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录</title>
</head>
<body>
	<!-- 	<form action="login">
		<textfield name="username" key="user" />
		<textfield name="password" key="pass" />
		<a href="www.baidu.com">baidu</a>
		<submit key="login" name="登陆" />
	</form> -->
	</br>
	<span style="float: right"> <a style="text-decoration: none"
		align="center" href="www.baidu.com">游客登录</a> | <a
		style="text-decoration: none" align="center" href="register.jsp">注册</a>
		| <a style="text-decoration: none" align="center" href="findout_pass.jsp">忘记密码</a></span>
	</br>
	</br>
	</br>
	</br>
	<form id="loginform" name="loginform" method="post" action="login">
		<table align="center" border="0" cellpadding="0" cellspacing="0">
			<tr>
				<td width="150">账户：</td>
				<td width="150"><input type="text" id="username"
					name="username"></td>
		
			</tr>
			<tr>
				<td width="150">密码：</td>
				<td width="150"><input type="password" id="password"
					name="password"></td>

			</tr>
			<tr>
				<td width="150"></td>
				<td width="150"><input type="submit" name="login" value="登录"
					style="width: 100px" /></td>
			
			</tr>
		</table>
	</form>
</body>
</html>