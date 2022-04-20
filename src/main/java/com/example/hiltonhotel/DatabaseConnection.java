package com.example.hiltonhotel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;


public class DatabaseConnection {
    public Connection databaselink;

    public Connection getconnection() {
        String databaseName = "hilton_db";
        String databaseUser = "root";
        String databasePass = "";
        String url = "jdbc:mysql://localhost/" + databaseName;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            databaselink=DriverManager.getConnection(url,databaseUser,databasePass);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return databaselink;
    }


}