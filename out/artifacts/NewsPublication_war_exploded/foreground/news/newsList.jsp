<%--
  Created by IntelliJ IDEA.
  User: hu
  Date: 2018/4/10
  Time: 20:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div class="data_list">
    <div class="dataHeader navi">
        ${navCode}
    </div>
    <div class="datas">

        <ul>
            <c:forEach items="${newestNewsListType}" var="newestNews">
                <li><span >[<fmt:formatDate value="${newestNews.publishDate}" type="date" pattern="yyyy-MM-dd"/>]</span>&nbsp;<a href="news?action=show&newsId=${newestNews.newsId}" title="${newestNews.title}">${fn:substring(newestNews.title,0 ,42)}</a></li>
            </c:forEach>
        </ul>
    </div>
    <div>
        ${pageNumber}
    </div>
</div>
