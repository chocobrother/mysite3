package com.cafe24.mysite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.cafe24.mysite.service.UserService;
import com.cafe24.mysite.vo.UserVo;

@Controller
@SessionAttributes("authUser")
@RequestMapping("/user2")


//sessionAttribute 위함
public class UserController2 {

	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String login() {
		return "user/login2";
	}
	
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String login(@ModelAttribute UserVo vo,
			Model model) {
		
		UserVo authUser = userService.getUser(vo);
		
		System.out.println(authUser);
		model.addAttribute("authUser",authUser);
		
		
		
		return "redirect:/main";
	}
	
	@ResponseBody
	@RequestMapping("/modify")
	public String modify(
			@ModelAttribute("authUser") UserVo authUser) {
	
		System.out.println(authUser);
		return "UserController2:modify";
	}
	
	@RequestMapping("/logout")
	public String logout(SessionStatus sessionStatus) {
		
		sessionStatus.setComplete();
		
		
		return "redirect:/user2/login";
	}
}
