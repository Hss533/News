<%--加入jar包  使用c标签--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"
         pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!--这个页面最终是要被包括的 head包括 logo 以及导航菜单-->
<div class="row-fluid">
    <div class="span12">
        <img src="${pageContext.request.contextPath}/images/logo2.png">
    </div>

</div>
<div class="row-fluid">
    <div class="span12">
        <div class="navbar">
            <div class="navbar-inner">
                <a class="brand" href="goIndex">首页</a>
                <ul class="nav">
                    <c:forEach var="newsType" items="${newsTypeList}" >
                        <li><a href="news?action=list&typeId=${newsType.newsTypeId}">${newsType.typeName}</a></li>
                    </c:forEach>
                </ul>
            </div>
        </div>
    </div>
</div>
