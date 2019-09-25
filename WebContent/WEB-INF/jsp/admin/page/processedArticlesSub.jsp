<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<script>
$(function(){
	// 恢复
	$('.recover').on({
		click:function(){
			articleId = $(this).closest("tr").find("td:eq(0) input").val();
		}
	});
	
	//批量恢复
	$("#batchRecover").on("click",function(){
		articleId = $('.tbl input[name=articleId]:checked').map(function(index,item) {
		      			return this.value;
		      		}).get().join(",");
		if(!articleId){
			alert("请选择要恢复的资讯");
		}
	});
	$('#deleteYes').click(function(){
		console.log("articleId："+articleId);
		if(articleId){
			$.post("admin/examineArticle",
					{"articleIds":articleId,
					"state":1
					},
					function(data){
						console.log(data);
						alert("审核正常");
					});
		}
	});
});
</script>	
	<div class="title"> 已处理资讯列表 </div>
          <div class="btns">
              <button id="batchRecover" type="button" class="btn btn-success btn-xs"  data-toggle="modal" data-target=".bs-example-modal-back">批量恢复</button>
          </div>
          <table class="tbl">
            <thead>
              <tr>
                <th width="50px">选择</th>
                <th width="300px">标题</th>
                <th width="100px">栏目</th>
                <th width="160px">发布时间</th>
                <td>背景音乐</td>
                <th width="80px">阅读量</th>
                <th width="80px">点赞量</th>
                <th width="80px">收藏量</th>
                <th width="80px">举报量</th>
                <th width="160px">状态</th>
                 <th width="160px">操作</th>
              </tr>
            </thead>
            <tbody>
            	<c:forEach items="${processedarticles }" var="article">
					<tr>
						<td><input name="articleId" type="checkbox" value="${article.id }"  ${(article.state == 2 or article.state==4)?'':"disabled='disabled'" }></td>
						<td><a style="color:red" href="user/showArticle?articleId=${article.id }" target="_blank">${article.title }</a></td>
						<td>${article.category.name }</td>
						<td><fmt:formatDate value="${article.releaseDate }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
						<td>${article.backgroundMusicUrl == null?"无":fn:substring(article.backgroundMusicUrl,44,-1) }</td>
						<td>${article.clickTimes }</td>
						<td>${article.likeTimes }</td>
						<td>${article.collectionTimes }</td>
						<td>${article.reportTimes }</td>
						<td>
							<c:choose>
								<c:when test="${article.state == 1}">
									举报审核正常
								</c:when>
								<c:when test="${article.state == 2}">
									审核不通过
								</c:when>
								<c:when test="${article.state == 3}">
									举报审核不通过
								</c:when>
								<c:when test="${article.state == 4}">
									已删除
								</c:when>
							</c:choose>
						</td>
						<td>
							<c:if test="${article.state == 2 or article.state==4}">
								<button type="button" class="btn btn-success btn-xs recover" data-toggle="modal" data-target=".bs-example-modal-back" 
								title="恢复">恢复正常</button>
							</c:if>
						</td>
					</tr>
				</c:forEach>
            </tbody>
          </table>
          
   <!--  恢复-->
<div class="modal fade bs-example-modal-back" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel">
  <div class="modal-dialog modal-sm" role="document">
    <div class="modal-content">
    <div class="modal-body">确定恢复吗？</div>
      <div class="modal-footer">
        <button type="button" class="btn btn-primary" data-dismiss="modal" id="deleteYes">确定</button>
        <button type="button" class="btn btn-default" data-dismiss="modal" id="backBtn">取消</button>
      </div>
    </div>

  </div>
</div>    