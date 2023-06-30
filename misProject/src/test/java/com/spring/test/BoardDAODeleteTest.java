package com.spring.test;

import java.util.List;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.spring.domain.BoardVO;
import com.spring.persistence.BoardDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/**/root-context.xml" })

public class BoardDAODeleteTest {

	private static final Logger logger = LoggerFactory.getLogger(BoardDAODeleteTest.class);
	
	@Inject
	private BoardDAO dao;
	
	@Test
	public void testDelete() throws Exception{
		
		// 게시글 삭제
		int bno_number = 2;
		dao.delete(bno_number);
		logger.info("DELETED!! " + dao.read(bno_number));
		
		// 전체 목록 확인
		List<BoardVO> list = dao.listAll();
		for(BoardVO vo : list) {
			logger.info("testDelete: " + vo);
		}
	}
	
	
}
