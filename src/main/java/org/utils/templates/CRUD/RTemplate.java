package org.utils.templates.CRUD;

import org.jetbrains.annotations.NotNull;

import java.sql.*;
import java.util.HashMap;

public abstract class RTemplate {

    protected static final String DBURL = "jdbc:mysql://localhost:3306/helpdesk";
    protected static final String USER = "root";
    protected static final String PASS = "";

    public Object execute(String sql, String operation) {
        try (
                Connection conn = getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
        ) {
            switch (operation.toLowerCase()) {
                case "no-admin" -> {
                    return initFirstAdmin(rs);
                }
                case "all-users" -> {
                    return findAllUsers(rs);
                }
                case null, default -> throw new IllegalArgumentException("Unknown Operation: " + operation);
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public Object executePrepared(String sql, String operation, String @NotNull ... args) {
        try (
                Connection conn = getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql);
        ) {
            for(int i =0; i < args.length; i++){
                pstmt.setString(i+1, args[i]);
            }
            switch (operation.toLowerCase()) {
                case "user-pass" -> {
                    try(ResultSet rs = pstmt.executeQuery()){
                        return checkUserExist(rs);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }
                case null, default -> throw new IllegalArgumentException("Unknown Operation: " + operation);
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    private Connection getConnection() throws SQLException{
        return DriverManager.getConnection(DBURL, USER, PASS);
    }

    protected abstract int initFirstAdmin(ResultSet rs) throws SQLException;
    protected abstract String checkUserExist(ResultSet rs) throws SQLException;
    protected abstract HashMap<String, String> findAllUsers(ResultSet rs) throws SQLException;
}

