<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="domain.files"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>文件管理</title>
</head>
<script type="text/javascript">
	function hidetb3(checkbox) {
		document.getElementById("tb3").style.visibility = "visible";
		if (checkbox.checked == true) {
			var arr = document.getElementsByName("checkbox");
			for (i = 0; i < arr.length; i++) {
				if (arr[i].checked) {
					document.getElementById("renameoldfile_name").value = arr[i].value;
					document.getElementById("deletefile_name").value = arr[i].value;
					document.getElementById("changeslevelfile_name").value = arr[i].value;
				}
			}
		} else {
			document.getElementById("tb3").style.visibility = "hidden";
		}
	}
	function createdirectory() {
		window
				.open(
						"createdirectory.jsp",
						"createdirectory",
						"fullscreen=no,toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=no,resizable=no, width=400,height=100,left=200,top=200");
	}
	function submitcreatedirectory() {
		document.getElementById("formcreatedirectory").submit();
	}
	function functionrename() {
		window
				.open(
						"rename.jsp",
						"rename",
						"fullscreen=no,toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=no,resizable=no, width=400,height=100,left=200,top=200");
	}
	function submitrename() {
		document.getElementById("formrename").submit();
	}
	function functionchangeslevel() {
		window
				.open(
						"changeslevel.jsp",
						"changeslevel",
						"fullscreen=no,toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=no,resizable=no, width=400,height=100,left=200,top=200");
	}
	function submitchangeslevel() {
		document.getElementById("formchangeslevel").submit();
		
	}
	function functiondelete() {
		if (confirm("确定要删除所选文件吗？")) {
			document.getElementById("formdelete").submit();
		} else {

		}
	}
	function functiondownload() {
		if (confirm("确定要下载所选文件吗？")) {
			//document.getElementById("formdelete").submit();
		} else {

		}
	}
	function functioncopyto() {
		window
				.open(
						"copyto.jsp",
						"copyto",
						"fullscreen=no,toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=no,resizable=no, width=400,height=100,left=200,top=200");
	}
	function functionmoveto() {
		window
				.open(
						"moveto.jsp",
						"moveto",
						"fullscreen=no,toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=no,resizable=no, width=400,height=100,left=200,top=200");
	}
</script>
<body>
	<%
		List<files> listpersonal = (List<files>) request.getAttribute("listpersonal");
		List<files> listcurrent = (List<files>) request.getAttribute("listcurrent");
		int index = 0;
		int[] idindex = new int[500];
		if (request.getAttribute("index") != null) {
			index = (int) request.getAttribute("index");
			idindex = (int[]) session.getAttribute("idindex");
			idindex[index] = (int) request.getAttribute("idindex");
			System.out.println("index=" + index);
			System.out.println("idindex[index]" + idindex[index]);
		}
		String username = (String) request.getAttribute("username");
		for (int i = 0; i < listpersonal.size(); i++) {
			if (listpersonal.get(i).getFile_name().equals(username)
					&& listpersonal.get(i).getFile_type().equals("userdirectory")) {
				idindex[0] = listpersonal.get(i).getFile_id();
				System.out.println("index=" + index);
				System.out.println("idindex[0]" + idindex[0]);
				break;
			}
		}
		String currentpath = "hdfs://localhost:9000/user/ubuntu/root";
		for (int i = 0; i <= index; i++) {
			for (int j = 0; j < listpersonal.size(); j++) {
				if (idindex[i] == listpersonal.get(j).getFile_id()) {
					currentpath = currentpath + "/" + listpersonal.get(j).getFile_name();
				}
			}
		}
		int prenode_id = idindex[index];
		String prenode_name = username;
		for (int i = 0; i < listpersonal.size(); i++) {
			if (listpersonal.get(i).getFile_id() == prenode_id) {
				prenode_name = listpersonal.get(i).getFile_name();
			}
		}
		session.setAttribute("username", username);
		session.setAttribute("currentpath", currentpath);
		session.setAttribute("prenode_id", prenode_id);
		session.setAttribute("prenode_name", prenode_name);
		session.setAttribute("index", index);
		session.setAttribute("idindex", idindex);
	%>
		<span style="font-size: 35px"> <a style="text-decoration: none"  align="center"
		href="">账户管理</a>&nbsp&nbsp&nbsp&nbsp&nbsp<a style="text-decoration: none" 
		align="center" href="">文件管理</a>
	</span>
	<span style="float: right"> 普通用户：<%=username %> | <a style="text-decoration: none"  align="center"
		href="login.jsp">退出</a></span>
	</br>
	</br>
	</br>
	<table id="tb1" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td width="100" align="center"><input type="button"
				name="onload" value="上传" onclick="onload()" style="width: 95px" /></td>
			<td width="100" align="center"><input type="button"
				name="createdirectory" value="新建文件夹" onclick="createdirectory()"
				style="width: 95px" /></td>
		</tr>
	</table>
	<table id="tb3" border="0" cellpadding="0" cellspacing="0"
		style="visibility: hidden">
		<tr>
			<td width="100" align="center"><input type="button" name="copy"
				value="复制到" onclick="functioncopyto()" style="width: 95px" /></td>
			<td width="100" align="center"><input type="button" name="move"
				value="移动到" onclick="functionmoveto()" style="width: 95px" /></td>
			<td width="100" align="center"><input type="button"
				name="download" value="下载" onclick="functiondownload()" style="width: 95px" />
			</td>
			<td width="100" align="center"><input type="button"
				name="rename" value="重命名" onclick="functionrename()"
				style="width: 95px" /></td>
			<td width="100" align="center"><input type="button"
				name="chageslevel" value="修改安全等级" onclick="functionchangeslevel()"
				style="width: 95px" /></td>
			<td width="100" align="center"><input type="button"
				name="delete" value="删除" onclick="functiondelete()"
				style="width: 95px" /></td>
		</tr>
	</table>
	</br>
	<table id="tb2" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td>
				<%
					if (index > 0) {
				%><a
				href="showfiles?file_id=<%=idindex[index - 1]%>&username=<%=username%>&index=<%=index - 1%>"
				style="text-decoration: none">返回上一级</a> <%
 	} else {
 %>返回上一级<%
 	}
 %>|<a
				href="showfiles?file_id=<%=idindex[0]%>
				&username=<%=username%>&index=0"
				<%session.setAttribute("idindex", idindex);%>
				style="text-decoration: none">全部文件</a> <%
 	for (int i = 1; i <= index; i++) {
 %>><a
				href="showfiles?file_id=<%=idindex[i]%>&username=<%=username%>&index=<%=i%>"
				<%session.setAttribute("idindex", idindex);%>
				style="text-decoration: none"> <%
 	for (int j = 0; j < listpersonal.size(); j++) {
 			if (listpersonal.get(j).getFile_id() == idindex[i]) {
 %> <%=listpersonal.get(j).getFile_name()%> <%
 	}
 		}
 %>
			</a> <%
 	}
 %>
			</td>
		</tr>
	</table>
	</br>
	<table align="center" id="tb4" border="1" cellpadding="0" cellspacing="0">
		<tr>
			<td width="600">文件名</td>
			<td>安全等级</td>
		</tr>
		<%
			for (int i = 0; i < listcurrent.size(); i++) {
		%>
		<tr>
			<td width="600"><input type="checkbox" name="checkbox"
				onclick="hidetb3(this)"
				value="<%=listcurrent.get(i).getFile_name()%>" /> <%
 	if (listcurrent.get(i).getFile_type() != "file") {
 %> <image width="25" heigeht="25" src="directory.jpg"> <%
 	} else {
 %> <image width="25" heigeht="25" src="file.png"> <%
 	}
 %> <a
					href="showfiles?file_id=<%=listcurrent.get(i).getFile_id()%>&username=<%=username%>&index=<%=index + 1%>
					<%session.setAttribute("idindex", idindex);%>"
					style="text-decoration: none"><%=listcurrent.get(i).getFile_name()%></a></td>
			<td align="center"><%=listcurrent.get(i).getSecurity_level()%></td>
		</tr>
		<%
			}
		%>
	</table>
	<form id="formcreatedirectory" name=" createdirectory" method="post"
		action="createdirectory">
		<input type="hidden" id="file_name" name="file_name" /> <input
			type="hidden" id="slevel" name="slevel" /> <input type="hidden"
			name="currentpath" value="<%=currentpath%>" /> <input type="hidden"
			name="username" value="<%=username%>" /> <input type="hidden"
			name="prenode_id" value="<%=prenode_id%>" /> <input type="hidden"
			name="prenode_name" value="<%=prenode_name%>" /> <input
			type="hidden" name="index" value="<%=index%>" />
	</form>
	<form id="formrename" name="formrename" method="post" action="rename">
		<input type="hidden" id="renameoldfile_name" name="oldfile_name" /> <input
			type="hidden" id="renamenewfile_name" name="newfile_name" /> <input
			type="hidden" name="currentpath" value="<%=currentpath%>" /> <input
			type="hidden" name="username" value="<%=username%>" /> <input
			type="hidden" name="prenode_id" value="<%=prenode_id%>" /> <input
			type="hidden" name="index" value="<%=index%>" />
	</form>
	<form id="formdelete" name="formdelete" method="post" action="delete">
		<input type="hidden" id="deletefile_name" name="deletefile_name" /> <input
			type="hidden" name="deletePath" value="<%=currentpath%>" /> <input
			type="hidden" name="username" value="<%=username%>" /> <input
			type="hidden" name="prenode_id" value="<%=prenode_id%>" /> <input
			type="hidden" name="index" value="<%=index%>" />
	</form>
	<form id="formchangeslevel" name="formchangeslevel" method="post"
		action="changeslevel">
		<input type="hidden" id="changedslevel" name="changedslevel" /> <input
			type="hidden" id="changeslevelfile_name" name="changeslevelfile_name" />
		<input type="hidden" name="username" value="<%=username%>" /> <input
			type="hidden" name="prenode_id" value="<%=prenode_id%>" /> <input
			type="hidden" name="index" value="<%=index%>" />
	</form>
</body>
</html>