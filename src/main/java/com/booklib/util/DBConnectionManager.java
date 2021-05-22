package com.booklib.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnectionManager {
    
    private static Connection con;
    private static String url = "jdbc:mysql://localhost:3306/booklib";
    private static String username = "root";
    private static String password = "";
    
    public static Connection getConnection(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url,username,password);
        }catch(ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }
        return con;
    }
    
}
