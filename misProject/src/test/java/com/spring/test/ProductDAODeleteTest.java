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
import com.spring.persistence.ProductDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/**/root-context.xml" })
public class ProductDAODeleteTest {
	
	private static final Logger logger = LoggerFactory.getLogger(ProductDAODeleteTest.class);
	
	@Inject
	private ProductDAO dao;
	
	@Test
	public void testDelete() throws Exception{
		
		// 게시글 삭제
		int pno_number = 2;
		dao.delete(pno_number);
		logger.info("DELETED!! " + dao.read(pno_number));
	}
}
