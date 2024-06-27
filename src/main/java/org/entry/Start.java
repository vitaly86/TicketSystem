package org.entry;

import org.database.CreateTables;
import org.login.LoginPage;

public class Start {
    public static void main(String[] args) {

        new CreateTables();
        IDandPasswords idandPasswords = new IDandPasswords();
        LoginPage loginPage = new LoginPage(idandPasswords.getLoginInfo());
    }
}