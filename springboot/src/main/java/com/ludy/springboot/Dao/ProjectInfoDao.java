package com.ludy.springboot.Dao;

import com.ludy.springboot.pojo.ConnectionPojo;
import com.ludy.springboot.pojo.ProjectInfo;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository("projectInfoDao")
public class ProjectInfoDao {
    public static Logger lo = Logger.getLogger(ProjectInfoDao.class);

    public List<ProjectInfo> selectTable() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        ConnectionPojo connPj = ConnectionPojo.getInstance();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<ProjectInfo> projectInfoList = new ArrayList<>();
        try {
            connPj.newInstance();
            conn = connPj.getConnection();
            connPj.setPrepareStatement(conn.prepareStatement("SELECT * FROM etl_project_info"));
            ps = connPj.getPrepareStatement();
            connPj.setResultSet(ps.executeQuery());
            rs = connPj.getResultSet();
            lo.info("Select information from the table \"etl_project_info\"!");
            while (rs.next()) {
                ProjectInfo pi = new ProjectInfo();
                pi.setName(rs.getString(1));
                pi.setDescription(rs.getString(2));
                pi.setId(rs.getString(3));
                pi.setDate(rs.getString(4));
                projectInfoList.add(pi);
                System.out.println(rs.getInt(1) + "------" +
                        rs.getString(2) +
                        "-----" + rs.getString(3) +
                        "-----" + rs.getString(4) +
                        "-----" + rs.getString(5));
            }

        } catch (SQLException e) {
            e.printStackTrace();
            lo.warn("SQLException error");
        } finally {
            connPj.closeAll();
        }
        lo.info("Display the table");
        return projectInfoList;
    }

    public boolean deleteTable(int index) {
        ConnectionPojo connPJ = ConnectionPojo.getInstance();
        Connection conn = null;
        PreparedStatement ps = null;
        int i = 0;
        try {
            connPJ.newInstance();
            conn = connPJ.getConnection();
            String sql = String.format("DELETE FROM etl_project_info WHERE S_PK_UUID = ?", index);
            connPJ.setPrepareStatement(conn.prepareStatement(sql));
            ps = connPJ.getPrepareStatement();
            i = ps.executeUpdate();
            lo.info("Delete rows " + index + "from table etl_project_info");
            System.out.println("Delete table at index = " + index + " successfully!");
        } catch (SQLException e2) {
            e2.printStackTrace();
        } finally {
            connPJ.closeAll();
        }
        return i > 0;
    }

    public boolean insertTable(String insertion) {
        ConnectionPojo connPj = ConnectionPojo.getInstance();
        Connection conn = null;
        PreparedStatement ps = null;
        int i = 0;
        try {
            connPj.newInstance();
            conn = connPj.getConnection();
            connPj.setPrepareStatement(conn.prepareStatement("INSERT INTO etl_project_info ('S_PROJECT_NAME', 'S_PROJECT_DESC', 'S_PROJECT_ID', 'D_CREATE_DATE') VALUES (" + insertion + ")"));
            ps = connPj.getPrepareStatement();
            i = connPj.executeUpdate();
            System.out.println("Insert the information of " + insertion + " successfully!");
        } catch (SQLException e2) {
            e2.printStackTrace();
        } finally {
            connPj.closeAll();
        }
        return i > 0;
    }

    public boolean updateTable(String name, String value, int index) {
        ConnectionPojo connPj = ConnectionPojo.getInstance();
        Connection conn = null;
        PreparedStatement ps = null;
        int i = 0;
        try {
            connPj.newInstance();
            conn = connPj.getConnection();
            String sql = "UPDATE etl_project_info " + name + " = " + value + "WHERE S_PK_UUID = " + index;
            connPj.setPrepareStatement(conn.prepareStatement(sql));
            ps = connPj.getPrepareStatement();
            i = connPj.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connPj.closeAll();
        }
        return i > 0;
    }


}



