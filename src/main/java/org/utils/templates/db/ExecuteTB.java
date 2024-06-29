package org.utils.templates.db;

import org.jetbrains.annotations.NotNull;

import java.sql.SQLException;
import java.sql.Statement;

public class ExecuteTB extends DatabaseTemplate{

    private final String[] queryTables;

    public ExecuteTB(String[] queryTables) {
        this.queryTables = queryTables;
    }

    @Override
    protected void executeSchema(@NotNull Statement stmt) throws SQLException {
        for(String sqlTB: queryTables){
            stmt.executeUpdate(sqlTB);
        }
    }
}
