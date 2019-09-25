package com.briup.util;

import java.sql.ResultSet;

/**
 *查询结果集处理类，对数据库查询结果进行处理
 */
public interface DataHandler {
	
	/**
	 * 根据sql语句查询结果，处理结果集，返回查询到的对象
	 * @param ResultSet res
	 * @return Object result
	 */
	public Object handler(ResultSet res);
}
