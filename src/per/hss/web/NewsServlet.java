package per.hss.web;

import per.hss.dao.CommentDao;
import per.hss.dao.NewsDao;
import per.hss.dao.NewswTypeDao;
import per.hss.model.Comment;
import per.hss.model.News;
import per.hss.model.PageBean;
import per.hss.util.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

public class NewsServlet extends HttpServlet {

    DbUtil dbUtil=new DbUtil();
    NewsDao newsDao=new NewsDao();
    NewswTypeDao newswTypeDao=new NewswTypeDao();
    CommentDao commentDao=new CommentDao();//评论

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");

        String action = request.getParameter("action");
        if ("list".equals(action)) {
                this.newsList(request,response);
        }
        if("show".equals(action))
        {
            this.newsShow(request,response);
        }
    }

    protected  void newsShow(HttpServletRequest request,HttpServletResponse response) throws  ServletException,IOException{
        //展示新闻
        String newsId=request.getParameter("newsId");
        Connection con=null;
        try{
            con=dbUtil.getCon();
            newsDao.newsClick(con,newsId);
            Comment s_comment=new Comment(Integer.parseInt(newsId));
            List<Comment> commentList=commentDao.commentList(con,s_comment);
            News news=newsDao.getNewsById(con,newsId);
            request.setAttribute("news",news);
            request.setAttribute("pageCode",this.getUpAndDownPageCode(newsDao.getUpAndDownPageId(con,newsId)));
            request.setAttribute("navCode",NavUtil.genNewsNavigation(news.getTypeName(),news.getTypeId()+"",news.getTitle()));
            request.setAttribute("commentList",commentList);
            request.setAttribute("mainPage","news/newShow.jsp");
            request.getRequestDispatcher("foreground/newsTemp.jsp").forward(request,response);
        }catch(Exception e)
        {
            e.printStackTrace();
        }finally{
            try {
                dbUtil.closeCon(con);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }


    protected void newsList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String typeId=request.getParameter("typeId");
       String page=request.getParameter("page");
       if(StringUtil.isEmpty(page))
       {
           page="1";
       }
        Connection con=null;
        News s_news=new News();
        if(StringUtil.isNotEmpty(typeId))
        {
            s_news.setTypeId(Integer.parseInt(typeId));
        }
        try{
            con=dbUtil.getCon();
            int total=newsDao.newsCount(con,s_news);//获取总记录数
            PageBean pageBean=new PageBean(Integer.parseInt(page),Integer.parseInt(PropertiesUtil.getValue("pageSize")));
            List<News> newestNewsListType=newsDao.newsList(con,s_news,pageBean);
            request.setAttribute("newestNewsListType",newestNewsListType);
            String typeName=newswTypeDao.getNewsTypeId(con,typeId).getTypeName();
            String  navCode= NavUtil.genNewsListNavigation(typeName,typeId);
            request.setAttribute("navCode",navCode);
            String pageNumber=PageUtil.getUpAndDownPagation(total,Integer.parseInt(page),Integer.parseInt(PropertiesUtil.getValue("pageSize")),typeId);
            request.setAttribute("pageNumber",pageNumber);
            request.setAttribute("mainPage","news/newsList.jsp");
            request.getRequestDispatcher("foreground/newsTemp.jsp").forward(request,response);
        }catch(Exception e)
        {
            e.printStackTrace();
        }finally {
            try {
                dbUtil.closeCon(con);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    }
    private String getUpAndDownPageCode(List<News> upAndDownPage){

        News upNews=upAndDownPage.get(0);
        News downNews=upAndDownPage.get(1);
        StringBuffer stringBuffer=new StringBuffer();
        if(upNews.getNewsId()==-1)
        {
            stringBuffer.append("<p>上一篇:没有</p>");

        }else{

            stringBuffer.append("<p>上一篇:&nbsp;&nbsp<a href='news?action=show&newsId="+upNews.getNewsId()+"'>&nbsp;&nbsp;"+upNews.getTitle()+"</a></p>");
        }
         if(downNews.getNewsId()==-1)
         {
             stringBuffer.append("<p>下一篇:没有</p>");

         }else{

             stringBuffer.append("<p>下一篇:&nbsp;&nbsp;<a href='news?action=show&newsId="+downNews.getNewsId()+"'>&nbsp;&nbsp;"+downNews.getTitle()+"</a></p>");
         }
        System.out.println("StringBuffer="+stringBuffer);
        return stringBuffer.toString();
    }
}