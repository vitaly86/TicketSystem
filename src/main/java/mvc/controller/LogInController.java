package mvc.controller;

import mvc.model.UsersModel;
import mvc.view.LogInTemplate;

import java.util.HashMap;

public class LogInController {

    public LogInController() {
        new UsersModel();
        new LogInTemplate(new IDandPasswords().getLogInInfo());
    }

    private static class IDandPasswords {
        HashMap<String, String> logininfo = new HashMap<>();

        public IDandPasswords() {
            this.logininfo.put("Bro", "Pizza");
            this.logininfo.put("Brometheus", "PASSWORD");
            this.logininfo.put("BroCode", "abc123");
        }

        private HashMap<String, String> getLogInInfo() {
            return logininfo;
        }
    }
}
