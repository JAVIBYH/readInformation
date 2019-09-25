package com.briup.util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 *数据库访问辅助类 
 */
public class DBHelper {
	/*private static String driver = "com.mysql.jdbc.Driver";
	private static String url ="jdbc:mysql://localhost:3306/cmsdev?useUnicode=true&characterEncoding=utf-8";
	private static String username ="briup";
	private static String password = "briup";*/
	private static String driver;
	private static String url;
	private static String username;
	private static String password;
	private static Connection conn;
	
	static{
		Properties prop = new Properties();
		try {
			prop.load(DBHelper.class.getResourceAsStream("../../../../db.properties"));
			driver = prop.getProperty("jdbc.driverClassName");
			url = prop.getProperty("jdbc.url");
			username = prop.getProperty("jdbc.username");
			password = prop.getProperty("jdbc.password");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static Connection getConnection(Boolean autoCommit){
		if(conn==null){
			try {
				Class.forName(driver);
				conn = DriverManager.getConnection(url,username,password);
				conn.setAutoCommit(autoCommit);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return conn;
	}
	public static void free(Statement st,Connection conn){
		free(null,st,conn);
	}
	
	public static void free(ResultSet rs,Statement st,Connection conn){
		if(rs!=null){
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(st!=null){
			try {
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(conn!=null){
			try {
				conn.close();
				conn = null;
				DBHelper.conn = null;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 封装查询方法
	 */
	public static Object query(String sql,DataHandler handler,Object... params) {
		conn = getConnection(false);
		PreparedStatement prep = null;
		ResultSet res = null;
		try {
			prep = conn.prepareStatement(sql);
			if(params!=null) {
				for(int i=0;i<params.length;i++) {
					prep.setObject(i+1, params[i]);
				}
			}
			res = prep.executeQuery();
			return handler.handler(res);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}finally {
			free(res, prep, null);
		}
	}
	/**
	 * 封装增删改方法
	 * @param String sql 要执行的SQL语句
	 * @param Object... params SQL语句参数列表
	 */
	public static void execute(String sql,Object... params) {
		
		conn = getConnection(false);
		PreparedStatement prep = null;
		try {
			prep = conn.prepareStatement(sql);
			if(params!=null) {
				for(int i=0;i<params.length;i++) {
					prep.setObject(i+1, params[i]);
				}
			}
			prep.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			free(prep, null);
		}
	}
	
}
