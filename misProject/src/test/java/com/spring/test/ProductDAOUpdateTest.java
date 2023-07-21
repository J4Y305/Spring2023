package com.spring.test;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


import com.spring.domain.ProductVO;

import com.spring.persistence.ProductDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/**/root-context.xml" })

public class ProductDAOUpdateTest {
	
	private static final Logger logger = LoggerFactory.getLogger(ProductDAOUpdateTest.class);
	
	@Inject
	private ProductDAO dao;
	
	@Test
	public void testUpdate() throws Exception{
		ProductVO vo = new ProductVO();
		vo.setPno(3);
		vo.setPname("수정된 제목");
		vo.setPrice(2000);
		vo.setContent("수정된 글");
		vo.setWriter("수정임");
		
		logger.info("testUpdate: " + vo);
		
		dao.update(vo);
		
	}

}
