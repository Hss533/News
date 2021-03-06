<%--
  Created by IntelliJ IDEA.
  User: hu
  Date: 2018/4/13
  Time: 23:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>后台管理系统</title>
    <link href="${pageContext.request.contextPath}/style/news.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/bootstrap/css/bootstrap-responsive.css" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/bootstrap/js/jQuery.js"></script>
    <script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.js"></script>
    <%
        String mainPage=request.getParameter("mainPage");
        if(mainPage==null||mainPage.equals(""))
        {
            mainPage="/background/default.jsp";
        }
    %>
</head>
<body>
<div class="container">
<jsp:include page="/background/common/head.jsp"/>
    <div class="row-fluid">
        <div class="span3">
            <div class="newsMenu">
                <ul class="nav nav-tabs nav-stacked">
                    <li><a href=""><strong>主页</strong></a></li>
                    <li><a href="#"><strong>新闻管理</strong></a></li>
                    <li><a href="#">&nbsp;&nbsp;新闻添加</a></li>
                    <li><a href="#">&nbsp;&nbsp;新闻维护</a></li>
                    <li><a href=""><strong>新闻评论管理</strong></a></li>
                    <li><a href="#">&nbsp;&nbsp;新闻评论维护</a></li>
                    <li><a href=""><strong>新闻类别管理</strong></a></li>
                    <li><a href="#">&nbsp;&nbsp;新闻类别添加</a></li>
                    <li><a href="#">&nbsp;&nbsp;新闻类别维护</a></li>
                    <li><a href=""><strong>友情连接管理</strong></a></li>
                    <li><a href="#">&nbsp;&nbsp;友情连接添加</a></li>
                    <li><a href="#">&nbsp;&nbsp;友情连接维护</a></li>
                    <li><a href=""><strong>系统管理</strong></a></li>
                    <li><a href="#">&nbsp;&nbsp;刷新服务器缓存</a></li>
                </ul>
            </div>
        </div>

        <div class="span9">
            <jsp:include page="<%=mainPage%>"/>
        </div>
    </div>
    <jsp:include page="/background/common/foot.jsp"/>
</div>
</body>
</html>
