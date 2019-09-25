package com.briup.listener;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.briup.bean.Category;
import com.briup.dao.IUserMapper;
import com.briup.exception.CategoryException;
import com.briup.service.ICategoryServcie;
import com.briup.service.IUserService;

/**
 * Application Lifecycle Listener implementation class ApplicationListener
 *
 */
@WebListener
public class ApplicationListener implements ServletContextListener {
	

    /**
     * Default constructor. 
     */
    public ApplicationListener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent sce)  { 
         System.out.println("tomcat destory---------");
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent sce)  { 
         System.out.println("tomcat init-----------");
         ServletContext application = sce.getServletContext();
         
         ICategoryServcie iCategoryService = (ICategoryServcie) WebApplicationContextUtils.
        			getRequiredWebApplicationContext(sce.getServletContext()).getBean("categoryServiceImpl");

         //查询所有一级菜单以及二级菜单
         List<Category> list = null;
         try {
			list = iCategoryService.listAllTwoCategory();
			System.out.println("所有菜单已经找到---------");
			System.out.println(list+"-----------");
			for(Category cate:list){
				System.out.println(cate+"==========");
			}
		} catch (CategoryException e) {
			e.printStackTrace();
		}
        application.setAttribute("categoryList", list);
         
    }
	
}
