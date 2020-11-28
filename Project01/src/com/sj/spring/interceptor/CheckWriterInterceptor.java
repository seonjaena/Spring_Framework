package com.sj.spring.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

import com.sj.spring.beans.ContentBean;
import com.sj.spring.beans.UserBean;
import com.sj.spring.service.BoardService;

public class CheckWriterInterceptor implements HandlerInterceptor {

	private UserBean loginUserBean;
	private BoardService boardService;
	
	public CheckWriterInterceptor(UserBean loginUserBean, BoardService boardService) {
		this.loginUserBean = loginUserBean;
		this.boardService = boardService;
	}
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		
		int content_idx = Integer.parseInt(request.getParameter("content_idx"));
		
		ContentBean contentBean = boardService.getContentInfo(content_idx);
		
		if(loginUserBean.getUser_idx() != contentBean.getContent_writer_idx()) {
			String context_path = request.getContextPath();
			response.sendRedirect(context_path + "/board/not_writer");
			return false;
		}
		
		return true;
		
	}
	
}
