package com.briup.service.impl;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.briup.bean.Category;
import com.briup.dao.ICategoryMapper;
import com.briup.exception.CategoryException;
import com.briup.service.ICategoryServcie;
import com.briup.util.DBHelper;

@Service
public class CategoryServiceImpl implements ICategoryServcie {
	//private ICategoryDao categoryDao = new CategoryDaoImpl();
	//private Connection conn = DBHelper.getConnection(false);
	
	@Autowired
	private ICategoryMapper iCategoryMapper;
	
	/**
	 * 获取所有一级菜单
	 */
	@Override
	public List<Category> listAllOneCategory() throws CategoryException{
		List<Category> list = null;
		try {
			 list = iCategoryMapper.findAllOneCategory();
			 if(list==null){
				 throw new Exception("没有一级菜单项");
			 }
		} catch (Exception e) {
			e.printStackTrace();
			throw new CategoryException(e.getMessage());
		}
		return list;
	}
	
	/**
	 * 获取所有一级菜单和二级菜单
	 */
	@Override
	public List<Category> listAllTwoCategory() throws CategoryException {
		List<Category> list1 = null;
		try {
			list1 = iCategoryMapper.findAllOneCategory();
			if(list1==null){
				throw new Exception("没有一级菜单");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Set<Category> list2 = null;
		for(Category cate:list1){
			try {
				list2 = iCategoryMapper.findAllTwoCategory(cate.getId());
				if(list2==null){
					throw new Exception("没有二级菜单");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			cate.setSubCategorys(list2);
		}
		return list1;
	}
	
	
	
	
	
	
	
	
	@Override
	public List<Category> findAllCategory() {
		return iCategoryMapper.findAllCategory();
	}
	@Override
	public void insertCategory(Category category) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void updateCategory(Category category) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void deleteCategorys(String categoryIds) {
		// TODO Auto-generated method stub
		
	}
	
	
	
}
