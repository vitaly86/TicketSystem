package org.database;

public class VerifyKeys {

    private static final VerifyKeys INCREMENT = new VerifyKeys();
    private static int zaehler;

    private VerifyKeys() {
        VerifyKeys.zaehler = 0;
    }

    public static VerifyKeys getIncrement(){
        VerifyKeys.zaehler++;
        return VerifyKeys.INCREMENT;
    }

    public int keyState(){
        return VerifyKeys.zaehler;
    }
}
