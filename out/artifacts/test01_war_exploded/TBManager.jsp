<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ page import="com.gyk.bean.TeacherBean" %>
<%@ page import="com.gyk.bean.TBBean" %>

<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
    List<TeacherBean> teacherList = (List<TeacherBean>) request.getAttribute("teacherList");
    List<TBBean> tblist = (List<TBBean>) request.getAttribute("tblist");
    String Building_ID = request.getParameter("Building_ID");


%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>校园宿舍管理系统</title>
    <base href="<%=basePath%>">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="Style/Style.css" rel="stylesheet" type="text/css"/>
</head>
<script language="JavaScript">


    function mycheck() {
        if (isNull(form1.TB_TeacherID.value)) {
            alert("请选择要添加的管理员！");
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
                                        楼栋管理员设置
                                    </td>
                                </tr>
                                <tr>
                                    <td height="470" align="center" valign="top" bgcolor="#F6F9FE">
                                        <form name="form1" method="post" action="<%=path%>/TBAddSave">
                                            <table width="100%%" border="0" cellspacing="0" cellpadding="0">
                                                <tr>
                                                    <td width="42%" height="30" style="padding-left:20px;"> 功能导航： <a
                                                            href="<%=path%>/BuildingManager">返回上层</a></td>
                                                    <td width="58%">添加管理员：
                                                        <select name="TB_TeacherID" id="TB_TeacherID">
                                                            <option value="">请选择</option>
                                                            <%--                        <s:iterator value="teacherlist">--%>
                                                            <%for (TeacherBean teacher : teacherList) {%>
                                                            <option value="<%=teacher.getTeacher_ID()%>">
                                                                <%=teacher.getTeacher_Name()%>
                                                            </option>
                                                            <%}%>
                                                            <%--                        </s:iterator>--%>
                                                        </select>
                                                        <input type="submit" name="button" id="button" value="点击添加">
                                                        <label for="TB_BuildingID"></label>
                                                        <input name="TB_BuildingID" type="text" class="noshow"
                                                               id="TB_BuildingID" value="<%=Building_ID%>"></td>
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
                                            <%--                  <s:iterator id="aa" value="list">--%>
                                            <%for (TBBean tb : tblist) {%>
                                            <tr align="center">
                                                <td height="25" align="center"><%=tb.getTeacher_Name()%>
                                                </td>
                                                <td><%=tb.getTeacher_Sex()%>
                                                </td>
                                                <td><%=tb.getTeacher_Tel()%>
                                                </td>
                                                <td align="center"><%=tb.getTeacher_Username()%>
                                                </td>
                                                <td align="center"><a
                                                        href="<%=path%>/TBDel?TB_ID=<%=tb.getTB_ID()%>&Building_ID=<%=tb.getTB_BuildingID()%>"
                                                        onClick="return confirm('确定要将该管理员从该楼栋移除吗？')">移除</a></td>
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
