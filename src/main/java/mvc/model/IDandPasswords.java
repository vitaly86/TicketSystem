package mvc.model;

import java.util.HashMap;

public class IDandPasswords {
    HashMap<String, String> logininfo = new HashMap<>();

    public IDandPasswords() {
        this.logininfo.put("Bro", "Pizza");
        this.logininfo.put("Brometheus", "PASSWORD");
        this.logininfo.put("BroCode", "abc123");
    }

    public HashMap<String, String> getLogInInfo() {
        return logininfo;
    }
}
