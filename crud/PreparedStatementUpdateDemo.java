package com.tolgaduran.jdbc.crud;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class PreparedStatementUpdateDemo {
    public static void main(String[] args) throws SQLException {
        Connection conn = DBUtil.getConnection(DBSets.ORACLE);

        String sql = "update NEWEMPLOYEES set SALARY=? where EMPLOYEE_ID=?";

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter Employee ID : ");
        int empno = scanner.nextInt();

        System.out.println("Enter New Salary : ");
        double salary = scanner.nextDouble();

        PreparedStatement pstmt = conn.prepareStatement(sql);

        pstmt.setDouble(1, salary);
        pstmt.setInt(2, empno);

        int result = pstmt.executeUpdate();
        if (result == 1) {
            System.out.println("Employee Salary Updated Successfully!!!");
        } else {
            System.err.println("Error while updating the Salary");
        }

        scanner.close();
        pstmt.close();
        conn.close();
    }
}
