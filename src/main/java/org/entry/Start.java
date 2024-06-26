package org.entry;

import org.login.LoginPage;

public class Start {
    public static void main(String[] args) {

        IDandPasswords idandPasswords = new IDandPasswords();

        LoginPage loginPage = new LoginPage(idandPasswords.getLoginInfo());
    }
}