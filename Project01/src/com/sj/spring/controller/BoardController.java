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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sj.spring.beans.ContentBean;
import com.sj.spring.beans.PageBean;
import com.sj.spring.beans.UserBean;
import com.sj.spring.service.BoardService;

@Controller
@RequestMapping("/board")
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	@Resource(name = "loginUserBean")
	private UserBean loginUserBean;
	
	@GetMapping("/main")
	public String main(@RequestParam("board_info_idx") int board_info_idx, Model model, 
					   @RequestParam(value = "page", defaultValue = "1") int page) {
		
		model.addAttribute("board_info_idx", board_info_idx);
		
		List<ContentBean> contentList = boardService.getContentList(board_info_idx, page);
		
		model.addAttribute("contentList", contentList);
		
		PageBean pageBean = boardService.getContentCnt(board_info_idx, page);
		model.addAttribute("pageBean", pageBean);
		
		return "board/main";
		
	}
	
	@GetMapping("/read")
	public String read(@RequestParam("board_info_idx") int board_info_idx,
					   @RequestParam("content_idx") int content_idx, 
					   @RequestParam("page") int page, 
					   Model model) {
		
		ContentBean readContentBean = boardService.getContentInfo(content_idx);
		List<String> file_list = boardService.getContentFile(content_idx);
		
		model.addAttribute("board_info_idx", board_info_idx);
		model.addAttribute("readContentBean", readContentBean);
		model.addAttribute("file_list", file_list);
		model.addAttribute("loginUserBean", loginUserBean);
		model.addAttribute("page", page);
		
		return "board/read";
		
	}
	
	@GetMapping("/write")
	public String write(@ModelAttribute("writeContentBean") ContentBean writeContentBean, 
						@RequestParam("board_info_idx") int board_info_idx) {
		
		writeContentBean.setContent_board_idx(board_info_idx);
		
		return "board/write";
		
	}
	
	@PostMapping("/write_pro")
	public String write_pro(@Valid @ModelAttribute("writeContentBean") ContentBean writeContentBean, BindingResult result) {
		
		if(result.hasErrors()) {
			
			return "board/write";
			
		}
		
		boardService.addContentInfo(writeContentBean);
		
		return "board/write_success";
		
	}
	
	@GetMapping("/modify")
	public String modify(@RequestParam("board_info_idx") int board_info_idx, 
						 @RequestParam("content_idx") int content_idx, 
						 @ModelAttribute("modifyContentBean") ContentBean modifyContentBean, 
						 @RequestParam("page") int page, 
						 Model model, HttpSession session) {
		
		boardService.getModifyContentInfo(content_idx, modifyContentBean);
		
		List<String> file_list = boardService.getModifyContentFile(content_idx);
		
		model.addAttribute("file_list", file_list);
		model.addAttribute("page", page);
		
		session.setAttribute("content_idx", content_idx);
		session.setAttribute("board_info_idx", board_info_idx);
		
		
		return "board/modify";
		
	}
	
	@PostMapping("/modify_pro")
	public String modify_pro(@Valid @ModelAttribute("modifyContentBean") ContentBean modifyContentBean, BindingResult result, 
							 HttpSession session, HttpServletRequest request, Model model) {
		
		int content_idx = (int)session.getAttribute("content_idx");
		int page = Integer.parseInt(request.getParameter("page"));
		model.addAttribute("page", page);
		model.addAttribute("content_idx", content_idx);
		
		if(result.hasErrors()) {

			return "board/modify";
			
		}
		
		boardService.modifyContentInfo(content_idx, modifyContentBean);
		
		int board_info_idx = (int)session.getAttribute("board_info_idx");
		
		session.removeAttribute("content_idx");
		session.removeAttribute("board_info_idx");
		
		model.addAttribute("board_info_idx", board_info_idx);
		
		return "board/modify_success";
		
	}
	
	@GetMapping("/delete")
	public String delete(@RequestParam("board_info_idx") int board_info_idx,
						 @RequestParam("content_idx") int content_idx, Model model) {
		
		boardService.deleteContentInfo(content_idx);
		
		model.addAttribute("board_info_idx", board_info_idx);
		
		return "board/delete";
		
	}
	
	@GetMapping("/not_writer")
	public String not_writer() {
		return "board/not_writer";
	}
	
}