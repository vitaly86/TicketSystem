package org.entry;

import mvc.controller.LogInController;
import mvc.model.UsersModel;
import mvc.gui.LogInTemplate;
import org.database.Schema;

public class Start {
    public static void main(String[] args) {
        /*
          Create Database
         */
        new Schema();

        /*
          User Anmeldung Fenster
         */
        UsersModel logInModel = new UsersModel();
        LogInTemplate logInView = new LogInTemplate();
        new LogInController(logInModel, logInView);
    }
}