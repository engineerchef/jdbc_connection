package com.tolgaduran.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestConnMYSQL {
    public static void main(String[] args) throws SQLException {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            conn = DBUtil.getConnection(DBSets.MYSQL);
            stmt = conn.createStatement();
            rs = stmt.executeQuery("select * from country");
            rs.last();

            System.out.println("Total Rows: " + rs.getRow());

            // System.out.println("Connection Successfully Established as MYSQL");
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
