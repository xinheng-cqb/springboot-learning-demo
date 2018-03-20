package com.springboot.learning.web;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
	@RequestMapping("/hello")
	public String hello(@RequestParam(value = "name", required = true, defaultValue = "admin") String name) {
		return "hello1 world , " + name;
	}

	@RequestMapping("/hello/{name}")
	public String helloTwo(@PathVariable(value = "name") String name) {
		return "hello2 world , " + name;
	}
}
