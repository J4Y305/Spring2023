package com.spring.web;

import java.sql.Connection;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/**/root-context.xml" })
public class DataSourceTest {
	
	@Inject
	private DataSource ds;
	
	private static final Logger logger = LoggerFactory.getLogger(DataSourceTest.class);

	@Test
	public void testConnection() throws Exception {
		
		// 1) DataSource를 사용하여 오라클 데이터베이스 접속
		try (Connection conn = ds.getConnection()) {
			logger.info("JUNIT: " + conn);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
