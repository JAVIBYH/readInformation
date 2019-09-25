package com.briup.service;

import com.briup.bean.Article;
import com.briup.bean.User;
import com.briup.exception.UserServcieException;

 

public interface IUserService {
	
	/**
	 * 根据账号查找用户
	 * @param account
	 * @return
	 */
	public User findUserByAccount(String account);
	
	/**
	 * 添加用户
	 * @param user
	 * @return
	 * @throws UserServcieException
	 */
	public void addUser(User user) throws UserServcieException;
	
	
	
	
	
	/**
	 * 处理手动登录
	 * @param account
	 * @param password
	 * @return
	 * @throws UserServcieException
	 */
	public User login(String account,String password) throws UserServcieException;
	
	/**
	 * 第三方登录，返回第三方登陆后保存入库或者更新入库的用户信息
	 * @param user
	 * @return
	 */
	public User thirdLogin(User user);
	
	/**
	 * 更新用户信息
	 * @param user
	 */
	public void updateUser(User user);
	
	/**
	 * 网站注册功能
	 * @param user
	 */
	public void register(User user) throws UserServcieException;
	
	/**
	 * 用户发布资讯
	 * @param title
	 * @param articleContent
	 * @param userId
	 * @param categoryId
	 */
	public void releaseArticle(Article article,long userId,long categoryId);
	
	/**
	 * 用户点赞资讯
	 * @param articleId
	 * @param userId
	 * @return
	 */
	public void likeArticle(int likeState,long articleId,long userId );
	/**
	 * 用户收藏咨询
	 * @param articleId
	 * @param userId
	 * @return
	 */
	public void collectionArticle(int likeState,long articleId,long userId );
	/**
	 * 用户举报咨询
	 * @param articleId
	 * @param userId
	 * @return
	 */
	public void reportArticle(long articleId,long userId,String reportType,String reportContent );
	
}
