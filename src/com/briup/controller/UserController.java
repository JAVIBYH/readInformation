package com.briup.controller;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpCookie;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.briup.bean.Article;
import com.briup.bean.Category;
import com.briup.bean.User;
import com.briup.service.IArticleService;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private IArticleService iArticleService;
	
	/**
	 * 显示用户界面右边  热点资讯推荐
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping("/showrecommendArticles")
	public ModelAndView showrecommendArticles(ModelAndView model,HttpSession session){
		model.setViewName("user/page/recommendSub");
		User user = (User) session.getAttribute("loginUser");
		List<Article> list = iArticleService.findArticlesByClickTimes(3, user.getId());
		//System.out.println(list+"-------------");
		session.setAttribute("articlePage", "page/recommendSub.jsp");
		model.addObject("recommendArticles", list);
		return model;
	}
	
	/**
	 * 显示资讯详细信息
	 * 点击后点击量加1
	 * @param model
	 * @param id
	 * @param session
	 * @return
	 */
	@RequestMapping("/showArticle")
	public ModelAndView showArticle(ModelAndView model ,@RequestParam("articleId") Long id,
			HttpSession session,HttpServletRequest request,HttpServletResponse response){
		model.setViewName("user/articleDetail");
		User user = (User) session.getAttribute("loginUser");
		iArticleService.updateArticleClickTimes(id, 1);
		Article article = iArticleService.findArticlesById(id, user.getId());
		//System.out.println(article.getUser().getNickname()+"==============");
		//添加cookie，以便查询历史记录
		Cookie[] cookies = request.getCookies();
		String aId = "";
		for(Cookie cookie:cookies){
			System.out.println("cookie:"+cookie.getName()+"----"+cookie.getValue()+"-----------------");
			if(("history+"+user.getId()).equals(cookie.getName())){
				aId = cookie.getValue().concat(","+id);
				//aId = cookie.getValue();
				cookie.setValue(URLEncoder.encode(aId));
				System.out.println("if-------------"+aId+"---------"+id);
			} else {
				Cookie cookie1 = new Cookie("history+"+user.getId(), String.valueOf(id));
				//cookie1.setMaxAge(7*24*60*60);    //设置cookie的最大生命周期为一周
				cookie1.setPath("/");    //设置路径为全路径（这样写的好处是同一项目下的页面都可以访问该cookie）
				response.addCookie(cookie1);   
				System.out.println("else-------------");
			}
			System.out.println(cookie.getValue()+"??????????????");
		}
		System.out.println(aId+"\\\\\\\\\\\\\\");
		model.addObject("showArticle", article);
		return model;
	}
	
	/**
	 * 资讯栏目中跟据 菜单id 显示资讯信息
	 * @param model
	 * @param session
	 * @param id
	 * @param mark
	 * @return
	 */
	@RequestMapping("/showArticleList")
	public ModelAndView showArticleList(
					ModelAndView model,HttpSession session,
					@RequestParam("categoryId") Long id,@RequestParam("mark") String mark)
	{
		model.setViewName("user/page/articleListSub");
		session.setAttribute("articlePage", "page/articleListSub.jsp");
		User user = (User) session.getAttribute("loginUser");
		List<Article> list = iArticleService.findArticlesByCategoryId(id, mark, user.getId());
		model.addObject("articlesByCategoryId", list);
		return model;
	}
	
	/**
	 * 用户发布内容
	 * @param model
	 * @return
	 */
	@RequestMapping("/showUserReleaseArticles")
	public ModelAndView showUserReleaseArticles(ModelAndView model,HttpSession session){
		model.setViewName("user/page/myPublishSub");
		session.setAttribute("articlePage", "page/myPublishSub.jsp");
		User user = (User) session.getAttribute("loginUser");
		List<Article> list = iArticleService.findArticlesByUserId(user.getId());
		model.addObject("releaseArticles", list);
		return model;
	}
	
	/**
	 * 获取前台数据 上传图文
	 * @param model
	 * @return
	 */
	/*@RequestMapping(value="/userReleaseArticles",method=RequestMethod.POST)
	public String userReleaseArticles(HttpServletRequest request,HttpServletResponse response,
			HttpSession session ){
		   Map<String, String> returnMap = new HashMap<String, String>();
	       String a=request.getParameter("title"); 
	       //取出form-data中的二进制字段
	       //System.out.println(a+"==================");
	       MultipartHttpServletRequest multipartRequest=(MultipartHttpServletRequest) request;  
	       MultipartFile multipartFile = multipartRequest.getFile("titlePage"); 
	       System.out.println(multipartFile.getSize()+"------------");    
	       return "";
	}*/
	
	/**
	 * 根据资讯id更新类别category_id
	 * @param articleId
	 * @param categoryId
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/updateArticleServlet")
	public String updateArticleServlet(@RequestParam("articleId") Long articleId,
			@RequestParam("categoryId") Long categoryId,HttpSession session,
			ModelAndView model){
		System.out.println(articleId+"++++++++++++");
		System.out.println(categoryId+"+++++++++++++");
		iArticleService.updateArticleByArticleId(articleId, categoryId);
		return "";
	}
	
	/**
	 * 浏览记录
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping("/showHistoryArticles")
	public ModelAndView showHistoryArticles(ModelAndView model,HttpSession session,
			HttpServletRequest request,HttpServletResponse response){
		model.setViewName("user/page/myHistory");
		session.setAttribute("articlePage", "page/myHistory.jsp");
		User user = (User)session.getAttribute("loginUser");
		Cookie[] cookies = request.getCookies();
		String historyValue = "";
		for(Cookie cookie:cookies){
			if(("history+"+user.getId()).equals(cookie.getName())){
				historyValue = cookie.getValue();
			}
			System.out.println(cookie.getValue()+")))))))))))");
		}
		System.out.println("====="+URLDecoder.decode(historyValue)+"+++++++++++++++++++++++");
		List<Article> list = iArticleService.findHistoryArticles(historyValue, user.getId());
		model.addObject("historyArticles",list);
		return model;
	}
	
	/**
	 * 我的点赞
	 * @param model
	 * @return
	 */
	@RequestMapping("/showUserLikeArticles")
	public ModelAndView showUserLikeArticles(ModelAndView model,HttpSession session){
		model.setViewName("user/page/myLike");
		session.setAttribute("articlePage", "page/myLike.jsp");
		User user = (User) session.getAttribute("loginUser");
		List<Article> list = iArticleService.findLikeArticlesByUserId(user.getId());
		model.addObject("likeArticles", list);
		return model;
	}
	
	/**
	 * 我的收藏
	 * @param model
	 * @return
	 */
	@RequestMapping("/showUserCollectionArticles")
	public ModelAndView showUserCollectionArticles(ModelAndView model,HttpSession session){
		model.setViewName("user/page/myCollection");
		session.setAttribute("articlePage", "page/myCollection.jsp");
		User user = (User) session.getAttribute("loginUser");
		List<Article> list = iArticleService.findCollectionArticlesByUserId(user.getId());
		model.addObject("collectionArticles", list);
		return model;
	}
	
	/**
	 * 我的举报
	 * @param model
	 * @return
	 */
	@RequestMapping("/showUserReportArticles")
	public ModelAndView showUserReportArticles(ModelAndView model,HttpSession session){
		model.setViewName("user/page/myReport");
		session.setAttribute("articlePage", "page/myReport.jsp");
		User user = (User) session.getAttribute("loginUser");
		List<Article> list = iArticleService.findReportArticlesByUserId(user.getId());
		model.addObject("reportArticles", list);
		return model;
	}
	
	/**
	 * 个人信息
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping("/showUserInformation")
	public ModelAndView showUserInformation(ModelAndView model,HttpSession session){
		model.setViewName("user/page/MyInformation");
		session.setAttribute("articlePage", "page/MyInformation.jsp");
		return model;
	}
	
	
	
	
}
	
	 
            
 
	
	

 
