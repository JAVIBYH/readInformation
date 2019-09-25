package com.briup.util;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/*
 * 工具类，存在static方法，获取sqlSession对象
 */
public class MyBatisSqlSessionFactory {
	private static SqlSessionFactory sqlSessionFactory;
	
	//私有构造器
	private MyBatisSqlSessionFactory() {}
	
	public static SqlSessionFactory getSqlSessionFactory(){
		if(sqlSessionFactory == null){
			InputStream inputStream = null;
			try {
				inputStream = Resources.getResourceAsStream("mybatis-config.xml"); 
				sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
			} catch (IOException e) {
				e.printStackTrace();
				throw new RuntimeException(e.getCause());
			}
		}
		return sqlSessionFactory;
	}
	
	public static SqlSession openSession() { 
		return openSession(false); 
	}
	
	public static SqlSession openSession(boolean autoCommit) { 
		return getSqlSessionFactory().openSession(autoCommit); 
	}
}
