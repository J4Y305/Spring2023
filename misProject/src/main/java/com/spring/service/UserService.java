package com.spring.service;

import java.util.List;

import com.spring.domain.SearchCriteria;
import com.spring.domain.UserVO;
import com.spring.dto.LoginDTO;

public interface UserService {
	public UserVO login(LoginDTO dto) throws Exception;

	public void create(UserVO vo) throws Exception;

	public UserVO read(String usid) throws Exception;

	public void update(UserVO vo) throws Exception;

	public void delete(String usid) throws Exception;

	// 페이징, 검색할 수 있는 list
	public List<UserVO> listSearch(SearchCriteria cri) throws Exception;

	// 페이징, 검색하기 위한 게시물 수 반환
	public int listSearchCount(SearchCriteria cri) throws Exception;
}
