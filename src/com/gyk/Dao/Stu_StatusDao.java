package com.gyk.Dao;

import com.gyk.bean.StudentBean;
import com.gyk.utils.DBUtil;

import java.sql.*;

public class Stu_StatusDao {
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    Statement stat = null;

    /**
     * 查学生入住信息
     *
     * @param str
     * @param strvalues
     * @return
     */
    public StudentBean GetStuAllMsg(String str, String strvalues) {
        Statement stat = null;
        String connect = null;
        StudentBean student = new StudentBean();
        if (!isInvalid(str) && !isInvalid(strvalues)) {
            connect = str + strvalues;
        }
        try {
            conn = DBUtil.getConnection();
            stat = conn.createStatement();
            String get_Stu_all_sql =
                    "SELECT * FROM `domitory`,`building`,`student` WHERE `Building_ID`=`Domitory_BuildingID` AND `Student_DomitoryID`=`Domitory_ID` AND " + connect;
            rs = stat.executeQuery(get_Stu_all_sql);
            while (rs.next()) {
                student.setStudent_ID(rs.getInt("Student_ID"));
                student.setStudent_DomitoryID(rs.getInt("Student_DomitoryID"));
                student.setStudent_BuildingID(rs.getInt("Domitory_BuildingID"));
                student.setStudent_Username(rs.getString("Student_Username"));
                student.setStudent_Password(rs.getString("Student_Password"));
                student.setStudent_Name(rs.getString("Student_Name"));
                student.setStudent_Sex(rs.getString("Student_Sex"));
                student.setStudent_Class(rs.getString("Student_Class"));
                student.setStudent_State(rs.getString("Student_State"));
                student.setBuilding_Name(rs.getString("Building_Name"));
                student.setDomitory_Name(rs.getString("Domitory_Name"));
                student.setDomitory_Type(rs.getString("Domitory_Type"));
                student.setDomitory_Number(rs.getString("Domitory_Number"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBUtil.close(conn, stat, rs);
        }
        return student;
    }

    /**
     * 更新
     *
     * @param studentBean
     * @return
     */
    public int UpdateStuMsg(StudentBean studentBean) {
        int result = 0;
        String sql = "update Student set ";
        sql += "Student_DomitoryID='" + studentBean.getStudent_DomitoryID() + "',";
        sql += "Student_Username='" + studentBean.getStudent_Username() + "',";
        sql += "Student_Password='" + studentBean.getStudent_Password() + "',";
        sql += "Student_Name='" + studentBean.getStudent_Name() + "',";
        sql += "Student_Sex='" + studentBean.getStudent_Sex() + "',";
        sql += "Student_Class='" + studentBean.getStudent_Class() + "',";
        sql += "Student_State='" + studentBean.getStudent_State() + "'";

        sql += " where Student_ID='" + studentBean.getStudent_ID() + "'";
        try {
            conn = DBUtil.getConnection();
            stat = conn.createStatement();
            result = stat.executeUpdate(sql);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBUtil.close(conn, stat, rs);
        }
        return result;
    }

    private boolean isInvalid(String value) {
        return (value == null || value.length() == 0);
    }


}
