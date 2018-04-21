<%--
  Created by IntelliJ IDEA.
  User: hu
  Date: 2018/4/12
  Time: 19:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<div class="data_list">
    <div>
        <div>
            ${navCode}
        </div>
        <div class="news_title">${news.title}</div>
        <div class="news_info">
            发布时间：[<fmt:formatDate value="${news.publishDate}" type="date" pattern="yyyy-MM-dd"/>]
            &nbsp;&nbsp;作者${news.author}
            &nbsp;&nbsp;新闻类别${news.typeName}
            &nbsp;&nbsp;点击次数${news.click}
        </div>
        <div class="news_content">
            ${news.content}
        </div>
        <div>
            <!--评论-->
            用户评论:
            <ul>
                <c:forEach items="${commentList}" var="comment">
                    <li>${comment.content}</li>
                </c:forEach>
            </ul>
        </div>
        <div>
            ${pageCode}
        </div>
        <!--添加评论-->
        <div>
            <form action="comment?action=save" method="post">
                <div>
                    <input type="text" value="${news.newsId}" id="newsId" name="newsId">
                    <textarea style="width: 98%" rows="3" id="content" name="content"></textarea>
                </div>
                <div >
                    <button class="btn btn-primary" type="submit">发表评论</button>
                </div>
            </form>
        </div>
    </div>
</div>