package com.tolgaduran.jdbc.crud;

import com.tolgaduran.jdbc.DBSets;
import com.tolgaduran.jdbc.DBUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UpdatableResultSetDemo {
    public static void main(String[] args) throws SQLException {
        try(
                Connection conn=DBUtil.getConnection(DBSets.ORACLE);
                Statement stmt=conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
                ResultSet rs=stmt.executeQuery("SELECT DEPARTMENT_ID,DEPARTMENT_NAME,MANAGER_ID,LOCATION_ID from DEPARTMENTS")
                ){
            rs.absolute(6);
            rs.updateString("Department_Name","Information Technology");
            rs.updateRow();

            System.out.println("Record Updated Successfully!!!");

            rs.moveToInsertRow();
            rs.updateInt("Department_Id",999);
            rs.updateString("Department_Name","Training");
            rs.updateInt("Manager_Id",200);
            rs.updateInt("Location_Id",1800);

            rs.insertRow();

            System.out.println("Record Inserted Successfully!!!");
        }
    }
}
