package com.example.hiltonhotel;
import java.sql.Connection;
import java.sql.DriverManager;
;


public class DatabaseConnection {
    public Connection databaselink;

    public Connection getconnection() {
        String databaseName = "hilton_db";
        String databaseUser = "root";
        String databasePass = "";
        String url = "jdbc:mysql://localhost/" + databaseName;

        try {

            databaselink=DriverManager.getConnection(url,databaseUser,databasePass);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return databaselink;
    }


}