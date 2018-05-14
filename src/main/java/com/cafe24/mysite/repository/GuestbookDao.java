package com.cafe24.mysite.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.mysite.vo.GuestbookVo;


@Repository
public class GuestbookDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	
	public int insert( GuestbookVo vo ) {
			
		int count = sqlSession.insert("guestbook.insert",vo);	
		
		return count;
	}
	
	public GuestbookVo get(Long no) {
		return sqlSession.selectOne("guestbook.getByNo",no);
	}
//	
//	public int delete( GuestbookVo vo ) {
//		
//		Map<String, Object> map = new HashMap<String, Object>();
//		
//		map.put("no", vo.getNo());
//		map.put("pwd", vo.getPassword());
//		
//		int count = sqlSession.delete("guestbook.delete",map);
//		
//		return count;
//	}
	
	public int delete( GuestbookVo vo ) {
		int count = sqlSession.delete("guestbook.delete", vo);
		return count;		
	}
	
	
	public List<GuestbookVo> getList() {
		List<GuestbookVo> list = 
			sqlSession.selectList("guestbook.getList");
	
		
		
		return list;
	}
	
	public List<GuestbookVo> getList2(Long no) {
		List<GuestbookVo> list = 
			sqlSession.selectList("guestbook.getList2",no);
	
		
		
		return list;
	}
	
}