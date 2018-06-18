package com.tolgaduran.jdbc;

import java.sql.*;

public class TestConnOracle {
    public static void main(String[] args) throws SQLException {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            conn = DBUtil.getConnection(DBSets.ORACLE);
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            rs = stmt.executeQuery("select * from EMPLOYEES");
            rs.last();

            System.out.println("Total Rows: " + rs.getRow());

            System.out.println("Connection Successfully Established as ORACLE");
        } catch (SQLException e) {
            DBUtil.getMsg(e);
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }
}
