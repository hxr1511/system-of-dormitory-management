<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<link href="Style/Style.css" rel="stylesheet" type="text/css"/>
<%
    //获取session，不用新建
    //只要获取当前session，获取不到返回null
    HttpSession session1 = request.getSession();
    if (session1 != null && session1.getAttribute("username") != null) {
%>
<table width="155" border="0" cellspacing="0" cellpadding="0">
    <tr>
        <td height="31" align="center" background="Images/left1.jpg"><strong>系统选项</strong></td>
    </tr>
    <tr>
        <td height="50" align="center" valign="top">
            <table width="150"
                   border="0" cellspacing="0" cellpadding="0">
                <tr>
                    <td height="5" align="center"><img src="Images/ic.gif"
                                                       width="1" height="1"></td>
                </tr>
                <tr>
                    <td height="30" align="center" background="Images/left2.jpg"
                        style="text-align:left; padding-left:40px;"><a
                            href="Index.jsp">后台首页</a></td>
                </tr>
                <tr>
                    <td height="5" align="center"><img src="Images/ic.gif"
                                                       width="1" height="1"></td>
                </tr>


                <%
                    //					if (session.getAttribute("type").toString().equals("1")) {
//					if(){
                %>
                <tr>
                    <td height="30" align="center" background="Images/left2.jpg"
                        style="text-align:left; padding-left:40px;"><a
                            href="<%=path%>/TeacherManager">楼栋管理员管理</a></td>
                </tr>
                <tr>
                    <td height="5" align="center"><img src="Images/ic.gif"
                                                       width="1" height="1"></td>
                </tr>
                <tr>
                    <td height="30" align="center" background="Images/left2.jpg"
                        style="text-align:left; padding-left:40px;"><a
                            href="<%=path%>/StudentManager">学生管理</a></td>
                </tr>
                <tr>
                    <td height="5" align="center"><img src="Images/ic.gif"
                                                       width="1" height="1"></td>
                </tr>
                <tr>
                    <td height="30" align="center" background="Images/left2.jpg"
                        style="text-align:left; padding-left:40px;"><a
                            href="<%=path%>/BuildingManager">楼栋管理</a></td>
                </tr>
                <tr>
                    <td height="5" align="center"><img src="Images/ic.gif"
                                                       width="1" height="1"></td>
                </tr>
                <tr>
                    <td height="30" align="center" background="Images/left2.jpg"
                        style="text-align:left; padding-left:40px;"><a
                            href="<%=path%>/DomitoryManager">宿舍管理</a></td>
                </tr>
                <tr>
                    <td height="5" align="center"><img src="Images/ic.gif"
                                                       width="1" height="1"></td>
                </tr>
                <tr>
                    <td height="30" align="center" background="Images/left2.jpg"
                        style="text-align:left; padding-left:40px;"><a
                            href="<%=path%>/StudentRZ">学生入住登记</a></td>
                </tr>
                <tr>
                    <td height="5" align="center"><img src="Images/ic.gif"
                                                       width="1" height="1"></td>
                </tr>
                <tr>
                    <td height="30" align="center" background="Images/left2.jpg"
                        style="text-align:left; padding-left:40px;"><a
                            href="<%=path%>/StudentTH.jsp">学生寝室调换</a></td>
                </tr>
                <tr>
                    <td height="5" align="center"><img src="Images/ic.gif"
                                                       width="1" height="1"></td>
                </tr>
                <tr>
                    <td height="30" align="center" background="Images/left2.jpg"
                        style="text-align:left; padding-left:40px;"><a
                            href="<%=path%>/StudentQC.jsp">学生迁出登记</a></td>
                </tr>
                <tr>
                    <td height="5" align="center"><img src="Images/ic.gif"
                                                       width="1" height="1"></td>
                </tr>
                <%--				<tr>--%>
                <%--					<td height="30" align="center" background="Images/left2.jpg"--%>
                <%--						style="text-align:left; padding-left:40px;"><a--%>
                <%--						href="<%=path%>AdminLog.action">学生缺寝记录</a></td>--%>
                <%--				</tr>--%>

                <tr>
                    <td height="5" align="center"><img src="Images/ic.gif"
                                                       width="1" height="1"></td>
                </tr>
                <%
                    //					}
                %>
                <%
                    //					if (session.getAttribute("type").toString().equals("2")) {
                %>
                <%--				<tr>--%>
                <%--					<td height="30" align="center" background="Images/left2.jpg"--%>
                <%--						style="text-align:left; padding-left:40px;"><a--%>
                <%--						href="<%=path%>MyStudent.action">学生管理</a></td>--%>
                <%--				</tr>--%>
                <%--				<tr>--%>
                <%--					<td height="5" align="center"><img src="Images/ic.gif"--%>
                <%--						width="1" height="1"></td>--%>
                <%--				</tr>--%>
                <%--				<tr>--%>
                <%--					<td height="30" align="center" background="Images/left2.jpg"--%>
                <%--						style="text-align:left; padding-left:40px;"><a--%>
                <%--						href="<%=path%>MyLog.action">学生缺寝记录</a></td>--%>
                <%--				</tr>--%>
                <%--				<tr>--%>
                <%--					<td height="5" align="center"><img src="Images/ic.gif"--%>
                <%--						width="1" height="1"></td>--%>
                <%--				</tr>--%>
                <%
                    //					}
                %>
                <%
                    //					if (session.getAttribute("type").toString().equals("3")) {
                %>
                <%--				<tr>--%>
                <%--					<td height="30" align="center" background="Images/left2.jpg"--%>
                <%--						style="text-align:left; padding-left:40px;"><a--%>
                <%--						href="<%=path%>StudentLog.action">我的缺寝记录</a></td>--%>
                <%--				</tr>--%>
                <%--				<tr>--%>
                <%--					<td height="5" align="center"><img src="Images/ic.gif"--%>
                <%--						width="1" height="1"></td>--%>
                <%--				</tr>--%>
                <%
                    //					}
                %>

                <tr>
                    <td height="30" align="center" background="Images/left2.jpg"
                        style="text-align:left; padding-left:40px;"><a
                            href="<%=path%>/PasswordUpdate.jsp">修改密码</a></td>
                </tr>
                <tr>
                    <td height="5" align="center"><img src="Images/ic.gif"
                                                       width="1" height="1"></td>
                </tr>
                <tr>
                    <td height="30" align="center" background="Images/left2.jpg"
                        style="text-align:left; padding-left:40px;"><a
                            href="<%=path%>/AdminQuit" onclick="return confirm('确定要退出系统吗？')">退出系统</a></td>
                    <%--					quit无法确定是否为退出Servlet--%>
                </tr>
            </table>
        </td>
    </tr>
</table>

<%
    } else {
        response.sendRedirect(path + "/Login.jsp");
    }
%>