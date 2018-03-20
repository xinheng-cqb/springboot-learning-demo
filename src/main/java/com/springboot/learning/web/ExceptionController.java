package com.springboot.learning.web;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springboot.learning.exception.MyException;

@Controller
public class ExceptionController {

	@RequestMapping("/exception")
	public String exceptionTest() throws Exception {
		String message = "test";
		return message.substring(8);
	}

	@RequestMapping("/jsonexception")
	public String exceptionJosnTest() throws MyException {
		try {
			String msg = "";
			while (msg.equals("")) {
				msg = null;
			}
		} catch (Exception e) {
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			throw new MyException(sw.toString());
		}
		throw new MyException("other error");
	}
}
