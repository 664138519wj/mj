package com.migu.online.intercepter;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.migu.online.configuration.NeedLogin;
import com.migu.online.model.User;

/**
 * 检查用户是否登录的拦截器
 * 
 * @author Yunlong Fan
 */
public class UserInterceptor extends HandlerInterceptorAdapter {

    // 无访问权限跳转页面
    private static final String NO_PERMISSION_URL = "/home/auth/toLogin";
    
    @Override
    public boolean preHandle(HttpServletRequest request,
            HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            // because all requests will go through here, so only dynamical
            // request should validate this.
            HandlerMethod method = (HandlerMethod) handler;
            NeedLogin needLogin = method.getMethodAnnotation(NeedLogin.class);
            if (needLogin != null) {
                return validateLogined(request, response);
            }
        }

        return true;
    }

    /**
     * 验证是否登录
     * 
     * @param handler
     * @return
     * @throws IOException
     * @throws ServletException 
     */
	private boolean validateLogined(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		User user = (User) SecurityUtils.getSubject().getPrincipal();
		if (user != null) {
			return true;
		} else {
	        String noPermissionUrl = NO_PERMISSION_URL + "?redirectUrl=" + getRedirectUrl(request);  
			request.getRequestDispatcher(noPermissionUrl).forward(request, response);
			return false;
		}
    }
	
	public String getRedirectUrl(HttpServletRequest request) {
		String url = "http://" + request.getServerName() // 服务器地址
				+ ":" + request.getServerPort() // 端口号
				+ request.getRequestURI();
		String queryurl = request.getQueryString();
		if (null != queryurl) {
			url += "?" + queryurl;
		}
		return url;
	}
}