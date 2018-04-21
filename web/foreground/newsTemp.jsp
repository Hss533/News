<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: hu
  Date: 2018/4/10
  Time: 19:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="${pageContext.request.contextPath}/style/news.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/bootstrap/css/bootstrap-responsive.css" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/bootstrap/js/jQuery.js"></script>
    <script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.js"></script>
    <title>新闻列表</title>
</head>
<body>
    <div class="container">
        <jsp:include page="/foreground/common/head.jsp"/>
        <div class="row-fluid">
            <div>
                <div class="span8">
                    <jsp:include page="${mainPage}"/>
                </div>
                <div class="span4">
                    <!--这里放热门新闻和最新更新-->
                    <div class="data_list right_news_list">
                        <div class="dataHeader">热点新闻</div>
                        <div class="datas">
                            <ul>
                                <c:forEach items="${hotNewsList}" var="hotNewsList">
                                    <li><a href="news?action=show&newsId=${hotNewsList.newsId}" title="${hotNewsList.title}">${hotNewsList.title}</a></li>
                                </c:forEach>
                            </ul>
                        </div>
                    </div>
                    <div class="data_list right_news_list">
                        <div class="dataHeader">最近更新</div>
                        <div class="datas">
                            <ul>
                                <c:forEach items="${newsestNewsList}" var="newsestNews">
                                    <li><a href="news?action=show&newsId=${newsestNews.newsId}" title="${newsestNews.title}">${newsestNews.title}</a></li>
                                </c:forEach>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <jsp:include page="/foreground/common/foot.jsp"/>
    </div>
</body>
</html>
