package com.briup.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.briup.bean.User;
import com.briup.exception.UserServcieException;
import com.briup.service.IUserService;
import com.briup.util.Md5Utils;

/**
 * 登录和注册控制器
 * @author hjw
 *
 */
@Controller
@RequestMapping("/systemController")
public class SystemController {
	
	@Autowired
	private IUserService iUserService;
	
	/**
	 * 登陆成功跳转
	 * 普通用户
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/userScreen",method=RequestMethod.GET)
	public ModelAndView userScreen(ModelAndView model){
		model.setViewName("user/userIndex");
		return model;
	}
	/**
	 * 登陆成功跳转
	 * 管理员
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/adminScreen",method=RequestMethod.GET)
	public ModelAndView adminScreen(ModelAndView model){
		model.setViewName("admin/adminIndex");
		return model;
	}
	
	/**
	 * 登录页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/login",method=RequestMethod.GET)
	public ModelAndView login(ModelAndView model){
		model.setViewName("login");
		return model;
	}
	
	/**
	 * 登录表单提交
	 * @param username
	 * @param password
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/login",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> login(
			@RequestParam(value="account",required=true) String account,
			@RequestParam(value="password",required=true) String password,
			HttpServletRequest request,HttpServletResponse response
			){
		Map<String, String> map = new HashMap<String, String>();
		if(StringUtils.isEmpty(account)){
			map.put("type", "error");
			map.put("msg", "用户名不能为空!");
			return map;
		}
		if(StringUtils.isEmpty(password)){
			map.put("type", "error");
			map.put("msg", "密码不能为空!");
			return map;
		}
		User user = iUserService.findUserByAccount(account);
		System.out.println(user.getNickname()+"---------------------");
		System.out.println(Md5Utils.md5(password)+"--------------");
		if(user==null){
			map.put("type", "error");
			map.put("msg", "账户不存在!");
			return map;
		}
		if(!Md5Utils.md5(password).equals(user.getPassword())){
			map.put("type", "error");
			map.put("msg", "密码错误!");
			return map;
		}
		request.getSession().setAttribute("loginUser", user);
		map.put("type", "success");
		map.put("msg", "登录成功!");
		map.put("role", String.valueOf(user.getRole()));
		return map;
	}
	
	
	
	/**
	 * 注册成功
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/regSuccess",method=RequestMethod.GET)
	public ModelAndView regSuccess(ModelAndView model){
		model.setViewName("success");
		return model;
	}
	
	/**
	 * 注册页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/register",method=RequestMethod.GET)
	public ModelAndView register(ModelAndView model){
		model.setViewName("register");
		return model;
	}
	
	/**
	 * 注册表单提交
	 * @param account
	 * @param password
	 * @param rePassword
	 * @return
	 */
	@RequestMapping(value="/register",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> register(User user){
		Map<String, String> map = new HashMap<String, String>();
		if(iUserService.findUserByAccount(user.getAccount())!=null){
			map.put("type", "error");
			map.put("msg", "该用户账号已存在!");
			return map;
		}
		System.out.println(user.getAccount()+"-------"+user.getPassword());
		user.setPassword(Md5Utils.md5(user.getPassword()));
		try {
			  iUserService.addUser(user);
		} catch (UserServcieException e) {
			e.printStackTrace();
			map.put("type", "error");
			map.put("msg", "添加用户失败!");
			return map;
		}
		map.put("type", "success");
		map.put("msg", "注册成功");
		return map;
	}
	

}
