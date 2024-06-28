package mvc.controller;

import mvc.model.IDandPasswords;
import mvc.model.UsersModel;
import mvc.view.LogInTemplate;

public class LogInController {

    public LogInController() {
        new UsersModel();
        new LogInTemplate(new IDandPasswords().getLogInInfo());
    }
}
