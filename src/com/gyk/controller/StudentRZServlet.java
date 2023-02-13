package com.gyk.controller;

import com.gyk.Dao.BuildingDao;
import com.gyk.Dao.DomitoryDao;
import com.gyk.Dao.Stu_StatusDao;
import com.gyk.Dao.StudentDao;
import com.gyk.bean.BuildingBean;
import com.gyk.bean.DomitoryBean;
import com.gyk.bean.StudentBean;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet({"/StudentRZ", "/StudentRZSave", "/StudentTHSave", "/StudentTH", "/StudentQC", "/StudentQCSave"})
public class StudentRZServlet extends HttpServlet {
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
        if ("/StudentRZ".equals(servletPath)) {
            doList(request, response, path);
        }
        if ("/StudentRZSave".equals(servletPath)) {
            doAdd(request, response, path);
        }
        if ("/StudentTH".equals(servletPath)) {
            doTHshow(request, response, path);
        }
        if ("/StudentTHSave".equals(servletPath)) {
            doTHSave(request, response, path);
        }
        if ("/StudentQC".equals(servletPath)) {
            doQCshow(request, response, path);
        }
        if ("/StudentQCSave".equals(servletPath)) {
            doQCSave(request, response, path);
        }
    }

    private void doQCSave(HttpServletRequest request, HttpServletResponse response, String path)
    {
        StudentBean student = new StudentBean();
        String Student_ID = request.getParameter("Student_ID");
        student = new Stu_StatusDao().GetStuAllMsg("Student_ID=", Student_ID);
        student.setStudent_State("Ǩ��");
        int result = new Stu_StatusDao().UpdateStuMsg(student);
        if (result == 1) {
            out.print("<script language='javascript'>alert('Ǩ�������ɹ���');window.location='" + path +
                    "/StudentTH.jsp';</script>");
            out.flush();
            out.close();
        } else {
            out.print("<script language='javascript'>alert('Ǩ������ʧ�ܣ�');window.location='" + path +
                    "/StudentTH.jsp';</script>");
            out.flush();
            out.close();
        }
    }


    private void doTHSave(HttpServletRequest request, HttpServletResponse response, String path) {
        StudentBean student = new StudentBean();
        String Building_ID = request.getParameter("Building_ID");
        String Domitory_ID = request.getParameter("Domitory_ID");
        String Student_ID = request.getParameter("Student_ID");


        //�ж�¥�����޸�����
        String strwhere = " and Domitory_ID=" + Domitory_ID + " and Domitory_BuildingID=" + Building_ID;
        List<DomitoryBean> domitorylist = new DomitoryDao().GetList(1, strwhere);

        if (domitorylist.size() < 1) {
            out.print("<script language='javascript'>alert('¥���޸�����');history.back(-1);</script>");
            out.flush();
            out.close();
        } else {
            int result = 0;
            student = new Stu_StatusDao().GetStuAllMsg("Student_ID=", Student_ID);
            student.setStudent_BuildingID(Integer.parseInt(Building_ID));
            student.setStudent_DomitoryID(Integer.parseInt(Domitory_ID));
            result = new Stu_StatusDao().UpdateStuMsg(student);
            if (result == 1) {
                out.print("<script language='javascript'>alert('���ҵ��������ɹ���');window.location='" + path +
                        "/StudentTH.jsp';</script>");
                out.flush();
                out.close();
            } else {
                out.print("<script language='javascript'>alert('���ҵ�������ʧ�ܣ�');window.location='" + path +
                        "/StudentTH.jsp';</script>");
                out.flush();
                out.close();
            }
        }
    }

    /**
     * Ǩ����ʾ
     * @param request
     * @param response
     * @param path
     * @throws ServletException
     * @throws IOException
     */
    private void doQCshow(HttpServletRequest request, HttpServletResponse response, String path) throws ServletException, IOException {
        String Student_Username = request.getParameter("Student_Username");
        //�ж�ѧ���Ƿ����
        StudentBean student = new Stu_StatusDao().GetStuAllMsg("Student_Username=", Student_Username);
        if (isInvalid(String.valueOf(student))) {
            out.print("<script language='javascript'>alert('ѧ�Ų����ڣ���ѧ��δ������ס״̬��');history.back(-1);</script>");
            out.flush();
            out.close();
        } else {
            request.setAttribute("student", student);
            request.getRequestDispatcher("/StudentQC2.jsp").forward(request, response);
        }
    }

    /**
     * ������ʾ��Ϣ
     *
     * @param request
     * @param response
     * @param path
     */
    private void doTHshow(HttpServletRequest request, HttpServletResponse response, String path)
            throws ServletException, IOException {
        String Student_Username = request.getParameter("Student_Username");
        String BuildingID = request.getParameter("BuildingID");
        List<BuildingBean> buildinglist = new ArrayList<>();
        //�ж�ѧ���Ƿ����
        StudentBean student = new Stu_StatusDao().GetStuAllMsg("Student_Username=", Student_Username);
        if (isInvalid(String.valueOf(student))||student.getStudent_ID()==0) {
            out.print("<script language='javascript'>alert('ѧ�Ų����ڣ���ѧ��δ������ס״̬��');history.back(-1);</script>");
            out.flush();
            out.close();
        }
        if (!isInvalid(BuildingID)) {
            String strwhere = "and Building_ID=" + BuildingID;
            List<DomitoryBean> DomitoryList = new DomitoryDao().GetList(1, strwhere);
            request.setAttribute("DomitoryList", DomitoryList);
//            String strwhereBuild=" where Building_ID="+BuildingID;
//            buildinglist = new BuildingDao().GetList(1,strwhereBuild);
        } else {
            // ��ѯ¥��
//            buildinglist = new BuildingDao().GetList(0, "");
        }
        buildinglist = new BuildingDao().GetList(0, "");
        request.setAttribute("buildinglist", buildinglist);
        List<DomitoryBean> DomitoryList = new DomitoryDao().GetList(0, "");
        request.setAttribute("DomitoryList1", DomitoryList);
        request.setAttribute("student", student);
//        System.out.println("�ɹ�");
        request.getRequestDispatcher("/StudentTH2.jsp").forward(request, response);
    }

    /**
     * �����ס
     *
     * @param request
     * @param response
     * @param path
     */
    private Object doAdd(HttpServletRequest request, HttpServletResponse response, String path)
            throws ServletException, IOException {
        String Student_Username = request.getParameter("Student_Username");
        String Domitory_ID = request.getParameter("Domitory_ID");
        String Building_ID = request.getParameter("Building_ID");

        //�ж�¥�����޸�����
        String strwhere = " and Domitory_ID=" + Domitory_ID + " and Domitory_BuildingID=" + Building_ID;
        List<DomitoryBean> domitorylist = new DomitoryDao().GetList(1, strwhere);
        //�ж�ѧ���Ƿ����
        StudentBean student = new StudentDao().GetfactorBean("Student_Username=", Student_Username);
        if (student == null) {
            out.print("<script language='javascript'>alert('ѧ�Ų����ڣ�');history.back(-1);</script>");
            out.flush();
            out.close();
            return null;
        } else {//�ж�ѧ����ס״̬
            if (!(student.getStudent_State().equals("δ��ס"))) {
                out.print("<script language='javascript'>alert('��ѧ������" + student.getStudent_State()
                        + "״̬����ֹ��ס������');history.back(-1);</script>");
                out.flush();
                out.close();
                return null;
            } else {//�ж�ѡ������
                if (domitorylist.size() < 1) {
                    out.print("<script language='javascript'>alert('¥���޸�����');history.back(-1);</script>");
                    out.flush();
                    out.close();
                    return null;
                }
            }
        }
        //�޸�״̬ ����

        student.setStudent_DomitoryID(Integer.parseInt(Domitory_ID));
        student.setBuilding_Name(new BuildingDao().GetBuildingId(Integer.parseInt(Building_ID)).getBuilding_Name());
        student.setStudent_State("��ס");

        int result = 0;
        result = new StudentDao().UpdateSave(student);
        if (result == 1) {
            out.print("<script language='javascript'>alert('��ס�����ɹ���');window.location='" +
                    path + "/StudentRZ';</script>");
            out.flush();
            out.close();
        }
        return null;
    }

    /**
     * ����������ѯ��ʾ����Buildinglist domitoryList
     * ����·����StudentRZ
     *
     * @param request
     * @param response
     * @param path
     */
    private void doList(HttpServletRequest request, HttpServletResponse response, String path)
            throws ServletException, IOException {
        //��ȡ����Buiding
        List<BuildingBean> buildingList = new BuildingDao().GetList(0, "");
        List<DomitoryBean> domitoryList = null;
        //�������ɸѡbuiding��Ӧ������list
        int key = 0;
        System.out.println("buildinglist:" + buildingList);
        String strwhere = " AND 1=1";
        String Building_ID = request.getParameter("Building_ID");
        System.out.println("BUilding_id:" + Building_ID);
        if (!isInvalid(Building_ID)) {
            key = 1;
            strwhere += " AND `Building_ID`='" + Building_ID + "'";
        }
        domitoryList = new DomitoryDao().GetList(key, strwhere);
        System.out.println(domitoryList);

        request.setAttribute("domitoryList", domitoryList);
        request.setAttribute("buildingList", buildingList);

        request.getRequestDispatcher("StudentRZ.jsp").forward(request, response);
    }


    // �ж��Ƿ��ֵ
    private boolean isInvalid(String value) {
        return (value == null || value.length() == 0);
    }

}
