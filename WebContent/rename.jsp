<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<script type="text/javascript">
	function yes() {
		window.opener.document.getElementById("renamenewfile_name").value = document
				.getElementById("newfile_name").value;
			window.opener.submitrename();
			window.close();
	};
	function no() {
		window.close();
	}
</script>
<body>
	<form id="rename" name="rename" method="post"
		action="rename">
		<table align="center" border="0" cellpadding="0" cellspacing="0">
			<tr>
				<td width="150">重命名：</td>
				<td width="150"><input type="text" id="newfile_name"
					name="newfile_name"></td>
			</tr>
			<tr>
				<td width="150" align="center"><input type="button"
					name="confirm" value="确定" style="width: 100px" onclick="yes()" /></td>
				<td width="150" align="center"><input type="button"
					name="cancel" value="取消" style="width: 100px" onclick="no()" /></td>
			</tr>
		</table>
	</form>
</body>
</html>