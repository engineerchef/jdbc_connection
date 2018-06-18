package com.tolgaduran.jdbc.crud;

import com.tolgaduran.jdbc.DBSets;
import com.tolgaduran.jdbc.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class PreparedStatementDeleteDemo {
    public static void main(String[] args) throws SQLException {
        Connection conn = DBUtil.getConnection(DBSets.ORACLE);

        String sql = "delete from NEWEMPLOYEES where EMPLOYEE_ID=?";

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter Employee ID : ");
        int empno = scanner.nextInt();

        PreparedStatement pstmt = conn.prepareStatement(sql);

        pstmt.setInt(1, empno);

        int result = pstmt.executeUpdate();
        if (result == 1) {
            System.out.println("Employee Record Removed Successfully!!!");
        } else {
            System.err.println("Error while removing employee record");
        }

        scanner.close();
        pstmt.close();
        conn.close();
    }
}
