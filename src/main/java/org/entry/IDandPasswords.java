package org.entry;

import java.util.HashMap;

public class IDandPasswords {
    HashMap<String, String> logininfo = new HashMap<String, String>();

    public IDandPasswords() {
        this.logininfo.put("Bro", "Pizza");
        this.logininfo.put("Brometheus", "PASSWORD");
        this.logininfo.put("BroCode", "abc123");
    }

    protected HashMap<String, String> getLoginInfo() {
        return logininfo;
    }
}
