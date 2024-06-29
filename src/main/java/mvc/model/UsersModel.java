package mvc.model;

import org.utils.templates.CRUD.CUDTemplate;
import org.utils.templates.CRUD.RTemplate;
import org.utils.templates.CRUD.SelectOperation;
import org.utils.templates.CRUD.UpdateOperation;

public class UsersModel {

    private final static String insertFirstAdmin = "INSERT INTO Users (username, role, password) VALUES (?, ?, ?)";

    private final static String getFirstAdmin = "SELECT COUNT(*) AS rowcount from Users";
    private final static String getUserCredetials = "SELECT password FROM users WHERE username = ?";


    public boolean noInitSchema(){
        boolean noAdmin;
        RTemplate findAdmin = new SelectOperation();
        noAdmin = ((int)findAdmin.execute(getFirstAdmin, "no-admin") == 0);
        return noAdmin;
    }

    public static void storeFirstAdmin(){
        CUDTemplate firstAdmin = new UpdateOperation();
        firstAdmin.executePrepare(insertFirstAdmin, "first-admin");
    }

    public String authenticate(String username){
        String currentPass;
        RTemplate displayUserPass = new SelectOperation();
        currentPass = (String) displayUserPass.executePrepared(getUserCredetials, "user-pass", username);
//        System.out.println(currentPass);
        return currentPass;
    }
}
