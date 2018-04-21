package per.hss.web;

import per.hss.dao.LinkDao;
import per.hss.dao.NewsDao;
import per.hss.dao.NewswTypeDao;
import per.hss.model.Link;
import per.hss.model.News;
import per.hss.model.NewsType;
import per.hss.util.DbUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class IndexServlet extends HttpServlet {

    /**
     * 这个类的作用是将整个静态页面实现动态化
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */

    DbUtil dbUtil=new DbUtil();
    NewsDao newsDao=new NewsDao();
    NewswTypeDao newsTypeDao=new NewswTypeDao();
    LinkDao linkDao=new LinkDao();

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       this.doPost(req,resp);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        Connection con=null;
        try{
            con=dbUtil.getCon();
            //新闻类别
            List<NewsType> newsTypeList=newsTypeDao.newsTypeList(con);


            //轮播新闻图片新闻
            String sql="select * from t_news where isImage=1 order by publishDate desc limit 0,5";
            List<News> imageNewsList=newsDao.newsList(con,sql);
            request.setAttribute("imageNewsList",imageNewsList);

            //新闻头条
            String sql1="select * from t_news where isHead=1 order by publishDate desc limit 0,1";
            List<News> headNewsList=newsDao.newsList(con,sql1);
            News headNews=headNewsList.get(0);
           // headNews.setContent(StringUtil.Html2Text(headNews.getImageName()));//获得不含html标签的文本???这个为什么不行啊？？？？
            request.setAttribute("headNews",headNews);

            //最近更新
            String sql2="select * from t_news  order by publishDate desc limit 0,8";
            List<News> updateNewsList=newsDao.newsList(con,sql2);
            request.setAttribute("updateNewsList",updateNewsList);

            //热点新闻
            String sql3="select * from t_news where isHot=1 order by publishDate desc limit 0,8 ";
            List<News> hotSpotNewsList=newsDao.newsList(con, sql3);
            request.setAttribute("hotSpotNewsList", hotSpotNewsList);
            System.out.println(hotSpotNewsList.size());
            for(News n:hotSpotNewsList){
                System.out.println(n.getTitle());
                System.out.println(n.getNewsId());
            }

            //辣么多的新闻
            List<List<News>> allIndexNewsList=new ArrayList<>();
            if(newsTypeList!=null&&newsTypeList.size()!=0)
            {
                for(int i=0;i<newsTypeList.size();i++)
                {
                    NewsType newsType=newsTypeList.get(i);
                    String sql4="select * from t_news,t_newsType where typeId=newstypeId and typeId="+newsType.getNewsTypeId()+" order by "+
                            "publishDate desc limit 0,8";
                    List<News> onSublist=newsDao.newsList(con,sql4);
                    allIndexNewsList.add(onSublist);
                }
            }
            request.setAttribute("allIndexNewsList",allIndexNewsList);

            //友情连接
            String sql5="select * from t_link order by orderNum";
            List<Link> linkList=linkDao.linkList(con,sql5);
            request.setAttribute("linkList",linkList);

            //
            request.getRequestDispatcher("index.jsp").forward(request,response);
        }catch(Exception  e)
        {
            e.printStackTrace();
        }finally
        {
            try{
                dbUtil.closeCon(con);
            }catch(Exception e)
            {
                e.printStackTrace();
            }
        }
    }
}
