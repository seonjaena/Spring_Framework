package com.sj.spring.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

import com.sj.spring.beans.BoardInfoBean;
import com.sj.spring.beans.MediaInfoBean;
import com.sj.spring.beans.UserBean;
import com.sj.spring.service.TopMenuService;

public class TopMenuInterceptor implements HandlerInterceptor {

	private TopMenuService topMenuService;
	private UserBean loginUserBean;
	
	public TopMenuInterceptor(TopMenuService topMenuService, UserBean loginUserBean) {
		this.topMenuService = topMenuService;
		this.loginUserBean = loginUserBean;
	}
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		
		List<BoardInfoBean> topMenuList = topMenuService.getTopMenuList();
		List<MediaInfoBean> mediaMenuList = topMenuService.getMediaMenuList();
		
		request.setAttribute("topMenuList", topMenuList);
		request.setAttribute("loginUserBean", loginUserBean);
		request.setAttribute("mediaMenuList", mediaMenuList);
		
		return true;
		
	}
	
}