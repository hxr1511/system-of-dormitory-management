package com.gyk.Dao;

import com.gyk.bean.DomitoryBean;
import com.gyk.utils.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DomitoryDao {
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    /**
     * 获取宿舍信息，包含条件查询
     *
     * @param key
     * @param strwhere
     * @return
     */
    public List<DomitoryBean> GetList(int key, String strwhere) {
        List<DomitoryBean> domitorylist = new ArrayList<>();
        try {
            String getList_sql = "SELECT *FROM `building` INNER JOIN `domitory`   WHERE `Building_ID`=`Domitory_BuildingID`  ";
            conn = DBUtil.getConnection();
            if (key == 1) {
                getList_sql += strwhere;
            }
            getList_sql +=" ORDER BY `Building_Name`,`Domitory_Name` ";
            ps = conn.prepareStatement(getList_sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                DomitoryBean domitory = new DomitoryBean();
                domitory.setDomitory_ID(rs.getInt("Domitory_ID"));
                domitory.setDomitory_BuildingID(rs.getInt("Building_ID"));
                domitory.setDomitory_BuildingName(rs.getString("Building_Name"));
                domitory.setDomitory_Type(rs.getString("Domitory_Type"));
                domitory.setDomitory_Number(rs.getString("Domitory_Number"));
                domitory.setDomitory_Name(rs.getString("Domitory_Name"));
                domitorylist.add(domitory);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, ps, rs);
        }
        return domitorylist;
    }

    /**
     * 通过id获取Domitory
     *
     * @param domitory_id
     * @return
     */
    public DomitoryBean GetDomitoryId(Integer domitory_id) {
        DomitoryBean domitory = new DomitoryBean();
        try {
            String select_domitory_sql = "select * from domitory where Domitory_ID=?";
            conn = DBUtil.getConnection();
            ps = conn.prepareStatement(select_domitory_sql);
            ps.setInt(1, domitory_id);
            rs = ps.executeQuery();
            while (rs.next()) {
                domitory.setDomitory_ID(rs.getInt("Domitory_ID"));
                domitory.setDomitory_BuildingID(rs.getInt("Domitory_BuildingID"));
                domitory.setDomitory_Name(rs.getString("Domitory_Name"));
                domitory.setDomitory_Type(rs.getString("Domitory_Type"));
                domitory.setDomitory_Number(rs.getString("Domitory_Number"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBUtil.close(conn, ps, rs);
        }
        return domitory;
    }

    /**
     * 更新
     *
     * @param domitory
     * @return
     */
    public int UpdateSave(DomitoryBean domitory) {
        int result = 0;
        try {
            conn = DBUtil.getConnection();
            String update_domitory_sql = "UPDATE `domitory` SET `Domitory_BuildingID`=?," +
                    "`Domitory_Name`=?,`Domitory_Type`=?,`Domitory_Number`=? WHERE `Domitory_ID`=?";
            ps = conn.prepareStatement(update_domitory_sql);
            ps.setInt(1, domitory.getDomitory_BuildingID());
            ps.setString(2, domitory.getDomitory_Name());
            ps.setString(3, domitory.getDomitory_Type());
            ps.setString(4, domitory.getDomitory_Number());
            ps.setInt(5, domitory.getDomitory_ID());
            result = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, ps, rs);
        }
        return result;
    }

    /**
     * 添加
     *
     * @param domitory
     * @return
     */
    public int AddSave(DomitoryBean domitory) {
        int result = 0;

        try {
            conn = DBUtil.getConnection();
            String add_domitory_sql =
                    "INSERT INTO `domitory`(`Domitory_BuildingID`,`Domitory_Name`,`Domitory_Type`,`Domitory_Number`)" +
                            "VALUES(?,?,?,?)";
            ps = conn.prepareStatement(add_domitory_sql);
            ps.setInt(1, domitory.getDomitory_BuildingID());
            ps.setString(2, domitory.getDomitory_Name());
            ps.setString(3, domitory.getDomitory_Type());
            ps.setString(4, domitory.getDomitory_Number());
            result = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, ps, rs);
        }
        return result;
    }

    /**
     * 删除
     *
     * @param domitory_id
     * @return
     */
    public int Delete(Integer domitory_id) {
        int result = 0;

        try {
            conn = DBUtil.getConnection();
            String delete_domitory_sql = "delete from domitory where Domitory_ID=?";
            ps = conn.prepareStatement(delete_domitory_sql);
            ps.setInt(1, domitory_id);
            result = ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBUtil.close(conn, ps, rs);
        }
        return result;
    }
}
