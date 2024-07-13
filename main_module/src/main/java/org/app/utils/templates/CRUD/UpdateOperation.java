package org.app.utils.templates.CRUD;

import org.app.utils.validations.PasswordUtils;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateOperation extends CUDTemplate{

    @Override
    protected void executeInsert(PreparedStatement pstmt) throws SQLException {
        String adminName = "admin";
        String adminRole = "admin";
        String adminPassword = "abc123";
        String adminHashedPassword = "";
        try {
            adminHashedPassword = PasswordUtils.hashPassword(adminPassword);
        }catch (Exception e){
            e.printStackTrace();
        }
        pstmt.setString(1, adminName);
        pstmt.setString(2, adminRole);
        pstmt.setString(3, adminHashedPassword);
        pstmt.executeUpdate();
        System.out.println("First Admin is Set-On");
    }
}
