<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改文件安全等级</title>
</head>
<script type="text/javascript">
	function yes() {
			window.opener.document.getElementById("changedslevel").value = document
					.getElementById("slevel").value;
			window.opener.submitchangeslevel();
			window.close();
	};
	function no() {
		window.close();
	}
</script>
<body>
	<form id="changeslevel" name="changeslevel" method="post"
		action="changeslevel">
		<table align="center" border="0" cellpadding="0" cellspacing="0">
			<tr>
				<td width="150">安全等级：</td>
				<td width="150"><select id="slevel" name="slevel"
					style="width: 175px">
						<option value=1>1</option>
						<option value=2 selected="selected">2</option>
						<option value=3>3</option>
				</select></td>
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