package org.database;

import java.sql.SQLException;

public class createTables extends CreateDB{

    private static final String DB_Name = "ticketsystem";
    private static final String DBPath = CreateDB.DB_URL + DB_Name;

    public createTables() throws SQLException {
        super();
    }
}