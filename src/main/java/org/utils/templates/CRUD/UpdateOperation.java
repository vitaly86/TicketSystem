package org.utils.templates.CRUD;

import org.utils.PasswordUtils;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateOperation extends CUDTemplate{

    @Override
    protected void executeInsert(PreparedStatement stmt) throws SQLException {
        String adminName = "admin";
        String adminRole = "admin";
        String adminPassword = "abc123";
        String adminHashedPassword = "";
        try {
            adminHashedPassword = PasswordUtils.hashPassword(adminPassword);
        }catch (Exception e){
            e.printStackTrace();
        }
        stmt.setString(1, adminName);
        stmt.setString(2, adminRole);
        stmt.setString(3, adminHashedPassword);
        stmt.executeUpdate();
        System.out.println("First Admin is Set-On");
    }
}
