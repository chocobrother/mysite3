package com.cafe24.mysite.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cafe24.mysite.service.UserService;
import com.cafe24.mysite.vo.UserVo;
import com.cafe24.security.Auth;
import com.cafe24.security.AuthUser;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/join",  method = RequestMethod.GET)
	public String join(@ModelAttribute UserVo vo) {
		return "user/join";
	}
	
	//이 객체안에있는 필드들 속성들을 검증하겠다 => valid
	//BindingResult 에 valid 바인딩한 결과가 들어있음
	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public String join(@ModelAttribute @Valid UserVo vo, BindingResult result) {
		
		//바인딩 결과 처리
		//이결과를 폼화면에 띄어줘야함
		//binding result를 jsp에 보내주면 된다
		//forword 안에 request.getattribute에 넣어준다거나 함
		//jsp에서 태그라이브러리를 제공함 우리가 파싱하고 있을순 없으니까 jstl el로 
		//
		if(result.hasErrors()) {
			
			List<ObjectError> list = result.getAllErrors();
			
			for(ObjectError error : list) {
				System.out.println( "Object Error" + error);
			}
			
			return "/user/join";
		}
		
		//userDao.insert(vo);
		
		userService.join(vo);
		
		return "redirect:/user/joinsuccess";
	}
	
	
	@RequestMapping(value = "/joinsuccess")
	public String joinsuccess() {
		return "user/joinsuccess";
	}

	@RequestMapping(value = "/login",method = RequestMethod.GET)
	public String login() {
		return "user/login";
	}
	
//	@RequestMapping(value = "/login",method = RequestMethod.POST)
//	public String login(
//			HttpSession session,
//			@ModelAttribute UserVo vo,
//			Model model) {
//		
//		UserVo authUser = userService.getUser(vo);
//				
////				.get(vo.getEmail(), vo.getPassword());
//		
//		if(authUser == null) {
//			model.addAttribute("result","fail");
//			return "user/login";
//		}
//		
//		//인증처리
//		session.setAttribute("authUser", authUser);
//
//		return "redirect:/main";
//	}
//	
//	@RequestMapping("/logout")
//	public String logout(HttpSession session) {
//		session.removeAttribute("authUser");
//		session.invalidate();
//		
//		return "redirect:/main";
//	}

	@Auth
	@RequestMapping(value = "/modify",method = RequestMethod.POST)
	public String modify(
			@RequestParam(value = "user_no",required = false) String no,
			@ModelAttribute UserVo vo,
			HttpSession session,
			@AuthUser UserVo authUser) {

		System.out.println(authUser);
		
		
		
//		System.out.println("MODIFY NO = :" + no);
		
		/*접근제어*/
//		
		 authUser = (UserVo)session.getAttribute("authUser");
		if(authUser == null) {
			return "redirect:/main";
		}

		userService.update(no, vo);
		
		return "user/modifysuccess";
	}
	
	
	@RequestMapping(value = "/modifyform",method = RequestMethod.GET)
	public String modifyform(
			Model model,
			HttpSession session,
			@AuthUser UserVo authUser) {

		
		System.out.println("AUTHUSER NAME : MODIFY" + authUser.getName());
		
		UserVo vo = userService.getUser(authUser.getNo());
		
		model.addAttribute(vo);
//		UserVo authUser = new UserDao().getName();
		
//		UserVo authUser = userService.getName();
		
		
		
//		model.addAttribute("authUser",authUser);
		
		return "user/modify";
	}
	
	
	
	
	
	/*
	@ExceptionHandler( UserDaoException.class )
	public String handleUserDaoException() {
		return "error/error";
	}
	 */
	
	
}
