<%--
  Created by IntelliJ IDEA.
  User: hu
  Date: 2018/4/13
  Time: 21:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>后台登陆界面</title>
</head>
<body>
<div>
    <form action="${pageContext.request.contextPath}/login?action=login" method="post">
        用户名<input type="text" name="userName" id="userName" value="${userName}">
        密码<input type="password" name="password" id="password" value="${password}">
        ${error}
        <input type="submit" value="登录">
    </form>
</div>
</body>
</html>
