<%@ page import="java.util.List" %>
<%@ page import="com.gyk.bean.TeacherBean" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    request.setCharacterEncoding("utf-8");
    response.setContentType("text/html;charset=utf-8");
  String path = request.getContextPath();
  String
          basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
  List<TeacherBean> teacherlist = (List<TeacherBean>) request.getAttribute("teacherlist");
%>
<html>
<head>
    <title>教师管理列表</title>
    <base href="<%=basePath%>">
</head>
<body>

<table>
    <tr><th>姓名</th>
        <th>性别</th>
        <th>电话</th>
        <th>用户名</th></tr>
    <%for (TeacherBean teacher:teacherlist){%>
    <tr>
        <td><%=teacher.getTeacher_Name()%></td>
    </tr>

    <%}%>
<%--<%--%>
<%--    for (TeacherBean teacher:teacherlist) {%>--%>
<%--        <tr>--%>
<%--            <td><%=teacher.getTeacher_Name()%></td>--%>
<%--            <td><%=teacher.getTeacher_Sex()%></td>--%>
<%--            <td><%=teacher.getTeacher_Tel()%></td>--%>
<%--            <td><%=teacher.getTeacher_Username()%></td>--%>
<%--        </tr>--%>
<%--    <%}%>--%>
<%--<c:forEach items="${teacherlist}" varStatus="teacherStatus" var="teacher">--%>
<%--    <tr>--%>
<%--&lt;%&ndash;        <td>${teacherStatus.}</td>&ndash;%&gt;--%>
<%--        <td>${teacher.Teacher_Name}</td>--%>
<%--        <td>${teacher.Teacher_Sex}</td>--%>
<%--        <td>${teacher.Teacher_ID}</td>--%>
<%--        <td>${teacher.Teacher_Tel}</td>--%>
<%--    </tr>--%>
<%--</c:forEach>--%>
</table>
</body>
</html>
