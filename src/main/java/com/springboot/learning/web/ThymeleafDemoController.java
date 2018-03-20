package com.springboot.learning.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ThymeleafDemoController {

	@RequestMapping("/thymeleaf")
	public String thymeleafDemoIndex(ModelMap modelMap) {
		modelMap.put("testKey", "ThymeleafDemo Test");
		return "ThymeleafDemoIndex";
	}
}
