package org.app.utils.templates.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class DatabaseTemplate {

    public static final String URL = "jdbc:mysql://localhost:3306/";
    public static final String DB = "helpdesk";
    protected static final String USER = "root";
    protected static final String PASS = "";

    public void execute(String source) {
        try (
                Connection conn = getConnection(source);
                Statement stmt = conn.createStatement();
                ) {
            executeSchema(stmt);
        }
        catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    private Connection getConnection(String source) throws SQLException, ClassNotFoundException {
        // Load MySQL JDBC Driver
//        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(source, USER, PASS);
    }

    protected abstract void executeSchema(Statement stmt) throws SQLException;
}
