package com.cafe24.mysite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe24.mysite.repository.GuestbookDao;
import com.cafe24.mysite.vo.GuestbookVo;

@Service	
public class GuestbookService {

	
	@Autowired
	private GuestbookDao guestbookDao;
	
	public void insert(GuestbookVo vo) {
		
		guestbookDao.insert(vo);
	}
	
	public GuestbookVo insertMessage(GuestbookVo guestbookVo) {
		GuestbookVo vo = null;
		
		int count = guestbookDao.insert(guestbookVo);

		if (count == 1) {
		 vo = guestbookDao.get(guestbookVo.getNo());
		}
 
		return vo;
	}
	
	
	
	public boolean delete(GuestbookVo vo) {
		
		int count = guestbookDao.delete(vo);
		
		return count == 1;
	}
	
	// public void delete(GuestbookVo vo) {
	//
	// guestbookDao.delete(vo);
	//
	//
	// }
	
	
	public List<GuestbookVo> getlist() {
		
		List<GuestbookVo> list = guestbookDao.getList();
		
		return list;
		
	}
	
	public List<GuestbookVo> getlist2(Long no) {

		
		List<GuestbookVo> list = guestbookDao.getList2(no);
		
		return list;
		
	}
}
