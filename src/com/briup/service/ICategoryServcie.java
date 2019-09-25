package com.briup.service;

import java.util.List;
import java.util.Map;

import com.briup.bean.Category;
import com.briup.exception.CategoryException;


public interface ICategoryServcie {
	
	/**
	 * 获取所有的一级菜单
	 * */
	public List<Category> listAllOneCategory() throws CategoryException;
	
	/**
	 * 获取所有的二级菜单
	 * */
	public List<Category> listAllTwoCategory() throws CategoryException;
	
	
	
	/**
	 * 查找所有的一级分类栏目列表，包含二级分类栏目
	 * @return
	 */
	public List<Category> findAllCategory() throws CategoryException;
	
	
	/**
	 * 新增栏目
	 * @param category
	 */
	public void insertCategory(Category category);
	
	/**
	 * 更新栏目
	 * @param category
	 */
	public void updateCategory(Category category);
	
	/**
	 * 删除栏目
	 * @param categoryIds
	 */
	public void deleteCategorys(String categoryIds);
}
