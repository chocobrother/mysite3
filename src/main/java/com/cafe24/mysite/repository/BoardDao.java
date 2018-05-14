package com.cafe24.mysite.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.mysite.vo.BoardVo;

@Repository
public class BoardDao {

	@Autowired
	private SqlSession sqlSession;
	
	public boolean insert( BoardVo vo ) {

		int count = sqlSession.insert("board.insert",vo);
		
		return count==1;
	}
	
	public int hitUpdate( BoardVo vo ) {
		
		int count = sqlSession.update("board.hitupdate", vo);
		
		return count;
	}

	
	public boolean orderUpdate( BoardVo vo ) {
		
		int count = sqlSession.update("board.orderupdate", vo);
		
		return count==1;
	}
	
	
	public int delete( BoardVo vo ) {
		
		int count = sqlSession.delete("board.delete", vo);
		
		return count;
	}
	
	
	public List<BoardVo> getList(int pg) {
		
		int pg1 = 5*(pg-1);
		
		List<BoardVo> list = 
			sqlSession.selectList("board.getList", pg1);
	
		System.out.println("getList() : " + pg);

		
		return list;
	}
	
	
	public int getTotalA() {
		List<BoardVo> list = 
			new ArrayList<BoardVo>();
	
		int totalA = 0;
		
		totalA = sqlSession.selectOne("board.getTotalA");
	
		System.out.println("getTotal() : " + totalA);
		
		return totalA;
	}
	
	
	public int maxGroupNo() {
		
		int groupNo = 0;
		
		groupNo = sqlSession.selectOne("board.maxGroupNo");
		
		System.out.println("maxGroupNo() : " + groupNo);
		
		return groupNo;
	}
	
	public Long maxOrderNo(Long group_no) {
	
		Long orderNo = (long) 0;
		
		orderNo = sqlSession.selectOne("board.maxOrderNo", group_no);
		
		System.out.println("maxGroupNo() : " + orderNo);
	
		return orderNo;
	}
	
	
	public Long maxdepth(Long group_no) {
		List<BoardVo> list = 
			new ArrayList<BoardVo>();
	
		Long depth = (long) 0;
		
		
		depth = sqlSession.selectOne("board.maxdepth", group_no);
		
		System.out.println("depth() : " + depth);
		
		return depth;
	}
	
	
	
	public List<BoardVo> viewList(BoardVo vo) {
		
		System.out.println("류리스트 dao 번호값" + vo.getNo());
		
		List<BoardVo> list = 
				sqlSession.selectList("board.viewList", vo);
		
		return list;
	}
	
	
	
	public boolean update(BoardVo vo) {
		
		
		int count = sqlSession.update("board.update", vo);
		
		return count==1;
	}
	
	
	
	public boolean replyInsert( BoardVo vo ) {
		boolean result = false;
		
		int count = sqlSession.insert("board.replyInsert", vo);
		
		return result;
	}
	

	
}