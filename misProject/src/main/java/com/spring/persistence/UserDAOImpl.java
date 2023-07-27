package com.spring.persistence;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.spring.domain.SearchCriteria;
import com.spring.domain.UserVO;
import com.spring.dto.LoginDTO;

@Repository
public class UserDAOImpl implements UserDAO{

	@Inject
	private SqlSession session;
	private static final String namespace = "com.spring.mapper.UserMapper";
	
	@Override
	public UserVO login(LoginDTO dto) throws Exception{
		return session.selectOne(namespace + ".login", dto);
	}

	@Override
	public void create(UserVO vo) throws Exception {
		session.insert(namespace + ".create", vo);
		
	}

	@Override
	public UserVO read(String usid) throws Exception {
		return session.selectOne(namespace + ".read", usid);
	}

	@Override
	public void update(UserVO vo) throws Exception {
		session.update(namespace + ".update", vo);
		
	}

	@Override
	public void delete(String usid) throws Exception {
		session.delete(namespace + ".delete", usid);		
	}

	@Override
	public List<UserVO> listSearch(SearchCriteria cri) throws Exception {
		return session.selectList(namespace + ".listSearch", cri);
	}

	@Override
	public int listSearchCount(SearchCriteria cri) throws Exception {
		return session.selectOne(namespace + ".listSearchCount", cri);
	}
	
	
	
}
