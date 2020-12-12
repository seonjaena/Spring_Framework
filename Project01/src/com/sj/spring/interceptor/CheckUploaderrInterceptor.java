package com.sj.spring.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

import com.sj.spring.beans.MediaBean;
import com.sj.spring.beans.UserBean;
import com.sj.spring.service.MediaService;

public class CheckUploaderrInterceptor implements HandlerInterceptor {

	private UserBean loginUserBean;
	private MediaService mediaService;
	
	public CheckUploaderrInterceptor(UserBean loginUserBean, MediaService mediaService) {
		this.loginUserBean = loginUserBean;
		this.mediaService = mediaService;
	}
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		
		int media_idx = Integer.parseInt(request.getParameter("media_idx"));
		
		MediaBean mediaBean = mediaService.getMediaInfo(media_idx);
		
		if(loginUserBean.getUser_idx() != mediaBean.getMedia_writer_idx()) {
			String context_path = request.getContextPath();
			response.sendRedirect(context_path + "/media/not_uploader");
			return false;
		}
		
		return true;
		
	}
	
}
