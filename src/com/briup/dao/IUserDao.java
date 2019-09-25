package com.briup.dao;

import com.briup.bean.Article;
import com.briup.bean.User;


public interface IUserDao {
	/**
	 * 根据账号查找用户
	 * @param account
	 * @return
	 */
	public User findUserByAccount(String account);
	
	/**
	 * 通过第三方平台ID查找用户
	 * @param thirdId
	 * @return
	 */
	public User findUserByThirdId(String thirdId);
	
	/**
	 * 更新用户昵称信息
	 * @param user
	 */
	public void updateName(User user);
	/**
	 * 第三方登录添加用户
	 * @param user
	 * @return
	 */
	public User insertUserThird(User user);
	/**
	 * 网站注册添加用户
	 * @param user
	 */
	public void insertUser(User user);

	/**
	 * 用户发布资讯
	 * @param title
	 * @param articleContent
	 * @param userId
	 * @param categoryId
	 */
	public void insertArticle(Article article,long userId,long categoryId);
	/**
	 * 新增用户点赞信息
	 * @param articleId
	 * @param userId
	 */
	public void insertUserLike(long articleId, long userId);
	
	/**
	 * 更新用户点赞状态
	 * @param articleId
	 * @param userId
	 * @param state
	 */
	public void updateUserLike(long articleId, long userId,int state);
	
	/**
	 * 新增用户收藏信息
	 * @param articleId
	 * @param userId
	 */
	public void insertUserCollection(long articleId, long userId);
	
	/**
	 * 更新用户收藏状态
	 * @param articleId
	 * @param userId
	 * @param state
	 */
	public void updateUserCollection(long articleId, long userId,int state);
	
	/**
	 * 添加用户举报信息
	 * @param articleId
	 * @param userId
	 * @param reportContent
	 */
	public void insertUserReport(long articleId, long userId,String reportType,String reportContent);
	
	/**
	 * 更新用户信息
	 * @param user
	 */
	public void updateUser(User user);
	
	
	/**
	 * 查找某资讯的个人举报信息
	 * @param articleId
	 * @return
	 *//*
	public List<UserReport> findUserReportsByArticleIdUserId(long articleId,long userId);*/
	
	/**
	 * 查找点击量最高的num篇资讯
	 * @param num
	 * @return
	 *//*
	public List<Article> findArticlesByClickTimes(int num, long UserId); 
	
	*//**
	 * 根据一级分类ID查找的资讯信息
	 * @param categoryId
	 * @param mark
	 * @return
	 *//*
	public List<Article> findArticlesByCategoryIdOne(long categoryId, long UserId);
	*//**
	 * 根据二级分类ID查找的资讯信息
	 * @param categoryId
	 * @param mark
	 * @return
	 *//*
	public List<Article> findArticlesByCategoryIdTwo(long categoryId, long UserId);
	
	*//**
	 * 根据咨询ID查找咨询信息
	 * @param articleId
	 * @return
	 *//*
	public Article findArticlesById(long articleId, long UserId);
	
	*//**
	 * 点击量加一
	 * @param addClickTimes
	 * @return
	 *//*
	public void updateArticleClickTimes(long articleId, long addClickTimes);
	*/
	/**
	 * 查询用户发布的所有资讯，包括所有状态，按照时间倒序排序
	 * @param userId
	 *//*
	public List<Article> findArticlesByUserId(long userId);
	
	*//**
	 * 查询用户点赞的的所有资讯，按照时间倒序排序
	 * @param userId
	 *//*
	public List<Article> findLikeArticlesByUserId(long userId);
	
	*//**
	 * 查询用户收藏的的所有资讯，按照时间倒序排序
	 * @param userId
	 *//*
	public List<Article> findCollectionArticlesByUserId(long userId);
	
	*//**
	 * 查询用户举报的的所有资讯，按照时间倒序排序
	 * @param userId
	 *//*
	public List<Article> findReportArticlesByUserId(long userId);
	
	*//**
	 * 用户发布资讯
	 * @param title
	 * @param articleContent
	 * @param userId
	 * @param categoryId
	 *//*
	public void insertArticle(Article article,long userId,long categoryId);
	*/
	/**
	 * 按照资讯状态查找资讯列表
	 * @param state
	 * @return
	 *//*
	public List<Article> findArticlesByState(int state);
	
	*//**
	 * 更新资讯所属栏目
	 * @param articleId
	 * @param categoryId
	 *//*
	public void updateArticleCategory(long articleId, long categoryId);
	
	*//**
	 * 审核资讯
	 * @param articleId
	 * @param state
	 *//*
	public void examineArticle(long articleId, long state);
	
	*//**
	 * 删除资讯
	 * @param articleId
	 *//*
	public void deleteArticleById(long articleId);
	
	*//**
	 * 根据举报状态查询资讯
	 * @param reportState
	 * @return
	 *//*
	public List<Article> findReportArticles(int reportState);
	
	*//**
	 * 处理被举报的资讯
	 * @param processState
	 * @param processContent
	 *//*
	public void processArticle(long articleId, String processContent);
	
	*//**
	 * 查找某资讯的所有举报信息
	 * @param articleId
	 * @return
	 *//*
	public List<UserReport> findUserReportsByArticleId(long articleId);
	*/
	/**
	 * 查询所有已处理过的资讯
	 * @return
	 */
//	public List<Article> findProcessedArticles();
	/**
	 * 查找所有的一级分类栏目列表，包含二级分类栏目
	 * @return
	 *//*
	public List<Category> findAllCategory();
	  *//**
	  * 新增栏目
	  * @param category
	  *//*
	public void insertCategory(Category category);
	
	   *//**
	   * 更新栏目
	   * @param category
	   *//*
	public void updateCategory(Category category);
	
	    *//**
	    * 删除栏目
	    * @param category
	    *//*
	public void deleteCategory(long categoryId);
	     */
}
