package com.sj.spring.controller;

import java.io.IOException;
import java.text.ParseException;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.scribejava.core.model.OAuth2AccessToken;
import com.sj.spring.beans.NaverLoginBean;
import com.sj.spring.beans.UserBean;
import com.sj.spring.service.UserService;
import com.sj.spring.validator.UserValidator;

@Controller
@RequestMapping("/user")
public class UserController {

	private NaverLoginBean naverLoginBean;
	private String apiResult = null;
	
	@Autowired
	private UserService userService;
	
	@Resource(name = "loginUserBean")
	private UserBean loginUserBean;
	
	@Autowired
	private void setNaverLoginBean(NaverLoginBean naverLoginBean) {
		this.naverLoginBean = naverLoginBean;
	}
	
	@GetMapping("/login")
	public String login(@ModelAttribute("tempLoginUserBean") UserBean tempLoginUserBean, Model model,
						@RequestParam(value = "fail", defaultValue = "false") boolean fail, HttpSession session) {
		
		String naverAuthUrl = naverLoginBean.getAuthorizationUrl(session);
		
		model.addAttribute("url", naverAuthUrl);
		model.addAttribute("fail", fail);
		
		return "user/login";
		
	}
	
	@PostMapping("/login_pro")
	public String login_pro(@Valid @ModelAttribute("tempLoginUserBean") UserBean tempLoginUserBean, BindingResult result) {
		
		if(result.hasErrors()) {
			
			return "user/login";
			
		}
		
		userService.getLoginUserInfo(tempLoginUserBean);

		if(loginUserBean.isUserLogin() == true) {
			
			return "user/login_success";
			
		}else {
			
			return "user/login_fail";
			
		}
		
	}
	
	@RequestMapping(value = "/call_back", method = {RequestMethod.GET, RequestMethod.POST})
	public String callback(Model model, @RequestParam String code, @RequestParam String state, HttpSession session) throws IOException, ParseException {
		OAuth2AccessToken oauthToken;
		oauthToken = naverLoginBean.getAccessToken(session, code, state);
		apiResult = naverLoginBean.getUserProfile(oauthToken);
		JSONParser parser = new JSONParser();
		Object obj = null;
		try {
			obj = parser.parse(apiResult);
		} catch (org.json.simple.parser.ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JSONObject jsonObj = (JSONObject) obj;
		JSONObject response_obj = (JSONObject)jsonObj.get("response");
		String name = (String)response_obj.get("name");
		int idx = Integer.parseInt((String)response_obj.get("id")) * -1 - 2;
		loginUserBean.setUserLogin(true);
		loginUserBean.setUser_name(name);
		loginUserBean.setUser_idx(idx);
		return "main";
	}
	
	@GetMapping("/join")
	public String join(@ModelAttribute("joinUserBean") UserBean joinUserBean) {
		
		return "user/join";
		
	}
	
	@PostMapping("/join_pro")
	public String join_pro(@Valid @ModelAttribute("joinUserBean") UserBean joinUserBean, BindingResult result) {
		
		if(result.hasErrors()) {
			
			return "user/join";
			
		}
		
		userService.addUserInfo(joinUserBean);
		
		return "user/join_success";
		
	}
	
	@GetMapping("/modify")
	public String modify(@ModelAttribute("modifyUserBean") UserBean modifyUserBean) {
		userService.getModifyUserInfo(modifyUserBean);
		return "user/modify";
	}
	
	@PostMapping("/modify_pro")
	public String modify_pro(@Valid @ModelAttribute("modifyUserBean") UserBean modifyUserBean, BindingResult result) {
		
		if(result.hasErrors()) {
			
			return "user/modify";
			
		}
		
		userService.modifyUserInfo(modifyUserBean);
		
		return "user/modify_success";
		
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		
		session.invalidate();
		
		return "user/logout";
		
	}
	
	@GetMapping("/not_login")
	public String not_login() {
		return "user/not_login";
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		UserValidator validator1 = new UserValidator();
		binder.addValidators(validator1);
	}
	
}