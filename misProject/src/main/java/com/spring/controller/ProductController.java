package com.spring.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.domain.BoardVO;
import com.spring.domain.Criteria;
import com.spring.domain.PageMaker;
import com.spring.domain.ProductVO;
import com.spring.domain.SearchCriteria;
import com.spring.domain.UserVO;
import com.spring.service.ProductService;

@Controller
@RequestMapping("/product/*")
public class ProductController {

	private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

	@Inject
	private ProductService service;

	// REGISTER
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public void registerGET() throws Exception {
		logger.info("register GET...");
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String registerPOST(ProductVO vo) throws Exception {
		logger.info("register POST...");

		// 게시글 등록
		service.register(vo);

		return "redirect:/product/list";
	}

	// REMOVE
	@RequestMapping(value = "/removePage", method = RequestMethod.POST)
	public String removePageGET(@RequestParam("pno") int pno, HttpSession session,
			@ModelAttribute("cri") SearchCriteria cri, RedirectAttributes rttr, Model model) throws Exception {
		logger.info("modifyPage GET.....");

		// 삭제하려면 로그인한 정보와 게시글의 작성자가 일치.

		// 1) 로그인 정보 가져오기
		UserVO user = (UserVO) session.getAttribute("login");

		// 2) 게시글의 작성자 id와 로그인 정보 id를 비교.
		// 2-1) 게시글 정보 가져오기
		ProductVO product = service.read(pno);

		// 2-2) 게시글 작성자와 id와 로그인 정보 id 비교.
		if (user.getUsid().equals(product.getWriter())) {
			// 작성자와 로그인 정보 같음 -> 게시글 삭제
			logger.info("remove POST.....");
			service.remove(pno);
			// 목록화면으로 이동.
			rttr.addFlashAttribute("msg", "SUCCESS");
			return "redirect:/product/list";
		} else {
			// 로그인 정보와 게시글 작성자가 일치하지 않는 경우 -> 상세페이지로 강제이동
			rttr.addAttribute("pno", pno);
			rttr.addAttribute("page", cri.getPage());
			rttr.addAttribute("perPageNum", cri.getPerPageNum());
			rttr.addAttribute("searchType", cri.getSearchType());
			rttr.addAttribute("keyword", cri.getKeyword());
			rttr.addFlashAttribute("msg", "잘못된 접근입니다.");

			return "redirect:/product/readPage";
		}
	}

	@RequestMapping(value = "/readPage", method = RequestMethod.GET)
	public void readPage(@RequestParam("pno") int pno, @ModelAttribute("cri") SearchCriteria cri, Model model)
			throws Exception {
		model.addAttribute(service.read(pno));
	}

	// 페이징 기능
	@RequestMapping(value = "list", method = RequestMethod.GET)
	public void listPage(@ModelAttribute("cri") SearchCriteria cri, Model model) throws Exception {
		logger.info("listPage GET.....");

		// 선택된 페이지의 게시글 정보로 10개 가져오기

		model.addAttribute("list", service.listSearch(cri));

		// 페이징 네비게이션 추가
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(service.listSearchCount(cri));

		// 페이징 정보 화면 전달
		model.addAttribute("pageMaker", pageMaker);
	}

	@RequestMapping(value = "/modifyPage", method = RequestMethod.GET)
	public String modifyPageGET(@RequestParam("pno") int pno, HttpSession session,
			@ModelAttribute("cri") SearchCriteria cri, RedirectAttributes rttr, Model model) throws Exception {
		logger.info("modifyPage GET.....");

		// 수정할 수 있으려면, 로그인한 정보와 글의 작성자의 정보가 동일할 때만 수정 page로 이동.

		// 1) 로그인 정보 가져오기
		UserVO user = (UserVO) session.getAttribute("login");

		// 2) 게시글의 작성자 id와 로그인 정보 id를 비교.
		// 2-1) 게시글 정보 가져오기
		ProductVO product = service.read(pno);

		// 2-2) 게시글 작성자와 id와 로그인 정보 id 비교.
		if (user.getUsid().equals(product.getWriter())) {
			// 작성자와 로그인 정보 같음.
			model.addAttribute(product);
			// 수정 페이지로 이동.
			return "/product/modifyPage";
		} else {
			// 로그인 정보와 게시글 작성자가 일치하지 않는 경우 -> 강제이동
			rttr.addAttribute("pno", pno);
			rttr.addAttribute("page", cri.getPage());
			rttr.addAttribute("perPageNum", cri.getPerPageNum());
			rttr.addAttribute("searchType", cri.getSearchType());
			rttr.addAttribute("keyword", cri.getKeyword());
			rttr.addFlashAttribute("msg", "잘못된 접근입니다.");

			return "redirect:/product/readPage";
		}
	}

	@RequestMapping(value = "/modifyPage", method = RequestMethod.POST)
	public String modifyPagePOST(ProductVO vo, @ModelAttribute("cri") SearchCriteria cri, RedirectAttributes rttr)
			throws Exception {

		logger.info("modifyPage POST.....");

		service.modify(vo);

		rttr.addAttribute("page", cri.getPage());
		rttr.addAttribute("perpage", cri.getPerPageNum());
		rttr.addAttribute("searchType", cri.getSearchType());
		rttr.addAttribute("keyword", cri.getKeyword());
		
		rttr.addFlashAttribute("msg", "SUCCESS");

		return "redirect:/product/list";
	}

}
