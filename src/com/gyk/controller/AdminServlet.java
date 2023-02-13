package com.gyk.controller;
/**
 * Admin管理
 */

import com.gyk.Dao.AdminDao;
import com.gyk.bean.AdminBean;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet({"/AdminLogin", "/AdminQuit", "/PasswordUpdateSave"})
public class AdminServlet extends HttpServlet {
    PrintWriter out;
    AdminBean admin;

    /**
     * servlet容器把所有请求发送到该方法，该方法默认行为是转发http请求到doXXX方法中
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getContextPath();
        String servletPath = request.getServletPath();
        if ("/AdminLogin".equals(servletPath)) {
            doLogin(request, response, path);
        } else if ("/AdminQuit".equals(servletPath)) {
            doQuit(request, response, path);
        } else if ("/PasswordUpdateSave".equals(servletPath)) {
            doPasswordUpdate(request, response, path);
        }
    }

    /**
     * 修改管理员密码
     *
     * @param request
     * @param response
     * @param path
     * @return
     */
    private void doPasswordUpdate(HttpServletRequest request, HttpServletResponse response, String path)
            throws ServletException, IOException {
        int result = 0;
        // 解决乱码，用于页面输出
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("utf-8");
        HttpSession session1 = request.getSession();
        AdminDao adminDao = new AdminDao();
        out = response.getWriter();

        String old_Password = request.getParameter("Password");
        String new_Password = request.getParameter("Password2");
        String Admin_ID = (String) session1.getAttribute("Admin_ID");
        System.out.println("old_Password:" + old_Password + ",Admin_ID:" + Admin_ID);//判断获取是否成功

        //验证数据库密码跟输入原密码是否重复
        if (adminDao.CheckPassword(Integer.valueOf(Admin_ID), old_Password)) {//正确执行if
            System.out.println("验证数据库密码跟输入原密码一样");
            //修改密码
            admin = adminDao.GetId(Integer.valueOf(Admin_ID));
            System.out.println(admin.toString());
            admin.setAdmin_Password(new_Password);
            result = adminDao.UpdateSave(admin);
            if (result == 1) {
                out.println("<script language='javascript'>alert('修改成功,去重新登录');window.location='Login.jsp';</script>");

            }
        } else {
            out.println("<script language='javascript'>alert('请重新填写,原密码输入错误');window.location='PasswordUpdate.jsp';</script>");
        }
    }

    /**
     * doLogin:登录校证
     * * 用户登录：
     * *  1.使用httpSession（未实施）
     * *  2.把登录校证的方法封装到AdminDao
     * *  3.获取前端Login.jsp数据，调用CheckLogin方法
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void doLogin(HttpServletRequest request, HttpServletResponse response, String path)
            throws ServletException, IOException {
        Cookie[] cookies = request.getCookies();
        String id = null;
        String Username, Password;
        AdminDao dao = new AdminDao();
        Boolean result = false;
        //1.调用请求对象对请求体使用utf-8字符集进行重新编辑
        request.setCharacterEncoding("utf-8");
        //2.调用请求对象读取请求体参数信息
        Username = request.getParameter("Username");
        Password = request.getParameter("Password");
        //3.调用Dao将查询验证信息推送到数据库服务器上
        id = dao.CheckLogin(Username, Password);
        if (id != null) {//用户存在
            //存入Cookies
            //获取session对象
            HttpSession session = request.getSession();
            session.setAttribute("Admin_ID", id);
            session.setAttribute("username", Username);
            session.setAttribute("password", Password);

            response.sendRedirect(path + "/Index.jsp");
        } else {
            //返回重新登录
            response.sendRedirect(path + "/Login.jsp");
        }
    }

    /**
     * 退出：
     * 销毁session
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void doQuit(HttpServletRequest request, HttpServletResponse response, String path) throws ServletException, IOException {

        //获取session，销毁session
        HttpSession session = request.getSession(false);
        if (session != null) {
            //手动销毁
            session.invalidate();
            //跳转登录页面
            response.sendRedirect(path + "/Login.jsp");
        }
    }
}
