<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%
		pageContext.setAttribute("APP_PATH", request.getContextPath());
%>
<!--搜索start  -->
         <div class="search_bar">
              <span class="search_title">热点资讯推荐</span>
          </div>
			<!--展示阅读量最高的三篇资讯  -->
 			<div class="news_list">
	            <ul class="list-group">
		            <c:forEach items="${recommendArticles }" var="article">
		                <li class="list-group-item">
		                    <div class="media">
		                    <div class="media-left">
		                    <c:if test="${article.type==0 }">
		                      <c:if test="${article.pictureUrlsList != null }">
			                      <a href="#" class="media-object">
			                        <c:forEach items="${article.pictureUrlsList }" var="pictureUrl">
										<img src="${pictureUrl }" alt="没图">
									</c:forEach>
		                      	</a>
		                      </c:if>
		                    </c:if>
		                    <c:if test="${article.type==1 }">
		                      <a href="#" class="media-object">
		                        <video src="./static/media/video.mp4" width="320" height="240" controls="controls">
		                          Your browser does not support the video tag.
		                          </video>
		                      </a>
		                    </c:if>
		                    </div>
		                    <div class="media-body">
		                      <a class="media-heading title_news" href="${APP_PATH }/user/showArticle?articleId=${article.id }" target="_blank">
		                      ${article.title }</a>
		                      <div class="media_content">${article.summary }</div>
		                      
		                      <div class="media_option">
		                        <div>阅读量:<b>${article.clickTimes }</b></div>
		                        <div>点赞量:<b>${article.likeTimes }</b></div>
		                        <div>收藏量:<b>${article.collectionTimes }</b></div>
		                        <div>发布时间:<b>${article.releaseDate }</b></div>
		
		                      </div>
		                    </div>
		
		                  </div>
		                </li>
		            </c:forEach>
	             </ul>
	       </div>
