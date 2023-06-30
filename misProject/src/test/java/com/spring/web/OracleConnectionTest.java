package com.spring.web;

import java.sql.Connection;
import java.sql.DriverManager;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OracleConnectionTest {
	// 오라클 접속 정보 설정
	private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:XE";
	private static final String USER = "spring2023";
	private static final String PW = "qwe123";

	private static final Logger logger = LoggerFactory.getLogger(OracleConnectionTest.class);

	@Test
	public void testConnection() throws Exception {
		// 1) JDBC 드리아버 로드
		Class.forName(DRIVER);

		// 2) 오라클 데이터베이스 접속
		try (Connection conn = DriverManager.getConnection(URL, USER, PW)) {
			logger.info("JUNIT: " + conn);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
