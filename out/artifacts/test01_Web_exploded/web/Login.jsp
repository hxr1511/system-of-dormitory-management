<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%--<%@ taglib prefix="s" uri="/struts-tags"%>--%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>校园宿舍管理系统</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="Style/Style.css" rel="stylesheet" type="text/css" />
	<link rel="shortcut icon" href="images/bitbug_favicon.ico" type="image/x-icon" />
</head>
<script language="JavaScript">

	function mycheck() {
		if (isNull(form1.Type.value)) {
			alert("请选择身份！");
			return false;
		}
		if (isNull(form1.Username.value)) {
			alert("请输入用户名！");
			return false;
		}
		if (isNull(form1.Password.value)) {
			alert("请输入密码！");
			return false;
		}

	}

	function isNull(str) {
		if (str == "") return true;
		var regu = "^[ ]+$";
		var re = new RegExp(regu);
		return re.test(str);
	}
	function check_return(){
		if (check_return.value == "check_fail"){
			alert("用户名或密码在数据库中不存在！请联系管理员进行添加修改！\n" +
					"错误提示+<%=request.getAttribute("Msg")%>")
		}
	}

</script>
<body>
<!--判断是否为重新登录 提示-->

<%--<div value="<%=request.getAttribute("Msg")%>" id="check_return" ></div>--%>
	<center>
		<br> <br> <br> <br> <br>
		<table width="1280" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td height="550" align="center" valign="top"
					background="Images/main.jpg">
					<table width="350" height="201" border="0" cellpadding="0"
						cellspacing="0">
						<tr>
							<td height="100" align="center"><h3>校园宿舍管理系统</h3></td>
						</tr>
						<tr>
							<td align="center" valign="top">
								<form name="form1" action="<%=path%>/AdminLogin" method="post"
									onSubmit="return mycheck()">
									<table width="100%" border="0" cellspacing="0" cellpadding="0">
										<tr>
											<td height="30" colspan="2" align="center" class="STYLE2"><span
												style="color:red;">

											</span></td>
										</tr>
										<tr>
											<td height="30" align="right" class="STYLE2">身份：</td>
											<td align="left"><select name="Type" id="Type">
													<option value="">请选择</option>
													<option value="系统管理员">系统管理员</option>
													<option value="楼宇管理员">楼宇管理员</option>
													<option value="学生">学生</option>
											</select></td>
										</tr>
										<tr>
											<td width="37%" height="30" align="right" class="STYLE2">用户名：</td>
											<td width="300" align="left"><input type="text"
												name="Username" id="Username" class="text1" /></td>
										</tr>
										<tr>
											<td height="30" align="right" class="STYLE2">密码：</td>
											<td align="left"><input type="password" name="Password"
												id="Password" class="text1" /></td>
										</tr>
										<tr>
											<td height="30" colspan="2" align="center"><label>
													<input type="submit" name="button" id="button" value="登录">
											</label></td>
										</tr>
									</table>
								</form>

							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>


	</center>
</body>
</html>
