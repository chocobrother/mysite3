package com.cafe24.mysite.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe24.mysite.repository.BoardDao;
import com.cafe24.mysite.vo.BoardVo;

@Service
public class BoardService {

	@Autowired
	private BoardDao boardDao;
	
	public Map getList(String pg1) {
		
		Map<String,Object> map = new HashMap<String,Object>();
		
		if(pg1 == null) {
			pg1 = "1";
		}
		
		int pg = Integer.parseInt(pg1);
		
		List<BoardVo> list = boardDao.getList(pg);
		
		
		for(BoardVo vo : list){
			
			System.out.println("이름 리스트 :" + vo);
		}
		
		int totalA = boardDao.getTotalA(); // 게시글 총 갯수 
		
		
		int totalP = (totalA+2) /3; //총페이지
//
		int startPage = (pg-1)/3*3+1; //시작번호
		int endPage = startPage + 2; //끝번호 
//		
		if(totalP < endPage) endPage = totalP;
		
		
		map.put("list", list);
		map.put("pg",pg);
		map.put("startPage", startPage);
		map.put("endPage", endPage);
		map.put("totalP", totalP);
		
		
		return map;
	}
	

	public List<BoardVo> view(BoardVo vo, String no) {
//		List<GuestbookVo> list = guestbookDao.getList();
		
		System.out.println(" 뷰 서비스 번호 값 : " + no);
		
		Long no1 = Long.parseLong(no);
		
		vo.setNo(no1);
		
		List<BoardVo> list = boardDao.viewList(vo);
		boardDao.hitUpdate(vo);
		
		
		
		return list;
		
	}
	
	public void delete(BoardVo vo,String no, String user_no) {
//		List<GuestbookVo> list = guestbookDao.getList();
		
		Long no1 = Long.parseLong(no);
		Long user_no1 = Long.parseLong(user_no);
		
		vo.setNo(no1);
		vo.setUser_no(user_no1);
		
		boardDao.delete(vo);
		
	}
	
	public void write(BoardVo vo,String title,String content, String no) {
//		List<GuestbookVo> list = guestbookDao.getList();
		
		
		Long user_no1 = Long.parseLong(no);
		
		Long group_no = (long) boardDao.maxGroupNo();
		
		vo.setTitle(title);
		vo.setContent(content);
		vo.setGroup_no(++group_no);
		vo.setDepth((long) 1);
		vo.setOrder_no((long) 1);
		vo.setHit((long) 1);
		vo.setUser_no(user_no1);
				
		boardDao.insert(vo);
		
	}
	
	
	public Map replyform(String no1,String GroupNo1, String orderNo1, String depth1, String user_no1) {
//		List<GuestbookVo> list = guestbookDao.getList();
		
		
		System.out.println(" 댓글폼 서비스 번호 값" + no1);
		
		Long no = Long.parseLong(no1);
		Long groupNo = Long.parseLong(GroupNo1);
		Long orderNo = Long.parseLong(orderNo1);
		Long depth = Long.parseLong(depth1);
		Long user_no = Long.parseLong(user_no1);
		
		
		
		Map<String, Object> map = new HashMap<String,Object>();		
		
		map.put("no", no);
		map.put("groupNo", groupNo);
		map.put("orderNo", orderNo);
		map.put("depth", depth);
		map.put("user_no", user_no);

					
		return map;
		
	}
	
	
	public void reply(BoardVo vo,String title,String content, String groupNo,String orderNo,
			String user_no,String depth,String no) {
//		List<GuestbookVo> list = guestbookDao.getList();
		
		
		Long no1 = Long.parseLong(no);

		Long groupNo1 = Long.parseLong(groupNo);
		
		Long user_no1 = Long.parseLong(user_no);
		
		Long orderNo1 = (long) boardDao.maxOrderNo(groupNo1);
		
		Long depth1 = (long)boardDao.maxdepth(groupNo1);
		
		
		vo.setNo(no1);
		vo.setTitle(title);
		vo.setContent(content);
		vo.setGroup_no(groupNo1);
		vo.setOrder_no(++orderNo1);
		vo.setDepth(++depth1);
		vo.setHit((long) 1);
		vo.setUser_no(user_no1);
				
		boardDao.replyInsert(vo);
		
	}
	
	
	public List<BoardVo> modifyform(BoardVo vo, String no1) {

		
		Long no = Long.parseLong(no1);
		
		vo.setNo(no);
		
		List<BoardVo> list = boardDao.viewList(vo);
		

					
		return list;
		
	}
	
public void update(BoardVo vo, String no1,String title, String content) {

		
		Long no = Long.parseLong(no1);
		
		vo.setNo(no);
		
		vo.setTitle(title);
		
		vo.setContent(content);
		
		boardDao.update(vo);
		

					
		
		
	}
	
	
	
}
