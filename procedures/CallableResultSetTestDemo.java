package com.tolgaduran.jdbc.procedures;

/*
create or replace procedure GetEmployeesByRefCursor(
p_deptno in Employees.Department_ID%TYPE,
p_recordSet out sys_refcursor
)
  as begin open p_recordSet for select EMPLOYEE_ID,FIRST_NAME || LAST_NAME as Employee_Name, EMAIL,SALARY from EMPLOYEES where DEPARTMENT_ID=p_deptno order by EMPLOYEE_ID;
  end;
*/

import com.tolgaduran.jdbc.DBSets;
import com.tolgaduran.jdbc.DBUtil;
import oracle.jdbc.OracleTypes;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class CallableResultSetTestDemo {
    public static void main(String[] args) throws SQLException {
        try (
                Connection conn=DBUtil.getConnection(DBSets.ORACLE);
                CallableStatement callableStatement=conn.prepareCall("{call GetEmployeesByRefCursor(?,?)}");
                Scanner scanner=new Scanner(System.in)
                ){
            System.out.println("Enter Department ID : ");
            int deptno=Integer.parseInt(scanner.nextLine());

            callableStatement.setInt(1,deptno);
            callableStatement.registerOutParameter(2,OracleTypes.CURSOR);
            callableStatement.execute();

            ResultSet rs=((oracle.jdbc.internal.OracleCallableStatement)callableStatement).getCursor(2);
            String format = "%-4s%-20s%-25s%-10f\n";
            while (rs.next()) {
                System.out.format(format, rs.getString("EMPLOYEE_ID"), rs.getString("Employee_Name"), rs.getString("EMAIL"), rs.getFloat("SALARY"));
            }
        }catch (SQLException e){
            DBUtil.getMsg(e);
        }
    }
}
