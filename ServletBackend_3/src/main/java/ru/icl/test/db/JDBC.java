package ru.icl.test.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.http.HttpSession;

public class JDBC {
           
    public JDBC(HttpSession session, String id, String name, String msg) { 
        //запись в БД Postgres
        Connection conn = null;
        PreparedStatement pstmt1 = null;
        PreparedStatement pstmt2 = null;
        String sql1 = "insert into users (clientid, fname) values (?, ?)";
        String sql2 = "insert into messages (clientid, message) values (?, ?)"; 
        
        try {
            conn = Database.getConnection();                       
            if (session.isNew()) { 
                pstmt1 = conn.prepareStatement(sql1);
                pstmt1.setString(1, id);
                pstmt1.setString(2, name);
                pstmt1.executeUpdate();
            }

            pstmt2 = conn.prepareStatement(sql2);
            pstmt2.setString(1, id);
            pstmt2.setString(2, msg);
            pstmt2.executeUpdate();                                            

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {                
            try {                    
                if (pstmt1!=null) pstmt1.close();
                if (pstmt2!=null) pstmt2.close();
                if (conn!=null)conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            } 
        }
    }
}
