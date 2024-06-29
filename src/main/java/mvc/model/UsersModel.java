package mvc.model;

import org.utils.templates.CRUD.CUDTemplate;
import org.utils.templates.CRUD.RTemplate;
import org.utils.templates.CRUD.SelectOperation;
import org.utils.templates.CRUD.UpdateOperation;

public class UsersModel {

    private final static String getFirstAdmin = "SELECT COUNT(*) AS rowcount from Users";
    private final static String insertFirstAdmin = "INSERT INTO Users (username, role, password) VALUES (?, ?, ?)";

    public boolean noInitSchema(){
        boolean noAdmin;
        RTemplate findAdmin = new SelectOperation();
        noAdmin = (findAdmin.execute(getFirstAdmin, "no-admin") == 0);
        return noAdmin;
    }

    public static void storeFirstAdmin(){
        CUDTemplate firstAdmin = new UpdateOperation();
        firstAdmin.executePrepare(insertFirstAdmin, "first-admin");
    }
}
