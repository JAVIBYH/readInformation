<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
 <style>
    .tbl td {
      text-align: center;
    }
  </style>
 <script>
$(function(){
	$('.passBtn').click(function(){
		var processContent = $(".modal textarea[name=processContent]").val();
		console.log(articleId+"--"+processContent);
		$.post("admin/ProcessReportArticle",{
			"articleId":articleId,
			"processState":1,
			"processContent":processContent
		},function(data){
			console.log(data);
			alert("审核正常");
		});
		
	});
	$('.noPassBtn').click(function(){
		var processContent = $(".modal textarea[name=processContent]").val();
		console.log(articleId+"--"+processContent);
		$.post("admin/ProcessReportArticle",{
			"articleId":articleId,
			"processState":3,
			"processContent":processContent
		},function(data){
			console.log(data);
			alert("审核异常");
		});
	});

	$('.tbl').on({
		click:function(){
			articleId = $(this).closest("tr").find("td:eq(0) input").val();
			$.post("admin/showReportMessages",
					{"articleId":articleId},
						function(data){
							console.log(data);
							$("#reportMessage tr").remove(":gt(0)");
							for(var i=0;i<data.length;i++){
								/* var newTr = $("<tr></tr>");
								var reportType = $("<td></td>");
								var reportContent = $("<td></td>");
								var reportDate = $("<td></td>");
								var nickname = $("<td></td>");
								var processContent = $("<td></td>");
								var processDate = $("<td></td>"); */
								$("#reportMessage").append("<tr><td>"+data[i].reportType+"</td><td>"+data[i].reportContent+"</td><td>"+data[i].reportDate+"</td><td>"+data[i].user.nickname+"</td><td>"+data[i].processContent+"</td><td>"+data[i].processDate+"</td></tr>");
							}
					});
			
		}
	},'button[title=点击]');

})
</script>
 <div class="title"> 举报待处理列表 </div>
          <!-- <div class="btns">
              <button type="button" class="btn btn-danger  btn-sm" data-toggle="modal" data-target=".bs-example-modal-del">批量删除</button>
          </div> -->
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
                <td>举报量</td>
                <th width="160px">举报信息查看</th>
              </tr>
            </thead>
            <tbody>
            	<c:forEach items="${reportArticles }" var="article">
					<tr>
						<td><input type="checkbox" value="${article.id }"></td>
						<td><a style="color:red" href="user/showArticle?articleId=${article.id }" target="_blank">${article.title }</a></td>
						<td>${article.category.name }</td>
						<td><fmt:formatDate value="${article.releaseDate }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
						<td>${article.backgroundMusicUrl == null?"无":fn:substring(article.backgroundMusicUrl,44,-1) }</td>
						<td>${article.clickTimes }</td>
						<td>${article.likeTimes }</td>
						<td>${article.collectionTimes }</td>
						<td>${article.reportTimes }</td>
						<td>
							<button title="点击" type="button" class="btn btn-success btn-xs" data-toggle="modal" data-target=".bs-example-modal-edit">点击查看</button>
						</td>
					</tr>
				</c:forEach>
            </tbody>
          </table>
    
    <!--  举报信息模态框 -->
     <div class="modal fade bs-example-modal-edit" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel">
      <div class="modal-dialog modal-lg" role="document">
          <div class="modal-content">
              <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="gridSystemModalLabel">查看举报待处理列表</h4>
              </div>
              <div class="modal-body">
                  <table class="table table-bordered" id="reportMessage">
                        <thead>
                          <tr>
                            <th>举报类型</th>
                            <th>举报内容</th>
                            <th>举报时间</th>
                            <th>举报用户</th>
                            <th>处理结果</th>
                            <th>处理时间</th>
                         </tr>
                      </thead>
                      <tbody>
                      
                      </tbody>
                  </table>
                  <div style="margin-bottom: 1em;">审核结果:</div>
                  <textarea name="processContent" id="" cols="100" rows="5"></textarea>
                  <div class="modal-footer">
                    <span class="messageSpan"></span>
                  </div>
                        </div>
                        <div class="modal-footer">
                         <button type="button" class="btn btn-success btn-sm passBtn" data-dismiss="modal">正常</button>
                          <button type="button" class="btn btn-danger btn-sm noPassBtn" data-dismiss="modal">异常</button>
                          <button type="button" class="btn btn-default btn-sm backBtn" data-dismiss="modal">取消</button>
                        </div>
                      </div>
      </div>
    </div>
