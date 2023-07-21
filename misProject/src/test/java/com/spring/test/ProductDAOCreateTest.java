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
public class ProductDAOCreateTest {
	
	private static final Logger logger = LoggerFactory.getLogger(ProductDAOCreateTest.class);
	
	@Inject
	private ProductDAO dao;
	
	@Test
	public void testCreate() throws Exception{
		ProductVO vo = new ProductVO();
		vo.setPname("새로운 제목");
		vo.setPrice(1000);
		vo.setContent("새로운 글");
		vo.setWriter("테스터");
		logger.info("testCreate: " + vo);
		
		dao.create(vo);
	}

}
