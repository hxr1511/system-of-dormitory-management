package com.gyk.controller;

import com.gyk.Dao.BuildingDao;
import com.gyk.bean.BuildingBean;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet({"/BuildingManager", "/BuildingUpdateSave", "/BuildingAddSave", "/BuildingDel"})
public class BuildingManagerServlet extends HttpServlet {
    PrintWriter out;

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String path = request.getContextPath();
        String servletPath = request.getServletPath();
        // ������룬����ҳ�����
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        out = response.getWriter();
        if ("/BuildingManager".equals(servletPath)) {
            doList(request, response, path);
        } else if ("/BuildingUpdateSave".equals(servletPath)) {
            doUpdateSave(request, response, path);
        } else if ("/BuildingAddSave".equals(servletPath)) {
            doAddSave(request, response, path);
        } else if ("/BuildingDel".equals(servletPath)) {
            doDel(request, response, path);
        }
    }

    /**
     * ɾ��¥��
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
        Integer Building_ID = Integer.valueOf(request.getParameter("Building_ID"));
        result = new BuildingDao().Delete(Building_ID);
        if (result == 1) {
//            response.sendRedirect(path+"/BuildingManager");
            out.println("<script language='javascript'>alert('ɾ���ɹ���');window.location='" +
                    path + "/BuildingManager';</script>");

        }
    }

    /**
     * ���¥�� doAddSave()
     * ����building.AddSave()
     *
     * @param request
     * @param response
     */
    private void doAddSave(HttpServletRequest request, HttpServletResponse response, String path)
            throws ServletException, IOException {
        int result = 0;
        String Building_Name = request.getParameter("Building_Name");
        String Building_Introduction = request.getParameter("Building_Introduction");
        BuildingBean building = new BuildingBean(Building_Name, Building_Introduction);
        result = new BuildingDao().AddSave(building);
        if (result == 1) {
            out.println("<script language='javascript'>alert('��ӳɹ���');window.location='" +
                    path + "/BuildingManager';</script>");
        }
    }

    /**
     * ��������¥��Ϣ  doUpdateSave
     * ����BuildingDao:UpdateSave()
     *
     * @param request
     * @param response
     * @param path
     */
    private void doUpdateSave(HttpServletRequest request, HttpServletResponse response, String path)
            throws ServletException, IOException {
        int result = 0;
        int Building_ID = Integer.parseInt(request.getParameter("Building_ID"));
        String Building_Name = request.getParameter("Building_Name");
        String Building_Introduction = request.getParameter("Building_Introduction");
        BuildingBean building = new BuildingBean(Building_ID, Building_Name, Building_Introduction);
        result = new BuildingDao().UpdateSave(building);
        if (result == 1) {
            out.println("<script language='javascript'>alert('���³ɹ���');window.location='" +
                    path + "/BuildingManager';</script>");
        }

    }

    /**
     * ��ʾ������Ϣ
     *
     * @param request
     * @param response
     * @param path
     * @throws ServletException
     * @throws IOException
     */
    private void doList(HttpServletRequest request, HttpServletResponse response, String path)
            throws ServletException, IOException {
        List<BuildingBean> buildingList;
        String strwhere = "where 1=1";
        int key = 0;
        String SearchKey = request.getParameter("SearchKey");
        if (!isInvalid(SearchKey)) {//�����ȡ�ǿ�
            key = 1;
            strwhere += " AND Building_Name='" + SearchKey + "'";
        }
        buildingList = new BuildingDao().GetList(key, strwhere);
//        System.out.println(buildingList.toString());
        request.setAttribute("buildingList", buildingList);
        request.getRequestDispatcher("BuildingManager.jsp").forward(request, response);
    }

    //    �ж�����Ϊ�գ�ȷ���Ƿ���Ҫ����
    private boolean isInvalid(String value) {
        return (value == null || value.length() == 0);
    }

}
