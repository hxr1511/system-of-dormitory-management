package com.gyk.Dao;

import com.gyk.bean.TeacherBean;
import com.gyk.utils.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 教师类方法：
 * List<TeacherBean> GetList()
 */
public class TeacherDao {

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    /**
     *获取教师列表
     */
//    public List<TeacherBean> GetList(String strwhere, String strorder){
    public List<TeacherBean> GetList(int key,String strwhere){
        List<TeacherBean> Teacherlist = new ArrayList<TeacherBean>();
        String getList_sql = "select * from `teacher` ";

        try {
            conn = DBUtil.getConnection();
            if (key ==1){
                    getList_sql +=strwhere;
            }
            //编译sql
            ps = conn.prepareStatement(getList_sql);
            //给？传值
            rs = ps.executeQuery();
            while (rs.next()){
                Integer Teacher_ID = rs.getInt("Teacher_ID");
                String Teacher_Username = rs.getString("Teacher_Username");
                String Teacher_Password = rs.getString("Teacher_Password");
                String Teacher_Name = rs.getString("Teacher_Name");
                String Teacher_Sex = rs.getString("Teacher_Sex");
                String Teacher_Tel = rs.getString("Teacher_Tel");
                TeacherBean teacher = new TeacherBean(Teacher_ID,Teacher_Username,Teacher_Password,Teacher_Name,Teacher_Sex,Teacher_Tel);
                Teacherlist.add(teacher);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn,ps,rs);
        }
//        System.out.println(Teacherlist);

        return Teacherlist;
    }

    /**
     * 更新用户信息
     * @param teacher
     * @return
     */
    public int UpdateSave(TeacherBean teacher){
        int result=0;
        try {
            conn = DBUtil.getConnection();
            String update_teacher_sql = "UPDATE teacher SET Teacher_Username=?,Teacher_Password=?,Teacher_Name=?,Teacher_Sex=?,Teacher_Tel=? WHERE Teacher_ID=?";
            ps = conn.prepareStatement(update_teacher_sql);

            ps.setString(1,teacher.getTeacher_Username());
//            if (!(isInvalid(teacher.getTeacher_Password()))){
                ps.setString(2,teacher.getTeacher_Password());
//            }
            ps.setString(3,teacher.getTeacher_Name());
            ps.setString(4,teacher.getTeacher_Sex());
            ps.setString(5,teacher.getTeacher_Tel());
            ps.setInt(6,teacher.getTeacher_ID());

            result = ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn,ps,rs);
        }
        return result;
    }

    /**
     * 添加教师信息
     */
    public int AddSave(TeacherBean teacher) {
        int result = 0;
        try {
            conn = DBUtil.getConnection();
            String add_teacher_sql =
        "insert into teacher(Teacher_Username,Teacher_Password,Teacher_Name,Teacher_Sex,Teacher_Tel) values (?,?,?,?,?)";
            //编译sql
            ps = conn.prepareStatement(add_teacher_sql);

            ps.setString(1, teacher.getTeacher_Username());
            ps.setString(2, teacher.getTeacher_Password());
            ps.setString(3, teacher.getTeacher_Name());
            ps.setString(4, teacher.getTeacher_Sex());
            ps.setString(5,teacher.getTeacher_Tel());
            result = ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn,ps,rs);
        }
        return result;
    }

    /**
     * 根据id获取用户信息
     */
    public TeacherBean GetTeacherId(int Teacher_Id){
        TeacherBean teacher = new TeacherBean();

        try {
            String selectId_teacher_sql = "select * from teacher where Teacher_ID=?";
            conn = DBUtil.getConnection();
            ps = conn.prepareStatement(selectId_teacher_sql);
            ps.setInt(1,Teacher_Id);
            rs = ps.executeQuery();
            while (rs.next()){
                teacher.setTeacher_ID(Teacher_Id);
                teacher.setTeacher_Username(rs.getString("Teacher_Username"));
                teacher.setTeacher_Password(rs.getString("Teacher_Password"));
                teacher.setTeacher_Name(rs.getString("Teacher_Name"));
                teacher.setTeacher_Sex(rs.getString("Teacher_Sex"));
                teacher.setTeacher_Tel(rs.getString("Teacher_Tel"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn,ps,rs);
        }
        return teacher;
    }
    /**
     * 删除教师信息
     */
    public int Delete(int Teacher_Id){
        int result = 0;
        try {
            conn = DBUtil.getConnection();
            String delete_teacher_sql = "delete from teacher where Teacher_Id=?";
            ps = conn.prepareStatement(delete_teacher_sql);
            ps.setInt(1,Teacher_Id);
            result = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn,ps,rs);
        }
        return result;
    }
}
