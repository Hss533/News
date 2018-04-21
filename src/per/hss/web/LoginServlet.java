package per.hss.web;

import per.hss.dao.UserDao;
import per.hss.model.User;
import per.hss.util.DbUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;

public class LoginServlet extends HttpServlet{

    DbUtil db=new DbUtil();
    UserDao userDao=new UserDao();
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("utf-8");
        String action=request.getParameter("action");
        if("login".equals(action))
        {
            userLogin(request,response);
        }
        if("logout".equals(action))
        {
            logout(request,response);
        }
    }
    private void userLogin(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException
    {
        HttpSession session=request.getSession();
        Connection con=db.getCon();
        String userName=request.getParameter("userName");
        String password=request.getParameter("password");
        User user=new User(userName,password);
        User currentUser=userDao.login(con,user);
        if(currentUser==null)
        {
            //登陆失败
            request.setAttribute("userName",userName);
            request.setAttribute("password",password);
            request.setAttribute("error","用户名或密码错误");
            request.getRequestDispatcher("background/login.jsp").forward(request,response);
        }
        else{
            session.setAttribute("user",currentUser);//设置session
            request.setAttribute("mainPage","background/default.jsp");
            request.getRequestDispatcher("background/main.jsp").forward(request,response);
        }

    }
    public void  logout(HttpServletRequest request,HttpServletResponse response)
    throws ServletException,IOException
    {
        request.getSession().invalidate();//删除session
        System.out.println(request.getContextPath()+"/background/login.jsp");
        response.sendRedirect(request.getContextPath()+"/background/login.jsp");//注销之后返回登录页面
    }
}
