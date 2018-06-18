package com.tolgaduran.jdbc.metadata;

import com.tolgaduran.jdbc.DBSets;
import com.tolgaduran.jdbc.DBUtil;

import java.sql.*;

public class ResultSetMetaDataDemo {
    public static void main(String[] args) {
        try (
                Connection conn = DBUtil.getConnection(DBSets.ORACLE);
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("select * from EMPLOYEES")
        ) {
            ResultSetMetaData rsmd = rs.getMetaData();

            int coloumnCount = rsmd.getColumnCount();

            String format = "%-50s%-25s%-20s%-20s\n";
            System.out.format(format, ":Column Name", "Column Type", "Is Nullable", "Is AutoIncrement");
            System.out.format(format, "------------", "-----------", "-----------", "----------------");

            for (int i = 1; i <= coloumnCount; i++) {
                System.out.format(format, rsmd.getColumnName(i), rsmd.getColumnType(i), rsmd.isNullable(i), rsmd.isAutoIncrement(i));
            }

            System.out.println("Total Columns : " + coloumnCount);
        } catch (SQLException e) {
            DBUtil.getMsg(e);
        }
    }
}
