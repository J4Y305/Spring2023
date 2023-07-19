package com.spring.service;


import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.spring.domain.BoardVO;
import com.spring.domain.Criteria;
import com.spring.domain.SearchCriteria;
import com.spring.persistence.BoardDAO;

@Service
public class BoardServiceImpl implements BoardService{
	
	@Inject
	private BoardDAO dao;
	
	@Override
	public void register(BoardVO vo) throws Exception{
		dao.create(vo);
	}
	@Override
	public BoardVO read(int bno) throws Exception{
		// 상세보기 요청 -> 조회수 1씩 증가(조회수 업데이트)
		dao.updateViewCount(bno);
		return dao.read(bno);
	}
	@Override
	public void modify(BoardVO vo) throws Exception{
		dao.update(vo);
	}
	@Override
	public void remove(int bno) throws Exception{
		dao.delete(bno);
	}
	@Override
	public List<BoardVO> listAll() throws Exception{
		return dao.listAll();
	}
	@Override
	public List<BoardVO> listCriteria(Criteria cri) throws Exception {
		return dao.listCriteria(cri);
	}
	@Override
	public int listCountCriteria(Criteria cri) throws Exception {
		return dao.listCountCriteria(cri);
	}
	@Override
	public List<BoardVO> listSearch(SearchCriteria cri) throws Exception {
		return dao.listSearchCriteria(cri);
	}
	@Override
	public int listSearchCount(SearchCriteria cri) throws Exception {
		return dao.listSearchCountCriteria(cri);
	}
		
}

