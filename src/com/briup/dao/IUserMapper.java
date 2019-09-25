package com.briup.dao;

import java.util.List;

import com.briup.bean.Category;
import com.briup.bean.User;


/*
 * 映射接口
 */
public interface IUserMapper {
	/**
	 * 根据用户名查找用户
	 * @param account
	 * @return
	 */
	public User findUserByAccount(String account);
	
	/**
	 * 添加用户
	 * @param user
	 */
	public void insertUser(User user);
	
	 
	
	
	
	
	
	/**
	 * 第三方登录 通过第三方平台ID查找用户
	 * @param thirdId
	 * @return
	 */
	public User findUserByThirdId(String thirdId);
	
	/**
	 * 第三方登录 更新用户昵称信息
	 * @param user
	 */
	public void updateName(User user);
	/**
	 * 第三方登录 添加用户
	 * @param user
	 * @return
	 */
	public User insertUserThird(User user);

}
