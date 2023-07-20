package com.spring.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spring.domain.UserVO;
import com.spring.dto.LoginDTO;
import com.spring.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

	@Inject
	private UserService service;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public void loginGET(@ModelAttribute("dto") LoginDTO dto) throws Exception {

	}

	@RequestMapping(value = "/loginPost", method = RequestMethod.POST)
	public void loginPOST(LoginDTO dto, Model model) throws Exception {

		UserVO vo = service.login(dto);

		if (vo == null) {
			return;
		}

		model.addAttribute("userVO", vo);
	}

	@RequestMapping(value="/logout", method=RequestMethod.GET)
	public String logout(HttpSession session) throws Exception{
		
		Object userVO=session.getAttribute("login");
		
		if(userVO!=null) {
			session.removeAttribute("login");
			session.invalidate();
		}
		
		return "redirect:/";
	}

}
