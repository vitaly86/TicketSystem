package org.utils.templates.CRUD;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SelectOperation extends RTemplate{

    @Override
    protected int executeSelect(ResultSet rs) throws SQLException {
        int count = 0;
        if(rs.next()){
            count = rs.getInt("rowcount");
        }
        return count;
    }
}
