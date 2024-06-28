package mvc.model;

import org.utils.DBConstants;

import java.sql.*;

public class UsersModel extends DBConstants {

    private final static String getFirstAdmin = "SELECT COUNT(*) AS rowcount from Users";

    public boolean noInitSchema(){
        boolean noAdmin = true;

        try(
                Connection conn_admin = DriverManager.getConnection(URL + DB, USER, PASS);
                Statement stmt = conn_admin.createStatement();
                ResultSet rs = stmt.executeQuery(getFirstAdmin);
        ) {
             if(rs.next()){
                 int count = rs.getInt("rowcount");
                 noAdmin = (count == 0);
             }
        } catch(SQLException e) {
            e.printStackTrace();
        }

        return noAdmin;
    }
}
