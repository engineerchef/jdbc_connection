package com.tolgaduran.jdbc.blobAndClob;

import com.tolgaduran.jdbc.DBSets;
import com.tolgaduran.jdbc.DBUtil;

import java.io.IOException;
import java.io.Reader;
import java.sql.*;

public class RetrieveCLOBDataFromDB {
    public static void main(String[] args) throws SQLException,IOException {
        Connection conn=DBUtil.getConnection(DBSets.ORACLE);

        String sql="select RESUME from NEWEMPLOYEES where EMPLOYEE_ID=500";
        PreparedStatement pstmt=conn.prepareStatement(sql);

        ResultSet rs=pstmt.executeQuery();

        if (rs.next()){
            Clob resume=rs.getClob("Resume");
            Reader data=resume.getCharacterStream();

            int i;
            String resumeDeatils="";
            while ((i=data.read())!=-1){
                resumeDeatils+=((char)i);
            }
            System.out.println("Resume Detail for Employee 500");
            System.out.println(resumeDeatils);
        }else {
            System.err.println("No record found for employee with ID 500");
        }

        rs.close();
        pstmt.close();
        conn.close();
    }
}
