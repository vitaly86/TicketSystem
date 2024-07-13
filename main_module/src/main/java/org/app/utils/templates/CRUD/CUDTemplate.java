package org.app.utils.templates.CRUD;

import java.sql.*;

public abstract class CUDTemplate {

    protected static final String DBURL = "jdbc:mysql://localhost:3306/helpdesk";
    protected static final String USER = "root";
    protected static final String PASS = "";

    public void executePrepare(String sql, String operation) {
        try (
                Connection conn = getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql);
        ) {
            if("first-admin".equals(operation)){
                executeInsert(pstmt);
            }else throw new IllegalArgumentException("Unknown Operation: " + operation);
        }
        catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    private Connection getConnection() throws SQLException, ClassNotFoundException {
        // Load MySQL JDBC Driver
//        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(DBURL, USER, PASS);
    }

    protected abstract void executeInsert(PreparedStatement pstmt) throws SQLException;
}
