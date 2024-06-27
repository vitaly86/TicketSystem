package org.database;

import java.sql.*;

public class queryDB {

    private final static String DB_URL = CreateDB.DB_URL + "helpdesk";
    private final static String USER = CreateDB.USER;
    private final static String PASS = CreateDB.PASS;

    private final static String getFirstAdmin = "SELECT COUNT(*) AS rowcount from Users";

    public boolean noInitSchema(){
        boolean noAdmin = true;

        try(
                Connection conn_admin = DriverManager.getConnection(DB_URL, USER, PASS);
                Statement stmt = conn_admin.createStatement();
                ResultSet rs = stmt.executeQuery(getFirstAdmin);
        ) {
             if(rs.next()){
                 int count = rs.getInt("rowcount");
                 noAdmin = (count == 0);
             }
        } catch(SQLException e) {
            e.printStackTrace();
        }

        return noAdmin;
    }
}
