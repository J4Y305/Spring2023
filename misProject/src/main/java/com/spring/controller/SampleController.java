package com.spring.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.domain.ProductVO;

@Controller
public class SampleController {
	
	private static final Logger logger = LoggerFactory.getLogger(SampleController.class);
	
	@RequestMapping("doA")
	public void doA() {	// 리턴타입  => 사용자 화면을 선택. void => 맵핑주소와 동일한 jsp 리턴
		// WEB-INF/views/doA.jsp 실행
		
		logger.info("doA() called...");
	}
	
	@RequestMapping("doB")
	public String doB() {	// 리턴타입  => 사용자 화면을 선택. String => home.jsp 리턴
		// WEB-INF/views/home.jsp 실행
		
		logger.info("doB() called...");
		return "home";
	}
	
	@RequestMapping("doC")
	public String doC(Model model, @ModelAttribute("msg") String msg) {	// 리턴타입  => 사용자 화면을 선택. String => productDetail.jsp 리턴
		// WEB-INF/views/productDetail.jsp 실행
		
		logger.info("doC() called..." + msg);
		
		// 상품정보 담기 => 추후 DB 연동
		ProductVO vo = new ProductVO();
		vo.setName("한글상품");
		vo.setPrice(10000);
		
		// 화면으로 상품정보를 전달 => ${productVO.name}
		// model.addAttribute(vo);
		
		// 화면으로 상품정보를 전달 => ${pVO.name}
		model.addAttribute("pVO", vo);
		
		
		return "productDetail";
	}
	
	@RequestMapping("doD")
	public String doD(RedirectAttributes rttr) {	// 리턴타입  => 사용자 화면을 선택. String => redirect 명시
		// /doC 실행
		
		logger.info("doD() called...");
		rttr.addAttribute("msg", "page move...");
		
		return "redirect:/doC";	
	}
	
	@RequestMapping("doJSON")
	public @ResponseBody ProductVO doJSON() {	// 리턴타입  => 사용자 화면을 선택. @ResponseBody 클래스명 => JSON 형태
		
		logger.info("doJSON() called...");
		
		// 상품정보 담기 => 추후 DB 연동
		ProductVO vo = new ProductVO();
		vo.setName("한글상품");
		vo.setPrice(10000);
		
		return vo;
	}
	
}
