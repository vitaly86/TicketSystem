package org.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateDB {

    public static final String DB_URL= "jdbc:mysql://localhost:3306/";
    private static final String USER = "root";
    private static final String PASS = "";

    public CreateDB() {
        try (
                Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
                Statement stmt = conn.createStatement();
        ){
            String sql = "CREATE DATABASE IF NOT EXISTS ticketsystem";
            stmt.executeUpdate(sql);
            System.out.println("Database created successfully...");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
