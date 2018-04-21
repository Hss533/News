<%--
  Created by IntelliJ IDEA.
  User: hu
  Date: 2018/4/14
  Time: 12:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript">
    function setDateTime()
    {
        var date=new Date();
        var day=date.getDay();
        var week;
        switch (day)
        {
            case 0:week="星期日";break;
            case 1:week="星期一";break;
            case 2:week="星期二";break;
            case 3:week="星期三";break;
            case 4:week="星期四";break;
            case 5:week="星期五";break;
            case 6:week="星期六";break;

        }
        var today=date.getFullYear()+"年"+(date.getMonth()+1)+"月"+date.getDate()+"日 "+week+date.getHours()+":"+date.getMinutes()+":"+date.getSeconds();
        document.getElementById("today").innerHTML=today;
    }
    window.setInterval("setDateTime()",1000);
    function logout()
    {
      if(confirm("您确定要退出系统么？"))
      {
          window.location.href='login?action=logout';
      }
    }
</script>
<div class="row-fluid">
    <div class="span12">
        <div>
            <div class="headLeft">
                <img src="${pageContext.request.contextPath}/images"><!--照片路径-->
            </div>
            <div class="headRight">
                欢迎你，管理员&nbsp;&nbsp;&nbsp;&nbsp;${user.userName}
                <a onclick="logout()">[&nbsp;安全退出&nbsp;]</a >
                <span id="today" class="currentDateTime"></span>
            </div>
        </div>
    </div>
</div>