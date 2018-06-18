package com.tolgaduran.jdbc.procedures;

import com.tolgaduran.jdbc.DBSets;
import com.tolgaduran.jdbc.DBUtil;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

/*
Create or replace procedure getTotalEmployeesByDepartment(
  deptno in Employees.Department_Id%Type,
  totalEmployees out number
)
as
  begin
    select count(*) into totalEmployees from Employees where Department_Id=deptno;
  end;
*/

public class CallableOutTestDemo {
    public static void main(String[] args) {
        try (
                Connection conn = DBUtil.getConnection(DBSets.ORACLE);
                CallableStatement callableStatement = conn.prepareCall("{ call GetTotalEmployeesByDepartment(?,?)}");
                Scanner scanner = new Scanner(System.in)
        ) {
            System.out.println("Enter Department ID : ");
            int deptno = Integer.parseInt(scanner.nextLine());

            callableStatement.setInt(1, deptno);
            callableStatement.registerOutParameter(2, Types.INTEGER);
            callableStatement.execute();

            int totalEmployees = callableStatement.getInt(2);
            System.out.println("Total Employees Working : " + totalEmployees);
        } catch (SQLException e) {
            DBUtil.getMsg(e);
        }
    }
}
