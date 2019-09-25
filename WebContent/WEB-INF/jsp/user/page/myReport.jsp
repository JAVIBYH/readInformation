<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
		pageContext.setAttribute("APP_PATH", request.getContextPath());
%>
<link rel="stylesheet" type="text/css" href="${APP_PATH }/static/css/publish.css">
<script type="text/javascript">
	$(function(){
		//点击未处理或者已处理之后的处理：
		$('.reportMsg').on("click",function(){
				var articleName = $(this).closest("tr").find("td:eq(1)").text();
				var articleId = $(this).attr("articleId");
				$.post("user/showUserReportMessage",
						{"articleId":articleId},
						function(data){
							console.log(data);
							$(".bs-example-modal-checkout #inputEmail1").val(data[0].article.title);
							$(".bs-example-modal-checkout #inputEmail2").val(data[0].reportType);
							$(".bs-example-modal-checkout #inputEmail3").val(data[0].reportContent);
							$(".bs-example-modal-checkout #inputEmail4").val(data[0].reportDate);
							$(".bs-example-modal-checkout #inputEmail5").val(data[0].processContent);
							$(".bs-example-modal-checkout #inputEmail6").val(data[0].processDate);
						});
				
		});
	});
</script>

<div class="search_bar">

              <span class="search_title">我的举报</span>&nbsp;&nbsp;
          </div>
          <!-- 搜索end -->
          <div class="news_list">
                <table class="tbl">
            <thead>
              <tr>
               <th>资讯标题</th>
				<th>作者</th>
				<th>所属栏目</th>
				<th>发布时间</th>
				<th>背景音乐</th>
				<th>阅读量</th>
				<th>点赞量</th>
				<th>收藏量</th>
                <th width="160px">查看处理结果</th>
              </tr>
            </thead>
            <tbody>
           		<c:forEach items="${reportArticles }" var="article">
				<tr>
					<td><a href="${APP_PATH }/user/showArticle?articleId=${article.id }" target="_blank">${article.title }</a></td>
					<td>${article.user.nickname }</td>
					<td>${article.category.name }</td>
					<td><fmt:formatDate value="${article.releaseDate }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
					<td>${article.backgroundMusicUrl == null?"无":fn:substring(article.backgroundMusicUrl,23,-1) }</td>
					<td>${article.clickTimes }</td>
					<td>${article.likeTimes }</td>
					<td>${article.collectionTimes }</td>
					<c:choose>
						<c:when test="${article.isReport>1 }">
							<td class="optior">
			                  <span articleId="${article.id }" class="reportMsg checkout_rea_no report_flag" data-toggle="modal" data-target=".bs-example-modal-checkout">已处理</span>
			                </td>
						</c:when>
						<c:otherwise>
							 <td class="optior">
			                  <span articleId="${article.id }" class="reportMsg checkout_rea_is report_flag" data-toggle="modal" data-target=".bs-example-modal-checkout">未处理</span>
			                </td>
						</c:otherwise>
					</c:choose>
				</tr>
			</c:forEach>
            </tbody>
          </table>
           </div>
           
          <!-- 处理结果model -->
<div class="modal fade bs-example-modal-checkout" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel">
  <div class="modal-dialog modal-lg" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="gridSystemModalLabel">举报信息</h4>
      </div>
      <div class="modal-body">
	 <form class="form-horizontal">
	          <div class="form-group">
	            <label for="inputEmail1" class="col-sm-2 control-label">举报文章标题</label>
	            <div class="col-sm-10">
	              <input type="email" class="form-control title_input" id="inputEmail1" placeholder="" readonly="readonly">
	            </div>
	          </div>
	          <div class="form-group">
	            <label for="inputEmail2" class="col-sm-2 control-label">举报类型</label>
	            <div class="col-sm-10">
	              <input type="email" class="form-control title_input" id="inputEmail2" placeholder="" readonly="readonly">
	            </div>
	          </div>
	          <div class="form-group">
	             <label for="inputEmail3" class="col-sm-2 control-label">举报理由</label>
	            <div class="col-sm-10">
	              <input type="email" class="form-control title_input" id="inputEmail3" placeholder="" readonly="readonly">
	            </div>
	          </div>
	          <div class="form-group">
	             <label for="inputEmail4" class="col-sm-2 control-label">举报时间</label>
	            <div class="col-sm-10">
	              <input class="form-control title_input" name="articleName"  id="inputEmail4" readonly="readonly">
	            </div>
	          </div>
	          <div class="form-group">
	             <label for="inputEmail5" class="col-sm-2 control-label">处理结果</label>
	            <div class="col-sm-10">
	              <input class="form-control title_input" name="articleName"  id="inputEmail5" placeholder="未处理" readonly="readonly">
	            </div>
	          </div>
	           <div class="form-group">
	             <label for="inputEmail6" class="col-sm-2 control-label">处理时间</label>
	            <div class="col-sm-10">
	              <input class="form-control title_input" name="articleName"  id="inputEmail6" placeholder="未处理" readonly="readonly">
	            </div>
	          </div>
	        </form> 
    
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">返回</button>
      </div>
    </div><!-- /.modal-content -->
  </div>
  </div>
  