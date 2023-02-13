package com.gyk.controller;

import com.gyk.Dao.AdminDao;
import com.gyk.utils.DBUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet("/Welcome")
public class WelcomeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getContextPath();
        AdminDao dao = new AdminDao();
        Cookie[] cookies = request.getCookies();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Boolean success = false;
        String username = null;
        String password = null;
        if (cookies != null){
            for (Cookie c:cookies) {
                String name = c.getName();
                if ("username".equals(name)){
                    username = c.getValue();
                }else if ("password".equals(name)){
                    password = c.getValue();
                }
            }
            if (username!=null && password!=null){
                //验证是否正确
                try {
                    conn = DBUtil.getConnection();
//                    success=dao.CheckLogin(username,password);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                } finally {
                    DBUtil.close(conn,ps,rs);
                }
                if (success){
                    //登录成功
                    response.sendRedirect(path+"/Index.jsp");
                }else {
                    //跳转到登录页面
                    response.sendRedirect(path+"/Login.jsp");
                }

            }else {
                //跳转到登录页面
                response.sendRedirect(path+"/Login.jsp");
            }
        }
    }
}
