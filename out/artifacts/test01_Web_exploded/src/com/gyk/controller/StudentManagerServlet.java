package com.gyk.controller;

import com.gyk.Dao.StudentDao;
import com.gyk.bean.StudentBean;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet({"/StudentManager","/StudentAddSave","/StudentDel","/StudentUpdateSave"})
public class StudentManagerServlet extends HttpServlet {
    PrintWriter out;
    StudentDao studentDao = new StudentDao();
    Integer Student_ID,Student_DomitoryID=null;
    String Student_Username,Student_Password,Student_Name,Student_Sex,Student_Class,Student_State,Domitory_Name,Building_Name,Domitory_Type,Domitory_Number,Student_Tel=null;


    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String path = request.getContextPath();
        String servletPath = request.getServletPath();
        //解决乱码，用于页面输出
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("utf-8");
        out=response.getWriter();
        if ("/StudentManager".equals(servletPath)){
            doList(request,response,path);
        }else if ("/StudentAddSave".equals(servletPath)){
            doAddSave(request,response,path);
        }else if ("/StudentDel".equals(servletPath)){
            doDel(request,response,path);
        }else if ("/StudentUpdateSave".equals(servletPath)){
            doUpdateSave(request,response,path);
        }
    }

    /**
     * 更新学生信息：doUpdateSave(request,response,path);
     * 调用StudentDao.UpdateSave方法
     * @param request
     * @param response
     * @param path
     */
    private void doUpdateSave(HttpServletRequest request, HttpServletResponse response, String path)
            throws ServletException, IOException{
        int result = 0;
        StudentBean student = new StudentDao().GetStudentId(Integer.valueOf(request.getParameter("Student_ID")));
        if(!isInvalid(request.getParameter("Student_Password"))){//判断获取值为空-> 赋值
            student.setStudent_Password(request.getParameter("Student_Password"));
        }
        student.setStudent_ID( Integer.parseInt(request.getParameter("Student_ID")));
        student.setStudent_Username(request.getParameter("Student_Username"));
        student.setStudent_Name(request.getParameter("Student_Name"));
        student.setStudent_Sex(request.getParameter("Student_Sex"));
        student.setStudent_Class(request.getParameter("Student_Class"));

        result = studentDao.UpdateSave(student);
        if (result==1){//保存成功,返回
//            response.sendRedirect(path+"/StudentManager");
            out.print("<script language='javascript'>alert('更新操作成功！');window.location='" +
                    path+"/StudentManager';</script>");
        }else {
            out.print("<script language='javascript'>alert('更新操作失败，请重试或联系管理员！');window.location='" +
                    path+"/StudentManager';</script>");
        }
    }

    private void doDel(HttpServletRequest request, HttpServletResponse response, String path)
            throws ServletException, IOException{
        int result = 0;
        Student_ID = Integer.valueOf(request.getParameter("Student_ID"));
        result = studentDao.Delete(Student_ID);
        if (result ==1){
            out.print("<script language='javascript'>alert('删除成功！');window.location='" +
                    path+"/StudentManager';</script>");

        }
    }

    /**
     * 添加学生：doAddSave()
     * 调用Teacher.AddSave()
     * @param request
     * @param response
     * @param path
     * @throws ServletException
     * @throws IOException
     */
    private void doAddSave(HttpServletRequest request, HttpServletResponse response, String path)
            throws ServletException, IOException{
        StudentBean student=new StudentBean();
        int result=0;
        student.setStudent_Username(request.getParameter("Student_Username"));
        student.setStudent_Password(request.getParameter("Student_Password"));
        student.setStudent_Name(request.getParameter("Student_Name"));
        student.setStudent_Sex(request.getParameter("Student_Sex"));
        student.setStudent_Class(request.getParameter("Student_Class"));
        result = studentDao.AddSave(student);
        if (result==1){
            out.print("<script language='javascript'>alert('添加操作成功！');window.location='" +
                    path+"/StudentManager';</script>");
        }
    }

    /**
     * 显示student：doList()
     * 调用StudentDao.GetList()
     * @param request
     * @param response
     * @param path
     * @throws ServletException
     * @throws IOException
     */
    private void doList(HttpServletRequest request, HttpServletResponse response, String path)
            throws ServletException, IOException{

        out = response.getWriter();
        String strwhere = "where 1=1";
        int key = 0;
        String State = request.getParameter("State");
        String SearchKey = request.getParameter("SearchKey");
        String SearchRow = request.getParameter("SearchRow");
        if (SearchRow!=null) {
            if (!(isInvalid(SearchKey))) {
                strwhere += " and " + SearchRow + "='" + SearchKey + "'";
            }
            if (!(isInvalid(State))) {
                strwhere += " and " + "Student_State='" + State + "'";
            }
            key=1;
        }
        List<StudentBean> studentList = studentDao.GetList(key,strwhere);
//        System.out.println(studentList);
        request.setAttribute("studentlist",studentList);
        request.getRequestDispatcher("StudentManager.jsp").forward(request,response);
    }
    private boolean isInvalid(String value) {
        return (value == null || value.length() == 0);
    }

}

