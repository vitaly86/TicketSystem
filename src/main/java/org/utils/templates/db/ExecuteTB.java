package org.utils.templates.db;

import mvc.model.UsersModel;
import org.jetbrains.annotations.NotNull;

import java.sql.SQLException;
import java.sql.Statement;

public class ExecuteTB extends DatabaseTemplate{

    private final String[] queryTables;
    private final static UsersModel setInitAdmin = new UsersModel();

    public ExecuteTB(String[] queryTables) {
        this.queryTables = queryTables;
    }

    @Override
    protected void executeSchema(@NotNull Statement stmt) throws SQLException {
        stmt.executeUpdate(queryTables[0]);
        if (setInitAdmin.noInitSchema()) {
            for (int i = 1; i < queryTables.length; i++) {
                stmt.executeUpdate(queryTables[i]);
            }
            UsersModel.storeFirstAdmin();
            System.out.println("All Tables created successfully!");
        }
    }
}
