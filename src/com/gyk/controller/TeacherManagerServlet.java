package com.gyk.controller;

import com.gyk.Dao.TeacherDao;
import com.gyk.bean.TeacherBean;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet({"/TeacherManager", "/TeacherUpdateSave", "/TeacherDel", "/TeacherAddSave"})
public class TeacherManagerServlet extends HttpServlet {
    PrintWriter out;

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String path = request.getContextPath();
        String servletPath = request.getServletPath();
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("utf-8");
        out = response.getWriter();
        if ("/TeacherManager".equals(servletPath)) {
            doList(request, response, path);
        } else if ("/TeacherUpdateSave".equals(servletPath)) {
            doUpdateSave(request, response, path);
        } else if ("/TeacherDel".equals(servletPath)) {
            doDel(request, response, path);
        } else if ("/TeacherAddSave".equals(servletPath)) {
            doAddSave(request, response, path);
        }
    }

    /**
     * 添加教师：doAdd()
     * 调用TeacherDao.AddSave()
     *
     * @param request
     * @param response
     * @param path
     */
    private void doAddSave(HttpServletRequest request, HttpServletResponse response, String path)
            throws ServletException, IOException {
        TeacherBean teacher = new TeacherBean();
        int result = 0;
        teacher.setTeacher_Username(request.getParameter("Teacher_Username"));
        teacher.setTeacher_Password(request.getParameter("Teacher_Password"));
        teacher.setTeacher_Name(request.getParameter("Teacher_Name"));
        teacher.setTeacher_Sex(request.getParameter("Teacher_Sex"));
        teacher.setTeacher_Tel(request.getParameter("Teacher_Tel"));
        result = new TeacherDao().AddSave(teacher);
        if (result == 1) {//成功返回首页显示
            out.println("<script language='javascript'>alert('添加成功！');window.location='" +
                    path + "/TeacherManager';</script>");
        }
    }

    /**
     * 删除用户 doDel
     * 调用dao：teacherDao.Delete(Teacher_Id);
     *
     * @param request
     * @param response
     * @param path
     * @throws ServletException
     * @throws IOException
     */
    private void doDel(HttpServletRequest request, HttpServletResponse response, String path)
            throws ServletException, IOException {

        int result = 0;
        //获取id
        int Teacher_Id = Integer.valueOf(request.getParameter("Teacher_ID"));
        result = new TeacherDao().Delete(Teacher_Id);
        if (result == 1) {
            out.println("<script language='javascript'>alert('删除成功！');window.location='" +
                    path + "/TeacherManager';</script>");
        } else {
            out.println("<script language='javascript'>alert('删除失败！请联系管理员重试！');window.location='" +
                    path + "/TeacherManager';</script>");
        }
    }

    /**
     * 修改信息：doUpdate()
     * 调用TeacherDao.UpdateSave
     *
     * @param request
     * @param response
     * @param path
     * @throws ServletException
     * @throws IOException
     */
    private void doUpdateSave(HttpServletRequest request, HttpServletResponse response, String path)
            throws ServletException, IOException {
        int result = 0;
        TeacherBean teacher = new TeacherBean();
        teacher.setTeacher_ID(Integer.parseInt(request.getParameter("Teacher_ID")));
        teacher.setTeacher_Username(request.getParameter("Teacher_Username"));
        teacher.setTeacher_Password(request.getParameter("Teacher_Password"));
        teacher.setTeacher_Name(request.getParameter("Teacher_Name"));
        teacher.setTeacher_Sex(request.getParameter("Teacher_Sex"));
        teacher.setTeacher_Tel(request.getParameter("Teacher_Tel"));
        result = new TeacherDao().UpdateSave(teacher);
        if (result == 1) {//保存成功,返回
            out.println("<script language='javascript'>alert('操作成功');window.location='" +
                    path + "/TeacherManager';</script>");
        } else {
            out.println("<script language='javascript'>alert('修改失败');window.location='" +
                    path + "/TeacherManager';</script>");
        }
    }

    /**
     * !!!
     * 显示teacher：doList()
     * 调用TeacherDao.GetList()
     *
     * @param request
     * @param response
     * @param path
     * @return
     * @throws ServletException
     * @throws IOException
     */
    protected void doList(HttpServletRequest request, HttpServletResponse response, String path)
            throws ServletException, IOException {
        String strwhere = null;
        int key = 0;
        String SearchKey = request.getParameter("SearchKey");
        String SearchRow = request.getParameter("SearchRow");
        if (SearchKey != "" && SearchRow != null) {
            strwhere = " where " + SearchRow + "='" + SearchKey + "'";
            key = 1;
        }
        /*return Teacherlist;返回的是查询到的list集合*/

        List<TeacherBean> teacherlist = new TeacherDao().GetList(key, strwhere);

        System.out.println(teacherlist);
        request.setAttribute("teacherlist", teacherlist);
        request.getRequestDispatcher("TeacherManager.jsp").forward(request, response);


    }


    private boolean isInvalid(String value) {
        return (value == null || value.length() == 0);
    }
}