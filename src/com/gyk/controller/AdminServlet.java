package com.gyk.controller;
/**
 * Admin����
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
     * servlet���������������͵��÷������÷���Ĭ����Ϊ��ת��http����doXXX������
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
     * �޸Ĺ���Ա����
     *
     * @param request
     * @param response
     * @param path
     * @return
     */
    private void doPasswordUpdate(HttpServletRequest request, HttpServletResponse response, String path)
            throws ServletException, IOException {
        int result = 0;
        // ������룬����ҳ�����
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("utf-8");
        HttpSession session1 = request.getSession();
        AdminDao adminDao = new AdminDao();
        out = response.getWriter();

        String old_Password = request.getParameter("Password");
        String new_Password = request.getParameter("Password2");
        String Admin_ID = (String) session1.getAttribute("Admin_ID");
        System.out.println("old_Password:" + old_Password + ",Admin_ID:" + Admin_ID);//�жϻ�ȡ�Ƿ�ɹ�

        //��֤���ݿ����������ԭ�����Ƿ��ظ�
        if (adminDao.CheckPassword(Integer.valueOf(Admin_ID), old_Password)) {//��ȷִ��if
            System.out.println("��֤���ݿ����������ԭ����һ��");
            //�޸�����
            admin = adminDao.GetId(Integer.valueOf(Admin_ID));
            System.out.println(admin.toString());
            admin.setAdmin_Password(new_Password);
            result = adminDao.UpdateSave(admin);
            if (result == 1) {
                out.println("<script language='javascript'>alert('�޸ĳɹ�,ȥ���µ�¼');window.location='Login.jsp';</script>");

            }
        } else {
            out.println("<script language='javascript'>alert('��������д,ԭ�����������');window.location='PasswordUpdate.jsp';</script>");
        }
    }

    /**
     * doLogin:��¼У֤
     * * �û���¼��
     * *  1.ʹ��httpSession��δʵʩ��
     * *  2.�ѵ�¼У֤�ķ�����װ��AdminDao
     * *  3.��ȡǰ��Login.jsp���ݣ�����CheckLogin����
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
        //1.������������������ʹ��utf-8�ַ����������±༭
        request.setCharacterEncoding("utf-8");
        //2.������������ȡ�����������Ϣ
        Username = request.getParameter("Username");
        Password = request.getParameter("Password");
        //3.����Dao����ѯ��֤��Ϣ���͵����ݿ��������
        id = dao.CheckLogin(Username, Password);
        if (id != null) {//�û�����
            //����Cookies
            //��ȡsession����
            HttpSession session = request.getSession();
            session.setAttribute("Admin_ID", id);
            session.setAttribute("username", Username);
            session.setAttribute("password", Password);

            response.sendRedirect(path + "/Index.jsp");
        } else {
            //�������µ�¼
            response.sendRedirect(path + "/Login.jsp");
        }
    }

    /**
     * �˳���
     * ����session
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void doQuit(HttpServletRequest request, HttpServletResponse response, String path) throws ServletException, IOException {

        //��ȡsession������session
        HttpSession session = request.getSession(false);
        if (session != null) {
            //�ֶ�����
            session.invalidate();
            //��ת��¼ҳ��
            response.sendRedirect(path + "/Login.jsp");
        }
    }
}
