package com.gyk.controller;

import com.gyk.Dao.TBDao;
import com.gyk.Dao.TeacherDao;
import com.gyk.bean.TBBean;
import com.gyk.bean.TeacherBean;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@WebServlet({"/TBManager", "/TBDel", "/TBAddSave"})
public class TBManagerServlet extends HttpServlet {
    PrintWriter out;

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getContextPath();
        String servletPath = request.getServletPath();
        // ������룬����ҳ�����
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        out = response.getWriter();
        if ("/TBManager".equals(servletPath)) {
            doList(request, response, path);
        } else if ("/TBDel".equals(servletPath)) {
            doDel(request, response, path);
        } else if ("/TBAddSave".equals(servletPath)) {
            doAddSave(request, response, path);
        }
    }

    /**
     * ���
     *
     * @param request
     * @param response
     * @param path
     */
    private void doAddSave(HttpServletRequest request, HttpServletResponse response, String path)
            throws ServletException, IOException {
        int result = 0;

        String TB_TeacherID = request.getParameter("TB_TeacherID");
        String TB_BuildingID = request.getParameter("TB_BuildingID");
        //�ж��Ƿ�Ϊ��
        if (!(isInvalid(TB_TeacherID))) {
            //��ѯ�Ƿ��Ѿ��ж���
            String strwhere = "AND `TB_BuildingID`=" + TB_BuildingID + " AND `TB_TeacherID`=" + TB_TeacherID;
            List<TBBean> list = new TBDao().GetList(1, strwhere);

            if (list.size() == 0) {//�����
                result = new TBDao().AddSave(TB_TeacherID, TB_BuildingID);
                if (result == 1) {
                    out.print("<script language='javascript'>alert('��ӳɹ���');window.location='" +
                            path + "//TBManager?Building_ID=" + TB_BuildingID +
                            "';</script>");

                }
            } else {//�и���Ϣ
//                out.println("<script language='javascript'>alert('����ӹ��ý�ʦ��Ϣ');</script>");
                out.print("<script language='javascript'>alert('����ӸĽ�ʦΪ����Ա��');window.location='" +
                        path + "//TBManager?Building_ID=" + TB_BuildingID +
                        "';</script>");
                out.flush();
                out.close();

            }
        } else {
            out.println("<script language='javascript'>alert('δ��ӡ�����ѡ��');</script>");

        }

    }

    /**
     * ɾ��
     *
     * @param request
     * @param response
     * @param path
     */
    private void doDel(HttpServletRequest request, HttpServletResponse response, String path)
            throws ServletException, IOException {
        Integer TB_ID = Integer.valueOf(request.getParameter("TB_ID"));
        String TB_TeacherID = request.getParameter("TB_TeacherID");
        String Building_ID = request.getParameter("Building_ID");
        int result = 0;
        result = new TBDao().Delete(TB_ID);

        if (result == 1) {
            out.print("<script language='javascript'>alert('ɾ���ɹ���');window.location='" +
                    path + "//TBManager?Building_ID=" + Building_ID +
                    "';</script>");
        }
    }

    /**
     * ��ȡ�б� ������ѯɸѡ
     *
     * @param request
     * @param response
     * @param path
     * @throws ServletException
     * @throws IOException
     */
    private void doList(HttpServletRequest request, HttpServletResponse response, String path)
            throws ServletException, IOException {
        int result = 0;
        int key = 0;
        String strwhere = " and 1=1 ";
        List<TBBean> tblist = null;
        String TB_BuildingID = request.getParameter("Building_ID");
        if (!isInvalid(TB_BuildingID)) {
            key = 1;
            strwhere += "and TB_BuildingID=" + TB_BuildingID;
            tblist = new TBDao().GetList(key, strwhere);
            if (tblist != null) {
                System.out.println("tblist ��ȡ�ɹ�");
                System.out.println("tblist:" + tblist);
                request.setAttribute("tblist", tblist);
                List<TeacherBean> teacherList = new TeacherDao().GetList(0, "");
                if (teacherList != null) {
                    request.setAttribute("teacherList", teacherList);
                }
                request.getRequestDispatcher("/TBManager.jsp").forward(request, response);
            }
        } else {
            out.println("<script language='javascript'>alert('��ȡʧ�ܣ��뷵������');window.location='/BuildingManager';</script>");
        }

    }

    // �ж��Ƿ��ֵ
    private boolean isInvalid(String value) {
        return (value == null || value.length() == 0);
    }

}
