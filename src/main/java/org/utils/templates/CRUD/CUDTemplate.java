package org.utils.templates.CRUD;

import java.sql.*;

public abstract class CUDTemplate {

    protected static final String DBURL = "jdbc:mysql://localhost:3306/helpdesk";
    protected static final String USER = "root";
    protected static final String PASS = "";

    public void executePrepare(String sql, String operation) {
        try (
                Connection conn = getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
        ) {
            if("first-admin".equals(operation)){
                executeInsert(stmt);
            }else throw new IllegalArgumentException("Unknown Operation: " + operation);
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    private Connection getConnection() throws SQLException{
        return DriverManager.getConnection(DBURL, USER, PASS);
    }

    protected abstract void executeInsert(PreparedStatement stmt) throws SQLException;
}
