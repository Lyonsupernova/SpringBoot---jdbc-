package com.ludy.springboot.pojo;


import java.sql.*;


public class ConnectionPojo {
    private static ConnectionPojo connPojo = new ConnectionPojo();

    public static Connection conn = null;

    public static PreparedStatement ps = null;

    public static ResultSet rs = null;

    private final String url = "jdbc:mysql://localhost:3306/test?useSSL=false&serverTimezone=CTT&useUnicode=true&characterEncoding=utf-8";

    private final String user = "root";

    private final String key = "123456";


    public ConnectionPojo() {
    }

    public static ConnectionPojo getInstance() {
        return connPojo;
    }

    public void newInstance() {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, key);
    }

    public void setPrepareStatement(PreparedStatement ps) {
        this.ps = ps;
    }

    public PreparedStatement getPrepareStatement() throws SQLException {
        return ps;
    }

    public void setResultSet(ResultSet rs) {
        this.rs = rs;
    }

    public ResultSet getResultSet() throws SQLException {
        return rs;
    }

    public int executeUpdate() throws SQLException {
        return ps.executeUpdate();
    }

    public void closeAll() {
        try {
            if (conn != null) {
                conn.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e2) {
            e2.printStackTrace();
        }
    }
}
