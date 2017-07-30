<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>注册</title>
</head>
<body>
	<br />
	<span style="float: right"> <a style="text-decoration: none"
		align="center" href="login.jsp">返回登录</a></span>
	<br />
	<form id="register" name="register" method="post" action="register">
		<table align="center" border="0" cellpadding="0" cellspacing="0">
			<tr>
				<td width="150">账户：</td>
				<td width="150"><input type="text" id="account" name="account"></td>
			</tr>
			<tr>
				<td width="150">密码：</td>
				<td width="150"><input type="password" id="password"
					name="password"></td>
			</tr>
			<tr>
				<td width="150">再次输入密码：</td>
				<td width="150"><input type="password" id="repassword"
					name="repassword"></td>
			</tr>
			<tr>
				<td width="150">电子邮箱：</td>
				<td width="150"><input type="text" id="email" name="email"></td>
			</tr>
			<tr>
				<td width="150">年龄：</td>
				<td width="150"><input type="text" id="age" name="age"></td>
			</tr>
			<tr>
				<td width="150">工薪阶层：</td>
				<td width="150"><select id="workclass" name="workclass"
					style="width: 235px">
						<option value=Private>Private</option>
						<option value=Self-emp-not-inc>Self-emp-not-inc</option>
						<option value=Self-emp-inc>Self-emp-inc</option>
						<option value=Federal-gov>Federal-gov</option>
						<option value=Local-gov>Local-gov</option>
						<option value=State-gov>State-gov</option>
						<option value=Without-pay>Without-pay</option>
						<option value=Never-worked>Never-worked</option>
				</select></td>
			</tr>
			<tr>
				<td width="150">教育：</td>
				<td width="150"><select id="education" name="education"
					style="width: 235px">
						<option value=Bachelors>Bachelors</option>
						<option value=Some-college>Some-college</option>
						<option value=11th>11th</option>
						<option value=HS-grad>HS-grad</option>
						<option value=Prof-school>Prof-school</option>
						<option value=Assoc-acdm>Assoc-acdm</option>
						<option value=Assoc-voc>Assoc-voc</option>
						<option value=7th-8th>7th-8th</option>
						<option value=12th>12th</option>
						<option value=Masters>Masters</option>
						<option value=1st-4th>1st-4th</option>
						<option value=10th>10th</option>
						<option value=Doctorate>Doctorate</option>
						<option value=5th-6th>5th-6th</option>
						<option value=Preschool>Preschool</option>
				</select></td>
			</tr>
			<tr>
				<td width="150">婚姻状态：</td>
				<td width="150"><select id="marital_status"
					name="marital_status" style="width: 235px">
						<option value=Married-civ-spouse>Married-civ-spouse</option>
						<option value=Divorced>Divorced</option>
						<option value=Never-married>Never-married</option>
						<option value=Separated>Separated</option>
						<option value=Widowed>Widowed</option>
						<option value=Married-spouse-absent>Married-spouse-absent</option>
						<option value=Married-AF-spouse>Married-AF-spouse</option>
				</select></td>
			</tr>
			<tr>
				<td width="150">职业：</td>
				<td width="150"><select id="occupation" name="occupation"
					style="width: 235px">
						<option value=Tech-support>Tech-support</option>
						<option value=Craft-repair>Craft-repair</option>
						<option value=Other-service>Other-service</option>
						<option value=Sales>Sales</option>
						<option value=Exec-managerial>Exec-managerial</option>
						<option value=Prof-specialty>Prof-specialty</option>
						<option value=Handlers-cleaners>Handlers-cleaners</option>
						<option value=Machine-op-inspct>Machine-op-inspct</option>
						<option value=Adm-clerical>Adm-clerical</option>
						<option value=Farming-fishing>Farming-fishing</option>
						<option value=Transport-moving>Transport-moving</option>
						<option value=Priv-house-serv>Priv-house-serv</option>
						<option value=Protective-serv>Protective-serv</option>
						<option value=Armed-Forces>Armed-Forces</option>
				</select></td>
			</tr>
			<tr>
				<td width="150">种族：</td>
				<td width="150"><select id="race" name="race"
					style="width: 235px">
						<option value=White>White</option>
						<option value=Asian-Pac-Islander>Asian-Pac-Islander</option>
						<option value=Amer-Indian-Eskimo>Amer-Indian-Eskimo</option>
						<option value=Other>Other</option>
						<option value=Black>Black</option>
				</select></td>
			</tr>
			<tr>
				<td width="150">性别：</td>
				<td width="150"><select id="sex" name="sex"
					style="width: 235px">
						<option value=Female>Female</option>
						<option value=Male>Male</option>
				</select></td>
			</tr>
			<tr>
				<td width="150">国家：</td>
				<td width="150"><select id="native_country"
					name="native_country" style="width: 235px">
						<option value=United-States>United-States</option>
						<option value=Cambodia>Cambodia</option>
						<option value=England>England</option>
						<option value=Puerto-Rico>Puerto-Rico</option>
						<option value=Canada>Canada</option>
						<option value=Germany>Germany</option>
						<option value=Outlying-US(Guam-USVI-etc)>Outlying-US(Guam-USVI-etc)</option>
						<option value=India>India</option>
						<option value=Japan>Japan</option>
						<option value=Greece>Greece</option>
						<option value=South>South</option>
						<option value=China>China</option>
						<option value=Cuba>Cuba</option>
						<option value=Iran>Iran</option>
						<option value=Honduras>Honduras</option>
						<option value=Philippines>Philippines</option>
						<option value=Italy>Italy</option>
						<option value=Poland>Poland</option>
						<option value=Jamaica>Jamaica</option>
						<option value=Vietnam>Vietnam</option>
						<option value=Mexico>Mexico</option>
						<option value=Portugal>Portugal</option>
						<option value=Ireland>Ireland</option>
						<option value=France>France</option>
						<option value=Dominican-Republic>Dominican-Republic</option>
						<option value=Laos>Laos</option>
						<option value=Ecuador>Ecuador</option>
						<option value=Taiwan>Taiwan</option>
						<option value=Haiti>Haiti</option>
						<option value=Columbia>Columbia</option>
						<option value=Hungary>Hungary</option>
						<option value=Guatemala>Guatemala</option>
						<option value=Nicaragua>Nicaragua</option>
						<option value=Scotland>Scotland</option>
						<option value=Thailand>Thailand</option>
						<option value=Yugoslavia>Yugoslavia</option>
						<option value=El-Salvador>El-Salvador</option>
						<option value=Trinadad&Tobago>Trinadad&Tobago</option>
						<option value=Peru>Peru</option>
						<option value=Hong>Hong</option>
						<option value=Holand-Netherlands>Holand-Netherlands</option>
				</select></td>
			</tr>
			<tr>
				<td width="150" align="center"></td>
				<td width="150" align="center"><input type="submit"
					name="register" value="注册" style="width: 100px" /></td>
			</tr>
		</table>
	</form>
</body>
</html>