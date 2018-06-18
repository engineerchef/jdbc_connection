package com.tolgaduran.jdbc.blobAndClob;

import com.tolgaduran.jdbc.DBSets;
import com.tolgaduran.jdbc.DBUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertCLOBDataToEmpResume {
    public static void main(String[] args) throws SQLException,FileNotFoundException {
        Connection conn=DBUtil.getConnection(DBSets.ORACLE);
        conn.setAutoCommit(false);
        PreparedStatement pstmt=null;

        String sql="update NEWEMPLOYEES set RESUME=? where EMPLOYEE_ID=500";
        pstmt=conn.prepareStatement(sql);

        String resumeFile="C:/Users/JavaEngineer/IdeaProjects/Pluralsight/src/com/tolgaduran/jdbc/blobAndClob/demo.txt";
        File file=new File(resumeFile);
        FileReader reader=new FileReader(file);

        pstmt.setCharacterStream(1,reader,(int)file.length());
        pstmt.executeUpdate();

        System.out.println("Resume Uploaded Successfully!!!");
        conn.commit();

        pstmt.close();
        conn.close();
    }
}
