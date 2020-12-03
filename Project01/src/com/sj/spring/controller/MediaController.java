package com.sj.spring.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sj.spring.beans.MediaBean;
import com.sj.spring.beans.PageBean;
import com.sj.spring.beans.UserBean;
import com.sj.spring.service.MediaService;
import com.sj.spring.validator.MediaValidator;

@Controller
@RequestMapping("/media")
public class MediaController {

	@Autowired
	private MediaService mediaService;
	
	@Resource(name = "loginUserBean")
	private UserBean loginUserBean;
	
	@GetMapping("/main")
	public String main(HttpServletRequest request, 
					   @RequestParam("media_info_idx") int media_info_idx, 
					   @RequestParam(value = "page", defaultValue = "1") int page) {
		
		List<MediaBean> mediaList = mediaService.getMediaList(media_info_idx, page);
		request.setAttribute("mediaList", mediaList);
		request.setAttribute("media_info_idx", media_info_idx);
		request.setAttribute("page", page);
		
		PageBean pageBean = mediaService.getMediaCnt(page, media_info_idx);
		request.setAttribute("pageBean", pageBean);
		
		return "media/main";
		
	}
	
	@GetMapping("/upload")
	public String upload(@ModelAttribute("writeMediaBean") MediaBean writeMediaBean, HttpSession session,
						 @RequestParam("media_info_idx") int media_info_idx, Model model) {
		
		model.addAttribute("media_info_idx", media_info_idx);
		session.setAttribute("media_info_idx", media_info_idx);
		
		return "media/upload";
		
	}
	
	@PostMapping("/upload_pro")
	public String upload_pro(@Valid @ModelAttribute("writeMediaBean") MediaBean writeMediaBean, BindingResult result,
							 HttpSession session) {
		
		int media_info_idx = (int)session.getAttribute("media_info_idx");

		if(result.hasErrors()) {
			
			return "media/upload";
			
		}
		session.removeAttribute("media_info_idx");
		writeMediaBean.setMedia_board_idx(media_info_idx);
		mediaService.addMediaInfo(writeMediaBean);
		
		return "media/upload_success";
		
	}
	
	@GetMapping("/read")
	public String read(@RequestParam("media_info_idx") int media_info_idx,
					   @RequestParam("media_idx") int media_idx, 
					   @RequestParam("page") int page,
					   HttpServletRequest request) {
		
		MediaBean readMediaBean = mediaService.getMediaInfo(media_idx);
		
		if(loginUserBean.getUser_idx() != readMediaBean.getMedia_writer_idx()) {
			mediaService.addViews(readMediaBean.getViews() + 1, media_idx);
		}

		request.setAttribute("media_info_idx", media_info_idx);
		request.setAttribute("media_idx", media_idx);
		request.setAttribute("page", page);
		request.setAttribute("loginUserBean", loginUserBean);
		request.setAttribute("readMediaBean", readMediaBean);
		
		return "media/read";
		
	}
	
	@GetMapping("/modify")
	public String modify(@ModelAttribute("modifyMediaBean") MediaBean modifyMediaBean,
						 @RequestParam("media_info_idx") int media_info_idx, 
						 @RequestParam("media_idx") int media_idx, 
						 @RequestParam("page") int page, 
						 Model model, HttpSession session) {

		mediaService.getModifyMediaInfo(modifyMediaBean, media_idx);
		model.addAttribute("media_info_idx", media_info_idx);
		model.addAttribute("page", page);
		session.setAttribute("media_idx", media_idx);
		
		return "media/modify";
		
	}
	
	@PostMapping("/modify_pro")
	public String modify_success(@Valid @ModelAttribute("modifyMediaBean") MediaBean modifyMediaBean, BindingResult result, 
								 HttpServletRequest request, HttpSession session, Model model) {
		
		if(result.hasErrors()) {
			
			return "media/modify";
			
		}
		
		int page = Integer.parseInt(request.getParameter("page"));
		int media_info_idx = Integer.parseInt(request.getParameter("media_info_idx"));
		int media_idx = (int) session.getAttribute("media_idx");
		session.removeAttribute("media_idx");
		
		modifyMediaBean.setMedia_idx(media_idx);
		modifyMediaBean.setMedia_board_idx(media_info_idx);
		model.addAttribute("page", page);
		mediaService.modifyMediaInfo(modifyMediaBean);
		
		return "media/modify_success";
		
	}
	
	@GetMapping("/delete")
	public String delete(@RequestParam("media_info_idx") int media_info_idx,
						 @RequestParam("media_idx") int media_idx, Model model) {
		
		mediaService.deleteMediaInfo(media_idx);
		
		model.addAttribute("media_info_idx", media_info_idx);
		
		return "media/delete";
		
	}
	
	@GetMapping("/not_writer")
	public String not_writer() {
		return "media/not_writer";
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		MediaValidator validator2 = new MediaValidator();
		binder.addValidators(validator2);
	}
	
}