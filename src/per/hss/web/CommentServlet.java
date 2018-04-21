package per.hss.web;

import per.hss.dao.CommentDao;
import per.hss.model.Comment;
import per.hss.util.DbUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;

public class CommentServlet extends HttpServlet {

    DbUtil dbUtil=new DbUtil();
    CommentDao commentDao=new CommentDao();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String action=request.getParameter("action");
        if("save".equals(action))
        {
            commentSave(request,response);
        }
    }

    /**
     * 保存评论
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void commentSave(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
        String newsId=request.getParameter("newsId");
        String content=request.getParameter("content");
        String userIp=request.getRemoteAddr();
        Comment comment=new Comment(Integer.parseInt(newsId),content,userIp);
        Connection con=null;
        con=dbUtil.getCon();
        commentDao.commentAdd(con,comment);
        request.getRequestDispatcher("news?action=show&newsId="+newsId).forward(request,response);

    }
}
