<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ page import="com.gyk.bean.StudentBean" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
    request.setCharacterEncoding("utf-8");
    List<StudentBean> studentList = (List<StudentBean>) request.getAttribute("studentlist");

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
                                    <td height="30" background="Images/mainMenuBg.jpg" style="padding-left:25px;">学生管理
                                    </td>
                                </tr>
                                <tr>
                                    <td height="470" align="center" valign="top" bgcolor="#F6F9FE">
                                        <form name="form1" method="post" action="<%=path%>/StudentManager">
                                            <table width="100%%" border="0" cellspacing="0" cellpadding="0">
                                                <tr>
                                                    <td width="28%" height="30" style="padding-left:20px;">
                                                        功能导航： <a href="StudentAdd.jsp">添加学生</a>
                                                    </td>
                                                    <td width="72%">查询：
                                                        <select name="State" id="State">
                                                            <option value="">全部学生</option>
                                                            <option value="入住">入住</option>
                                                            <option value="未入住">未入住</option>
                                                            <option value="迁出">迁出</option>

                                                        </select>
                                                        <select name="SearchRow" id="SearchRow">
                                                            <option value="Student_Name">姓名</option>
                                                            <option value="Student_Username">学号</option>
                                                            <option value="Student_Class">班级</option>
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
                                                <td height="25" bgcolor="#D5E4F4"><strong>学号</strong></td>
                                                <td bgcolor="#D5E4F4"><strong>姓名</strong></td>
                                                <td bgcolor="#D5E4F4"><strong>性别</strong></td>
                                                <td bgcolor="#D5E4F4"><strong>班级</strong></td>
                                                <td bgcolor="#D5E4F4"><strong>状态</strong></td>
                                                <td bgcolor="#D5E4F4"><strong>操作</strong></td>
                                            </tr>
                                            <%for (StudentBean student : studentList) {%>
                                            <tr align="center">
                                                <td height="25" align="center"><%=student.getStudent_Username()%>
                                                </td>
                                                <td><%=student.getStudent_Name()%>
                                                </td>
                                                <td><%=student.getStudent_Sex()%>
                                                </td>
                                                <td><%=student.getStudent_Class()%>
                                                </td>
                                                <td align="center"><%=student.getStudent_State()%>
                                                </td>
                                                <td align="center">
                                                    <a href="StudentUpdate.jsp?Student_ID=<%=student.getStudent_ID()%>">修改</a>
                                                    <a href="<%=path%>/StudentDel?Student_ID=<%=student.getStudent_ID()%>"
                                                       onClick="return confirm('确定要删除该学生吗？')">删除</a>
                                                </td>
                                            </tr>
                                            <%}%>
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
