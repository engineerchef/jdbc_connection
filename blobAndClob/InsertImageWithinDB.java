package com.tolgaduran.jdbc.blobAndClob;

import com.tolgaduran.jdbc.DBSets;
import com.tolgaduran.jdbc.DBUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertImageWithinDB {
    public static void main(String[] args) throws SQLException,IOException {
        Connection conn=DBUtil.getConnection(DBSets.ORACLE);
        conn.setAutoCommit(false);

        String sql="update NEWEMPLOYEES set PHOTO =? where EMPLOYEE_ID=500";
        PreparedStatement pstmt=conn.prepareStatement(sql);

        File file=new File("D:\\demo.jpg");
        FileInputStream fis=new FileInputStream(file);
        pstmt.setBinaryStream(1,fis,fis.available());

        int count=pstmt.executeUpdate();

        System.out.println("Total records updated : "+count);
        conn.commit();

        pstmt.close();
        conn.close();
    }
}
