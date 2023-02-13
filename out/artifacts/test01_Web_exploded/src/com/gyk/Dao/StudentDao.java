package com.gyk.Dao;

import com.gyk.bean.StudentBean;
import com.gyk.bean.TeacherBean;
import com.gyk.utils.DBUtil;

import javax.servlet.http.HttpServletRequest;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDao {
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public List<StudentBean> GetList(int key, String strwhere) {
        List<StudentBean> studentList = new ArrayList<>();
        String getList_sql = "select * from `student`";
        try {
            conn = DBUtil.getConnection();
            if (key == 1){//判断是否为输入查询
                getList_sql+=strwhere;
            }
            //编译sql
            ps= conn.prepareStatement(getList_sql);
            rs = ps.executeQuery();
            while (rs.next()){
                StudentBean student = new StudentBean();
                student.setStudent_ID(rs.getInt("Student_ID"));
                student.setStudent_DomitoryID(rs.getInt("Student_DomitoryID"));
                student.setStudent_Username(rs.getString("Student_Username"));
                student.setStudent_Password(rs.getString("Student_Password"));
                student.setStudent_Name(rs.getString("Student_Name"));
                student.setStudent_Sex(rs.getString("Student_Sex"));
                student.setStudent_Class(rs.getString("Student_Class"));
                student.setStudent_State(rs.getString("Student_State"));
                studentList.add(student);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn,ps,rs);
        }
        return studentList;
    }

    /**
     * 根据id查学生信息
     * @param Student_Id
     * @return
     */
    public StudentBean GetStudentId(Integer Student_Id) {
        StudentBean student=new StudentBean();
        try {
            String selectId_student_sql =
                    "SELECT *FROM `student`where Student_ID=?";
            conn = DBUtil.getConnection();
            ps = conn.prepareStatement(selectId_student_sql);
            ps.setInt(1,Student_Id);

            rs = ps.executeQuery();
            while (rs.next()){
                try {
                    student.setStudent_ID(Integer.parseInt(rs.getString("Student_ID")));
                    student.setStudent_Username(rs.getString("Student_Username"));
                    student.setStudent_DomitoryID(rs.getInt("Student_DomitoryID"));
                    student.setStudent_Password(rs.getString("Student_Password"));
                    student.setStudent_Name(rs.getString("Student_Name"));
                    student.setStudent_Sex(rs.getString("Student_Sex"));
                    student.setStudent_Class(rs.getString("Student_Class"));
                    student.setStudent_State(rs.getString("Student_State"));
                    student.setDomitory_Name(rs.getString("Domitory_Name"));
                    student.setBuilding_Name(rs.getString("Building_Name"));

                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn,ps,rs);
        }
        return student;
    }



    /**
     * 添加
     * @param student
     * @return
     */
    public int AddSave(StudentBean student) {
        int result = 0;
        try {
            conn = DBUtil.getConnection();
            String add_student_sql =
                    "insert into student(Student_Username,Student_Password,Student_Name,Student_Sex,Student_Class) values(?,?,?,?,?)";
            //编译sql
            ps = conn.prepareStatement(add_student_sql);
            ps.setString(1,student.getStudent_Username());
            ps.setString(2,student.getStudent_Password());
            ps.setString(3,student.getStudent_Name());
            ps.setString(4,student.getStudent_Sex());
            ps.setString(5,student.getStudent_Class());
            result=ps.executeUpdate();
            if (result==1){
                System.out.println("success");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn,ps,rs);
        }
        return result;

    }

    /**
     * 删除学生信息
     * @param Student_Id
     * @return
     */
    public int Delete(Integer Student_Id) {
        int result = 0;
        try {
            conn = DBUtil.getConnection();
            String delete_teacher_sql = "delete from student where Student_ID=?";
            ps = conn.prepareStatement(delete_teacher_sql);
            ps.setInt(1,Student_Id);
            result = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn,ps,rs);
        }
        return result;
    }

    /**
     * 更新修改学生信息
     * @param student
     * @return
     */
    public int UpdateSave(StudentBean student) {
        int result = 0;
        try {
            conn = DBUtil.getConnection();
            String update_student_sql = "UPDATE `student` SET `Student_DomitoryID`=?,`Student_Username`=?,`Student_Password`=?,`Student_Name`=?,`Student_Sex`=?,`Student_Class`=?,`Student_State`=? WHERE `Student_ID`=?";
            ps = conn.prepareStatement(update_student_sql);
            ps.setInt   (1,student.getStudent_DomitoryID());
            ps.setString(2,student.getStudent_Username());
            ps.setString(3,student.getStudent_Password());
            ps.setString(4,student.getStudent_Name());
            ps.setString(5,student.getStudent_Sex());
            ps.setString(6,student.getStudent_Class());
            ps.setString(7,student.getStudent_State());
            ps.setInt   (8,student.getStudent_ID());
            result=ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn,ps,rs);
        }
        return result;
    }


    /**
     * 根据其他条件查询信息
     */
    public StudentBean GetfactorBean(String factor, String student_factorvalue) {
        StudentBean student=new StudentBean();
        Statement stat = null;
        try {
            conn = DBUtil.getConnection();
            stat=conn.createStatement();
            String get_factor_sql ="SELECT*FROM `student` WHERE 1=1 and "+factor+student_factorvalue;

            rs = stat.executeQuery(get_factor_sql);
            System.out.println(rs);
            while (rs.next()){
                student.setStudent_ID(rs.getInt("Student_ID"));
                student.setStudent_DomitoryID(rs.getInt("Student_DomitoryID"));
                student.setStudent_Username(rs.getString("Student_Username"));
                student.setStudent_Password(rs.getString("Student_Password"));
                student.setStudent_Name(rs.getString("Student_Name"));
                student.setStudent_Sex(rs.getString("Student_Sex"));
                student.setStudent_Class(rs.getString("Student_Class"));
                student.setStudent_State(rs.getString("Student_State"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn,stat,rs);
        }
        return student;
    }
}
