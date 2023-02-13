<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.gyk.bean.BuildingBean" %>
<%@ page import="com.gyk.Dao.BuildingDao" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	List<BuildingBean> buildingList = new BuildingDao().GetList(0,null);
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>校园宿舍管理系统</title>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="Style/Style.css" rel="stylesheet" type="text/css" />
</head>
<script language="JavaScript">


	function mycheck() {
		if (isNull(form1.Domitory_BuildingID.value)) {
			alert("请选择楼栋！");
			return false;
		}
		if (isNull(form1.Domitory_Name.value)) {
			alert("请输入寝室号！");
			return false;
		}
		if (isNull(form1.Domitory_Type.value)) {
			alert("请输入寝室类型！");
			return false;
		}
		if (isNull(form1.Domitory_Number.value)) {
			alert("请输入人数！");
			return false;
		}
		if (isNull(form1.Domitory_Tel.value)) {
			alert("请输入电话！");
			return false;
		}
		//判断手机号码合法性
		var tel = form1.Domitory_Tel.value
		if (!(/^1[3|4|5|8][0-9]\d{4,8}$/.test(tel))) {
			alert("联系电话号格式不正确，请重新输入！");
			//document.mobileform.mobile.focus();
			return false;
		}
	}

	function isNull(str) {
		if (str == "") return true;
		var regu = "^[ ]+$";
		var re = new RegExp(regu);
		return re.test(str);
	}
</script>
<body>
	<center>
		<table width="900" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td height="60" bgcolor="#E6F5FF"
					style="color:#06F; font-size:19px; font-weight:bolder; padding-left:50px;">校园宿舍管理系统</td>
			</tr>
			<tr>
				<td height="30" background="Images/MenuBg.jpg">&nbsp;</td>
			</tr>
			<tr>
				<td height="500" align="center" valign="top"><table width="900"
						border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td width="191" height="500" align="center" valign="top"
								background="Images/leftbg.jpg"><%@ include file="Left.jsp"%>
							</td>
							<td width="709" align="center" valign="top" bgcolor="#F6F9FE"><table
									width="709" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td height="30" background="Images/mainMenuBg.jpg"
											style="padding-left:25px;">添加宿舍</td>
									</tr>
									<tr>
										<td height="470" align="center" valign="top" bgcolor="#F6F9FE"><form
												name="form1" method="post" action="<%=path%>/DomitoryAddSave"
												onSubmit="return mycheck()">
												<table width="100%" border="0" cellspacing="0"
													cellpadding="0">
													<tr>
														<td width="33%" height="30" align="right">&nbsp;</td>
														<td width="67%">&nbsp;</td>
													</tr>
													<tr>
														<td height="30" align="right"><span
															style="color:red;">*</span>楼栋：</td>
														<td><select name="Domitory_BuildingID"
															id="Domitory_BuildingID">
																<option value="">请选择</option>
																<%for (BuildingBean building:buildingList){%>
																	<option value="<%=building.getBuilding_ID()%>"><%=building.getBuilding_Name()%></option>
																<%}%>
														</select></td>
													</tr>
													<tr>
														<td height="30" align="right"><span
															style="color:red;">*</span>寝室号：</td>
														<td><input name="Domitory_Name" type="text"
															class="text2" id="Domitory_Name"></td>
													</tr>
													<tr>
														<td height="30" align="right"><span
															style="color:red;">*</span>寝室类型：</td>
														<td><input name="Domitory_Type" type="text"
															class="text2" id="Domitory_Type"></td>
													</tr>
													<tr>
														<td height="30" align="right"><span
															style="color:red;">*</span>人数：</td>
														<td><input name="Domitory_Number" type="text"
															class="text2" id="Domitory_Number"></td>
													</tr>
													<tr>
														<td height="30">&nbsp;</td>
														<td><input type="submit" name="button" id="button"
															value="添加宿舍"> &nbsp;&nbsp; <input type="button"
															name="button2" id="button2" value="返回上页"
															onClick="javascript:history.back(-1);"></td>
													</tr>
												</table>
											</form></td>
									</tr>
								</table></td>
						</tr>
					</table></td>
			</tr>
			<tr>
				<td height="35" background="Images/bootBg.jpg">&nbsp;</td>
			</tr>
		</table>

	</center>
</body>
</html>
