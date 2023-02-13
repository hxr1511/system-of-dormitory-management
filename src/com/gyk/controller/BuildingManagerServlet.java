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
        // 解决乱码，用于页面输出
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
     * 删除楼栋
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
            out.println("<script language='javascript'>alert('删除成功！');window.location='" +
                    path + "/BuildingManager';</script>");

        }
    }

    /**
     * 添加楼栋 doAddSave()
     * 调用building.AddSave()
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
            out.println("<script language='javascript'>alert('添加成功！');window.location='" +
                    path + "/BuildingManager';</script>");
        }
    }

    /**
     * 更新宿舍楼信息  doUpdateSave
     * 调用BuildingDao:UpdateSave()
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
            out.println("<script language='javascript'>alert('更新成功！');window.location='" +
                    path + "/BuildingManager';</script>");
        }

    }

    /**
     * 显示所有信息
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
        if (!isInvalid(SearchKey)) {//如果获取非空
            key = 1;
            strwhere += " AND Building_Name='" + SearchKey + "'";
        }
        buildingList = new BuildingDao().GetList(key, strwhere);
//        System.out.println(buildingList.toString());
        request.setAttribute("buildingList", buildingList);
        request.getRequestDispatcher("BuildingManager.jsp").forward(request, response);
    }

    //    判断属性为空，确定是否需要传参
    private boolean isInvalid(String value) {
        return (value == null || value.length() == 0);
    }

}
