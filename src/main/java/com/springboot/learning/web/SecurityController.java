package com.springboot.learning.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SecurityController {

	@RequestMapping("/")
	public String index() {
		return "index";
	}

	@RequestMapping("/security")
	public String security() {
		return "security";
	}

	@RequestMapping("login")
	public String login() {
		return "login";
	}
}
