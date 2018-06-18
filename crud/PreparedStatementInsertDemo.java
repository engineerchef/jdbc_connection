package com.tolgaduran.jdbc.crud;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

/*
create table NewEmployees as (select EMPLOYEE_ID, FIRST_NAME as EMPLOYEE_NAME,EMAIL,HIRE_DATE,SALARY FROM EMPLOYEES);
 */

public class PreparedStatementInsertDemo {
    public static void main(String[] args) throws SQLException {
        Connection conn = DBUtil.getConnection(DBSets.ORACLE);

        int empno;
        String ename, email;
        Date hiredate;
        double salary;

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Employee ID : ");
        empno = Integer.parseInt(scanner.nextLine());

        System.out.println("Enter Employee Name : ");
        ename = scanner.nextLine();

        System.out.println("Enter Email : ");
        email = scanner.nextLine();

        System.out.println("Enter Date of Joining : ");
        hiredate = Date.valueOf(scanner.nextLine());

        System.out.println("Enter Salary : ");
        salary = scanner.nextDouble();

        String sql = "insert into NEWEMPLOYEES values(?,?,?,?,?)";

        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, empno);
        pstmt.setString(2, ename);
        pstmt.setString(3, email);
        pstmt.setDate(4, hiredate);
        pstmt.setDouble(5, salary);

        int result = pstmt.executeUpdate();

        if (result == 1) {
            System.out.println("Record inserted succesfully!!!");
        } else {
            System.err.println("Error while adding the record!!!");
        }

        scanner.close();
        ;
        pstmt.close();
        conn.close();
    }
}
