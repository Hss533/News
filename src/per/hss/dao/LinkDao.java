package per.hss.dao;
import per.hss.model.Link;
import per.hss.model.Link.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.sql.Connection;

public class LinkDao {

    public List<Link> linkList(Connection con,String sql)
    {
        List<Link> linkList=new ArrayList<>();
        try {
            PreparedStatement ptmt=con.prepareStatement(sql);
            ResultSet rs=ptmt.executeQuery();
            while(rs.next())
            {
                 Link link=new Link();
                 link.setLinkId(rs.getInt("linkId"));
                 link.setLinkName(rs.getString("linkName"));
                 link.setLinkUrl(rs.getString("linkUrl"));
                 link.setLinkEmail(rs.getString("linkEmail"));
                 link.setOrderNum(rs.getInt("orderNum"));
                  linkList.add(link);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return linkList;
    }

}
