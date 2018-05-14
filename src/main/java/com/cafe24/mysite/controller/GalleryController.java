package com.cafe24.mysite.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/gallery")
public class GalleryController {
	
	@RequestMapping("")
	public String ajax() {
			
		System.out.println("zzzzzzzzzzvvvvvvvvvvvvvvv");
		
		return "gallery/index";
	}
	
}
