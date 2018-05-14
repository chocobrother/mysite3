package com.cafe24.mysite.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cafe24.mysite.service.BoardService;
import com.cafe24.mysite.vo.BoardVo;

@Controller
@RequestMapping("/board")
public class BoardController {

	@Autowired
	private BoardService boardservice;
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)	
	public String list(Model model,@RequestParam(value = "pg",required = false) String pg) {
		
		Map<String,Object> map = new HashMap<String,Object>(); 

		map =  boardservice.getList(pg);
		
		model.addAttribute("map",map);
		
		return "board/list";
	}
	
	
	@RequestMapping(value = "/view", method = RequestMethod.GET)	
	public String view(Model model, 
			@RequestParam(value = "no",required = false) String no,
			@ModelAttribute BoardVo vo) {


		System.out.println(" view no ~~~~~~~~~: " + no);
		
		
		List<BoardVo> list = boardservice.view(vo,no);
		
		model.addAttribute("list",list);
		
		
		return "board/view";
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.GET)	
	public String delete(Model model,
			@RequestParam(value = "no",required = false) String no,
			@RequestParam(value = "user_no",required = false) String user_no,
			@ModelAttribute BoardVo vo) {
		
		
		
		boardservice.delete(vo,no,user_no);
		
		return "redirect:/board/list";
	}
	

	@RequestMapping(value = "/write", method = RequestMethod.GET)
	public String write() {
		
		return "board/write";
	}
	
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)	
	public String write(Model model,
			@RequestParam(value = "title",required = false) String title,
			@RequestParam(value = "content",required = false) String content,
			@RequestParam(value = "no",required = false) String no,
			@ModelAttribute BoardVo vo) {
		
		System.out.println("WRITE : + " + no);
		
		boardservice.write(vo,title,content,no);
		
		
		return "redirect:/board/list";
	}
	
	
	
	@RequestMapping(value = "/replyform", method = RequestMethod.GET)
	public String replyform(Model model,
			@RequestParam(value = "no",required = false) String no,
			@RequestParam(value = "groupNo",required = false) String groupNo,
			@RequestParam(value = "orderNo",required = false) String orderNo,
			@RequestParam(value = "depth",required = false) String depth,	
			@RequestParam(value = "user_no",required = false) String user_no
			) {
		
		Map<String,Object> map = new HashMap<String,Object>(); 

		System.out.println(" 댓글 폼 번호 값 : " + no);
		
		map =  boardservice.replyform(no,groupNo, orderNo, depth, user_no);
		
		model.addAttribute("map",map);
		
		return "board/replyform";
	}
	
	
	@RequestMapping(value = "/reply", method = RequestMethod.POST)	
	public String reply(Model model,
			@RequestParam(value = "title",required = false) String title,
			@RequestParam(value = "content",required = false) String content,
			@RequestParam(value = "groupNo",required = false) String groupNo,
			@RequestParam(value = "orderNo",required = false) String orderNo,
			@RequestParam(value = "user_no",required = false) String user_no,
			@RequestParam(value = "depth",required = false) String depth,
			@RequestParam(value = "no",required = false) String no,
		
			@ModelAttribute BoardVo vo) {
		
		System.out.println("ddddddddddd" + title +"/"+ content +"/"+ groupNo);
		
		
		boardservice.reply(vo,title,content,groupNo,orderNo,user_no,depth,no);
		
		
		return "redirect:/board/list";
	}
	
	

	@RequestMapping(value = "/modifyform", method = RequestMethod.GET)
	public String modifyform(Model model,
			@RequestParam(value = "no",required = false) String no,
			@ModelAttribute BoardVo vo
			) {
		
		

      List<BoardVo> list = boardservice.modifyform(vo,no);
      
      model.addAttribute("list",list);
      
 
		return "board/modify";
	}
	
	
	@RequestMapping(value = "/modify", method = RequestMethod.POST)	
	public String modify(Model model,
			@RequestParam(value = "title",required = false) String title,
			@RequestParam(value = "content",required = false) String content,
			@RequestParam(value = "no",required = false) String no,
			@ModelAttribute BoardVo vo) {
		
		boardservice.update(vo, no,title,content);
		
		
		return "redirect:/board/list";
	}
	
}
