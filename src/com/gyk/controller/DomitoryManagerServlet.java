package com.gyk.controller;

import com.gyk.Dao.BuildingDao;
import com.gyk.Dao.DomitoryDao;
import com.gyk.bean.DomitoryBean;
import com.gyk.bean.StudentBean;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet({"/DomitoryManager", "/DomitoryDel", "/DomitoryUpdateSave", "/DomitoryAddSave"})
public class DomitoryManagerServlet extends HttpServlet {
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
        if ("/DomitoryManager".equals(servletPath)) {
            doList(request, response, path);
        } else if ("/DomitoryUpdateSave".equals(servletPath)) {
            doUpdate(request, response, path);
        } else if ("/DomitoryAddSave".equals(servletPath)) {
            doAddSave(request, response, path);
        } else if ("/DomitoryDel".equals(servletPath)) {
            doDel(request, response, path);
        }
    }

    /**
     * 删除学生
     *
     * @param request
     * @param response
     * @param path
     */
    private void doDel(HttpServletRequest request, HttpServletResponse response, String path)
            throws ServletException, IOException {
        int result = 0;
        Integer Domitory_ID = Integer.valueOf(request.getParameter("Domitory_ID"));
        result = new DomitoryDao().Delete(Domitory_ID);
        if (result == 1) {
            response.sendRedirect(path + "/DomitoryManager");
        }
    }

    /**
     * 添加宿舍
     *
     * @param request
     * @param response
     * @param path
     */
    private void doAddSave(HttpServletRequest request, HttpServletResponse response, String path)
            throws ServletException, IOException {
        int result = 0;
        DomitoryBean domitory = new DomitoryBean();
//        Integer Domitory_ID = Integer.parseInt(request.getParameter("Domitory_ID"));
        domitory.setDomitory_BuildingID(Integer.parseInt(request.getParameter("Domitory_BuildingID")));
        domitory.setDomitory_Name(request.getParameter("Domitory_Name"));
        domitory.setDomitory_Type(request.getParameter("Domitory_Type"));
        domitory.setDomitory_Number(request.getParameter("Domitory_Number"));
        result = new DomitoryDao().AddSave(domitory);

        if (result == 1) {
            out.print("<script language='javascript'>alert('操作成功！');window.location='" +
                    path + "/DomitoryManager';</script>");
            out.flush();
            out.close();
        }
    }

    /**
     * 跟新宿舍数据
     *
     * @param request
     * @param response
     * @param path
     */
    private void doUpdate(HttpServletRequest request, HttpServletResponse response, String path)
            throws ServletException, IOException {
        int result = 0;
        Integer Domitory_ID = Integer.parseInt(request.getParameter("Domitory_ID"));
        Integer Domitory_BuildingID = Integer.parseInt(request.getParameter("Domitory_BuildingID"));
        String Domitory_Name = request.getParameter("Domitory_Name");
        String Domitory_Type = request.getParameter("Domitory_Type");
        String Domitory_Number = request.getParameter("Domitory_Number");
        String Domitory_BuildingName = new BuildingDao().GetBuildingId(Domitory_BuildingID).getBuilding_Name();
        DomitoryBean domitory = new DomitoryBean(Domitory_ID, Domitory_BuildingID, Domitory_Name, Domitory_Type, Domitory_Number, Domitory_BuildingName);
        result = new DomitoryDao().UpdateSave(domitory);
        if (result == 1) {
            response.sendRedirect(path + "/DomitoryManager");
        }


    }

    /**
     * 显示列表 查询
     *
     * @param request
     * @param response
     * @param path
     */
    private void doList(HttpServletRequest request, HttpServletResponse response, String path)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        out = response.getWriter();
        String strwhere = " AND 1=1";
        int key = 0;
        String SearchKey = request.getParameter("SearchKey");
        String SearchRow = request.getParameter("SearchRow");
        String Domitory_BuildingID = request.getParameter("Domitory_BuildingID");
        if (!isInvalid(SearchKey) || !isInvalid(Domitory_BuildingID)) {
            key = 1;
            System.out.println("SearchKey:" + SearchKey + ",SearchRow:" + SearchRow + ",Domitory_BuildingID:" + Domitory_BuildingID);
            if (!isInvalid(Domitory_BuildingID)) {
                strwhere += " AND `Domitory_BuildingID` = '" + Domitory_BuildingID + "'";
            }
            if (!isInvalid(SearchKey)) {
                strwhere += " AND `" + SearchRow + "` = '" + SearchKey + "'";
            }
        }
        List<DomitoryBean> domitoryList = new DomitoryDao().GetList(key, strwhere);
//        System.out.println(domitoryList.toString());
        request.setAttribute("domitorylist", domitoryList);
        request.getRequestDispatcher("DomitoryManager.jsp").forward(request, response);
    }

    //    判断属性为空，确定是否需要传参
    private boolean isInvalid(String value) {
        return (value == null || value.length() == 0);
    }
}
