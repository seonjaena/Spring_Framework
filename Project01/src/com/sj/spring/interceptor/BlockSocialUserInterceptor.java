package com.sj.spring.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

import com.sj.spring.beans.UserBean;

public class BlockSocialUserInterceptor implements HandlerInterceptor {

	private UserBean loginUserBean;
	public BlockSocialUserInterceptor(UserBean loginUserBean) {
		this.loginUserBean = loginUserBean;
	}
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		
		if(loginUserBean.isUserSocial() == true) {
			String context_path = request.getContextPath();
			response.sendRedirect(context_path + "/user/social_id");
			return false;
		}
		
		return true;
		
	}
	
}
