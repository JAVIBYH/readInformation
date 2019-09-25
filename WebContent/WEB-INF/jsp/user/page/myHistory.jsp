<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
		pageContext.setAttribute("APP_PATH", request.getContextPath());
%>
  <link rel="stylesheet" type="text/css" href="${APP_PATH }/static/css/publish.css">
  <!-- 
<script type="text/javascript" src="static/js/publish.js"></script>
<script type="text/javascript" src="static/js/wangEditor.js"></script> -->
	<div class="search_bar">
      <span class="search_title">浏览记录</span>&nbsp;&nbsp;
    </div>
          <!-- 搜索end -->
          <div class="news_list">
           <table class="tbl">
            <thead>
              <tr>
				<th>资讯题目</th>
				<th>作者</th>
				<th>所属栏目</th>
				<th>发布时间</th>
				<th>背景音乐</th>
				<th>阅读量</th>
				<th>点赞量</th>
				<th>收藏量</th>
              </tr>
            </thead>
            <tbody>
              <c:forEach items="${historyArticles }" var="article">
					<tr>
						<td><a style="color:gray" href="${APP_PATH }/user/showArticle?articleId=${article.id }" target="_blank">${article.title }</a></td>
						<td>${article.user.nickname }</td>
						<td>${article.category.name }</td>
						<td><fmt:formatDate value="${article.releaseDate }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
						<td>${article.backgroundMusicUrl == null?"无":fn:substring(article.backgroundMusicUrl,23,-1) }</td>
						<td>${article.clickTimes }</td>
						<td>${article.likeTimes }</td>
						<td>${article.collectionTimes }</td>
					</tr>
			 </c:forEach>
            </tbody>
          </table>
     </div>