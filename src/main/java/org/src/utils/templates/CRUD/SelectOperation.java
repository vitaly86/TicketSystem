package org.src.utils.templates.CRUD;

import org.jetbrains.annotations.NotNull;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class SelectOperation extends RTemplate{

    @Override
    protected int initFirstAdmin(@NotNull ResultSet rs) throws SQLException {
        int count = 0;
        if(rs.next()){
            count = rs.getInt("rowcount");
        }
        return count;
    }

    @Override
    protected String checkUserExist(@NotNull ResultSet rs) throws SQLException {
        String storedPassword;
        if(rs.next()){
            storedPassword = rs.getString("password");
        }else{
            storedPassword = "not found";
        }
        return storedPassword;
    }

    @Override
    protected HashMap<String, String> findAllUsers(@NotNull ResultSet rs) throws SQLException {
        HashMap<String, String> container = new HashMap<>();
        while(rs.next()){
            container.put(rs.getString("username"), rs.getString("password"));
        }
        return container;
    }
}
