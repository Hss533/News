<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%--
  Created by IntelliJ IDEA.
  User: hu
  Date: 2018/4/6
  Time: 16:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>

<html>
  <head>
   <meta http-equiv="Content-Type" content="text/html;charset=UFT-8">
    <title></title>
      <link href="${pageContext.request.contextPath}/style/news.css" rel="stylesheet">
      <link href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.css" rel="stylesheet">
      <link href="${pageContext.request.contextPath}/bootstrap/css/bootstrap-responsive.css" rel="stylesheet">
      <script src="${pageContext.request.contextPath}/bootstrap/js/jQuery.js"></script>
      <script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.js"></script>

  </head>
  <body>
  <div class="container">
    <jsp:include page="foreground/common/head.jsp"/>
    <div class="row-fluid">
      <div class="span9">
        <div>
          <DIV style="width: 330px; height: 228px;" class="tuhuo"></DIV>

        </div>
        <div class="newsHeader_list">
          <div class="newsHeader">
            <div class="newsHeader"><!--头条-->
              <h3><a href="news?action=show=NewsId=${headNews.newsId}" title="${headNews.title}">${fn:substring(headNews.title,0,20)}</a></h3>
              <p>${headNews.content}...<a href="news?action=show&newId=${headNews.newsId}">[查看全文]</a></p>
            </div>
            <div class="currentUpdate">
              <div class="currentUpdateHeader" style="text-align: left">最近更新</div>
              <div class="currentUpdateDatas">
                <table width="100%">
                  <c:forEach items="${updateNewsList}" var="updateNews" varStatus="status">
                    <c:if test="${status.index%2==0}">
                      <tr width="50%">
                    </c:if>
                    <td>
                      <a href="news?action=show&newsId=${updateNews.newsId}" title="${updateNews.title}">${updateNews.title}</a>
                    </td>
                    <c:if test="${status.index%2==1}">
                      </tr>
                    </c:if>
                  </c:forEach>
                </table>
              </div>
            </div><!--最近更新-->
          </div>
        </div>
      </div>
      <div class="span3"><!--热点新闻 这个是管理员自己设置的   热门新闻是点击量多的-->
        <div class="data_list hotspot_news_list">
          <div class="dataHeader">热点新闻</div>
          <div class="datas">
            <ul>
              <c:forEach items="${hotSpotNewsList}" var="hotNewsList">
                <li><a href="news?action=show&newsId=${hotNewsList.newsId}" title="${hotNewsList.title}">${hotNewsList.title}</a></li>
              </c:forEach>
            </ul>

          </div>
        </div>
        <div class="data_list hotspot_news_list">
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
    </div><!--第一层-->
  <!--前端页面没有做好-->
    <c:forEach items="${allIndexNewsList}" varStatus="allStaues" var="list1">
      <c:forEach items="${list1}" varStatus="all" var="list2">

        <p>${list2.title}</p>
      </c:forEach>
    </c:forEach>
    <!--将底下的多数类别新闻动态化-->
    <c:forEach items="${allIndexNewsList}" var="allNewsList" varStatus="allStatus">
      <c:if test="allStatus%3==0">
        <div class="row-fluid">
          <c:forEach items="${allNewsList}" var="newsList" varStatus="oneStaus">
            <c:if test="${oneStaus.first}">
              <div class="span4">
                <div class="data_list news_list">
                  <div class="dataHeader">
                      ${newsTypeList.typeName}
                        <span class="more">
                          <a hred="news?action=list&typeId=${newsList.typeId}">
                            更多...
                          </a>
                        </span>
                  </div>
                </div>
              </div>
              <ul>
            </c:if>
            <li>
              胡莎莎
              <a href="news?action=show&newsId=${newsList.newsId}" title="${newsList.title}">
                ${newsList.title}
              </a>
            </li>
            <c:if test="${oneStaus.last}">
              </ul>
            </c:if>
          </c:forEach>
        </div>
      </c:if>

      <c:if test="allStatus%3==1||allStatus.last">
        </div><!--判断行-->
      </c:if>
    </c:forEach>

    <jsp:include page="foreground/common/link.jsp"/>
    <jsp:include page="foreground/common/foot.jsp"/>
  </div>

  <script type="text/javascript">
      var auto;
      var index=0;
      $('.tuhuo ul li').hover(function(){
          clearTimeout(auto);
          index=$(this).index();
          move(index);
      },function(){
          auto=setTimeout('autogo('+index+')',3000);
      });

      function autogo(){
          if(index<5){
              move(index);
              index++;
          }
          else{
              index=0;
              move(index);
              index++;
          }
      }
      function move(l){
          var src=$('.tu_img').eq(index).attr('src');
          $("#fou_img").css({  "opacity": "0"  });
          $('#fou_img').attr('src',src);
          $('#fou_img').stop(true).animate({ opacity: '1'},1000);
          $('.tuhuo ul li').removeClass('fouce');
          $('.tuhuo ul li').eq(index).addClass('fouce');
          $('.tuhuo p').hide();
          $('.tuhuo p').eq(index).show();
          var ao=$('.tuhuo p').eq(index).children('a').attr('href');
          $('#fou_img').parent('a').attr("href",ao);
      }
      autogo();
      setInterval('autogo()',3000);
  </script>
  </body>
</html>
