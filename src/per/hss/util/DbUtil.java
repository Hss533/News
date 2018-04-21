package per.hss.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbUtil {
    public Connection getCon()
    {
        Connection con=null;
        try {
            Class.forName(PropertiesUtil.getValue("jdbcName"));
            con=DriverManager.getConnection(PropertiesUtil.getValue("dbUrl"),PropertiesUtil.getValue("dbUserName"),PropertiesUtil.getValue("dbPassword"));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return con;
    }
    public void closeCon(Connection con)
    {
        if(con!=null)
        {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
