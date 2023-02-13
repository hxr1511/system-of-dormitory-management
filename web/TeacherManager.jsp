<%--<%@ taglib prefix="s" uri="http://java.sun.com/jsp/jstl/core" %>--%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ page import="com.gyk.bean.TeacherBean" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
    request.setCharacterEncoding("utf-8");
    List<TeacherBean> teacherList = (List<TeacherBean>) request.getAttribute("teacherlist");
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>校园宿舍管理系统</title>
    <base href="<%=basePath%>">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="Style/Style.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<center>
    <table width="900" border="0" cellspacing="0" cellpadding="0">
        <tr>
            <td height="60" bgcolor="#E6F5FF"
                style="color:#06F; font-size:19px; font-weight:bolder; padding-left:50px;">校园宿舍管理系统
            </td>
        </tr>
        <tr>
            <td height="30" background="Images/MenuBg.jpg">&nbsp;</td>
        </tr>
        <tr>
            <td height="500" align="center" valign="top">
                <table width="900" border="0" cellspacing="0" cellpadding="0">
                    <tr>
                        <td width="191" height="500" align="center" valign="top" background="Images/leftbg.jpg">
                            <%@ include file="Left.jsp" %>
                        </td>
                        <td width="709" align="center" valign="top" bgcolor="#F6F9FE">
                            <table width="709" border="0" cellspacing="0" cellpadding="0">
                                <tr>
                                    <td height="30" background="Images/mainMenuBg.jpg" style="padding-left:25px;">
                                        楼栋管理员管理
                                    </td>
                                </tr>
                                <tr>
                                    <td height="470" align="center" valign="top" bgcolor="#F6F9FE">
                                        <form name="form1" method="post" action="<%=path%>/TeacherManager">
                                            <table width="100%%" border="0" cellspacing="0" cellpadding="0">
                                                <tr>
                                                    <td width="28%" height="30" style="padding-left:20px;"> 功能导航： <a
                                                            href="TeacherAdd.jsp">添加楼栋管理员</a></td>
                                                    <td width="72%">查询：
                                                        <select name="SearchRow" id="SearchRow">
                                                            <option value="Teacher_Name">姓名</option>
                                                            <option value="Teacher_Tel">电话</option>
                                                            <option value="Teacher_Username">用户名</option>
                                                        </select>
                                                        <input name="SearchKey" type="text" class="text1"
                                                               id="SearchKey">
                                                        <input type="submit" name="button" id="button" value="点击查询">
                                                    </td>
                                                </tr>
                                            </table>
                                        </form>
                                        <table width="100%" border="0" cellspacing="0" cellpadding="0">
                                            <tr align="center" class="t1">
                                                <td height="25" bgcolor="#D5E4F4"><strong>姓名</strong></td>
                                                <td bgcolor="#D5E4F4"><strong>性别</strong></td>
                                                <td bgcolor="#D5E4F4"><strong>电话</strong></td>
                                                <td bgcolor="#D5E4F4"><strong>用户名</strong></td>
                                                <td bgcolor="#D5E4F4"><strong>操作</strong></td>
                                            </tr>

                                            <%for (TeacherBean teacher : teacherList) {%>
                                            <tr align="center">
                                                <td height="25" align="center"><%=teacher.getTeacher_Name()%>
                                                </td>
                                                <td><%=teacher.getTeacher_Sex()%>
                                                </td>
                                                <td><%=teacher.getTeacher_Tel()%>
                                                </td>
                                                <td align="center"><%=teacher.getTeacher_Username()%>
                                                </td>
                                                <td align="center"><a
                                                        href="TeacherUpdate.jsp?Teacher_ID=<%=teacher.getTeacher_ID()%>">修改</a>
                                                    <a href="<%=path%>/TeacherDel?Teacher_ID=<%=teacher.getTeacher_ID()%>"
                                                       onClick="return confirm('确定要删除该楼栋管理员吗？')">删除</a></td>
                                            </tr>
                                            <%}%>
                                            <%--                  <s:iterator id="aa" value="list">--%>
                                            <%--                    <tr align="center">--%>
                                            <%--                      <td height="25" align="center">${Teacher_Name}</td>--%>
                                            <%--                      <td>${Teacher_Sex}</td>--%>
                                            <%--                      <td>${Teacher_Tel}</td>--%>
                                            <%--                      <td align="center">${Teacher_Username}</td>--%>
                                            <%--                      <td align="center"><a href="TeacherUpdate.jsp?Teacher_ID=${Teacher_ID}">修改</a>--%>
                                            <%--                      					<a href="/TeacherDel?Teacher_ID=${Teacher_ID}" onClick="return confirm('确定要删除该楼栋管理员吗？')">删除</a></td>--%>
                                            <%--                    </tr>--%>
                                            <%--                  </s:iterator>--%>
                                        </table>
                                    </td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                </table>
            </td>
        </tr>
        <tr>
            <td height="35" background="Images/bootBg.jpg">&nbsp;</td>
        </tr>
    </table>

</center>
</body>
</html>
