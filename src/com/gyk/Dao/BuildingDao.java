package com.gyk.Dao;

import com.gyk.bean.BuildingBean;
import com.gyk.utils.DBUtil;
//import org.apache.taglibs.standard.util.XmlUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BuildingDao {
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    /**
     * 获取building列表  GetList
     */
    public List<BuildingBean> GetList(int key, String strwhere) {
        List<BuildingBean> buildingList = new ArrayList<>();
        String getList_sql = "select * from `building` ";
        try {
            conn = DBUtil.getConnection();
            if (key == 1) {
                getList_sql += strwhere;
            }
            getList_sql+=" order by Building_Name ";
            //编译sql
            ps = conn.prepareStatement(getList_sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                int Building_ID = rs.getInt("Building_ID");
                String Building_Name = rs.getString("Building_Name");
                String Building_Introduction = rs.getString("Building_Introduction");
                BuildingBean building = new BuildingBean(Building_ID, Building_Name, Building_Introduction);
                buildingList.add(building);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBUtil.close(conn, ps, rs);
        }
        return buildingList;
    }

    /**
     * 根据id获取building信息
     *
     * @param building_id
     * @return
     */
    public BuildingBean GetBuildingId(Integer building_id) {
        BuildingBean building = new BuildingBean();
        try {
            conn = DBUtil.getConnection();
            String selectid_building_sql = "select * from `building` where Building_ID=?";
            ps = conn.prepareStatement(selectid_building_sql);
            ps.setInt(1, building_id);
            rs = ps.executeQuery();
            while (rs.next()) {
                building.setBuilding_ID(building_id);
                building.setBuilding_Name(rs.getString("Building_Name"));
                building.setBuilding_Introduction(rs.getString("Building_Introduction"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, ps, rs);
        }
        return building;
    }

    /**
     * 更新
     *
     * @param building
     * @return
     */

    public int UpdateSave(BuildingBean building) {
        int result = 0;
        try {
            conn = DBUtil.getConnection();
            String update_building_sql = "update building set Building_Name=?,Building_Introduction=? where Building_ID=?";
            ps = conn.prepareStatement(update_building_sql);
            ps.setInt(3, building.getBuilding_ID());
            ps.setString(1, building.getBuilding_Name());
            ps.setString(2, building.getBuilding_Introduction());
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
     * @param building
     * @return
     */

    public int AddSave(BuildingBean building) {
        int result = 0;
        try {
            conn = DBUtil.getConnection();
            String add_building_sql = "insert into building(Building_Name,Building_Introduction) values (?,?)";
            ps = conn.prepareStatement(add_building_sql);
            ps.setString(1, building.getBuilding_Name());
            ps.setString(2, building.getBuilding_Introduction());
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
     * @param building_id
     * @return
     */

    public int Delete(Integer building_id) {
        int result = 0;
        try {
            conn = DBUtil.getConnection();
            String delete_building_sql = "delete from building where Building_ID=?";
            ps = conn.prepareStatement(delete_building_sql);
            ps.setInt(1, building_id);
            result = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, ps, rs);
        }
        return result;
    }
}
