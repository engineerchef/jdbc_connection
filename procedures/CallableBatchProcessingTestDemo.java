package com.tolgaduran.jdbc.procedures;

import com.tolgaduran.jdbc.DBSets;
import com.tolgaduran.jdbc.DBUtil;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Scanner;

/*
CREATE OR REPLACE PROCEDURE AddNewEmployee(
  eid   in NEWEMPLOYEES.EMPLOYEE_ID%TYPE,
  ename in NEWEMPLOYEES.Employee_Name%TYPE,
  email in NEWEMPLOYEES.Email%type,
  doj   in NEWEMPLOYEES.Hire_Date%type,
  sal   in NEWEMPLOYEES.Salary%type
)
is begin insert into NEWEMPLOYEES values (eid, ename, email, doj, sal);
  commit;
end;
 */

public class CallableBatchProcessingTestDemo {
    public static void main(String[] args) {
        try (
                Connection conn = DBUtil.getConnection(DBSets.ORACLE);
                CallableStatement callableStatement = conn.prepareCall("{call AddNewEmployee(?,?,?,?,?)}")
        ) {
            String option;

            do {
                Scanner scanner = new Scanner(System.in);
                System.out.println("Enter Employee # : ");
                int empno = Integer.parseInt(scanner.nextLine());
                System.out.println("Enter Employee Name : ");
                String ename = scanner.nextLine();
                System.out.println("Enter Email ID : ");
                String email = scanner.nextLine();
                System.out.println("Enter Hire Date : ");
                Date doj = Date.valueOf(scanner.nextLine());
                System.out.println("Enter Salary : ");
                double salary = scanner.nextDouble();

                callableStatement.setInt(1, empno);
                callableStatement.setString(2, ename);
                callableStatement.setString(3, email);
                callableStatement.setDate(4, doj);
                callableStatement.setDouble(5, salary);

                callableStatement.addBatch();

                System.out.println("Do you want to add another Record (yes/no)?");
                option = scanner.nextLine();
            } while (option.equals("yes"));

            int[] updateCounts = callableStatement.executeBatch();
            System.out.println("Total Records Inserted are : " + updateCounts.length);
        } catch (SQLException e) {
            DBUtil.getMsg(e);
        }
    }
}
