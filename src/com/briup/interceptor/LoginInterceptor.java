package com.briup.interceptor;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 登录过滤拦截器
 * @author 14760
 *
 */
public class LoginInterceptor implements HandlerInterceptor{

	@Override
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object arg2) throws Exception {
		String uri = request.getRequestURI();
		System.out.println("进入拦截器---------uri--"+uri);
		Object user = request.getSession().getAttribute("loginUser");
		if(user==null){
			System.out.println("未登录或者登录失效------uri---"+uri);
			if("XMLHttpRequest".equals(request.getHeader("X-Requested-With"))){
				Map<String, String> map = new HashMap<String, String>();
				map.put("type", "error");
				map.put("msg", "登录状态已失效，请重新登录！");
				response.getWriter().write(JSONObject.fromObject(map).toString());
				return false;
			}
			response.sendRedirect(request.getContextPath()+"/systemController/login");
			return false;
		}
		return true;
	}

}
