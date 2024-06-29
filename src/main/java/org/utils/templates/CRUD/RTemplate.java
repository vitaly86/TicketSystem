package org.utils.templates.CRUD;

import java.sql.*;

public abstract class RTemplate {

    protected static final String DBURL = "jdbc:mysql://localhost:3306/helpdesk";
    protected static final String USER = "root";
    protected static final String PASS = "";

    public int execute(String sql, String operation) {
        int result = 0;
        try (
                Connection conn = getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
        ) {
            if("no-admin".equals(operation)){
                result = executeSelect(rs);
            }else throw new IllegalArgumentException("Unknown Operation: " + operation);
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return result;
    }

    private Connection getConnection() throws SQLException{
        return DriverManager.getConnection(DBURL, USER, PASS);
    }

    protected abstract int executeSelect(ResultSet rs) throws SQLException;
}
