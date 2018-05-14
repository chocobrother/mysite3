package com.cafe24.mysite.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cafe24.mysite.service.GuestbookService;
import com.cafe24.mysite.vo.GuestbookVo;

@Controller
@RequestMapping("/guestbook")
public class GuestbookController {

	@Autowired
	private GuestbookService guestbookservice;
	
//	@Autowired
//	private GuestbookDao guestbookDao;
	
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model) {
		
		List<GuestbookVo> list = null;  
				
		list = guestbookservice.getlist();
		
		model.addAttribute("list",list);
		
		return "guestbook/list";
	}
	
	
	@RequestMapping(value = "/form")
	public String form(Long no, Model model) {
			
		 model.addAttribute("no",no);
		
		return "guestbook/deleteform";
	}
	

	@RequestMapping(value="/add",method=RequestMethod.POST)
	public String add(@ModelAttribute GuestbookVo vo) {
//		guestbookDao.insert(vo);
		
		guestbookservice.insert(vo);
		return "redirect:/guestbook/list";
	}
	

	@RequestMapping(value="/delete")
	public String delete(@ModelAttribute GuestbookVo vo) {
			
		guestbookservice.delete(vo);
		return "redirect:/guestbook/list";
	}
	
	
	@RequestMapping(value="/ajax")
	public String ajax() {
			
		
		return "guestbook/index-ajax";
	}
	
	
	
	
}
