package com.tolgaduran.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
    private static final String OracleUser = "username"; // Use username for Oracle Server
    private static final String OraclePass = "password"; // Use password for Oracle Server
    private static final String ORACLEURL = "jdbc:oracle:thin:@localhost:1521:xe";

    private static final String MYSQLUser = "username"; //use username for MYSQL Server
    private static final String MYSQLPass = "password"; // use password for MYSQL Server
    private static final String MYSQLURL = "jdbc:mysql://localhost:3306/world?useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

    public static Connection getConnection(DBSets sets) throws SQLException {
        switch (sets) {
            case MYSQL:
                return DriverManager.getConnection(MYSQLURL, MYSQLUser, MYSQLPass);
            case ORACLE:
                return DriverManager.getConnection(ORACLEURL, OracleUser, OraclePass);
            default:
                return null;
        }
    }

    public static void getMsg(Exception e){
        System.err.println(e.getMessage());
    }
}
