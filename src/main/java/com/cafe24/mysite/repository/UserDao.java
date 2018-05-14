package com.cafe24.mysite.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.StopWatch;

import com.cafe24.mysite.exception.UserDaoException;
import com.cafe24.mysite.vo.UserVo;


@Repository
public class UserDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	public UserVo get(Long no) {
		
		return sqlSession.selectOne("user.getByNo",no);	
	}
	
	
	public UserVo get(String email) {
		
		return sqlSession.selectOne("user.getByEmail",email);	
	}
	
	
	public UserVo get(UserVo vo) throws UserDaoException{
			
		UserVo result =
				sqlSession.selectOne("user.getByEmailAndPassword", vo);
	
		return result;
	}

	
	public boolean insert(UserVo vo) {
		
		
		int count = sqlSession.insert("user.insert",vo);

		return count == 1;
	}
	
	public boolean update(UserVo vo) {
		
		int count = sqlSession.update("user.update",vo);
		
		return count == 1;
	}
	
	
	
	
//	private Connection getConnection() throws SQLException {
//		Connection conn = null;
//		try {
//			//1. 드라이버 로딩
//			Class.forName( "com.mysql.jdbc.Driver" );
//			
//			//2. 연결하기
//			String url="jdbc:mysql://localhost/webdb";
//			conn = DriverManager.getConnection(url, "webdb", "webdb");
//		} catch( ClassNotFoundException e ) {
//			System.out.println( "드러이버 로딩 실패:" + e );
//		} 
//		
//		return conn;
//	}	
	
}
