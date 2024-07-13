package org.app.utils.templates.db;

import org.jetbrains.annotations.NotNull;
import java.sql.SQLException;
import java.sql.Statement;

public class ExecuteDB extends DatabaseTemplate{

    private final String query;

    public ExecuteDB(String query) {
        this.query = query;
    }

    @Override
    protected void executeSchema(@NotNull Statement stmt) throws SQLException {
        stmt.executeUpdate(query);
        System.out.println("Database created successfully...");
    }
}
