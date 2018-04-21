package per.hss.dao;

import per.hss.model.NewsType;
import per.hss.util.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
public class NewswTypeDao {

    /**
     * 遍历所有新闻类别
     * @param con
     * @return
     */
    public List<NewsType> newsTypeList(Connection con)
    {
        List<NewsType> newsTypeList=new ArrayList<>();
        String sql="select * from t_newstype";
        try {
            PreparedStatement ptmt=con.prepareStatement(sql);
            ResultSet re=ptmt.executeQuery();
            while(re.next())
            {
                NewsType newsType=new NewsType();
                newsType.setNewsTypeId(re.getInt("newstypeId"));
                newsType.setTypeName(re.getString("typeName"));
                newsTypeList.add(newsType);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return newsTypeList;
    }
    public NewsType getNewsTypeId(Connection con,String newsTypeId)
    {
        NewsType newsType=new NewsType();
        String sql="select * from t_newsType where newsTypeId=?";
        try {
            PreparedStatement ptmt=con.prepareStatement(sql);
            ptmt.setString(1,newsTypeId);
            ResultSet res=ptmt.executeQuery();
            if(res.next())
            {
                newsType.setNewsTypeId(res.getInt("newsTypeId"));
                newsType.setTypeName(res.getString("typeName"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    return newsType;
    }
    public static void  main(String[] args)
    {
        DbUtil dbUtil=new DbUtil();
        Connection con=dbUtil.getCon();
        NewswTypeDao test=new NewswTypeDao();
        List<NewsType> list=test.newsTypeList(con);
        for(NewsType t:list)
        {
            System.out.println(t.getNewsTypeId());
            System.out.println(t.getTypeName());
        }
    }
}
