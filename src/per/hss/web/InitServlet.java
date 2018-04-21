package per.hss.web;

import per.hss.dao.NewsDao;
import per.hss.dao.NewswTypeDao;
import per.hss.model.News;
import per.hss.model.NewsType;
import per.hss.util.DbUtil;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import java.sql.Connection;
import java.util.List;

public class InitServlet extends HttpServlet {
    DbUtil dbUtil=new DbUtil();
    NewsDao newsDao=new NewsDao();
    NewswTypeDao newswTypeDao=new NewswTypeDao();

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext application=config.getServletContext();
        this.refreshSystem(application);
    }
    public void refreshSystem(ServletContext application)
    {
        Connection con=null;
        try{
            con=dbUtil.getCon();
            //新闻类别
            List<NewsType> newsTypeList=newswTypeDao.newsTypeList(con);
            application.setAttribute("newsTypeList",newsTypeList);
            for(NewsType n:newsTypeList) {
                System.out.println(n.getTypeName());
            }

            //最新更新
            String sql="select * from t_news order by publishDate desc limit 0,8";
            List<News> newsestNewsList=newsDao.newsList(con,sql);
            application.setAttribute("newsestNewsList",newsestNewsList);
            for(News n:newsestNewsList)
            {
                System.out.println(n.getTitle());
            }

            //热点新闻
            sql="select * from t_news order by click desc limit 0,8";
            List<News> hotNewsList=newsDao.newsList(con,sql);
            application.setAttribute("hotNewsList",hotNewsList);
        }catch(Exception e)
        {
            e.printStackTrace();
        }finally {
            try{

                dbUtil.closeCon(con);

            }catch(Exception e)
            {
                e.printStackTrace();
            }
        }
    }
}
