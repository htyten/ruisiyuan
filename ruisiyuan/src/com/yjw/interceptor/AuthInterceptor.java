package com.yjw.interceptor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.yjw.pojo.Account;
import com.yjw.pojo.Menu;
import com.yjw.pojo.Role;
import com.yjw.service.MenuService;
import com.yjw.service.RoleService;

/**
 * 权限控制
 * @author eason
 *
 * 2016年6月3日下午5:26:13
 */
public class AuthInterceptor implements HandlerInterceptor {
	
	private Log log = LogFactory.getLog(AuthInterceptor.class);
	
	@Autowired
	private RoleService roleService;
	@Autowired
	private MenuService menuService;
	
	/**
	 * 权限缓存
	 */
	private static Map<Integer, List<Menu>> authCache = new HashMap<Integer, List<Menu>>();
	/**
	 * 不做权限控制的路径
	 */
	private String[] publicAuths = {"loginOn.do"};
	
	/**
	 * 在DispatcherServlet完全处理完请求后被调用,可用于清理资源等
	 */
	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
	}

	/**
	 * 在业务处理器处理请求执行完成后,生成视图之前执行的动作
	 */
	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
	}

	/**
	 * 在业务处理器处理请求之前被调用 
	 * 		return true 请求放行
	 */
	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object obj) throws Exception {
		String requestUri = req.getRequestURI();  
        String contextPath = req.getContextPath();  
        String path = requestUri.substring(contextPath.length() + 1, requestUri.length());
        
        
        log.debug("访问路径：" + path);
        
        // 不做权限的路径，直接通过
        for(String publicAuth : publicAuths) {
        	if(path.equals(publicAuth)) {
        		log.debug("访问通过...");
        		return true;
        	}
        }
        
        // 获取当前登录用户
        Account acc = (Account) req.getSession().getAttribute("loginAccount");
        // 获取当前登录用户的角色信息
        if(acc.getRoles() == null) {
        	acc.setRoles(roleService.getRolesByAcc(acc.getAccount()));
        	// 第一次查询到登录用户的角色信息后，重新设置到session，下次拦截的时候就不需要再去DB查询登录用户对应的角色
        	req.getSession().setAttribute("loginAccount", acc);
        }
        // 装载当前用户所有可以访问的菜单
        List<Menu> menus = new ArrayList<Menu>();
        for(Role r : acc.getRoles()) {
        	if(authCache.get(r.getId()) == null) {
        		// 当缓存当中没有角色对应的菜单项的时候，查询DB，并缓存
        		authCache.put(r.getId(), menuService.getMenusByRoleId(r.getId()));
        	}
        	menus.addAll(authCache.get(r.getId()));  // 装载菜单
        }
        
        // 权限判断
        boolean authFlag = false;
        for(Menu m : menus) {
        	if(m.getAuthPath() != null && !m.getAuthPath().equals("") && m.getAuthPath().equals(path)) {
        		authFlag = true;
        		break;
        	}
        }
        
        if(!authFlag) {
        	log.debug("访问不允许通过,跳转到权限错误页......");
        	res.sendRedirect(contextPath + "/error/error-auth.jsp");
        	return false;
        }
        log.debug("访问通过...");
		return true;
	}

	public static Map<Integer, List<Menu>> getAuthCache() {
		return AuthInterceptor.authCache;
	}

	public static void setAuthCache(Map<Integer, List<Menu>> authCache) {
		AuthInterceptor.authCache = authCache;
	}

}
