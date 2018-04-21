package per.hss.dao;

import per.hss.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDao  {
    public User login(Connection con,User user)
    {
        String sql="select * from t_user where userName=? and password=?";
        User currentUser=null;
        try {
            PreparedStatement ptmt=con.prepareStatement(sql);
            ptmt.setString(1,user.getUserName());
            ptmt.setString(2,user.getPassword());
            ResultSet res=ptmt.executeQuery();
            if(res.next())
            {
                currentUser=new User();
                currentUser.setPassword(res.getString("password"));
                currentUser.setUserName(res.getString("userName"));
                currentUser.setUserId(res.getInt("userId"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return currentUser;
    }
}
