package org.entry;

import mvc.controller.LogInController;
import mvc.model.UsersModel;
import mvc.view.LogInTemplate;
import org.database.Schema;

public class Start {
    public static void main(String[] args) {
        new Schema();

        UsersModel logInModel = new UsersModel();
        LogInTemplate logInView = new LogInTemplate();
        new LogInController(logInModel, logInView);
    }
}