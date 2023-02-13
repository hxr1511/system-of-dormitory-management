package com.gyk.Dao;

import com.gyk.bean.AdminBean;
import com.gyk.utils.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminDao {
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    String Admin_id = null;
    AdminBean admin;

    /**
     * ��֤��¼
     * sql:SELECT*FROM `admin` WHERE Admin_Username='8506b114' AND Admin_Password='1234'
     */
    public String CheckLogin(String username, String password) {
        Boolean success = false;//���巵�ر���
        try {
            conn = DBUtil.getConnection();
            String sql_select_login = "SELECT*FROM `admin` WHERE Admin_Username=? AND Admin_Password=?";
            //����sql
            ps = conn.prepareStatement(sql_select_login);
            //������ֵ
            ps.setString(1, username);
            ps.setString(2, password);
            //ִ��sql
            rs = ps.executeQuery();
            //�ó�������ֻ��һ��
            if (rs.next()) {
                Admin_id = rs.getString("Admin_ID");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, ps, rs);
        }
        return Admin_id;
    }
    /**
     * ��¼��ͨ��session���ݵ�id��ȡ����Ա������Ϣ
     */


    /**
     * ��֤����һ�� �����޸�����
     */
    public Boolean CheckPassword(int id, String new_password) {
        Boolean result = false;
        try {
            conn = DBUtil.getConnection();
            String check_pwd_sql = "SELECT * FROM admin WHERE Admin_ID=? AND Admin_Password=?";
            ps = conn.prepareStatement(check_pwd_sql);
            ps.setInt(1, id);
            ps.setString(2, new_password);
            rs = ps.executeQuery();
            while (rs.next()) {
                result = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, ps, rs);
        }
        return result;
    }

    /**
     * ��ȡ��Ӧid���û���Ϣ
     *
     * @param id
     * @return
     */
    public AdminBean GetId(int id) {
        int result = 0;
        try {
            conn = DBUtil.getConnection();
            String select_id_sql = "select * from admin where Admin_ID=?";
            ps = conn.prepareStatement(select_id_sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
//            System.out.println(rs);
            while (rs.next()) {
                String Admin_Username = rs.getString("Admin_Username");
                String Admin_Password = rs.getString("Admin_Password");
                String Admin_Name = rs.getString("Admin_Name");
                String Admin_Sex = rs.getString("Admin_Sex");
                String Admin_Tel = rs.getString("Admin_Tel");
                admin = new AdminBean(id, Admin_Username, Admin_Password, Admin_Name, Admin_Sex, Admin_Tel);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, ps, rs);
        }
        return admin;
    }

    /**
     * ���¹���Ա��Ϣ
     *
     * @param admin
     * @return
     */

    public int UpdateSave(AdminBean admin) {
        int result = 0;
        try {
            conn = DBUtil.getConnection();
            String update_admin_sql =
                    "update admin set Admin_username=?,Admin_Password=?,Admin_Name=?,Admin_Sex=?,Admin_Tel=? where Admin_ID=?";
            ps = conn.prepareStatement(update_admin_sql);
            ps.setString(1, admin.getAdmin_Username());
            ps.setString(2, admin.getAdmin_Password());
            ps.setString(3, admin.getAdmin_Name());
            ps.setString(4, admin.getAdmin_Sex());
            ps.setString(5, admin.getAdmin_Tel());
            ps.setInt(6, admin.getAdmin_ID());
            result = ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, ps, rs);
        }
        return result;
    }
}
