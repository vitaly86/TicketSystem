package org.database;

import org.utils.PasswordUtils;

import java.sql.*;

public class CreateTables extends CreateDB{

    private final static String DB_URL = CreateDB.DB_URL + "helpdesk";
    private final static String USER = CreateDB.USER;
    private final static String PASS = CreateDB.PASS;

    private final static String[] createTableSQLs = {
            "CREATE TABLE IF NOT EXISTS Notes (" +
                    "nid INT(2) AUTO_INCREMENT PRIMARY KEY, " +
                    " ntid INT(4) NOT NULL, " +
                    " nuid INT(4) NOT NULL, " +
                    " text VARCHAR(255) NOT NULL)",

            "CREATE TABLE IF NOT EXISTS Tickets (" +
                    "tid INT(10) AUTO_INCREMENT PRIMARY KEY, " +
                    " beschreibung VARCHAR(1500) NOT NULL, " +
                    " category ENUM(\"E-Mail\", \"Windows\", \"Hardware\", \"Citrix\", \"Software\", \"Sonstiges\") NOT NULL, " +
                    " priority ENUM(\"3-niedrig\", \"2-mittel\", \"1-hoch\") NOT NULL, " +
                    " zeitstempel TIMESTAMP NOT NULL DEFAULT current_timestamp(), " +
                    " status ENUM(\"Neu\", \"In Bearbeitung\", \"Fertig\") NOT NULL DEFAULT \"Neu\", " +
                    " title VARCHAR(150) NOT NULL, " +
                    " ersteller INT(4) NOT NULL, " +
                    " bearbeiter INT(4) NOT NULL)",

            "CREATE TABLE IF NOT EXISTS Users (" +
                    "uid INT(4) AUTO_INCREMENT PRIMARY KEY, " +
                    " username VARCHAR(50) UNIQUE NOT NULL, " +
                    " role ENUM(\"User\", \"Supporter\", \"Admin\") NOT NULL DEFAULT \"User\", " +
                    " password VARCHAR(255) NOT NULL)"
    };

    private final static String[] createForeignKeysSQLs = {
            "ALTER TABLE Notes " +
                    " ADD CONSTRAINT fk_notes_ticket FOREIGN KEY (ntid) " +
                    " REFERENCES Tickets (tid) ON UPDATE CASCADE",

            "ALTER TABLE Notes " +
                    " ADD CONSTRAINT fk_notes_users FOREIGN KEY (nuid) " +
                    " REFERENCES Users (uid) ON UPDATE CASCADE",

            "ALTER TABLE Tickets " +
                    " ADD CONSTRAINT fk_tickets_users FOREIGN KEY (ersteller) " +
                    " REFERENCES Users (uid) ON UPDATE CASCADE"
    };

    private final static QueryDB setInitAdmin = new QueryDB();

    public CreateTables(){
        super();
        try(
                Connection conn_tb = DriverManager.getConnection(DB_URL, USER, PASS);
                Statement stmt = conn_tb.createStatement();
        ){
            for(String sqlTB: createTableSQLs){
                stmt.executeUpdate(sqlTB);
            }

            System.out.println("All Tables created successfully!");

        }catch (SQLException e){
            e.printStackTrace();
        }

        try(
                Connection conn_fk = DriverManager.getConnection(DB_URL, USER, PASS);
                Statement stmt = conn_fk.createStatement();
        ) {
            if (setInitAdmin.noInitSchema()) {

                for (String fKey : createForeignKeysSQLs) {
                    stmt.executeUpdate(fKey);
                }

                System.out.println("Foreign Keys created successfully.");

                storeFirstAdmin();

            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    private static void storeFirstAdmin(){
        String adminName = "admin";
        String adminRole = "admin";
        String adminPassword = "abc123";
        String adminHashedPassword = "";
        try {
            adminHashedPassword = PasswordUtils.hashPassword(adminPassword);
        }catch (Exception e){
            e.printStackTrace();
        }

        String sql = "INSERT INTO Users (username, role, password) VALUES (?, ?, ?)";

        try(
                Connection conn_admin = DriverManager.getConnection(DB_URL, USER, PASS);
                PreparedStatement pstmt = conn_admin.prepareStatement(sql);
                ){
            pstmt.setString(1, adminName);
            pstmt.setString(2, adminRole);
            pstmt.setString(3, adminHashedPassword);
            pstmt.executeUpdate();
            System.out.println("First Admin is Set-On");
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }
}