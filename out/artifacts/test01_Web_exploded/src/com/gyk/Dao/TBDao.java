package com.gyk.Dao;

import com.gyk.bean.TBBean;
import com.gyk.utils.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TBDao {
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    /**
     * 获取列表
     */
    public List<TBBean> GetList(int key,String strwhere){
        List<TBBean> tbList=new ArrayList<>();

        try {
            conn = DBUtil.getConnection();
            String getList_sql = "SELECT * FROM `tb`,`teacher`,`building` WHERE `TB_TeacherID`=`Teacher_ID` AND `TB_BuildingID`=`Building_ID`";
            if (key==1){
                getList_sql+=strwhere;
            }
            ps = conn.prepareStatement(getList_sql);
            rs = ps.executeQuery();
            while (rs.next()){
                TBBean tb = new TBBean();

                tb.setTB_ID(rs.getInt("TB_ID"));
                tb.setTB_TeacherID(rs.getInt("TB_TeacherID"));
                tb.setTB_BuildingID(rs.getInt("TB_BuildingID"));
                tb.setTeacher_Username(rs.getString("Teacher_Username"));
                tb.setTeacher_Name(rs.getString("Teacher_Name"));
                tb.setTeacher_Sex(rs.getString("Teacher_Sex"));
                tb.setTeacher_Tel(rs.getString("Teacher_Tel"));
                tb.setBuilding_Name(rs.getString("Building_Name"));
                tbList.add(tb);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn,ps,rs);
        }
        return tbList;

    }



    public int Delete(Integer tb_id) {
        int result = 0;
        try {
            conn=DBUtil.getConnection();
            String delete_tb_sql = "delete from tb where TB_ID=?";
            ps=conn.prepareStatement(delete_tb_sql);
            ps.setInt(1,tb_id);
            result = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn,ps,rs);
        }
        return result;
    }

    public int AddSave(String tb_teacherID, String tb_buildingID) {
        int result=0;
        try {
            conn = DBUtil.getConnection();
            String add_tb_sql = "insert into tb (tb_teacherID,TB_BuildingID) values (?,?)";
            ps = conn.prepareStatement(add_tb_sql);
            ps.setString(1, tb_teacherID);
            ps.setString(2, tb_buildingID);
            result = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn,ps,rs);
        }
        return result;
    }
}
