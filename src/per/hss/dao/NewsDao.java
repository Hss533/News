package per.hss.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import per.hss.model.News;
import per.hss.model.PageBean;
import per.hss.util.DateUtil;
import per.hss.util.DbUtil;
import per.hss.util.PropertiesUtil;

import javax.servlet.ServletException;

public class NewsDao {

     public List<News> newsList(Connection con,String sql){

         List<News> newsList=new ArrayList<>();
         try {
             PreparedStatement pstmt=con.prepareStatement(sql);
             ResultSet rs=pstmt.executeQuery();
             while(rs.next()){
                 News news=new News();
                 news.setNewsId(rs.getInt("newsId"));
                 news.setTitle(rs.getString("title"));
                 news.setContent(rs.getString("content"));
                 news.setPublishDate(DateUtil.stringToDate(rs.getString("publishDate"), "yyyy-MM-dd HH:mm:ss"));
                 news.setAuthor(rs.getString("author"));
                 news.setTypeId(rs.getInt("typeId"));
                 news.setClick(rs.getInt("click"));
                 news.setIsHead(rs.getInt("isHead"));
                 news.setImageName(PropertiesUtil.getValue("userImage")+rs.getString("imageName"));
                 news.setIsHot(rs.getInt("isHot"));
                 newsList.add(news);
             }
         } catch (SQLException e) {
             e.printStackTrace();
         }
         return newsList;
    }

    /**
     * 在新闻显示界面获取新闻  还要进行分页操作
     * @param con
     * @param s_news
     * @param pageBean
     * @return
     * @throws Exception
     */
    public List<News> newsList(Connection con, News s_news, PageBean pageBean)throws Exception
    {
        List<News> newsList=new ArrayList<>();

        StringBuffer sb=new StringBuffer("select * from t_news t1,t_newsType t2 where t1.typeId=t2.newsTypeId");//外键等于主键
        if(s_news.getTypeId()!=-1)
        {
            sb.append(" and t1.typeId="+s_news.getTypeId());
        }
        sb.append(" order by t1.publishDate desc ");
        if(pageBean!=null)
        {
            sb.append(" limit "+pageBean.getStart()+","+pageBean.getPageSize());
        }
        try {

            PreparedStatement pstmt=con.prepareStatement(sb.toString());
            ResultSet rs=pstmt.executeQuery();
            while(rs.next()){
                News news=new News();
                news.setNewsId(rs.getInt("newsId"));
                news.setTitle(rs.getString("title"));
                news.setContent(rs.getString("content"));
                news.setTypeId(rs.getInt("typeId"));
                news.setClick(rs.getInt("click"));
                news.setIsHead(rs.getInt("isHead"));
                news.setPublishDate(DateUtil.stringToDate(rs.getString("publishDate"), "yyyy-MM-dd HH:mm:ss"));
                news.setAuthor(rs.getString("author"));
                news.setImageName(PropertiesUtil.getValue("userImage")+rs.getString("imageName"));
                news.setIsHot(rs.getInt("isHot"));
                newsList.add(news);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return newsList;
    }

    /**
     * 返回总记录数
     * @param con
     * @param s_news
     * @return
     */
    public int newsCount(Connection con,News s_news)
    {
        int returnNum=0;
        StringBuffer sb=new StringBuffer("select count(*) as total from t_news");
        if(s_news.getTypeId()!=-1)
        {
            sb.append(" and typeId="+s_news.getTypeId());

        }
        try {
            PreparedStatement ptmt=con.prepareStatement(sb.toString().replaceFirst("and","where"));
            ResultSet rs=ptmt.executeQuery();
            if(rs.next())
            {
                returnNum= rs.getInt("total");
            }else returnNum=0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return returnNum;
    }

    /**
     * 根据新闻的id获取新闻
     * @param con
     * @param newsId
     * @return
     */
    public News getNewsById(Connection con,String newsId) {
        String sql=" select * from t_news t1,t_newstype t2 where t1.typeId=t2.newstypeId and t1.newsId=?";
        News news=new News();
        try {
            PreparedStatement ptmt=con.prepareStatement(sql);
            ptmt.setString(1,newsId);
            ResultSet res=ptmt.executeQuery();

            while(res.next()){

                news.setNewsId(res.getInt("newsId"));
                news.setTitle(res.getString("title"));
                news.setContent(res.getString("content"));
                news.setPublishDate(DateUtil.stringToDate(res.getString("publishDate"),"yyyy-MM-dd HH:mm:ss"));
                news.setAuthor(res.getString("author"));
                news.setTypeName(res.getString("typeName"));
                news.setImageName(PropertiesUtil.getValue("userImage")+res.getString("imageName"));
                news.setTypeId(res.getInt("typeId"));
                news.setClick(res.getInt("click"));
                news.setIsHead(res.getInt("isHead"));
                news.setIsHot(res.getInt("isHot"));

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return news;
    }
    /**
     * 实现点击一次浏览次数加一    实际上就是show一次浏览次数加1

     * @throws ServletException
     * @throws IOException
     */
    public int newsClick(Connection con,String newsId) throws Exception{
        String sql="update t_news set click=click+1 where newsId=?";
        PreparedStatement ptmt=con.prepareStatement(sql);
        ptmt.setString(1,newsId);
        return ptmt.executeUpdate();
    }

    /**
     * 实现上一篇和下一篇 啊哈哈哈哈哈哈
     * @param newsId
     * @return
     */
    public List<News> getUpAndDownPageId(Connection con,String newsId) {
        List<News> upAndDownPage = new ArrayList<>();
        String sql = "select * from t_news where newsId<? order by newsId desc limit 1";
        try {
            PreparedStatement ptmt = con.prepareStatement(sql);
            ptmt.setString(1, newsId);
            ResultSet rs = ptmt.executeQuery();
            if (rs.next()) {//写一个构造方法
                upAndDownPage.add(new News(rs.getInt("newsId"), rs.getString("title")));
            } else {
                upAndDownPage.add(new News(-1, " "));
            }

            sql = "select * from t_news where newsId>? order by newsId asc limit 1";
            try {
                ptmt = con.prepareStatement(sql);
                ptmt.setString(1, newsId);
                rs = ptmt.executeQuery();
                if (rs.next()) {//写一个构造方法
                    upAndDownPage.add(new News(rs.getInt("newsId"), rs.getString("title")));
                } else {
                    upAndDownPage.add(new News(-1, ""));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        return upAndDownPage
                ;
    }
    public static void main(String[] args)
    {
        DbUtil dbUtil=new DbUtil();
        Connection con=dbUtil.getCon();
        String  sql="select * from t_news where isHead=1 order by publishDate desc limit 0,1";
        NewsDao test=new NewsDao();
        News news=new News();
        news.setTypeId(2);
        test.newsCount(con,news);
        System.out.println(test.newsCount(con,news));
    }
}
