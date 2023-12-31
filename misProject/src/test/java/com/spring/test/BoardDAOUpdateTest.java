package com.spring.test;

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

public class BoardDAOUpdateTest {

	private static final Logger logger = LoggerFactory.getLogger(BoardDAOUpdateTest.class);
	
	@Inject
	private BoardDAO dao;
	
	@Test
	public void testUpdate() throws Exception{
		BoardVO vo = new BoardVO();
		vo.setBno(3);
		vo.setTitle("수정된 제목");
		vo.setContent("수정된 글");
		vo.setWriter("수정임");
		
		logger.info("testUpdate: " + vo);
		
		dao.update(vo);
		
	}

}
