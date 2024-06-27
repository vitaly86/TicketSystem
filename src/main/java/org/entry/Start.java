package org.entry;

import org.database.createTables;
import org.login.LoginPage;

public class Start {
    public static void main(String[] args) {

        new createTables();
        IDandPasswords idandPasswords = new IDandPasswords();
        LoginPage loginPage = new LoginPage(idandPasswords.getLoginInfo());
    }
}