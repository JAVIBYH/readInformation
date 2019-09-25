package com.briup.service.impl;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.briup.bean.Article;
import com.briup.bean.User;
import com.briup.dao.IUserDao;
import com.briup.dao.IUserMapper;
import com.briup.exception.UserServcieException;
import com.briup.service.IUserService;
import com.briup.util.MyBatisSqlSessionFactory;

@Service
public class UserServiceImpl implements IUserService{
	//private IUserDao userDao = new UserDaoImpl();
	//应该进行其他方式的处理
	//private Connection conn = DBHelper.getConnection(false);
	
	@Autowired
	private IUserMapper iUserMapper;
	
	
	//private SqlSession session = MyBatisSqlSessionFactory.openSession();
	//private IUserMapper mapper = session.getMapper(IUserMapper.class);
	
	/**
	 * 根据账户查找用户
	 */
	@Override
	public User findUserByAccount(String account) {
		return iUserMapper.findUserByAccount(account);
	}
	
	/**
	 * 添加用户
	 */
	@Override
	public void addUser(User user) throws UserServcieException {
	  iUserMapper.insertUser(user);
	}
	
	
	
	
	
	@Override
	public User login(String account, String password) throws UserServcieException {
		//用户名或密码任意一个为空，直接登录失败，抛出异常
		
		//通过dao层映射对象查询登录用户
		
		return null;
	}
	
	@Override
	public User thirdLogin(User user) {
		/*//判断用户是否曾经登录过
		User findUser = mapper.findUserByThirdId(user.getThirdId());
		if(findUser != null) {
			//用户曾经登陆过，昵称信息可能更改过，更新
			findUser.setNickname(user.getNickname());
			mapper.updateName(findUser);
		}else {
			//用户首次登录，做添加，相当于注册
			mapper.insertUserThird(user);
		}
		session.commit();
		
		//将刚写入的查询出来 返回
		if(findUser == null)
			findUser = mapper.findUserByThirdId(user.getThirdId());
		
		return findUser;*/
		return null;
	}
	
	@Override
	public void updateUser(User user) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void register(User user) throws UserServcieException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void releaseArticle(Article article, long userId, long categoryId) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void likeArticle(int likeState, long articleId, long userId) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void collectionArticle(int likeState, long articleId, long userId) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void reportArticle(long articleId, long userId, String reportType, String reportContent) {
		// TODO Auto-generated method stub
		
	}

	

	
	
}
