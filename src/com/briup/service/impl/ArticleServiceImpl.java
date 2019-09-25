package com.briup.service.impl;

import java.sql.Connection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.briup.bean.Article;
import com.briup.bean.UserReport;
import com.briup.dao.IArticleMapper;
import com.briup.service.IArticleService;
import com.briup.util.DBHelper;

 
@Service
public class ArticleServiceImpl implements IArticleService{
	//private IArticleDao articleDao = new ArticleDaoImpl();
	//private Connection conn = DBHelper.getConnection(false);
	
	@Autowired
	private IArticleMapper iArticleMapper;
	
	/**
	 * 根据点击次数查找资讯
	 */
	@Override
	public List<Article> findArticlesByClickTimes(int num, long userId) {
		return iArticleMapper.findArticlesByClickTimes(num, userId);
	}
	
	/**
	 * 根据咨询ID查找咨询信息
	 */
	@Override
	public Article findArticlesById(long articleId, long userId) {
		return iArticleMapper.findArticlesById(articleId, userId);
	}
	
	/**
	 * 更新资讯点击量 调用方法加 1
	 */
	@Override
	public void updateArticleClickTimes(long articleId, long addClickTimes) {
		iArticleMapper.updateArticleClickTimes(articleId, addClickTimes);
	}
	
	/**
	 * 根据分类ID与级别查找对应的资讯信息
	 */
	@Override
	public List<Article> findArticlesByCategoryId(long categoryId, String mark, long userId) {
		if("2".equals(mark)){
			return iArticleMapper.findArticlesByCategoryIdTwo(categoryId, userId);
		} else {
			return iArticleMapper.findArticlesByCategoryIdOne(categoryId, userId);
		}
	}
	
	/**
	 * 查询用户发布的所有文章，包括所有状态，按照时间倒序排序
	 */
	@Override
	public List<Article> findArticlesByUserId(long userId) {
		return iArticleMapper.findArticlesByUserId(userId);
	}
	
	/**
	 * 通过articleId改变categoryId从而更新category
	 */
	@Override
	public void updateArticleByArticleId(long articleId, long categoryId) {
		iArticleMapper.updateArticleByArticleId(articleId, categoryId);
	}
	
	/**
	 * 查询用户的浏览记录，
	 */
	@Override
	public List<Article> findHistoryArticles(String historyValue, long userId) {
		return iArticleMapper.findHistoryArticles(historyValue, userId);
	}
	
	/**
	 * 查询用户点赞的的所有文章，按照时间倒序排序
	 */
	@Override
	public List<Article> findLikeArticlesByUserId(long userId) {
		return iArticleMapper.findLikeArticlesByUserId(userId);
	}
	
	/**
	 * 查询用户收藏的的所有文章，按照时间倒序排序
	 */
	@Override
	public List<Article> findCollectionArticlesByUserId(long userId) {
		return iArticleMapper.findCollectionArticlesByUserId(userId);
	}
	
	/**
	 * 查询用户举报的的所有文章，按照时间倒序排序
	 */
	@Override
	public List<Article> findReportArticlesByUserId(long userId) {
		return iArticleMapper.findReportArticlesByUserId(userId);
	}
	
	
	
	
	
	
	@Override
	public List<Article> findArticlesByState(int state) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void updateArticleCategory(long articleId, long categoryId) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void examineArticle(String articleIds, long state) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void deleteArticleById(String articleIds) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public List<Article> findReportArticles(int reportState) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void processArticle(long articleId, int processState, String processContent) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public List<UserReport> findUserReportsByArticleId(long articleId) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<UserReport> findUserReportsByArticleIdUserId(long articleId, long userId) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<Article> findProcessedArticles() {
		// TODO Auto-generated method stub
		return null;
	}

	

	
	
	
}
