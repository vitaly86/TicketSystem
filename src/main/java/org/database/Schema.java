package org.database;

import mvc.model.UsersModel;
import org.utils.templates.db.DatabaseTemplate;
import org.utils.templates.db.ExecuteDB;
import org.utils.templates.db.ExecuteTB;

public class Schema {

    public Schema() {
        String sqlDB = "CREATE DATABASE IF NOT EXISTS helpdesk";
        DatabaseTemplate executeDB = new ExecuteDB(sqlDB);
        executeDB.execute(ExecuteDB.URL);
        new SchemaTables();
    }

    public static class SchemaTables{

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

        private final static UsersModel setInitAdmin = new UsersModel();

        public SchemaTables() {

            DatabaseTemplate executeTB = new ExecuteTB(createTableSQLs);
            executeTB.execute(ExecuteTB.URL + ExecuteTB.DB);
            System.out.println("All Tables created successfully!");

            if (setInitAdmin.noInitSchema()) {
                DatabaseTemplate executeFK = new ExecuteTB(createForeignKeysSQLs);
                executeFK.execute(ExecuteTB.URL + ExecuteTB.DB);
                System.out.println("Foreign Keys created successfully.");
                UsersModel.storeFirstAdmin();
            }
        }
    }
}
