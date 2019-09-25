package com.briup.dao;

import java.util.List;
import java.util.Set;

import com.briup.bean.Category;


public interface ICategoryMapper {
	
	/**
	 * 查询所有一级菜单
	 * @return
	 * @throws Exception
	 */
	public List<Category> findAllOneCategory() throws Exception;
	
	/**
	 * 查询所有二级菜单
	 * @param parentId
	 * @return
	 * @throws Exception
	 */
	public Set<Category> findAllTwoCategory(Long parentId) throws Exception;
	
	
	
	
	
	
	
	
	/**
	 * 查找所有的一级分类栏目列表，包含二级分类栏目
	 * @return
	 */
	public List<Category> findAllCategory();
	
	 
	
	
	
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
	 * @param category
	 */
	public void deleteCategory(long categoryId);
}
