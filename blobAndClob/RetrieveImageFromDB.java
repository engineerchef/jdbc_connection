package com.tolgaduran.jdbc.blobAndClob;

import com.tolgaduran.jdbc.DBSets;
import com.tolgaduran.jdbc.DBUtil;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.*;

public class RetrieveImageFromDB {
    public static void main(String[] args) throws SQLException,IOException {
        Connection conn=DBUtil.getConnection(DBSets.ORACLE);

        String sql="select PHOTO from NEWEMPLOYEES where EMPLOYEE_ID=500";
        PreparedStatement pstmt=conn.prepareStatement(sql);

        ResultSet rs=pstmt.executeQuery();

        if (rs.next()){
            Blob imgBlob=rs.getBlob("Photo");
            FileOutputStream fos=new FileOutputStream("D:/img.jpg");

            fos.write(imgBlob.getBytes(1,(int)imgBlob.length()));

            fos.flush();
            fos.close();

            System.out.println("Photo of Employee 500 has been Downloaded successfully!!!");
        }else {
            System.out.println("Employee record not found!!!");
        }

        rs.close();
        pstmt.close();
        conn.close();
    }
}
