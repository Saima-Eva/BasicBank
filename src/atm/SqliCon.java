/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atm;

import java.sql.*;

/**
 *
 * @author Shashiki
 */
public class SqliCon {
    public static Connection Connector() {
        
        try {
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection("jdbc:sqlite:mydatabase.sqlite");
            return conn;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
        
    }
    
}
