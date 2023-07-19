package com.spring.persistence;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.spring.domain.UserVO;
import com.spring.dto.LoginDTO;

@Repository
public class UserDAOImpl implements UserDAO{

	@Inject
	private SqlSession sqlSession;
	private static final String namespace = "com.spring.mapper.UserMapper";
	
	@Override
	public UserVO login(LoginDTO dto) throws Exception{
		return sqlSession.selectOne(namespace + ".login", dto);
	}
	
}
